package com.example.wastemanagementapp.models;

public class SellHistory {
    private String wasteType;
    private String weight;
    private String location;
    private String date;

    public SellHistory(String wasteType, String weight, String location, String date) {
        this.wasteType = wasteType;
        this.weight = weight;
        this.location = location;
        this.date = date;
    }

    public String getWasteType() {
        return wasteType;
    }

    public String getWeight() {
        return weight;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
