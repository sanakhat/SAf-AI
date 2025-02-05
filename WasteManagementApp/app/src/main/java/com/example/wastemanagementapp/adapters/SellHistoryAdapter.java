package com.example.wastemanagementapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wastemanagementapp.R;
import com.example.wastemanagementapp.models.SellHistory;
import java.util.List;

public class SellHistoryAdapter extends RecyclerView.Adapter<SellHistoryAdapter.ViewHolder> {

    private Context context;
    private List<SellHistory> sellHistoryList;

    public SellHistoryAdapter(Context context, List<SellHistory> sellHistoryList) {
        this.context = context;
        this.sellHistoryList = sellHistoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_sell_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SellHistory sellHistory = sellHistoryList.get(position);
        holder.txtWasteType.setText("Waste Type: " + sellHistory.getWasteType());
        holder.txtWeight.setText("Weight: " + sellHistory.getWeight() + " kg");
        holder.txtLocation.setText("Location: " + sellHistory.getLocation());
        holder.txtDate.setText("Date: " + sellHistory.getDate());
    }

    @Override
    public int getItemCount() {
        return sellHistoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtWasteType, txtWeight, txtLocation, txtDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
