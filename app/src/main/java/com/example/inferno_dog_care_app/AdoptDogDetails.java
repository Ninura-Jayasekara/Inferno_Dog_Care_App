package com.example.inferno_dog_care_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class AdoptDogDetails extends AppCompatActivity {
    TextView dogName, dogAge,dogColor, dogContactNo, dogGender, dogWeight;
    String ID;
    private ImageView homeicon,sellicon,buyicon,appointmenticon,profileicon;

    //progress dialog
    ProgressDialog pd;

    //firestore instance
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adopt_dogdetails);

        //Progress Dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //Initialize views with its xml
        dogName = findViewById(R.id.txt_adoptdetails_name);
        dogAge = findViewById(R.id.txt_adoptdetails6);
        dogColor = findViewById(R.id.txt_adoptdetails8);
        dogContactNo = findViewById(R.id.txt_adoptdetails10);
        dogGender= findViewById(R.id.txt_adoptdetails7);
        dogWeight = findViewById(R.id.txt_adoptdetails9);

      /*  homeicon = findViewById(R.id.homeicon);
        sellicon = findViewById(R.id.sellicon);
        buyicon = findViewById(R.id.buyicon);
        appointmenticon = findViewById(R.id.appoinmenticon);
        profileicon = findViewById(R.id.profileicon);

        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
            }
        });

        sellicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdvertisement();
            }
        });

        buyicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdvertisements();
            }
        });

        appointmenticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVetlist();
            }
        });

        profileicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        String color =getIntent().getStringExtra("color");
        String contactNo=getIntent().getStringExtra("contact_no");
        String gender=getIntent().getStringExtra("gender");
        String weight=getIntent().getStringExtra("weight");
        dogName.setText(name);
        dogAge.setText(age);
        dogColor.setText(color);
        dogContactNo.setText(contactNo);
        dogGender.setText(gender);
        dogWeight.setText(weight);

    }

  /*  private void openVetlist() {
        Intent intent = new Intent(this,VetDetails.class);
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
    }*/

}