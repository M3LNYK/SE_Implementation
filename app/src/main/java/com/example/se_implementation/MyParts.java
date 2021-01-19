package com.example.se_implementation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyParts extends AppCompatActivity {
    private PartsViewModel partsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_about_order);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_parts_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PartsAdapter adapter = new PartsAdapter();
        recyclerView.setAdapter(adapter);

        partsViewModel = ViewModelProviders.of(this).get(PartsViewModel.class);
        partsViewModel.getAllParts().observe(this, new Observer<List<Parts>>() {
            @Override
            public void onChanged(List<Parts> parts) {
                adapter.setParts(parts);
            }
        });
    }
}
