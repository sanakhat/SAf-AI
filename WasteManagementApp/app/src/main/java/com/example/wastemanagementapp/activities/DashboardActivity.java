package com.example.wastemanagementapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.wastemanagementapp.R;
import com.example.wastemanagementapp.database.DatabaseHelper;
import com.example.wastemanagementapp.utils.SessionManager;

public class DashboardActivity extends AppCompatActivity {

    private TextView txtWelcome, txtTotalWaste, txtTotalEarnings;
    private Button btnSellWaste, btnWallet, btnSellHistory;
    private DatabaseHelper db;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtWelcome = findViewById(R.id.txtUserName);
        txtTotalWaste = findViewById(R.id.btnProfile);
        txtTotalEarnings = findViewById(R.id.btnWallet);
        btnSellWaste = findViewById(R.id.btnSellWaste);
        btnWallet = findViewById(R.id.btnWallet);
        btnSellHistory = findViewById(R.id.btnSellHistory);

        db = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);
        String userEmail = sessionManager.getUserEmail();

        txtWelcome.setText("Welcome, " + db.getUserName(userEmail));
        txtTotalWaste.setText("Total Waste Sold: " + db.getTotalWaste(userEmail) + " kg");
        txtTotalEarnings.setText("Total Earnings: $" + db.getTotalEarnings(userEmail));

        btnSellWaste.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, SellWasteActivity.class)));
        btnWallet.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, WalletActivity.class)));
        btnSellHistory.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, SellHistoryActivity.class)));
    }
}
