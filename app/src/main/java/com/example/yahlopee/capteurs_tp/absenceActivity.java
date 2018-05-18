package com.example.yahlopee.capteurs_tp;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class absenceActivity extends AppCompatActivity {
    SensorManager sensorManager;
    List<Integer> myList = new ArrayList<Integer>();
    private TextView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);
        list = (TextView) findViewById(R.id.list);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        for (int i=1; i<=35;i++){
            Avaible(i);
        }
        listSensorNotAvaible();

    }


    private void listSensorNotAvaible() {
        StringBuffer listeS = new StringBuffer();
        for (Integer il: myList){
            listeS.append("\n Nouveau capteur: \r\n");
            listeS.append("\tNom: "+getType(il)+"\r\n");
            listeS.append("---------------------------------------------------");

            //Toast.makeText(this, ""+il, Toast.LENGTH_SHORT).show();
        }
        

        list.setText(listeS.toString());

    }

    public void Avaible(int i){
        if (sensorManager.getDefaultSensor(i) != null){

        }else{
            myList.add(i);
        }

    }

    public String getType(int i){
        String strType;
        switch (i){
            case 1:strType = "TYPE_ACCELEROMETER";break;
            case 2:strType ="TYPE_MAGNETIC";break;
            case 3:strType ="TYPE_ORENTATION";break;
            case 4:strType = "TYPE_GYROSCOPE";    break;
            case 5:strType ="TYPE_LIGTH";break;
            case 6:strType = "TYPE_PRESSUER";break;
            case 7:strType = "TYPE_TEMPERATURE";break;
            case 8:strType = "TYPE_PROXIMITY";break;
            case 9:strType = "TYPE_GRAVITY";break;
            case 10:strType = "TYPE_LINEAR-CALIBRATION";    break;
            case 11:strType = "TYPE_ROTATION_VECTOR";break;
            case 12:strType = "TYPE_RELATIVE-HUMIDITE";break;
            case 13:strType = "TYPE_TEMPERATURE";break;
            case 14:strType = "TYPE_MAGNETIC";break;
            case 15:strType = "TYPE_GAME-ROTATION";break;
            case 16:strType = "TYPE_GYROSCOPE-UNCALIBRATE";break;
            case 17:strType = "TYPE_SIGNIFICANT-MOTION";break;
            case 18:strType = "TYPE_STEP-DETECTOR";break;
            case 19:strType = "TYPE_STEP-COUNTER";break;
            case 20:strType = "TYPE_GEOMACNETIC";break;
            case 21:strType = "TYPE_HEART-RATE";break;
            case 28:strType = "TYPE_POSE-6DOT";break;
            case 29:strType = "TYPE_STATIONARY-DETECT";break;
            case 30:strType = "TYPE_MOTION-DETECT";break;
            case 34:strType = "TYPE_LOW-LATENCY";break;
            case 35:strType = "TYPE_ACCELEROMETRE-UNCALIBRATE";break;
            default: strType = "TYPE_UNKNOW";break;
        }
        return strType;

    }
}
