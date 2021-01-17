package com.example.se_implementation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddPartActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.se_implementation.EXTRA_NAME";
    public static final String EXTRA_CATEGORY = "com.example.se_implementation.EXTRA_CATEGORY";
    public static final String EXTRA_PRODUCER = "com.example.se_implementation.EXTRA_PRODUCER";

    private EditText editTextPartName;
    private EditText editTextPartCategory;
    private EditText editTextPartProducer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        Button add = (Button) findViewById(R.id.buttonAddPart);
        add.setOnClickListener(v -> {
            savePart();
        });

        editTextPartName = findViewById(R.id.nameAdd);
        editTextPartCategory = findViewById(R.id.categoryAdd);
        editTextPartProducer = findViewById(R.id.producerAdd);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_part);
        setTitle("Add part");
    }

    private void savePart() {
        String name = editTextPartName.getText().toString();
        String category = editTextPartCategory.getText().toString();
        String producer = editTextPartProducer.getText().toString();

        if(name.trim().isEmpty() || category.trim().isEmpty() || producer.trim().isEmpty()){
            Toast.makeText(this, "Fill in all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_CATEGORY, category);
        data.putExtra(EXTRA_PRODUCER, producer);

        setResult(RESULT_OK, data);
        finish();
    }
}
