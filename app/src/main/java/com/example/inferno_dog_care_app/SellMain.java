package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SellMain extends AppCompatActivity implements View.OnClickListener {

    private ImageView sell_pet, view_sell_pet;

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
