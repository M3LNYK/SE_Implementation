package com.example.se_implementation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddAPartActivity extends AppCompatActivity {
    public static final String EXTRA_NAME =
            "com.example.se_implementation.EXTRA_NAME";
    public static final String EXTRA_CATEGORY =
            "com.example.se_implementation.EXTRA_CATEGORY";
    public static final String EXTRA_PRODUCER =
            "com.example.se_implementation.EXTRA_PRODUCER";

    private EditText editTextName;
    private EditText editTextCategory;
    private EditText editTextProducer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_a_part);

        editTextName = findViewById(R.id.edit_text_part_name);
        editTextCategory = findViewById(R.id.edit_text_part_category);
        editTextProducer = findViewById(R.id.edit_text_part_producer);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_part);
        setTitle("Add part");
    }

    private void savePart() {
        String name = editTextName.getText().toString();
        String category = editTextCategory.getText().toString();
        String producer = editTextProducer.getText().toString();

        if(name.trim().isEmpty() || category.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a part's name and a category", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_CATEGORY, category);
        data.putExtra(EXTRA_PRODUCER, producer);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_part_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_part:
                savePart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}