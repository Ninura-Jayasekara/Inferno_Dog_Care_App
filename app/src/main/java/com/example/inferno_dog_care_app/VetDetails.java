package com.example.inferno_dog_care_app;

//IT20175498
//View Data In Recycler View

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

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


public class VetDetails  extends AppCompatActivity {



    //Recycler View
    RecyclerView recyclerView;

    //ArrayList
    ArrayList<Veterinarian> veterinarianArrayList;

    //Adapter
    VetAdapter vetAdapter;

    //Firebase instance
    FirebaseFirestore db;

    //progress Dialog
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_details);


        //view progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        //Initialize views with its xml

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        //Firestore
        db = FirebaseFirestore.getInstance();

        //Array List
        veterinarianArrayList = new ArrayList<Veterinarian>();

        //adaptor
        vetAdapter = new VetAdapter(VetDetails.this,veterinarianArrayList);

        //Set adaptor to recycler view
        recyclerView.setAdapter(vetAdapter);


        //Function called to fetch data
        EventChangeListner();






    }


    // fetch data from the database
    private void EventChangeListner() {

        db.collection("veterinarian").orderBy("ID", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null){

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();

                            Log.e("Firestore Error",error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){

                                veterinarianArrayList.add(dc.getDocument().toObject(Veterinarian.class));
                            }

                            vetAdapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }

                    }
                });

    }


}