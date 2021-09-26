package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class VetLogin extends AppCompatActivity {

    EditText VEmail, VPassword;
    Button Login_btn;
    TextView Signup;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vet_logging);

        firebaseAuth = FirebaseAuth.getInstance();

        Signup = findViewById(R.id.txt_signup);
        Signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),VetRegistation.class));
            }
        });

        VEmail = findViewById(R.id.email3);
        VPassword = findViewById(R.id.txt_user_password);
        Login_btn = findViewById(R.id.btn_user_login);

        Login_btn.setOnClickListener((v) -> {

            String Email = VEmail.getText().toString().trim();
            String Password = VPassword.getText().toString().trim();

            if(TextUtils.isEmpty(Email)) {
                VEmail.setError("Email required");
                return;
            }
            if(TextUtils.isEmpty(Password)) {
                VPassword.setError("Password required");
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener((task) -> {
                if(task.isSuccessful()) {
                    Toast.makeText(VetLogin.this, "Logged in Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),VetProfile.class));
                } else {
                    Toast.makeText(VetLogin.this, "Error!!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }
}
