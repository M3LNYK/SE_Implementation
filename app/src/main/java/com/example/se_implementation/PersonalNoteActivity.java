package com.example.se_implementation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalNoteActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private Button modifyB;
    private Button addChangesB;
    private Switch switch2;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NOTE = "note";
    public static final String SWITCH2 = "switch2";

    private String note;
    private boolean switchOnOff;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_note);

        textView = (TextView) findViewById(R.id.textView_note);
        editText = (EditText) findViewById(R.id.editTextNote);
        modifyB = (Button) findViewById(R.id.buttonModifyNote);
        addChangesB = (Button) findViewById(R.id.buttonAddNote);
        switch2 = (Switch) findViewById(R.id.switch2);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_part);
        setTitle("Personal note");

        modifyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(editText.getText().toString());
            }
        });
        addChangesB.setOnClickListener(new View.OnClickListener() {
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

        editor.putString(NOTE, textView.getText().toString());
        editor.putBoolean(SWITCH2, switch2.isChecked());

        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        note = sharedPreferences.getString(NOTE, " ");
        switchOnOff = sharedPreferences.getBoolean(SWITCH2,  false);
    }
    public void updateViews() {
        textView.setText(note);
        switch2.setChecked(switchOnOff);
    }
}
