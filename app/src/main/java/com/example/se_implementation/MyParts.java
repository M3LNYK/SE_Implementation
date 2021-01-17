package com.example.se_implementation;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyParts extends AppCompatActivity {
    private OrderDetailsPartsViewModel orderDetailsPartsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewStatistics);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        OrderDetailsPartsAdapter adapter = new OrderDetailsPartsAdapter();
        recyclerView.setAdapter(adapter);

        orderDetailsPartsViewModel = ViewModelProviders.of(this).get(OrderDetailsPartsViewModel.class);
        orderDetailsPartsViewModel.getAllParts().observe(this, new Observer<List<OrderDetailsParts>>() {
            @Override
            public void onChanged(List<OrderDetailsParts> orderDetailsParts) {
                adapter.setParts(orderDetailsParts);
            }
        });
    }
}
