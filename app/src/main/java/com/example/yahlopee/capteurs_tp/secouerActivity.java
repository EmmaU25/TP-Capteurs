package com.example.yahlopee.capteurs_tp;

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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class secouerActivity extends Activity{
    private SensorManager sensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    private boolean flashOn = false;
    private Camera objCamera = null;
    private TextView text;
    int cont =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secouer);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(mSensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        text = (TextView) findViewById(R.id.te);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            text.setText("Votre appareil a le capteur neccesaire");
        }else{
            text.setText("Votre appareil n'a pas de capteur neccesaire");
        }

    }

    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;

            if (mAccel > 12) {
                CameraA();

            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    protected void onPause() {
        sensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(mSensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void CameraA(){
        text.setText("Hello"+(cont=cont+1));
    }

}
