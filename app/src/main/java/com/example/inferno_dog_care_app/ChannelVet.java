package com.example.inferno_dog_care_app;

//IT20175498
//Choose to make a new appointment or view appointments

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChannelVet extends AppCompatActivity implements View.OnClickListener {

    //Image Views
    private ImageView channel_choose_make, channel_choose_show;
    private ImageView home,sell,buy,appointment,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channelvet_choose);

        //defining views
        channel_choose_make = (ImageView)findViewById(R.id.channel_choose_make) ;
        channel_choose_show = (ImageView)findViewById(R.id.add_sell_img) ;

        home = findViewById(R.id.home_icon);
        sell = findViewById(R.id.sell_icon);
        buy = findViewById(R.id.buy_icon);
        appointment = findViewById(R.id.appo_icon);
        profile = findViewById(R.id.profile_icon);

        //add click listners to views

        channel_choose_make.setOnClickListener(this);
        channel_choose_show.setOnClickListener(this);

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

    //select the icon what user want

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.channel_choose_make:i=new Intent(this,VetDetails.class);startActivity(i);break;
            case R.id.add_sell_img:i=new Intent(this,AppointmentHold.class);startActivity(i);break;
            default:break;
        }
    }

    private void openVetlist() {
        Intent intent = new Intent(this,AppointmentHold.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }

    private void openAdvertisement() {
        Intent intent = new Intent(this,SellPet.class);
        startActivity(intent);
    }

    public void openAdvertisements() {
        Intent intent = new Intent(this,BuyPet.class);
        startActivity(intent);
    }
}
