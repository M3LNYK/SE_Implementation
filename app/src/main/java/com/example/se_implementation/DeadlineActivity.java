package com.example.se_implementation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DeadlineActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button changeTextB;
    private Button saveTextB;
    private Switch switch1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DEADLINE = "deadline";
    public static final String SWITCH1 = "switch1";

    private String deadline;
    private boolean switchOnOff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline);

        textView = (TextView) findViewById(R.id.textViewDate);
        editText = (EditText) findViewById(R.id.editTextDate);
        changeTextB = (Button) findViewById(R.id.buttonChange);
        saveTextB = (Button) findViewById(R.id.buttonSaveDate);
        switch1 = (Switch) findViewById(R.id.switch_date_changed);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_deadline);
        setTitle("Deadline");

        changeTextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());
            }
        });

        saveTextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        loadData();
        updateViews();

    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(DEADLINE, textView.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        deadline = sharedPreferences.getString(DEADLINE, "Not set");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateViews() {
        textView.setText(deadline);
        switch1.setChecked(switchOnOff);
    }
}
