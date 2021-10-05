package com.example.inferno_dog_care_app;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;

public class VetIncome extends AppCompatActivity {

    TextView Colum1_n, Colum1_d , Income;
    EditText Count;
    Button Calculate_btn;
    //progress dialog

    ProgressDialog pd;

    //firestore instance

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_income);

        Colum1_n = findViewById(R.id.c1_n);
        Colum1_d = findViewById(R.id.c1_d);
        Calculate_btn = findViewById(R.id.calc_income);
        Count = findViewById(R.id.appointmentsCount);
        Income = findViewById(R.id.income_amount);

        db = FirebaseFirestore.getInstance();
        fetchdata();

        Calculate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(Count.getText().toString().trim())){
                    getIncome();
                }else{
                    Toast.makeText(getApplicationContext(),"Enter Your Appointment Count",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    //Calculation
    private void getIncome() {
        float count = Float.parseFloat(Count.getText().toString().trim());

        float income = (float) ((count * 500) - (((count*500)/100)*5));

        DecimalFormat df = new DecimalFormat("0.00");

        Income.setText(""+df.format(income));
    }


    private void fetchdata() {
        DocumentReference document =db.collection("Appointments").document("3ead3e2f-adb8-4b38-87e8-cdbc06a402b4");
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Colum1_n.setText(documentSnapshot.getString("Owner"));
                    Colum1_d.setText(documentSnapshot.getString("Date"));

                }

                else
                    Toast.makeText(getApplicationContext(),"Row not found",Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Failed to fetch data",Toast.LENGTH_LONG).show();
            }
        });

    }



}


