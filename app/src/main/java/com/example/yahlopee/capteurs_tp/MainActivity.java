package com.example.yahlopee.capteurs_tp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button liste, detection, accelerometre, direction,secouer,proximite;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Actions pour les boutons
    public void liste(View view){
        intent = new Intent(MainActivity.this,ListeActivity.class);
        startActivity(intent);
        Toast.makeText(this, "On vas regarder la liste de capteurs", Toast.LENGTH_SHORT).show();
    }

    public void presence(View view){
        intent = new Intent(MainActivity.this,absenceActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Hello presence", Toast.LENGTH_SHORT).show();
    }

    public void accelerometre(View view){
        intent = new Intent(MainActivity.this,accelerometreActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Hello accelerometre", Toast.LENGTH_SHORT).show();
    }
    public void direction(View view){
        intent = new Intent(MainActivity.this, directionActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Hello direction", Toast.LENGTH_SHORT).show();
    }
    public void secouer(View view){
        intent = new Intent(MainActivity.this,secouerActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Hello secouer", Toast.LENGTH_SHORT).show();
    }
    public void proximite(View view){
        intent = new Intent(MainActivity.this,proximiteActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Hello proximite", Toast.LENGTH_SHORT).show();
    }


}
