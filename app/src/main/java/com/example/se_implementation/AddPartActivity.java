package com.example.se_implementation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddPartActivity extends AppCompatActivity {
    private EditText editTextPartName;
    private EditText editTextPartCategory;
    private EditText editTextPartProducer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        editTextPartName = findViewById(R.id.nameAdd);
        editTextPartCategory = findViewById(R.id.categoryAdd);
        editTextPartProducer = findViewById(R.id.producerAdd);
    }
}
