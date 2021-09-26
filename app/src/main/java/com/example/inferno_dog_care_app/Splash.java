package com.example.inferno_dog_care_app;

//IT20175498
//Splash Screen (On Start)

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //after splash menu redirect to main menu
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent i = new Intent(Splash.this, MainMenu.class);

                startActivity(i);

                finish();
            }

            //set delay

        },3000);
    }
}

