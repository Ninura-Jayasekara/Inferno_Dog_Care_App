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

public class DogRegister extends AppCompatActivity {
    //views
    EditText Dog_name, Dog_gender, Dog_breed, Dog_birthday, Dog_color;
    Button Dog_submit;
    String fullName;
    String gender1;
    String phone1;
    String email1;
    String password1;
    String txt_dogname,txt_doggender,txt_dogbreed,txt_dogbday,txt_dogcolor;

    //progress dialog
    ProgressDialog pd;

    //firestore instance
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reg_dog);

        //Initialize views with its xml
        Dog_name = findViewById(R.id.txt_dogname);
        Dog_gender = findViewById(R.id.txt_doggender);
        Dog_breed = findViewById(R.id.txt_dogbreed);
        Dog_birthday = findViewById(R.id.txt_dogbday);
        Dog_color = findViewById(R.id.txt_dogcolor);
        Dog_submit = findViewById(R.id.btn_create_account);

        //Progress Dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //getextra
        fullName = getIntent().getStringExtra("User_name");
        gender1 = getIntent().getStringExtra("Gender");
        phone1 = getIntent().getStringExtra("Phone");
        email1 = getIntent().getStringExtra("Email");
        password1 = getIntent().getStringExtra("Password");

        //click button to upload data
        Dog_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input data
                 txt_dogname = Dog_name.getText().toString().trim();
                 txt_doggender = Dog_gender.getText().toString().trim();
                 txt_dogbreed = Dog_breed.getText().toString().trim();
                 txt_dogbday = Dog_birthday.getText().toString();
                 txt_dogcolor = Dog_color.getText().toString();
                //function call to upload data
                UploadData (txt_dogname, txt_doggender, txt_dogbreed, txt_dogbday, txt_dogcolor);

            }
        });
    }

    private void UploadData(String txt_dogname, String txt_doggender, String txt_dogbreed, String txt_dogbday, String txt_dogcolor) {

        //set title to progress bar
        pd.setTitle("Registering User");

        //show progress bar when user click create account button
        pd.show();

        //random id for each data to be stored
        String id = UUID.randomUUID().toString();

        // Create a new user with name
        Map<String, Object> doc = new HashMap<>();
        doc.put("ID", id);
        doc.put("Name",fullName);
        doc.put("Gender",gender1);
        doc.put("Phone",phone1);
        doc.put("Email",email1);
        doc.put("Password",password1);
        doc.put("DogName",txt_dogname);
        doc.put("DogGender", txt_doggender);
        doc.put("DogBreed", txt_dogbreed);
        doc.put("DogBday", txt_dogbday);
        doc.put("DogColor", txt_dogcolor);

        //add data
        db.collection("user").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added successfully
                        pd.dismiss();
                        Toast.makeText(DogRegister.this, "User Registered...", Toast.LENGTH_SHORT).show();
                        openlogin();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        //this will be called when there is any error while uploading
                        pd.dismiss();
                        Toast.makeText(DogRegister.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void openlogin() {
        Intent intent = new Intent(this,UserLogin.class);
        startActivity(intent);
    }
}