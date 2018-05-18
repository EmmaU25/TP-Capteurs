package com.example.yahlopee.capteurs_tp;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class ListeActivity extends AppCompatActivity {
    SensorManager sensorManager;
    private TextView list,tit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        list = (TextView) findViewById(R.id.list);
        tit = (TextView) findViewById(R.id.title);
        listSensor();
    }

    private void listSensor() {
        List <Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuffer listeS = new StringBuffer();

        for (Sensor sensor: sensors){
            listeS.append("\n Nouveau capteur trouvé: \r\n");
            listeS.append("\tNom: "+sensor.getName()+"\r\n");
            listeS.append("\tLe type: "+getType(sensor.getType())+"\r\n");
            listeS.append("\tLa version: " + sensor.getVersion() + ".0\r\n");
            listeS.append("\tEst-il disponible?: "+ getAvaible(sensor.getType())+"\r\n");
            listeS.append("---------------------------------------------------------------------------------------------");
        }

        list.setText(listeS.toString());

    }
    public String getAvaible(int i){
        String band = "";
        if (sensorManager.getDefaultSensor(i) != null){
            band="OUI";
        }else{
            band="NON";
        }
        return band;
    }

    public String getType(int i){
        String strType;
        switch (i){
            case Sensor.TYPE_ACCELEROMETER: strType = "TYPE_ACCELEROMETER";break;
            case Sensor.TYPE_GRAVITY:strType = "TYPE_GRAVITY";break;
            case Sensor.TYPE_GYROSCOPE:    strType = "TYPE_GYROSCOPE";    break;
            case Sensor.TYPE_LIGHT:strType = "TYPE_LIGHT";break;
            case Sensor.TYPE_LINEAR_ACCELERATION:strType = "TYPE_LINEAR_ACCELERATION";break;
            case Sensor.TYPE_MAGNETIC_FIELD:strType = "TYPE_MAGNETIC_FIELD";break;
            case Sensor.TYPE_ORIENTATION:strType = "TYPE_ORIENTATION";break;
            case Sensor.TYPE_PRESSURE:strType = "TYPE_PRESSURE";break;
            case Sensor.TYPE_PROXIMITY:    strType = "TYPE_PROXIMITY";    break;
            case Sensor.TYPE_ROTATION_VECTOR:    strType = "TYPE_ROTATION_VECTOR";break;
            case Sensor.TYPE_TEMPERATURE:strType = "TYPE_TEMPERATURE";break;
            default: strType = "TYPE_UNKNOW";break;
        }
        return strType;

    }
}
