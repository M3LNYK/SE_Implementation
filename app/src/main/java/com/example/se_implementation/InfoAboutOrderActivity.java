package com.example.se_implementation;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class InfoAboutOrderActivity extends AppCompatActivity {
    private PartsViewModel partsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_about_order);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_parts_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

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