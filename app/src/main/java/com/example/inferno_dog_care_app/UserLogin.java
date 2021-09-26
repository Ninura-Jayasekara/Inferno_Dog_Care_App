package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
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

public class UserLogin extends AppCompatActivity {
    EditText Email, Password;
    Button Login_btn;
    TextView Signup;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        firebaseAuth = FirebaseAuth.getInstance();
        Signup = findViewById(R.id.txt_btnsign);
        Signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UserRegister.class));
            }
        });

        Email = findViewById(R.id.txt_email);
        Password = findViewById(R.id.txt_user_password);
        Login_btn = findViewById(R.id.btn_user_login);
        Login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //extract
                if(Email.getText().toString().isEmpty()){
                    Email.setError("Email is missing");
                    return;
                }
                if(Password.getText().toString().isEmpty()){
                    Password.setError("Password is missing");
                    return;
                }
                //validate
                firebaseAuth.signInWithEmailAndPassword(Email.getText().toString(),Password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        //login is successful
                        startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UserLogin.this, "e.getMessage", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
