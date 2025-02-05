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

public class SellHistoryActivity extends AppCompatActivity {

    private ListView listSellHistory;
    private TextView txtNoHistory;
    private DatabaseHelper db;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_history);

        listSellHistory = findViewById(R.id.listSellHistory);
       /* txtNoHistory = findViewById(R.id.txtNoHistory);
        db = new DatabaseHelper(this);
        sessionManager = new SessionManager(this);

        String userEmail = sessionManager.getUserEmail();
        List<SellHistory> historyList = db.getTransactions(userEmail);

        if (historyList.isEmpty()) {
            txtNoHistory.setText("No sell history available.");
            txtNoHistory.setVisibility(View.VISIBLE);
        } else {
            SellHistoryAdapter adapter = new SellHistoryAdapter(this, historyList);
            listSellHistory.setAdapter(adapter);
        }*/
    }
}
