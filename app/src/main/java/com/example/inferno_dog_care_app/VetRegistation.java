package com.example.inferno_dog_care_app;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VetRegistation extends AppCompatActivity {

//views

    EditText F_name, Gender, Address, Phone, Email, Password;
    Button Vet_next;
    String f_name, gender, address, phone, email, password;

    //progress dialog

    ProgressDialog pd;

    //firestore instance

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_registration);



        //Initialize views with its xml

        F_name = findViewById(R.id.name);
        Gender = findViewById(R.id.gender);
        Address = findViewById(R.id.address);
        Phone = findViewById(R.id.phone);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Vet_next = findViewById(R.id.vet_next);

        //Progress Dialog

        pd = new ProgressDialog(this);

        //firestore

        db = FirebaseFirestore.getInstance();

        //click button to upload data

        Vet_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //input data
                f_name = F_name.getText().toString().trim();
                gender = Gender.getText().toString().trim();
                address = Address.getText().toString().trim();
                phone = Phone.getText().toString().trim();
                email = Email.getText().toString().trim();
                password = Password.getText().toString().trim();

                openProfessional();


            }
        });

    }


    private void openProfessional() {
        Intent intent = new Intent(this, VetProfessionalDetails.class);
        intent.putExtra("F_name",f_name);
        intent.putExtra("Gender",gender);
        intent.putExtra("Address",address);
        intent.putExtra("Phone",phone);
        intent.putExtra("Email",email);
        intent.putExtra("Password",password);
        startActivity(intent);
    }
}
