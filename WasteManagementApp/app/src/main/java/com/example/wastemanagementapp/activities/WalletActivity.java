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

public class WalletActivity extends AppCompatActivity {

    private TextView txtBalance;
    private ListView listTransactions;
    private DatabaseHelper db;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        txtBalance = findViewById(R.id.txtBalance);
        listTransactions = findViewById(R.id.listTransactions);
        db = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        String userEmail = sessionManager.getUserEmail();
        double balance = db.getUserBalance(userEmail);
        txtBalance.setText("Balance: $" + balance);

        List<String> transactions = db.getTransactions(userEmail);
        if (transactions.isEmpty()) {
            txtBalance.setText("No transactions available.");
        } else { txtBalance.setText("paisa hmmmm");        }
    }
}
