package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChannelVet extends AppCompatActivity implements View.OnClickListener {

    private ImageView channel_choose_make, channel_choose_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channelvet_choose);

        //defining views
        channel_choose_make = (ImageView)findViewById(R.id.channel_choose_make) ;
        channel_choose_show = (ImageView)findViewById(R.id.add_sell_img) ;

        //add click listners to views

        channel_choose_make.setOnClickListener(this);
        channel_choose_show.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.channel_choose_make:i=new Intent(this,VetDetails.class);startActivity(i);break;
            case R.id.add_sell_img:i=new Intent(this,AppointmentDetails.class);startActivity(i);break;
            default:break;
        }
    }
}
