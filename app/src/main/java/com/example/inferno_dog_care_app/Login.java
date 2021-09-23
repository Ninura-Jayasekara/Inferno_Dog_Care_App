package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Login  extends AppCompatActivity implements View.OnClickListener {

    private ImageView loginVet, loginUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //defining views
        loginVet = (ImageView)findViewById(R.id.img_vet) ;
        loginUser = (ImageView)findViewById(R.id.img_user) ;

        //add click listners to views

        loginVet.setOnClickListener(this);
        loginUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
            case R.id.img_vet:i=new Intent(this,VetLogin.class);startActivity(i);break;
            case R.id.img_user:i=new Intent(this,UserLogin.class);startActivity(i);break;
            default:break;
        }
    }
}
