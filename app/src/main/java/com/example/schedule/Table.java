package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Table extends AppCompatActivity {
TextView text;
String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        text = findViewById(R.id.textV);
        Bundle extras = getIntent().getExtras();
        str = extras.getString("STRING_I_NEED"); // get id group
        text.setText(str); // enter id group for check it out

    }
}