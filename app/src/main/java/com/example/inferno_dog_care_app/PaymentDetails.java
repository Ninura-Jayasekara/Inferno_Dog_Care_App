package com.example.inferno_dog_care_app;

//IT20175498
//Add payment details

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class PaymentDetails extends AppCompatActivity {

    //views
    AppointmentModel Appointment = new AppointmentModel();
    EditText mCardNo, mName, mCvv, mExpire, mAmount;
    Button mPaymentBtn;
    String AppointmentID,Doctor,Customer,Date,Phone;
    String channel_name, channel_date, channel_phone, fullname, id;

    String paymentedit_cardno;
    String paymentedit_name;
    String paymentedit_cvv;
    String paymentedit_expire;
    String paymentedit_amount;


    //progress dialog

        ProgressDialog pd;

    //firestore instance

        FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_payment);


        //getExtra
        AppointmentID = getIntent().getStringExtra("AppID");
        Doctor = getIntent().getStringExtra("Doctor");
        Customer = getIntent().getStringExtra("Customer");
        Date = getIntent().getStringExtra("Date");
        Phone = getIntent().getStringExtra("Contact");



        //Initialize views with its xml

            mCardNo = findViewById(R.id.paymentedit_cardno);
            mName = findViewById(R.id.paymentedit_name);
            mCvv = findViewById(R.id.paymentedit_cvv);
            mExpire = findViewById(R.id.paymentedit_expire);
            mAmount = findViewById(R.id.paymentedit_amount);
            mPaymentBtn = findViewById(R.id.btn_payment);

        //Progress Dialog

            pd = new ProgressDialog(this);

        //firestore

            db = FirebaseFirestore.getInstance();

        //click button to upload data

            mPaymentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //validation
                    if(!TextUtils.isEmpty(mCardNo.getText().toString().trim())
                            && !TextUtils.isEmpty(mName.getText().toString().trim())
                            && !TextUtils.isEmpty(mCvv.getText().toString().trim())
                            && !TextUtils.isEmpty(mExpire.getText().toString().trim())
                            && !TextUtils.isEmpty(mAmount.getText().toString().trim())){

                    //input data
//                        paymentedit_cardno = mCardNo.getText().toString().trim();
//                        paymentedit_name = mName.getText().toString().trim();
//                        paymentedit_cvv = mCvv.getText().toString().trim();
//                        paymentedit_expire = mExpire.getText().toString().trim();
//                        paymentedit_amount = mAmount.getText().toString().trim();

                    //function call to upload data
                        UploadData (paymentedit_cardno, paymentedit_name, paymentedit_cvv, paymentedit_expire, paymentedit_amount);
                }else{
                    Toast.makeText(PaymentDetails.this, "Empty Fields Not Allowed!", Toast.LENGTH_LONG).show();
                }


                }
            });

    }


    //Redirect Do Confirmation page method

    public void openAppointmentConfirmation()
    {
        Intent intent = new Intent(this,AppointmentConfirmation.class);
        intent.putExtra("AppID",AppointmentID);
        intent.putExtra("Doctor",Doctor);
        intent.putExtra("Customer",Customer);
        intent.putExtra("Contact",Phone);
        intent.putExtra("Date",Date);
        startActivity(intent);
    }

    //Upload data to database method

    private void UploadData(String paymentedit_cardno, String paymentedit_name, String paymentedit_cvv, String paymentedit_expire, String paymentedit_amount) {

        //set title to progress bar
            pd.setTitle("Verifying Payment Details");

        //show progress bar when user click payment button
            pd.show();

        //random id for each data to be stored
            String id = UUID.randomUUID().toString();


        // Create a new user with a first and last name
            Map<String, Object> doc = new HashMap<>();
                doc.put("ID", id);
                doc.put("Card No",paymentedit_cardno);
                doc.put("Name", paymentedit_name);
                doc.put("CVV", paymentedit_cvv);
                doc.put("Expire", paymentedit_expire);
                doc.put("Amount" , paymentedit_amount);

        //add data
            db.collection("payments").document(id).set(doc)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //this will be called when data is added successfully
                            
                            pd.dismiss();
                            Toast.makeText(PaymentDetails.this, "Verified...", Toast.LENGTH_SHORT).show();
                            //redirecting
                            openAppointmentConfirmation();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //this will be called when there is any error while uploading

                            pd.dismiss();
                            Toast.makeText(PaymentDetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
    }
}
