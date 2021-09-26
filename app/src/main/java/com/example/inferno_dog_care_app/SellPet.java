package com.example.inferno_dog_care_app;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SellPet extends AppCompatActivity {

    //views
    EditText mOwnerName, mPhone, mAddress, mBreed, mNoOfPuppies, mBirthday, mPrice;
    Button mSubmitBtn;
    private ImageView mSellPic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ImageView home,buy,appointment,profile;


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
        mSellPic = findViewById(R.id.add_sell_img);

        //Progress Dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        home = findViewById(R.id.homeicon);
        buy = findViewById(R.id.buyicon);
        appointment = findViewById(R.id.appoinmenticon);
        profile = findViewById(R.id.profileicon);

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

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSellPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choosePicture();
            }
        });

        //click button to upload data
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //input data
                String sell_edit1 = mOwnerName.getText().toString().trim();
                String sell_edit2 = mPhone.getText().toString().trim();
                String sell_edit3 = mAddress.getText().toString().trim();
                String sell_edit4 = mBreed.getText().toString().trim();
                String sell_edit5 = mNoOfPuppies.getText().toString().trim();
                String sell_edit6 = mBirthday.getText().toString();
                String sell_edit7 = mPrice.getText().toString().trim();

                //function call to upload data
                UploadData (sell_edit1,sell_edit2,sell_edit3,sell_edit4,sell_edit5,sell_edit6,sell_edit7);

                //redirecting
                openHome();

            }
        });



    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && requestCode == RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            mSellPic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String pid = UUID.randomUUID().toString();
        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageReference.child("images/"+ pid);

       mountainsRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded.", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(),"Failed To Uploaded.", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred()/ taskSnapshot.getTotalByteCount());
                        pd.setMessage("Percentage: " + (int) progressPercent + "%");
                    }
                });

        // Create a reference to 'images/mountains.jpg'
        //StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

        // While the file names are the same, the references point to different files
       // mountainsRef.getName().equals(mountainImagesRef.getName());    // true
       // mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false
    }

    public void openHome()
    {
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



    private void UploadData(String sell_edit1, String sell_edit2, String sell_edit3, String sell_edit4, String sell_edit5, String sell_edit6, String sell_edit7) {

        //set title to progress bar
        pd.setTitle("Advertisement Is Added Successfully!");

        //show progress bar when user click submit button
        pd.show();

        //random id for each data to be stored
        String id = UUID.randomUUID().toString();


        // Create a new advertisement
        Map<String, Object> doc = new HashMap<>();
        doc.put("ID",id);//id of data
        doc.put("Owner_Name",sell_edit1);
        doc.put("Phone_Number", sell_edit2);
        doc.put("Address",sell_edit3);
        doc.put("Breed", sell_edit4);
        doc.put("No_Of_Puppies",sell_edit5);
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
