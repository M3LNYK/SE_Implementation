package com.example.se_implementation;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPartActivity extends AppCompatActivity {

    private EditText idEditText;
    private EditText nameEditText;
    private EditText categoryEditText;
    private EditText producerEditText;

    private String id;
    private String name;
    private String category;
    private String producer;

    private String work_id;
    private OrderDetailsDB dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_part);

        dataBase = new OrderDetailsDB(this);

        idEditText = (EditText) findViewById(R.id.idAdd);
        nameEditText = (EditText) findViewById(R.id.nameAdd);
        categoryEditText = (EditText) findViewById(R.id.categoryAdd);
        producerEditText = (EditText) findViewById(R.id.producerAdd);

        Bundle b = getIntent().getExtras();
        if(b != null)
        {
            work_id = b.getString("work");
        }

        Button addButton = (Button) findViewById(R.id.buttonAddPart);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = (idEditText.getText().toString().equals(""))? null : idEditText.getText().toString();
                name = (nameEditText.getText().toString().equals(""))? null : nameEditText.getText().toString();
                category = (categoryEditText.getText().toString().equals(""))? null : categoryEditText.getText().toString();
                producer = (producerEditText.getText().toString().equals(""))? null : producerEditText.getText().toString();
                findId();

                if (dataBase.addPart(id, name, category, producer, work_id))
                {
//                    Toast.makeText(AddPartActivity.this, "Part Added", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(AddPartActivity.this, Parts.class);
//                    Bundle b = new Bundle();
//                    b.putString("work", work_id);
//                    intent.putExtras(b);
//                    startActivity(intent);
                }
                else
                if (id == null && name == null)
                    Toast.makeText(AddPartActivity.this, "ID and part's name cannot be empty", Toast.LENGTH_SHORT).show();
                else if (id == null)
                    Toast.makeText(AddPartActivity.this, "ID cannot be empty", Toast.LENGTH_SHORT).show();
                else if (name == null)
                    Toast.makeText(AddPartActivity.this, "Part's name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findId(){
        Cursor cursor = dataBase.getAllParts();
        Part part;
        part = new Part(
            cursor.getString(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            cursor.getString(4)
        );
    }
}
