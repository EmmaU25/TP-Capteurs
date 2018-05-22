package com.example.yahlopee.capteurs_tp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class directionActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelometre;
    private TextView test,vals;
    private long last;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        test = (TextView) findViewById(R.id.test);
        vals = (TextView) findViewById(R.id.vals);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        last = System.currentTimeMillis();
        accelometre  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null){
            test.setText("Votre appareil n'a pas de capteur neccessaires");
        }
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, accelometre);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, accelometre, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        long timeC =  System.currentTimeMillis();
        if((timeC - last)> 100){
            Long timeD = (timeC - last);
            last = timeC;
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            if(y >= -9 && y<=-1){
                test.setText("L'appareil penche vers bas");
            }else if(y >= 2 && y<=9){
                test.setText("L'appareil penche vers haut");
            }else if(x>= -9 && x<= -1){
                test.setText("L'apparel penche a droite");
            }else if(x>=1 && x<=9){
                test.setText("L'appareil penche a gauche");
            }else{
                test.setText("");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void stop(){
        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK)){
            stop();
        }
        return super.onKeyDown(keyCode, event);
    }
}
