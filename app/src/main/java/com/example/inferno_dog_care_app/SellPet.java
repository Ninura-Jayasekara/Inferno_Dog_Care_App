package com.example.inferno_dog_care_app;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.Continuation;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

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

    UploadTask uploadTask;

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
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(ContextCompat.checkSelfPermission(SellPet.this, Manifest.permission.READ_EXTERNAL_STORAGE)!=
                            PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(SellPet.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        ActivityCompat.requestPermissions(SellPet.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                    }else{
                        ChooseImage();
                    }
                }else{
                    ChooseImage();
                }
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



            }
        });



    }

    private void ChooseImage() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .start(SellPet.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                imageUri = result.getUri();
                mSellPic.setImageURI(imageUri);
            }else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();

            }
        }
    }

    /*private void uploadPicture() {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();
        final String pid = UUID.randomUUID().toString();
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
    }*/

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

    private String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType((contentResolver.getType(uri)));
    }



    private void UploadData(String sell_edit1, String sell_edit2, String sell_edit3, String sell_edit4, String sell_edit5, String sell_edit6, String sell_edit7) {
        if(!TextUtils.isEmpty(sell_edit1) && !TextUtils.isEmpty(sell_edit2) ||
                !TextUtils.isEmpty(sell_edit3) && !TextUtils.isEmpty(sell_edit4) &&
                !TextUtils.isEmpty(sell_edit5) && !TextUtils.isEmpty(sell_edit6) &&
                !TextUtils.isEmpty(sell_edit7) && imageUri != null){

            //set title to progress bar
            pd.setTitle("Advertisement Is Added Successfully!");

            //show progress bar when user click submit button
            pd.show();

            final StorageReference reference = storageReference.child(System.currentTimeMillis()+ "."+getFileExt(imageUri));
            uploadTask = reference.putFile(imageUri);

            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if(!task.isSuccessful()){
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if(task. isSuccessful()){
                        Uri downloadUri =task.getResult();

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
                        doc.put("imageURL",downloadUri.toString());

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
            });


            //redirecting
            openHome();

        }else {
            Toast.makeText(this, "Please Fill All The Fields",Toast.LENGTH_SHORT).show();
        }


    }
}