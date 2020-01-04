// TODO: 01/03/2020 add "Register" title to activity_register.xml

package com.swapnil.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {

    private Button mRegister;
    private EditText registerEmailInput;
    private EditText registerPasswordInput;

    //Instance of FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mRegister = findViewById(R.id.register);
        registerEmailInput = findViewById(R.id.registerUsername);
        registerPasswordInput = findViewById(R.id.registerPassword);

        //Initialize Firebase authentication system
        mAuth = FirebaseAuth.getInstance();

        //When user clicks register, fetch the data from the editTexts, input into Firebase auth, and throw message.
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(
                        registerEmailInput.getText().toString(),
                        registerPasswordInput.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show(); //ie user exists, wrong data, etc
                        }
                    }
                });
            }
        });

    }
}
