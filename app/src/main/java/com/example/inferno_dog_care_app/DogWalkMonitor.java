package com.example.inferno_dog_care_app;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class DogWalkMonitor extends AppCompatActivity {
    private Chronometer chronometer;
    private EditText weight, calories;
    private Button caloriesBtn;
    private long pauseOfSet;
    private  boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_walk);

        weight = findViewById(R.id.walk_dogedit1);
        calories = findViewById(R.id.walk_dogedit3);
        caloriesBtn = findViewById(R.id.walk_dogbtn4);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        caloriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(weight.getText().toString().trim())){
                    calculateBurnedCalories();
                }else{
                    Toast.makeText(getApplicationContext(),"Enter weight",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void calculateBurnedCalories() {
        float dogWeight = Float.parseFloat(weight.getText().toString().trim());
        float time = pauseOfSet;
        float minutes = (time/60000);
        float burnedCalories = (float) (((3.0*3.5*dogWeight)/200)*minutes);
        DecimalFormat df = new DecimalFormat("0.00");
        calories.setText(""+df.format(burnedCalories));
    }

    public void startChronometer(View view){
        if(!running){
            chronometer.setBase(SystemClock.elapsedRealtime() -pauseOfSet);
            chronometer.start();
            running=true;
        }
    }

    public void pauseChronometer(View view){
        if(running){
            chronometer.stop();
            pauseOfSet = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void resetChronometer(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOfSet = 0;
        if(running){
            chronometer.stop();
            running= false;
        }
    }
}
