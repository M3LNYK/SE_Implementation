package com.example.se_implementation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsPartsAdapter extends RecyclerView.Adapter<OrderDetailsPartsAdapter.PartsHolder> {
    private List<OrderDetailsParts> partsList = new ArrayList<>();

    @NonNull
    @Override
    public PartsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View partView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.part_item, parent, false);
        return new PartsHolder(partView);
    }

    @Override
    public void onBindViewHolder(@NonNull PartsHolder holder, int position) {
        OrderDetailsParts currentPart = partsList.get(position);
        holder.textViewId.setText(currentPart.getId());
        holder.textViewName.setText(currentPart.getPart_name());
        holder.textViewCategory.setText(currentPart.getCategory());
        holder.textViewProducer.setText(currentPart.getProducer());
    }

    @Override
    public int getItemCount() {
        return partsList.size();
    }

    public void setParts(List<OrderDetailsParts> orderDetailsParts){
        this.partsList = orderDetailsParts;
        notifyDataSetChanged();
    }

    class PartsHolder extends RecyclerView.ViewHolder {
        private TextView textViewId;
        private TextView textViewName;
        private TextView textViewCategory;
        private TextView textViewProducer;

         public PartsHolder(@NonNull View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.text_view_id_part);
            textViewName = itemView.findViewById(R.id.text_view_name_part);
            textViewCategory = itemView.findViewById(R.id.text_view_category_part);
            textViewProducer = itemView.findViewById(R.id.text_view_producer_part);
        }
    }
}
