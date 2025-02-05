package com.example.wastemanagementapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "WasteManagement.db";
    private static final int DATABASE_VERSION = 2; // Increment for database changes

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT UNIQUE, password TEXT, balance REAL DEFAULT 0.0, phone TEXT)"); // Added phone column here initially
        db.execSQL("CREATE TABLE sell_history (id INTEGER PRIMARY KEY AUTOINCREMENT, user_email TEXT, waste_type TEXT, weight REAL, location TEXT, date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
        db.execSQL("CREATE TABLE transactions (id INTEGER PRIMARY KEY AUTOINCREMENT, user_email TEXT, amount REAL, date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE users ADD COLUMN phone TEXT"); // Add phone column if upgrading from version 1
        }
        // Add more 'if' blocks for future upgrades
    }

    public boolean addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        long result = db.insert("users", null, values);
        db.close();
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"email"}, "email=? AND password=?",
                new String[]{email, password}, null, null, null);
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return userExists;
    }

    public void insertSellWaste(String userEmail, String wasteType, String weight, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user_email", userEmail);
        values.put("waste_type", wasteType);
        values.put("weight", weight);  // Store weight as a double
        values.put("location", location);
        db.insert("sell_history", null, values);
        db.close();
    }

    public List<String> getTransactions(String userEmail) {
        List<String> transactions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT amount, date FROM transactions WHERE user_email = ?", new String[]{userEmail});
        if (cursor.moveToFirst()) {
            do {
                transactions.add("$" + cursor.getDouble(0) + " - " + cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return transactions;
    }

    public double getUserBalance(String userEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT balance FROM users WHERE email = ?", new String[]{userEmail});
        double balance = 0.0;
        if (cursor.moveToFirst()) {
            balance = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return balance;
    }

    public String getUserName(String userEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", new String[]{"name"}, "email=?", new String[]{userEmail}, null, null, null);
        String name = "";
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        }
        cursor.close();
        db.close();
        return name;
    }

    public double getTotalWaste(String userEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(weight) FROM sell_history WHERE user_email=?", new String[]{userEmail});
        double totalWaste = 0.0;
        if (cursor.moveToFirst()) {
            totalWaste = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return totalWaste;
    }

    public double getTotalEarnings(String userEmail) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(weight) * 5 FROM sell_history WHERE user_email=?", new String[]{userEmail}); // Example: 1kg = $5
        double totalEarnings = 0.0;
        if (cursor.moveToFirst()) {
            totalEarnings = cursor.getDouble(0);
        }
        cursor.close();
        db.close();
        return totalEarnings;
    }


    public List<SellHistory> getSellHistory(String userEmail) {
        List<SellHistory> sellHistoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("sell_history", null, "user_email=?", new String[]{userEmail}, null, null, "date DESC"); // Order by date

        if (cursor.moveToFirst()) {
            do {
                SellHistory sellHistory = new SellHistory();
                sellHistory.setWasteType(cursor.getString(cursor.getColumnIndexOrThrow("waste_type")));
                sellHistory.setWeight(cursor.getDouble(cursor.getColumnIndexOrThrow("weight")));
                sellHistory.setLocation(cursor.getString(cursor.getColumnIndexOrThrow("location")));
                sellHistory.setDate(cursor.getString(cursor.getColumnIndexOrThrow("date"))); // Assuming date is stored as string
                sellHistoryList.add(sellHistory);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return sellHistoryList;
    }




    public class SellHistory { // Inner class for SellHistory model
        private String wasteType;
        private double weight;
        private String location;
        private String date;


        public String getWasteType() {
            return wasteType;
        }

        public void setWasteType(String wasteType) {
            this.wasteType = wasteType;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}