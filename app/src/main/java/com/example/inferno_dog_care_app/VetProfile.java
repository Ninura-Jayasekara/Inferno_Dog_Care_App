package com.example.inferno_dog_care_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.ResourceBundle;

public class VetProfile extends AppCompatActivity {

        EditText Doc_name, Detail1, Detail2, Detail3, Detail4, Detail5, Detail6;
        FirebaseFirestore fStore;
        Button Delete, Update, Appointments_btn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.vet_profile);

            Doc_name = (EditText) findViewById(R.id.doc_name);
            Detail1 = (EditText)findViewById(R.id.detail1);
            Detail2 = (EditText)findViewById(R.id.detail2);
            Detail3 = (EditText)findViewById(R.id.detail3);
            Detail4 = (EditText)findViewById(R.id.detail4);
            Detail5 = (EditText)findViewById(R.id.doc_description1);
            Detail6 = (EditText)findViewById(R.id.doc_description3);
            Update = (Button)findViewById(R.id.vet_up_b);
            Delete = (Button)findViewById(R.id.vet_del_b);
            Appointments_btn = (Button)findViewById(R.id.vet_app_b);


            fStore = FirebaseFirestore.getInstance();
            fetchdata();

            Appointments_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(VetProfile.this,VetIncome.class);
                    startActivity(intent);
                }
            });

        }


    private void fetchdata() {
        DocumentReference document =fStore.collection("veterinarian").document("64fde926-31b8-4f53-85f1-00f44047d135");
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Doc_name.setText("Dr."+documentSnapshot.getString("Name"));
                    Detail1.setText(documentSnapshot.getString("Gender"));
                    Detail2.setText(documentSnapshot.getString("Address"));
                    Detail3.setText(documentSnapshot.getString("Phone"));
                    Detail4.setText(documentSnapshot.getString("Email"));
                    Detail5.setText(documentSnapshot.getString("Specialization"));
                    Detail6.setText(documentSnapshot.getString("RegistrationNo"));



                    //update data

                    Update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String address =Detail2.getText().toString();
                            String phone =Detail3.getText().toString();
                            String email =Detail4.getText().toString();
                            HashMap hashMap = new HashMap();
                            hashMap.put("Address", address);
                            hashMap.put("Phone", phone);
                            hashMap.put("Email", email);
                            document.update(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(VetProfile.this,"Data Updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(VetProfile.this,e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });


                    // delete vet

                    Delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            document.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(VetProfile.this,"Advertisement is Deleted", Toast.LENGTH_SHORT).show();
                                    openHome();
                                }

                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(VetProfile.this,e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
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

   private void openHome() {
       Intent intent = new Intent(this,MainMenu.class);
       startActivity(intent);
    }


}

    
