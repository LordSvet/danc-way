package com.example.dancway.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dancway.R;
import com.example.dancway.controller.UserController;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private TextView signup;
    private UserController userController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // only to verify if the login session is already saved or not
        if (UserController.getInstance(this).autologin())
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        setContentView(R.layout.activity_login);

        userController = UserController.getInstance(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
// making Register text clickable and moving from login screen back to register activity
        signup = (TextView) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent ( LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        switch(view.getId()) {
            case R.id.signup:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.signIn:
                userController.login(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
        }
    }
    }

