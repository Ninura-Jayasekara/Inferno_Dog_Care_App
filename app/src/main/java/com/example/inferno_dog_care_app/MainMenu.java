package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu  extends AppCompatActivity implements View.OnClickListener {

    private ImageView login, profile, channelvet, walkdog, bmi, foodcal, adopt, buymain, sellmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);



            //defining views
            login = (ImageView)findViewById(R.id.login) ;
            profile = (ImageView)findViewById(R.id.profile) ;
            channelvet = (ImageView)findViewById(R.id.channelvet) ;
            walkdog = (ImageView)findViewById(R.id.walkdog) ;
            bmi = (ImageView)findViewById(R.id.bmi) ;
            foodcal = (ImageView)findViewById(R.id.foodcal) ;
            adopt = (ImageView)findViewById(R.id.foodcal);
            buymain = (ImageView)findViewById(R.id.buymain) ;
            sellmain = (ImageView)findViewById(R.id.sellmain) ;


            //add click listners to views

            login.setOnClickListener(this);
            profile.setOnClickListener(this);
            channelvet.setOnClickListener(this);
            walkdog.setOnClickListener(this);
            bmi.setOnClickListener(this);
            foodcal.setOnClickListener(this);
            adopt.setOnClickListener(this);
            buymain.setOnClickListener(this);
            sellmain.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Intent i;

            switch (v.getId()){
                case R.id.bmi:i=new Intent(this,BmiCalculator.class);startActivity(i);break;
                case R.id.channelvet:i=new Intent(this,ChannelVet.class);startActivity(i);break;

                default:break;
            }

        }
    }


