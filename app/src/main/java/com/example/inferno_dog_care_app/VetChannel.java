package com.example.inferno_dog_care_app;

//IT20175498
//Channel a veterinarian & send data to database

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VetChannel extends AppCompatActivity {

    TextView textname, textspecial, textreg, textaddress, textphone, textemail;
    EditText cName, cDate, cPhone;
    Button bhold, bpay;
    String channel_name, channel_date, channel_phone, fullname, id;
    boolean isHold=true;

    //progress dialog

    ProgressDialog pd;

    //firestore instance

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_vet);


        //Progress Dialog

        pd = new ProgressDialog(this);

        //firestore

        db = FirebaseFirestore.getInstance();

        //Initialize views with its xml

        textname=findViewById(R.id.txt_channel_dis_name);
        textspecial = findViewById(R.id.txt_channel_dis_deg);
        textreg = findViewById(R.id.txt_channel_dis_reg);
        textaddress = findViewById(R.id.txt_channel_dis_address);
        textphone = findViewById(R.id.txt_channel_dis_phone);
        textemail = findViewById(R.id.txt_channel_dis_email);

        cName = findViewById(R.id.channel_name);
        cDate = findViewById(R.id.channel_date);
        cPhone = findViewById(R.id.channel_phone);
        bhold = findViewById(R.id.channel_btn_hold);
        bpay = findViewById(R.id.channel_btn_payment);


        //Get extra

        String id=getIntent().getStringExtra("ID");
        String name="Dr "+getIntent().getStringExtra("Name");
        String special=getIntent().getStringExtra("Specialization");
        String address =getIntent().getStringExtra("Address");
        String registrationNo=getIntent().getStringExtra("RegistrationNo");
        String phone=getIntent().getStringExtra("Phone");
        String email=getIntent().getStringExtra("Email");
        fullname=getIntent().getStringExtra("Name");


        //set variables

        textname.setText(name);
        textspecial.setText(special);
        textreg.setText(registrationNo);
        textaddress.setText(address);
        textphone.setText(phone);
        textemail.setText(email);


        //hold button click

        bhold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check the button
                isHold=true;

                //input data
                channel_name = cName.getText().toString().trim();
                channel_date = cDate.getText().toString().trim();
                channel_phone = cPhone.getText().toString().trim();


                //function call to upload data
                UploadData ();



            }
        });

        //payment button click

        bpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isHold=false;

                //Validation

                if(!TextUtils.isEmpty(cName.getText().toString())
                        && !TextUtils.isEmpty(cDate.getText().toString())
                        && !TextUtils.isEmpty(cPhone.getText().toString())
                       ){
                //input data
                channel_name = cName.getText().toString().trim();
                channel_date = cDate.getText().toString().trim();
                channel_phone = cPhone.getText().toString().trim();

                    //function call to upload data
                    UploadData ();


                }else{
                    Toast.makeText(VetChannel.this, "Empty Fields Not Allowed!", Toast.LENGTH_LONG).show();
                }





            }
        });



    }


        //put extra

        private void openAppointment () {
            Intent intent = new Intent(this, AppointmentDetails.class);
            intent.putExtra("AppID",id);
            intent.putExtra("Doctor",fullname);
            intent.putExtra("Customer",channel_name);
            intent.putExtra("Contact",channel_phone);
            intent.putExtra("Date",channel_date);
            startActivity(intent);

        }


    //Redirect to payment details class
    private void openPaymentDetails () {
        Intent intent = new Intent(this, PaymentDetails.class);

        startActivity(intent);

    }

    //upload data

    private void UploadData() {

        //set title to progress bar
        pd.setTitle("Making the appointment...");

        //show progress bar when user click payment button
        pd.show();

        //random id for each data to be stored
        id = UUID.randomUUID().toString();


        // Create a new user with a first and last name
        CollectionReference Appoinments = db.collection("Appointments");
        Map<String, Object> doc = new HashMap<>();
        doc.put("ID", id);
        doc.put("DoctorName", fullname);
        doc.put("Date", channel_date);
        doc.put("Owner", channel_name);
        doc.put("Phone", channel_phone);
        Appoinments.document(id).set(doc);

        //add data
        db.collection("Appointments").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added successfully

                        pd.dismiss();
                        Toast.makeText(VetChannel.this, "Appointment Place...", Toast.LENGTH_SHORT).show();
                        //redirecting
                        if(isHold){

                            //Validation
                            if(!TextUtils.isEmpty(cName.getText().toString())
                                    && !TextUtils.isEmpty(cDate.getText().toString())
                                    && !TextUtils.isEmpty(cPhone.getText().toString())
                            ){
                                openAppointment();
                            }else{
                                Toast.makeText(VetChannel.this, "Empty Fields Not Allowed!", Toast.LENGTH_LONG).show();
                            }}
                            else{
                                openPaymentDetails();
                            }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when there is any error while uploading

                        pd.dismiss();
                        Toast.makeText(VetChannel.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

}


