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

public class VetProfessionalDetails extends AppCompatActivity {

//views

    EditText Reg_no, Reg_year, Specialization, Description;
    Button Vet_submit;

   String fullName;
    String gender;
    String address;
    String phone;
    String email;
    String password;

    //progress dialog

    ProgressDialog pd;

    //firestore instance

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_prof_info);



        //Initialize views with its xml

        Reg_no = findViewById(R.id.reg_no);
        Reg_year = findViewById(R.id.reg_year);
        Specialization = findViewById(R.id.specification);
        Description = findViewById(R.id.description);
        Vet_submit = findViewById(R.id.c_account);

        //Progress Dialog

        pd = new ProgressDialog(this);

        //firestore

        db = FirebaseFirestore.getInstance();

        //getextra
        fullName = getIntent().getStringExtra("F_name");
        gender = getIntent().getStringExtra("Gender");
        address = getIntent().getStringExtra("Address");
        phone = getIntent().getStringExtra("Phone");
        email = getIntent().getStringExtra("Email");
        password = getIntent().getStringExtra("Password");


        //click button to upload data

        Vet_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //input data
                String reg_no = Reg_no.getText().toString().trim();
                String reg_year = Reg_year.getText().toString().trim();
                String specification = Specialization.getText().toString().trim();
                String description = Description.getText().toString();
                //function call to upload data
                UploadData (reg_no, reg_year, specification, description);



            }
        });

    }

    private void UploadData(String reg_no, String reg_year, String specification, String description) {


    //set title to progress bar
        pd.setTitle("Registering Veterinarian");

        //show progress bar when user click payment button
        pd.show();

        //random id for each data to be stored
        String id = UUID.randomUUID().toString();


        // Create a new user with a first and last name
        Map<String, Object> doc = new HashMap<>();
        doc.put("ID", id);
        doc.put("Name",fullName);
        doc.put("Gender",gender);
        doc.put("Address",address);
        doc.put("Phone",phone);
        doc.put("Email",email);
        doc.put("Password",password);
        doc.put("RegistrationNo",reg_no);
        doc.put("RegistrationYear", reg_year);
        doc.put("Specialization", specification);
        doc.put("Description", description);


        //add data
        db.collection("veterinarian").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added successfully

                        pd.dismiss();
                        Toast.makeText(VetProfessionalDetails.this, "Veterinarian Registered...", Toast.LENGTH_SHORT).show();
                        openlogin();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when there is any error while uploading

                        pd.dismiss();
                        Toast.makeText(VetProfessionalDetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void openlogin() {
        Intent intent = new Intent(this,VetLogin.class);
        startActivity(intent);
    }
}
