package com.example.schedule;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ClipData;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
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
static ArrayList<Lesson> Week = new ArrayList<>();

static ViewPager viewPager;
static TabLayout tabLayout;
static long timestamp;
static URL generatedUrl;

public static String Time[] = new String[] { "Время","8:30\n  -\n10:00","10:10\n  -\n11:40","12:00 \n  -\n13:30", "13:40\n  -\n15:10", "15:20\n  -\n16:50", "17:00\n  -\n18:30", "18:40\n  -\n20:10", "20:15\n  -\n21:45"};
public static Lesson Lesson_first = new Lesson("0", "", "", "", "","", "", "", "", 0, 0);
public static ArrayList<Lesson> Mnd = new ArrayList<>(1);
public static ArrayList<Lesson> Tue = new ArrayList<>(1);
public static ArrayList<Lesson> Wed = new ArrayList<>(1);
public static ArrayList<Lesson> Th = new ArrayList<>(1);
public static ArrayList<Lesson> Fri = new ArrayList<>(1);
public static ArrayList<Lesson> Sat = new ArrayList<>(1);
public static int[] sizeDay = new int[] {1,1,1,1,1,1};

    class SceduleTask extends AsyncTask<URL, Void, String> {
        // Вызов на получение данных из потока по указанному url
        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
Log.i("myTag","Connect Table " + urls[0]);
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

            if(response.equals("[]"))   { Log.i("myTag","NuLL"); }
            else {
                try {

                    JSONObject jsonObject = new JSONObject(response);

//Log.i("myTag","Size response Json: " + jsonObject.length());

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
                        switch (day){
                            case 1: Table.Mnd.set(number, new Lesson(subgroup, name_lesson, special,
                                    type_lesson, building,
                                    room, Family, Name, Secondname, day, number));
                                    sizeDay[0] = number; break;
                            case 2: Table.Tue.set(number, new Lesson(subgroup, name_lesson, special,
                                    type_lesson, building,
                                    room, Family, Name, Secondname, day, number));
                                    sizeDay[1] = number; break;
                            case 3: Table.Wed.set(number, new Lesson(subgroup, name_lesson, special,
                                    type_lesson, building,
                                    room, Family, Name, Secondname, day, number));
                                    sizeDay[2] = number; break;
                            case 4: if(Table.Th.get(number).getNumber()!=number){
                                        Table.Th.set(number, new Lesson(subgroup, name_lesson, special,
                                        type_lesson, building,
                                        room, Family, Name, Secondname, day, number));
                                    }else{
                                        Table.Th.get(number).addLesson(new Lesson(subgroup, name_lesson, special,
                                                type_lesson, building,
                                                room, Family, Name, Secondname, day, number).getFull_name_lesson());
                                    }
                                    sizeDay[3] = number; break;
                            case 5: Table.Fri.set(number, new Lesson(subgroup, name_lesson, special,
                                    type_lesson, building,
                                    room, Family, Name, Secondname, day, number));
                                    sizeDay[4] = number; break;
                            case 6: Table.Sat.set(number, new Lesson(subgroup, name_lesson, special,
                                    type_lesson, building,
                                    room, Family, Name, Secondname, day, number));
                                    sizeDay[5]++; break;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i("myTag","Size Mnd " + Table.Mnd.size());
                Log.i("myTag","Size Tue " + Table.Tue.size());
                Log.i("myTag","Size Wed " + Table.Wed.size());
                Log.i("myTag","Size Th " + Table.Th.size());
                Log.i("myTag","Size Fri " + Table.Fri.size());
                Log.i("myTag","Size Sat " + Table.Sat.size());
//Log.i("myTag!!!!!!!!!!!!!!!","Size Week: " + Week.size());
            }
            viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),Table.this));
            tabLayout.setupWithViewPager(viewPager);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        Table.GGManager.setContext(this);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.sliding_tabs);
        Log.i("myTag","Add ViewPage and Tab");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        groupurl = extras.getString("GROUPURL"); // get id group
        //text.setText(groupurl); // enter id group for check it out
        generatedUrl = generateURL("/" + groupurl + "//" + DateMonday(), "printschedule");
        //new MainActivity.QueryTask().execute(generatedUrl);
        WeekAdd();

        sizeDay = new int[] {1,1,1,1,1,1};

        new SceduleTask().execute(generatedUrl);
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

        timestamp = calendar.getTimeInMillis();

        return timestamp + "/";
    }

public static void WeekAdd(){
    for(int Les = 0; Les < 9; Les++){
        Table.Mnd.add(Lesson_first);
        Table.Tue.add(Lesson_first);
        Table.Wed.add(Lesson_first);
        Table.Th.add(Lesson_first);
        Table.Fri.add(Lesson_first);
        Table.Sat.add(Lesson_first);
    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Table.Mnd.clear();
        Table.Tue.clear();
        Table.Wed.clear();
        Table.Th.clear();
        Table.Fri.clear();
        Table.Sat.clear();
        sizeDay = new int[] {1,1,1,1,1,1};
        switch (item.getItemId()) {
            case android.R.id.home:
                MainActivity.t = 3;
                this.finish();
                break;
            case R.id.item1: timestamp-=604800000;
                generatedUrl = generateURL("/" + groupurl + "//" + timestamp + "/", "printschedule");
                WeekAdd();
                new SceduleTask().execute(generatedUrl);
                break;
            case R.id.item2: timestamp+=604800000;
                generatedUrl = generateURL("/" + groupurl + "//" + timestamp + "/", "printschedule");
                WeekAdd();
                new SceduleTask().execute(generatedUrl);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void onBackPressed() {
        finishAffinity();
        return;
    }

    public static class GGManager {
        private static Context context;

        public static Context getContext() {
            return context;
        }

        public static void setContext(Context context) {
            Table.GGManager.context = context;
        }
    }

}