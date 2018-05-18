package com.example.yahlopee.capteurs_tp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class directionActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magnetic, accelometre;
    private TextView test,vals;
    private float [] accelometerVector = new float[3];
    private float [] magneticVector = new float[3];
    private float [] resultMatrix = new float[9];
    private float [] values = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        test = (TextView) findViewById(R.id.test);
        vals = (TextView) findViewById(R.id.vals);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        magnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelometre  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null || sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null ){
            test.setText("Votre appareil a les capteur neccesaires");
        }else{
            test.setText("Votre appareil n'a pas de capteur neccessaires");
        }
    }

    @Override
    protected void onPause() {
        // unregister the sensor (désenregistrer le capteur)
        sensorManager.unregisterListener(this, magnetic);
        sensorManager.unregisterListener(this, accelometre);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(this, magnetic, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, accelometre, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelometerVector =sensorEvent.values;
        }else if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magneticVector = sensorEvent.values;
        }

        sensorManager.getRotationMatrix(resultMatrix,null, accelometerVector, magneticVector);

        sensorManager.getOrientation(resultMatrix, values);


        float x =(float) Math.toDegrees(values[0]);
        // le pitch
        float y = (float) Math.toDegrees(values[1]);
        // le roll
        float z = (float) Math.toDegrees(values[2]);

        if(y<1 && x>50 && x<140){
            test.setText("L'appareil penche en haut");
        }else if (y>1 && x>150){
            test.setText("L'appareil penche en bas");
        }else if(z>1 && y<0 && x>0){
            test.setText("L'appareil penche a droite");
        }else if(z<1 && y<0 && x>150 && x>0){
            test.setText("L'appareil penche a gauche");
        }else if (x<-100 && y<0 && z<0){
            test.setText("L'appareil ne penche pas");
        }



        vals.setText("X: "+x+"\nY: "+y+"\nZ: "+z);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
