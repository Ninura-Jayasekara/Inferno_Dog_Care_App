package com.example.inferno_dog_care_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class BuyPet extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Advertisement> advertisementArrayList;
    AdvertisementAdapter advertisementAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    private ImageView home,sell,appointment,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buypet);

        home = findViewById(R.id.homeicon);
        sell = findViewById(R.id.sellicon);
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

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        advertisementArrayList = new ArrayList<Advertisement>();
        advertisementAdapter = new AdvertisementAdapter(BuyPet.this, advertisementArrayList);

        recyclerView.setAdapter(advertisementAdapter);


        //get data from firestore
        EventChangelistner();

    }
    private void openVetlist() {
        Intent intent = new Intent(this,VetDetails.class);
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

    private void EventChangelistner() {
        db.collection("Sell_Dog").orderBy("ID", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }

                        for(DocumentChange dc :value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                advertisementArrayList.add(dc.getDocument().toObject(Advertisement.class));
                            }

                            advertisementAdapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }
}
