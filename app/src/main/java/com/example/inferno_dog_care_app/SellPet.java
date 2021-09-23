package com.example.inferno_dog_care_app;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SellPet extends AppCompatActivity{
    //Views
    EditText sell_edit1,sell_edit2,sell_edit3, sell_edit4, sell_edit5, sell_edit6, sell_edit7;
    Button sell_btn;
    /*private ImageView add_sell_img;
    public Uri imageUri;

    private FirebaseStorage storage;
    private StorageReference storageReference;*/

    //progress dialog
    ProgressDialog pd;

    //Firestore Instance
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_pet);
        //actionbar and its title
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("Add Data");

        //Initialize views with its xml
        sell_edit1 = findViewById(R.id.sell_edit1);
        sell_edit2 = findViewById(R.id.sell_edit2);
        sell_edit3 = findViewById(R.id.sell_edit3);
        sell_edit4 = findViewById(R.id.sell_edit4);
        sell_edit5 = findViewById(R.id.sell_edit5);
        sell_edit6 = findViewById(R.id.sell_edit6);
        sell_edit7 = findViewById(R.id.sell_edit7);
        sell_btn = findViewById(R.id.sell_btn);
        //add_sell_img = findViewById(R.id.add_sell_img);

        //progress dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        /*storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();*/

        //click button to upload data
        sell_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data
                String ownerName = sell_edit1.getText().toString().trim();
                String phone = sell_edit2.getText().toString().trim();
                String address = sell_edit3.getText().toString().trim();
                String breed = sell_edit4.getText().toString().trim();
                Integer noOfPuppies = Integer.valueOf(sell_edit5.getText().toString().trim());
                String birthday = sell_edit5.getText().toString();
                Double Price = Double.valueOf(sell_edit6.getText().toString().trim());

                //choosePicture();

                //function call to upload data
                uploadData(ownerName, phone, address, breed, noOfPuppies, birthday, Price);

            }
        });

    }

    /*private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            add_sell_img.setImageURI(imageUri);
            uploadPicture();

        }
    }*/

   /* private void uploadPicture() {
        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageReference.child("mountains.jpg");

        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

        // While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
    }*/

    private void uploadData(String ownerName, String phone, String address, String breed, Integer noOfPuppies, String birthday, Double price) {
        //set title of progress bar
        pd.setTitle("Adding Data to Firestore");

        //show progress bar when user click the submit button
        pd.show();

        //random id for each data to be stored
        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id",id);//id of data
        doc.put("owner_name",ownerName);
        doc.put("phone_no", phone);
        doc.put("address",address);
        doc.put("breed", breed);
        doc.put("no_of_puppies",noOfPuppies);
        doc.put("birthday",birthday);
        doc.put("price",price);

        //add this data
        db.collection("sellDog").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data added successfully
                        pd.dismiss();
                        Toast.makeText(SellPet.this,"Uploaded...",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called if there is any error while uploading
                        pd.dismiss();

                        //get and show error message
                        Toast.makeText(SellPet.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
