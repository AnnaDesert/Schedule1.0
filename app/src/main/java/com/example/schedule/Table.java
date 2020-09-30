package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.schedule.utils.NetworkUtils.generateURL;
import static com.example.schedule.utils.NetworkUtils.getResponseFromURL;

public class Table extends AppCompatActivity {
static TextView text;
String groupurl;

    static class SceduleTask extends AsyncTask<URL, Void, String> {
        // Вызов на получение данных из потока по указанному url
        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }
        @Override
        protected  void  onPostExecute(String response){
            String subgroup = null;
            String name_lesson = null;
            String type_lesson = null;
            String building = null;
            String room = null;
            String Family = null;
            String Name = null;
            String Secondname = null;
            int number;
            int day;

            ArrayList<Lesson> Week = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(response);

                for(int i = 0; i < jsonObject.length(); i++) {
                    JSONObject Info = jsonObject.getJSONObject(String.valueOf(i));

                    subgroup = Info.getString("NumberSubGruop"); // получение id
                    name_lesson = Info.getString("TitleSubject");
                    type_lesson = Info.getString("TypeLesson");
                    number = Info.getInt("NumberLesson");
                    day = Info.getInt("DayWeek");
                    building = Info.getString("Korpus");
                    room = Info.getString("NumberRoom");
                    Family = Info.getString("Family");
                    Name = Info.getString("Name");
                    Secondname = Info.getString("SecondName");

                    Week.add(number - 1, new Lesson(subgroup,  name_lesson,
                            type_lesson,  building,
                            room,  Family,  Name,  Secondname, day, number));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Table.text.setText(Week.get(7).getFull_name_lesson());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        text = findViewById(R.id.textV);
        Bundle extras = getIntent().getExtras();
        groupurl = extras.getString("GROUPURL"); // get id group
        //text.setText(groupurl); // enter id group for check it out
        URL generatedUrl = generateURL("/" + groupurl + "//" + DateMonday(), "printschedule");
        //new MainActivity.QueryTask().execute(generatedUrl);
        new SceduleTask().execute(generatedUrl);
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