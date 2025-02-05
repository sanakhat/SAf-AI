package com.example.wastemanagementapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wastemanagementapp.R;
import com.example.wastemanagementapp.database.DatabaseHelper;
import com.example.wastemanagementapp.utils.SessionManager;
import com.example.wastemanagementapp.adapters.SellHistoryAdapter;
import com.example.wastemanagementapp.models.SellHistory;

import java.util.List;


public class SignupActivity extends AppCompatActivity {
    private EditText etName, etEmail, etPassword;
    private Button btnSignup;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DatabaseHelper(this);

        etName = findViewById(R.id.name);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (db.addUser(name, email, password)) {
                Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Signup failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
