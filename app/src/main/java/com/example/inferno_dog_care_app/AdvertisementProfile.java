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

public class AdvertisementProfile extends AppCompatActivity {
    EditText mOwnerName, mPhone ,mBreed, mAddress, mNoOfPuppies, mBirthday, mPrice;
    TextView headingBreed;
    String id;
    private ImageView home,sell,buy,appointment,profile;

    //progress dialog
    ProgressDialog pd;
    //firestore instance
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_profile);

        //Progress Dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //Initialize views with its xml
        mOwnerName = findViewById(R.id.adv_name);
        mPhone = findViewById(R.id.adv_phone);
        mAddress = findViewById(R.id.adv_address);
        mBreed = findViewById(R.id.adv_breed);
        mNoOfPuppies= findViewById(R.id.adv_count);
        mBirthday = findViewById(R.id.adv_birthday);
        mPrice = findViewById(R.id.adv_price);
        headingBreed = findViewById(R.id.buyprof_txt1);

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

        String id = getIntent().getStringExtra("ID");
        String name = getIntent().getStringExtra("Owner_Name");
        String phone = getIntent().getStringExtra("Phone_Number");
        String address =getIntent().getStringExtra("Address");
        String breed=getIntent().getStringExtra("Breed");
        String count=getIntent().getStringExtra("No_Of_Puppies");
        String birthday=getIntent().getStringExtra("Birthday");
        String price=getIntent().getStringExtra("Price");
        headingBreed.setText(breed);
        mOwnerName.setText(name);
        mPhone.setText(phone);
        mAddress.setText(address);
        mBreed.setText(breed);
        mNoOfPuppies.setText(count);
        mBirthday.setText(birthday);
        mPrice.setText(price);

    }

    private void openVetlist() {
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
    }

}
