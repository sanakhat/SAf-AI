package com.example.wastemanagementapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView; // Import TextView
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wastemanagementapp.R;
import com.example.wastemanagementapp.database.DatabaseHelper;
import com.example.wastemanagementapp.utils.SessionManager;

import com.example.wastemanagementapp.activities.*;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView btnSignup; // Now a TextView
    private DatabaseHelper db;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup); // No cast needed

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (db.checkUser(email, password)) {
                sessionManager.createSession(email);
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignup.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, SignupActivity.class))); // No cast needed
    }
}