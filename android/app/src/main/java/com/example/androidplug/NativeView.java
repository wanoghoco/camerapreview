package com.example.androidplug;


import static androidx.core.app.ActivityCompat.requestPermissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.camera.core.CameraProvider;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import io.flutter.plugin.platform.PlatformView;
import java.util.Map;
import java.util.concurrent.ExecutionException;

class NativeView extends ActivityCompat implements PlatformView {

   private ListenableFuture<ProcessCameraProvider>cameraProviderListenableFuture;
    private PreviewView previewView;
    private View cameraView;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    NativeView(@NonNull Context context, View cameraView, PreviewView previewView, int id, @Nullable Map<String, Object> creationParams) {
        this.context=context;
        checkPermissions();
        this.previewView=previewView;
        this.cameraView=cameraView;

    }

   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
   void InitializeView(){
    cameraProviderListenableFuture=ProcessCameraProvider.getInstance(context);
    cameraProviderListenableFuture.addListener(()->{
            try{
                ProcessCameraProvider cameraProvider= cameraProviderListenableFuture.get();
                startCameraX(cameraProvider);
       }
            catch(ExecutionException ex){

            }
            catch(InterruptedException ex){

            }
    },getMainExecutor(context));
   }

   @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
   private  void  startCameraX(ProcessCameraProvider cameraProvider){
   cameraProvider.unbindAll();
       CameraSelector cameraSelector=new CameraSelector.Builder()
               .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
               .build();
       Preview preview=new Preview.Builder().build();
       preview.setSurfaceProvider(previewView.getSurfaceProvider());
       cameraProvider.bindToLifecycle((LifecycleOwner) context,cameraSelector,preview);
   }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void checkPermissions(){
      if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED){
          ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CAMERA},1);
        }
        else{
            InitializeView();
        }
    }

    @NonNull
    @Override
    public View getView() {
        return cameraView;
    }

    @Override
    public void dispose() {}
}