package com.example.se_implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
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

        Button buttonDeadline = findViewById(R.id.button_deadline);
        buttonDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoAboutOrderActivity.this, DeadlineActivity.class);
                startActivity(intent);
            }
        });

        Button buttonPersonalNote = findViewById(R.id.button_personal_note);
        buttonPersonalNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoAboutOrderActivity.this, PersonalNoteActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_parts_info);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final PartsAdapter adapter = new PartsAdapter();
        recyclerView.setAdapter(adapter);

        partsViewModel = ViewModelProviders.of(this).get(PartsViewModel.class);
        partsViewModel.getAllParts().observe(this, new Observer<List<Part>>() {
            @Override
            public void onChanged(List<Part> parts) {
                adapter.setParts(parts);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                partsViewModel.delete(adapter.getPartAt(viewHolder.getAdapterPosition()));
                Toast.makeText(InfoAboutOrderActivity.this, "This part deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_part_menu, menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_parts:
                partsViewModel.deleteAllParts();
                Toast.makeText(this, "All parts were deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
