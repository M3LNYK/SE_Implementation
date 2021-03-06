package com.example.se_implementation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PartsAdapter extends RecyclerView.Adapter<PartsAdapter.PartsHolder> {
    private List<Part> parts = new ArrayList<>();

    @NonNull
    @Override
    public PartsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View partView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.part_item, parent, false);
        return new PartsHolder(partView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartsHolder holder, int position) {
        Part currentPart = parts.get(position);
        holder.textViewName.setText(currentPart.getPart_name());
        holder.textViewCategory.setText(currentPart.getCategory());
        holder.textViewProducer.setText(currentPart.getProducer());
    }

    @Override
    public int getItemCount() {
        return parts.size();
    }

    public void setParts(List<Part> parts){
        this.parts = parts;
        notifyDataSetChanged();
    }

    public Part getPartAt(int position) {
        return parts.get(position);
    }

    class PartsHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewCategory;
        private TextView textViewProducer;

         public PartsHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name_part);
            textViewCategory = itemView.findViewById(R.id.text_view_category_part);
            textViewProducer = itemView.findViewById(R.id.text_view_producer_part);
        }
    }
}
