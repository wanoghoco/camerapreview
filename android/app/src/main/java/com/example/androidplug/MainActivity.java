package com.example.androidplug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.view.PreviewView;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;

public class MainActivity extends FlutterActivity {

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
       View  CameraView=this.getLayoutInflater().inflate(R.layout.cameraview,null);
      PreviewView previewView=CameraView.findViewById(R.id.previewview);
        flutterEngine
                .getPlatformViewsController()
                .getRegistry()
                .registerViewFactory("native", new NativeViewFactory(CameraView,previewView));
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1&&grantResults.length>0){
            for(int x=0; x<grantResults.length; x++){
                if(grantResults[x]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"Permission Not Yet Granted", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            Toast.makeText(getApplicationContext(),"Permission Granted", Toast.LENGTH_LONG).show();
            //InitializeView();
        }
    }
}


