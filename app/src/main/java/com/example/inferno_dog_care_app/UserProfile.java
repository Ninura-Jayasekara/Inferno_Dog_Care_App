package com.example.inferno_dog_care_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class UserProfile extends AppCompatActivity {
    EditText  UserDetail2, UserDetail3;
    TextView User_name, UserDetail1, UserDetail4, UserDetail5, UserDetail6, UserDetail7,UserDetail8;
    FirebaseFirestore fStore;
    Button Delete, Update;
    private ImageView home,buy,appointment,sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        User_name = (TextView) findViewById(R.id.txt_userprofile_name);
        UserDetail1 = (TextView) findViewById(R.id.txt_userprofile_gender);
        UserDetail2 = (EditText)findViewById(R.id.txt_userprofile_phone);
        UserDetail3 = (EditText)findViewById(R.id.txt_userprofile_email);
        UserDetail4 = (TextView) findViewById(R.id.txt_userprofile_dogname);
        UserDetail5 = (TextView) findViewById(R.id.txt_userprofile_dogbreed);
        UserDetail6 = (TextView) findViewById(R.id.txt_userprofile_doggender);
        UserDetail7 = (TextView) findViewById(R.id.txt_userprofile_dogbday);
        UserDetail8 = (TextView) findViewById(R.id.txt_userprofile_dogcolor);
        Update = (Button)findViewById(R.id.btn_userprofile_update);
        Delete = (Button)findViewById(R.id.btn_userprofile_delete);
        fStore = FirebaseFirestore.getInstance();

        home = findViewById(R.id.homeicon);
        buy = findViewById(R.id.buyicon);
        appointment = findViewById(R.id.appoinmenticon);
        sell= findViewById(R.id.sellicon);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHome();
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

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdvertisement();
            }
        });

        fetchdata();
    }

    private void fetchdata() {
        DocumentReference document =fStore.collection("user").document("5b5a5402-f26b-4645-b169-0780436c3613");
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    User_name.setText("Name : "+documentSnapshot.getString("Name"));
                    UserDetail1.setText("Gender : "+documentSnapshot.getString("Gender"));
                    UserDetail2.setText(documentSnapshot.getString("Phone"));
                    UserDetail3.setText(documentSnapshot.getString("Email"));
                    UserDetail4.setText("Name : "+documentSnapshot.getString("DogName"));
                    UserDetail5.setText("Gender : "+documentSnapshot.getString("DogGender"));
                    UserDetail6.setText("Breed : "+documentSnapshot.getString("DogBreed"));
                    UserDetail7.setText("Birth Day : "+documentSnapshot.getString("DogBday"));
                    UserDetail8.setText("Color : "+documentSnapshot.getString("DogColor"));

                    Update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String phone =UserDetail2.getText().toString();
                            String email =UserDetail3.getText().toString();
                            HashMap hashMap = new HashMap();
                            hashMap.put("Phone", phone);
                            hashMap.put("Email", email);
                            document.update(hashMap).addOnSuccessListener(new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Toast.makeText(UserProfile.this,"Data Updated", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UserProfile.this,e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                    // delete user
                    Delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            document.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(UserProfile.this,"User Profile Deleted", Toast.LENGTH_SHORT).show();
                                    openHome();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UserProfile.this,e.toString(), Toast.LENGTH_SHORT).show();
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

    private void openVetlist() {
        Intent intent = new Intent(this,VetDetails.class);
        startActivity(intent);
    }

    public void openAdvertisements() {
        Intent intent = new Intent(this,BuyPet.class);
        startActivity(intent);
    }

    private void openAdvertisement() {
        Intent intent = new Intent(this,SellPet.class);
        startActivity(intent);
    }

}
