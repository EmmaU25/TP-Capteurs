package com.example.yahlopee.capteurs_tp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class accelerometreActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometre;
    private LinearLayout backgr;
    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometre);
        backgr = (LinearLayout) findViewById(R.id.backgr);
        label = (TextView) findViewById(R.id.values);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometre = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    @Override
    protected void onPause() {
        // unregister the sensor (désenregistrer le capteur)
        sensorManager.unregisterListener(this, accelerometre);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, accelerometre, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = 0;
        float y= 0;
        float z = 0;
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];
        }

        if(z<0 && z<2 ){
            backgr.setBackgroundColor(getResources().getColor(R.color.inferieur));
        }else if(z>1 && z<5){
            backgr.setBackgroundColor(getResources().getColor(R.color.superieur));
        }else if(z>4 ){
            backgr.setBackgroundColor(getResources().getColor(R.color.moyennes));
        }

        label.setText("x: "+x+"\ny: "+y+"\nz: "+z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
