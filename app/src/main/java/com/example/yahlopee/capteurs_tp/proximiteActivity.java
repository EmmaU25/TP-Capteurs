package com.example.yahlopee.capteurs_tp;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class proximiteActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor proximity;
    private TextView texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximite);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        texte = (TextView) findViewById(R.id.test);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null){
            texte.setText("Votre appareil a le capteur proximité");
        }else{
            texte.setText("Votre appareil n'a pas de capteur proximite");
        }
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, proximity);
        super.onPause();
    }

    @Override
    protected void onResume() {
        boolean proximite;
        super.onResume();
        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_UI);
        //proximite = sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float p = 0;
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            p = event.values[0];
        }

        if(p<=0){
            texte.setText("Vous etes tres proche du sensor");
        }else{
            texte.setText("Vous etes tres loin du sensor");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
