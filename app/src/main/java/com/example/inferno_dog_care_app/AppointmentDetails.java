package com.example.inferno_dog_care_app;

//IT20175498
//

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AppointmentDetails extends AppCompatActivity {


    //firestore instance
    FirebaseFirestore db;

    //progress dialog
    ProgressDialog progressDialog;


    AppointmentModel Appointment = new AppointmentModel();
    String AppointmentID,Doctor,Customer,Date,Phone;


    //Views

    TextView Dname,Oname;
    EditText Odate, Ophone;
    Button btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_details);

        //progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();


        //firestore
        db = FirebaseFirestore.getInstance();

        //getExtra
        AppointmentID = getIntent().getStringExtra("AppID");
        Doctor = "Dr. "+getIntent().getStringExtra("Doctor");
        Customer = getIntent().getStringExtra("Customer");
        Date = getIntent().getStringExtra("Date");
        Phone = getIntent().getStringExtra("Contact");


        //Initialize views with its xml

        Dname = findViewById(R.id.txt_doctorname2);
        Oname = findViewById(R.id.txt_ownername2);
        Odate = findViewById(R.id.txt_date_detail);
        Ophone = findViewById(R.id.txt_ownerphone3);
        btnDelete=findViewById(R.id.btn_details_delete);
        btnUpdate=findViewById(R.id.btn_details_update);

        //set variables

        Dname.setText(Doctor);
        Oname.setText(Customer);
        Odate.setText(Date);
        Ophone.setText(Phone);

        //progress dialog
        progressDialog.dismiss();


        //update button
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        //Delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Delete();
            }
        });

        //EventChangeListner();

    }


    //Update

    public void update(){

        Map<String, Object> doc = new HashMap<>();
        doc.put("ID", AppointmentID);
        doc.put("DoctorName", Doctor);
        doc.put("Date", Odate.getText().toString().trim());
        doc.put("Owner", Customer);
        doc.put("Phone", Ophone.getText().toString().trim());

        db.collection("Appointments").document(AppointmentID)
                .set(doc)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.setMessage("Updating...");
                        progressDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        },4000);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.setMessage("Error occurred while updating...");
                        progressDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        },4000);
                    }
                });
    }


    //Delete

    public void Delete(){

        db.collection("Appointments").document(AppointmentID)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressDialog.setMessage("Success...");
                        progressDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        },4000);
                        RedirectToVetDetails();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.setMessage(e.getMessage());
                        progressDialog.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                            }
                        },4000);
                    }
                });
    }


    //Redirect to vet details
    public void RedirectToVetDetails()
    {
        Intent intent = new Intent(this,VetDetails.class);

        startActivity(intent);


    }
}

