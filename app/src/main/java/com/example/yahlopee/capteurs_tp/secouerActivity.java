package com.example.yahlopee.capteurs_tp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class secouerActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mngr;
    private Boolean isOn = false;
    private long lts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secouer);
        mngr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mngr.registerListener(this, mngr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), mngr.SENSOR_DELAY_NORMAL);
        lts = System.currentTimeMillis();
    }

    @TargetApi(VERSION_CODES.M)
    private void flashOff(){
        CameraManager cm = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            String [] id = cm.getCameraIdList();
            String cameraId = id[0];
            cm.setTorchMode(cameraId,false);
            isOn = false;
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @TargetApi(VERSION_CODES.M)
    private void flashOn(){
        CameraManager cm = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            String [] id = cm.getCameraIdList();
            String cameraId = id[0];
            cm.setTorchMode(cameraId,true);
            isOn = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void stop(){
        mngr.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];

        float acceleration = (x*x + y*y+ z*z)/(mngr.GRAVITY_EARTH * mngr.GRAVITY_EARTH);
        if(acceleration > 4){
            if(isOn){
                flashOff();
            }else{
                flashOn();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK)){
            stop();

        }
        return super.onKeyDown(keyCode,event);
    }


}
