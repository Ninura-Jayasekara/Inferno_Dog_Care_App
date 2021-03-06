package com.example.inferno_dog_care_app;

//IT20175498
//Main menu

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu  extends AppCompatActivity implements View.OnClickListener {

    //Views
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
            adopt = (ImageView)findViewById(R.id.adopt);
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

        //On Click method
        @Override
        public void onClick(View v) {
            Intent i;

            switch (v.getId()){
                case R.id.bmi:i=new Intent(this,BmiCalculator.class);startActivity(i);break;
                case R.id.channelvet:i=new Intent(this,ChannelVet.class);startActivity(i);break;
                case R.id.sellmain:i=new Intent(this,SellMain.class);startActivity(i);break;
                case R.id.buymain:i=new Intent(this, BuyPet.class);startActivity(i);break;
                case R.id.walkdog:i=new Intent(this,DogWalkMonitor.class);startActivity(i);break;
                case R.id.foodcal:i=new Intent(this,FoodCalculator.class);startActivity(i);break;
                case R.id.adopt:i=new Intent(this, AdoptDog.class);startActivity(i);break;
                case R.id.login:i=new Intent(this,Login.class);startActivity(i);break;
                case R.id.profile:i=new Intent(this,VetIncome.class);startActivity(i);break;

                default:break;
            }

        }
    }


