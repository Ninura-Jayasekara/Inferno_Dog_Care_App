package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AppointmentConfirmation extends AppCompatActivity {


    AppointmentModel Appointment = new AppointmentModel();
    String AppointmentID,Doctor,Cus,Date,Phone;
    private ImageView home,sell,buy,appointment,profile;
    TextView docName, cDate, cName, cPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_confirmation);

        //getExtra
        AppointmentID = getIntent().getStringExtra("AppID");
        Doctor = "Dr. "+getIntent().getStringExtra("Doctor");
        Cus = getIntent().getStringExtra("Customer");
        Date = getIntent().getStringExtra("Date");
        Phone = getIntent().getStringExtra("Contact");

        //Initialize views with its xml

        docName = findViewById(R.id.txt_doctorname2);
        cDate = findViewById(R.id.txt_ownername4);
        cName = findViewById(R.id.txt_ownername2);
        cPhone = findViewById(R.id.txt_ownername3);

        home = findViewById(R.id.home_icon);
        sell = findViewById(R.id.sell_icon);
        buy = findViewById(R.id.buy_icon);
        appointment = findViewById(R.id.appo_icon);
        profile = findViewById(R.id.profile_icon);

        //set variables
        docName.setText(Doctor);
        cDate.setText(Date);
        cName.setText(Cus);
        cPhone.setText(Phone);

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
