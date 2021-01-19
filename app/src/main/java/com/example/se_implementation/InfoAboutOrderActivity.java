package com.example.se_implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class InfoAboutOrderActivity extends AppCompatActivity {
    public static final int ADD_PART_REQUEST = 1;

    private PartsViewModel partsViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_about_order);

        FloatingActionButton buttonAddPart = findViewById(R.id.button_add_part);
        buttonAddPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoAboutOrderActivity.this, AddAPartActivity.class);
                startActivityForResult(intent, ADD_PART_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_parts_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        PartsAdapter adapter = new PartsAdapter();
        recyclerView.setAdapter(adapter);

        partsViewModel = ViewModelProviders.of(this).get(PartsViewModel.class);
        partsViewModel.getAllParts().observe(this, new Observer<List<Part>>() {
            @Override
            public void onChanged(List<Part> parts) {
                adapter.setParts(parts);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==  ADD_PART_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            String name = data.getStringExtra(AddAPartActivity.EXTRA_NAME);
            String category = data.getStringExtra(AddAPartActivity.EXTRA_CATEGORY);
            String producer = data.getStringExtra(AddAPartActivity.EXTRA_PRODUCER);

            Part part = new Part(name, category, producer);
            partsViewModel.insert(part);

            Toast.makeText(this, "Part saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Part wasn't saved", Toast.LENGTH_SHORT).show();
        }
    }
}
