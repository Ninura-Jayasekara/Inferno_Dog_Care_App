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

public class SellPet extends AppCompatActivity {

    //views
    EditText mOwnerName, mPhone, mAddress, mBreed, mNoOfPuppies, mBirthday, mPrice;
    Button mSubmitBtn;

    //progress dialog
    ProgressDialog pd;

    //Firestore instance
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_pet);

        //Initialize views with its xml
        mOwnerName = findViewById(R.id.sell_edit1);
        mPhone = findViewById(R.id.sell_edit2);
        mAddress = findViewById(R.id.sell_edit3);
        mBreed = findViewById(R.id.sell_edit4);
        mNoOfPuppies= findViewById(R.id.sell_edit5);
        mBirthday = findViewById(R.id.sell_edit6);
        mPrice = findViewById(R.id.sell_edit7);
        mSubmitBtn = findViewById(R.id.sell_btn);

        //Progress Dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //click button to upload data
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //input data
                String sell_edit1 = mOwnerName.getText().toString().trim();
                String sell_edit2 = mPhone.getText().toString().trim();
                String sell_edit3 = mAddress.getText().toString().trim();
                String sell_edit4 = mBreed.getText().toString().trim();
                Integer sell_edit5 = Integer.valueOf(mNoOfPuppies.getText().toString().trim());
                String sell_edit6 = mBirthday.getText().toString();
                Double sell_edit7 = Double.valueOf(mPrice.getText().toString().trim());

                //function call to upload data
                UploadData (sell_edit1,sell_edit2,sell_edit3,sell_edit4,sell_edit5,sell_edit6,sell_edit7);

                //redirecting
                openHome();

            }
        });

    }
    public void openHome()
    {
        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }
    private void UploadData(String sell_edit1, String sell_edit2, String sell_edit3, String sell_edit4, Integer sell_edit5, String sell_edit6, Double sell_edit7) {

        //set title to progress bar
        pd.setTitle("Advertisement Is Added Successfully!");

        //show progress bar when user click submit button
        pd.show();

        //random id for each data to be stored
        String id = UUID.randomUUID().toString();


        // Create a new advertisement
        Map<String, Object> doc = new HashMap<>();
        doc.put("ID",id);//id of data
        doc.put("Owner Name",sell_edit1);
        doc.put("Phone Number", sell_edit2);
        doc.put("Address",sell_edit3);
        doc.put("Breed", sell_edit4);
        doc.put("No Of Puppies",sell_edit5);
        doc.put("Birthday",sell_edit6);
        doc.put("Price",sell_edit7);

        //add data
        db.collection("Sell_Dog").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added successfully

                        pd.dismiss();
                        Toast.makeText(SellPet.this, "Advertisement Uploaded...", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called when there is any error while uploading
                        pd.dismiss();

                        //get and show error message
                        Toast.makeText(SellPet.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
