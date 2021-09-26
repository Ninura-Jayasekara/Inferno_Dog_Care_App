package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ViewSellAdvertisement extends AppCompatActivity  {
    EditText OwnerName, Phone, Address, Breed, NoOfPuppies, Birthday, Price;
    FirebaseFirestore fStore;
    Button btnUpdate, btnDelete;
    private ImageView home,sell,buy,appointment,profile;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_sellpet);


        OwnerName = (EditText) findViewById(R.id.view_name);
        Phone = (EditText) findViewById(R.id.view_phone);
        Address = (EditText) findViewById(R.id.view_address);
        Breed =(EditText) findViewById(R.id.view_breed);
        NoOfPuppies =(EditText) findViewById(R.id.view_count);
        Birthday = (EditText) findViewById(R.id.channel_date);
        Price =(EditText) findViewById(R.id.view_price);
        btnUpdate = (Button)findViewById(R.id.view_updatebtn);
        btnDelete =(Button) findViewById(R.id.view_deletebtn);

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


        fStore = FirebaseFirestore.getInstance();

        fetchdata();

    }

    private void openVetlist() {
        Intent intent = new Intent(this,VetDetails.class);
        startActivity(intent);
    }

    private void openHome() {
        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }


    public void fetchdata() {
        DocumentReference document =fStore.collection("Sell_Dog").document("fd25e50a-10b5-49b2-a389-fd39de17d117");
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    OwnerName.setText(documentSnapshot.getString("Owner_Name"));
                    Phone.setText(documentSnapshot.getString("Phone_Number"));
                    Address.setText(documentSnapshot.getString("Address"));
                    Breed.setText(documentSnapshot.getString("Breed"));
                    NoOfPuppies.setText(documentSnapshot.getString("No_Of_Puppies"));
                    Birthday.setText(documentSnapshot.getString("Birthday"));
                    Price.setText(documentSnapshot.getString("Price"));

                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String count =NoOfPuppies.getText().toString();
                            String price =Price.getText().toString();

                            HashMap hashMap = new HashMap();
                            hashMap.put("No_Of_Puppies", count);
                            hashMap.put("Price", price);
                            document.update(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(ViewSellAdvertisement.this,"Advertisement is Updated", Toast.LENGTH_SHORT).show();
                                    openAdvertisements();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ViewSellAdvertisement.this,e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            document.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(ViewSellAdvertisement.this,"Advertisement is Deleted", Toast.LENGTH_SHORT).show();
                                    openAdvertisement();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ViewSellAdvertisement.this,e.toString(), Toast.LENGTH_SHORT).show();
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

    private void openAdvertisement() {
        Intent intent = new Intent(this,SellPet.class);
        startActivity(intent);
    }

    public void openAdvertisements() {
        Intent intent = new Intent(this,BuyPet.class);
        startActivity(intent);
    }



}
