package com.example.firebaseproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    EditText emailID, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailID = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);
        btnSignUp = findViewById(R.id.signUpButton);
        tvSignIn = findViewById(R.id.extistingAccount);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailID.getText().toString();
                String pass = password.getText().toString();
                if(email.isEmpty()){
                    emailID.setError("Email can not be empty");
                    emailID.requestFocus();
                } else if (pass.isEmpty()){
                    password.setError("Password can not be ampty");
                    password.requestFocus();

                } else if (email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields are empty",Toast.LENGTH_SHORT);
                } else if(!(email.isEmpty() && pass.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Signup unsuccessful. Try again.",Toast.LENGTH_SHORT);

                            } else {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));

                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_SHORT);
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
