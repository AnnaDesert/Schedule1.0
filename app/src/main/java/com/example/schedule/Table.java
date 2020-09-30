package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;


import java.util.Calendar;
import java.util.Date;


public class Table extends AppCompatActivity {
TextView text;
String groupurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        text = findViewById(R.id.textV);
        Bundle extras = getIntent().getExtras();
        groupurl = extras.getString("GROUPURL"); // get id group

        text.setText("fhfjdfhbd");
        //text.setText("/" + groupurl + "//" + DateMonday() + "/printschedule");
       // URL generatedUrl = generateURL(, ""); // Генерация url
       // new ScheduleTask().execute(generatedUrl); //Обращение к классу выше
    }

    public String DateMonday(){
        Date nowdate = new Date();// текущая дата

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowdate);
        int day = calendar.get(Calendar.DAY_OF_WEEK);// номер дня недели
        calendar.add(Calendar.DAY_OF_WEEK, -(day-2));

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long timestamp = calendar.getTimeInMillis();

        return timestamp + "/";
    }


}