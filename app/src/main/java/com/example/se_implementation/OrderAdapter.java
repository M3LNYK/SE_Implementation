package com.example.se_implementation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {
    private List<Order> orders = new ArrayList<>();


    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new OrderHolder(itemView);
    }

    //Method where everything is put on it's place
    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {
        Order currentOrder = orders.get(position);
        holder.textViewIdNumber.setText(String.valueOf(currentOrder.getId()));
        holder.textViewCarBrand.setText(currentOrder.getCarBrand());
        holder.textViewCarModel.setText(currentOrder.getCarModel());
        holder.textViewDescription.setText(currentOrder.getDescription());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setOrders(List<Order> orders){
        this.orders = orders;
        notifyDataSetChanged();
    }

    class OrderHolder extends RecyclerView.ViewHolder{
        private TextView textViewIdNumber;
        private TextView textViewCarBrand;
        private TextView textViewCarModel;
        private TextView textViewDescription;

        public OrderHolder(@NonNull View itemView) {
            super(itemView);
            textViewIdNumber = itemView.findViewById(R.id.text_view_id_number);
            textViewCarBrand = itemView.findViewById(R.id.text_view_car_brand);
            textViewCarModel = itemView.findViewById(R.id.text_view_car_model);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
        }
    }
}
