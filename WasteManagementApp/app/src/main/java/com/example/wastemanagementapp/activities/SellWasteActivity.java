package com.example.wastemanagementapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wastemanagementapp.R;
import com.example.wastemanagementapp.database.DatabaseHelper;
import com.example.wastemanagementapp.utils.SessionManager;

public class SellWasteActivity extends AppCompatActivity {

    private EditText etWasteType, etWeight, etLocation;
    private Button btnCaptureImage, btnSell;
    private ImageView imgWastePreview;
    private DatabaseHelper db;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_waste);

        etWasteType = findViewById(R.id.etWasteType);
        etWeight = findViewById(R.id.etWeight);
        etLocation = findViewById(R.id.etLocation);
        btnCaptureImage = findViewById(R.id.btnCaptureImage);
        btnSell = findViewById(R.id.btnSell);
        imgWastePreview = findViewById(R.id.imgWastePreview);

        db = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        btnSell.setOnClickListener(v -> {
            String wasteType = etWasteType.getText().toString();
            String weight = etWeight.getText().toString();
            String location = etLocation.getText().toString();
            String userEmail = sessionManager.getUserEmail();

            if (wasteType.isEmpty() || weight.isEmpty() || location.isEmpty()) {
                Toast.makeText(SellWasteActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                db.insertSellWaste(userEmail, wasteType, weight, location);
                Toast.makeText(SellWasteActivity.this, "Waste listed successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
