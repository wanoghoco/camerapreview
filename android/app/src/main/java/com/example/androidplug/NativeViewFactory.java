package com.example.androidplug;

import android.content.Context;
import android.os.Build;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.camera.view.PreviewView;

import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.Map;

class NativeViewFactory extends PlatformViewFactory {
    View CameraView;
    PreviewView previewView;

    NativeViewFactory(View CameraWidget,PreviewView previewView) {
        super(StandardMessageCodec.INSTANCE);
        this.previewView=previewView;
        this.CameraView=CameraWidget;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public PlatformView create(@NonNull Context context, int id, @Nullable Object args) {
        final Map<String, Object> creationParams = (Map<String, Object>) args;
        return new NativeView(context,CameraView,previewView, id, creationParams);
    }
}