package com.example.schedule;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

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
//Log.i("myTag","Connect " + urls[0]);
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
            String special = null;
            int number;
            int day;


            ArrayList<Lesson> Week = new ArrayList<>();

            if(response.equals("[]"))   { Table.text.setText("Расписания нет"); }
            else {
                try {

                    JSONObject jsonObject = new JSONObject(response);

//Log.i("myTag","Size response: " + jsonObject.length());

                    for (int i = 0; i < jsonObject.length(); i++) {
                        JSONObject Info = jsonObject.getJSONObject(String.valueOf(i));

                        subgroup = Info.getString("NumberSubGruop"); // получение id
                        name_lesson = Info.getString("TitleSubject");
                        special = Info.getString("special");
                        type_lesson = Info.getString("TypeLesson");
                        number = Info.getInt("NumberLesson");
                        day = Info.getInt("DayWeek");
                        building = Info.getString("Korpus");
                        room = Info.getString("NumberRoom");
                        Family = Info.getString("Family");
                        Name = Info.getString("Name");
                        Secondname = Info.getString("SecondName");
//Log.i("myTag","Parsing end \nsubgroup" + subgroup + "\nname " + name_lesson + "\nspecial " + special + "\ntype " + type_lesson + "\nnumber " + number + "\nday " + day + "\nkorpus " + building + "\nroom " + room);
                            Week.add(i, new Lesson(subgroup, name_lesson, special,
                                    type_lesson, building,
                                    room, Family, Name, Secondname, day, number));
//Log.i("myTag","Size Week: " + Week.size() + "\n " + Week.get(i).getFull_name_lesson() + "\n------------------------");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
//Log.i("myTag!!!!!!!!!!!!!!!","Size Week: " + Week.size());
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        ViewPager viewPager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        Log.i("myTag","Add ViewPage and Tab");

        ActionBar actionBar =getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        groupurl = extras.getString("GROUPURL"); // get id group
        //text.setText(groupurl); // enter id group for check it out
        URL generatedUrl = generateURL("/" + groupurl + "//" + DateMonday(), "printschedule");
        //new MainActivity.QueryTask().execute(generatedUrl);
        new SceduleTask().execute(generatedUrl);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),Table.this));
        tabLayout.setupWithViewPager(viewPager);
        Log.i("myTag","Add ViewPage inside Tab");
    }



    ////////////////////////////////////////////
    public static String DateMonday(){
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MainActivity.t = 3;
//Log.i("myTag","Exit Table t = " + MainActivity.t + " adress " + CreateNewListOfButton.url_adress[2]);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onBackPressed() {
        finishAffinity();
        return;
    }

}