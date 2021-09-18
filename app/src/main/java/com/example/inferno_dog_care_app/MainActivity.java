package com.example.inferno_dog_care_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_calculator);
        setContentView(R.layout.main_menu);
        setContentView(R.layout.appointment_details);
        setContentView(R.layout.channelvet_choose);
        setContentView(R.layout.view_sellpet);
        setContentView(R.layout.buypet);
        setContentView(R.layout.main_sellpet);
        setContentView(R.layout.buy_profile);
        setContentView(R.layout.dog_walk);
    }
}