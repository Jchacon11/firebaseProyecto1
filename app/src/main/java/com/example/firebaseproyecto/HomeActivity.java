package com.example.firebaseproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class HomeActivity extends AppCompatActivity {
    Button btnLogout, btnDatos;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private EditText mEditTextFrase;
    private Button mbtnCrearFrase;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mbtnCrearFrase = findViewById(R.id.btnCrearFrase);
        mEditTextFrase = findViewById(R.id.editTextFrase);


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mbtnCrearFrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Frase = mEditTextFrase.getText().toString();
                Map<String, Object>usuarioMap = new HashMap<>();

                usuarioMap.put("Email", "fncbroxah@gmail.com");
                usuarioMap.put("Frase", Frase);

                mDatabase.child("Usuarios").push().setValue(usuarioMap);

            }
        });


        btnLogout = findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent (HomeActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });


    }


}
