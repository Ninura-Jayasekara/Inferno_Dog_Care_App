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


public class UserRegister extends AppCompatActivity {
    //views
    EditText User_name, Gender, Phone, Email, Password;
    Button User_next;
    String user_name, gender, phone, email, password;

    //progress dialog
    ProgressDialog pd;

    //firestore instance
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reg);

        //Initialize views with its xml
        User_name = findViewById(R.id.txt_fname);
        Gender = findViewById(R.id.txt_gender);
        Phone = findViewById(R.id.txt_phone);
        Email = findViewById(R.id.txt_email2);
        Password = findViewById(R.id.txt_password2);
        User_next = findViewById(R.id.btn_user_reg);

        //Progress Dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //click button to upload data
        User_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //input data
                user_name = User_name.getText().toString().trim();
                gender = Gender.getText().toString().trim();
                phone = Phone.getText().toString().trim();
                email = Email.getText().toString().trim();
                password = Password.getText().toString().trim();
                openProfessional();

            }
        });
    }

    private void openProfessional() {
        Intent intent = new Intent(this, DogRegister.class);
        intent.putExtra("User_name",user_name);
        intent.putExtra("Gender",gender);
        intent.putExtra("Phone",phone);
        intent.putExtra("Email",email);
        intent.putExtra("Password",password);
        startActivity(intent);
    }
}