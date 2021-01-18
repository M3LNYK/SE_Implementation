package com.example.se_implementation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MyOrders extends AppCompatActivity {
    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        FloatingActionButton buttonAddOrder = findViewById(R.id.button_add_order);
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyOrders.this, "Ability to add orders", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        OrderAdapter adapter = new OrderAdapter();
        recyclerView.setAdapter(adapter);

        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        orderViewModel.getAllOrders().observe(this, orders -> {
            //Update RecyclerView
            //Toast.makeText(MyOrders.this,  "onChanged", Toast.LENGTH_SHORT).show();
            adapter.setOrders(orders);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                orderViewModel.delete(adapter.getOrderAtPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(MyOrders.this, "Order Completed", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void goToStats(View view) {
        Intent intent = new Intent(MyOrders.this, OrderDetailsActivity.class);
        startActivity(intent);
    }

}
