package com.example.inferno_dog_care_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class VetLogin extends AppCompatActivity {
    EditText Email, Password;
    Button Login_btn;
    TextView Signup;
    FirebaseAuth firebaseAuth;
    String email = String.valueOf(Email);

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference userCollection = db.collection("veterinarian");

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
        Email = findViewById(R.id.email3);
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
                        Intent intent = new Intent(VetLogin.this,VetProfile.class);
                        intent.putExtra("Email", email);
                        startActivity(intent);

                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(VetLogin.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
