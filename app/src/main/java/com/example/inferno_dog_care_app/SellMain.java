package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SellMain extends AppCompatActivity implements View.OnClickListener {

    private ImageView sell_pet, view_sell_pet;
    private ImageView home,sell,buy,appointment,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sellpet);

        //defining views
        sell_pet = (ImageView)findViewById(R.id.sell_pet) ;
        view_sell_pet = (ImageView)findViewById(R.id.view_sell_pet) ;

        //add click listners to views

        sell_pet.setOnClickListener(this);
        view_sell_pet.setOnClickListener(this);

        home = findViewById(R.id.homeicon);
        sell = findViewById(R.id.sellicon);
        buy = findViewById(R.id.buyicon);
        appointment = findViewById(R.id.appoinmenticon);
        profile = findViewById(R.id.profileicon);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdvertisement();
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdvertisements();
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVetlist();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void openVetlist() {
        Intent intent = new Intent(this,VetDetails.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }

    public void openAdvertisements() {
        Intent intent = new Intent(this,BuyPet.class);
        startActivity(intent);
    }

    private void openAdvertisement() {
        Intent intent = new Intent(this,SellPet.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.sell_pet:i=new Intent(this,SellPet.class);startActivity(i);break;
            case R.id.view_sell_pet:i=new Intent(this,ViewSellAdvertisement.class);startActivity(i);break;

            default:break;
        }

    }
}
