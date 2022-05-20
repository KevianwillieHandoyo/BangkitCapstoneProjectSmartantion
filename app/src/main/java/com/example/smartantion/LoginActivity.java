package com.example.smartantion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail;
    EditText loginPassword;
    TextView register;
    Button loginButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        loginEmail = findViewById(R.id.registerEmail);
        loginPassword = findViewById(R.id.registerPassword);
        register = findViewById(R.id.login);
        loginButton = findViewById(R.id.registerButton);

        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(view ->{
            loginUser();
        });
        register.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(){
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            loginEmail.setError("Please enter your email.");
            loginEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            loginPassword.setError("Please enter your password.");
            loginPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task<AuthResult> task){
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Logged in successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Login error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}