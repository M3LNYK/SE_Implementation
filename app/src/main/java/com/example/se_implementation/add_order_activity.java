package com.example.se_implementation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class add_order_activity extends AppCompatActivity {
    public static final String EXTR

    private EditText editTextCarBrand;
    private EditText editTextCarModel;
    private EditText editTextProblemDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_activity);

        editTextCarBrand = findViewById(R.id.edit_text_car_brand);
        editTextCarModel = findViewById(R.id.edit_text_car_model);
        editTextProblemDesc = findViewById(R.id.edit_text_problem_desc);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_order);
        setTitle("Add Order");


    }

    private void saveOrder(){
        String carBrand = editTextCarBrand.getText().toString();
        String carModel = editTextCarModel.getText().toString();
        String problemDesc = editTextProblemDesc.getText().toString();

        //Check if not empty
        if(carBrand.trim().isEmpty() || carModel.trim().isEmpty() || problemDesc.trim().isEmpty()) {
            Toast.makeText(this, "Please insert correct info!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    //Create save menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_order_menu, menu);
        return true;
    }

    //handle clicks on menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_order:
                saveOrder();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
