package com.example.schedule;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.schedule.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    static RecyclerView listButton;
    public  static int  t = 0;
    static String three = "";
    static String three_group = "";

    ///////////////////////////////////////////////////////////////////////////////////////////////
    static class QueryTask extends AsyncTask<URL, Void, String> {
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

        //////////////////// Вывод данных в Вью
        @Override
        protected  void  onPostExecute(String response){
            String id = null;
            String name = null;

// повышение универсальности для парсирования
            if( response.indexOf("idDivision") != - 1) {
                id = "idDivision";
                name = "titleDivision";
            } else  if( response.indexOf("kurs") != - 1) {
                id = "kurs";
                name = "kurs";
            } else if( response.indexOf("idgruop") != - 1) {
                id = "idgruop";
                name = "title";
            }

            String Infoid = null;
            String Infoname = null;
            int IntInfoid = 0;
            ArrayList<Catalogue> catalogue = new ArrayList<Catalogue>();

            try {
                JSONArray jsonArray = new JSONArray(response);// Получение массива
                Log.i("myTag","Size response: " + jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject Info = jsonArray.getJSONObject(i);// Получение iого обьекта

                    Infoid = Info.getString(id); // получение id
                    IntInfoid = Integer.parseInt(Infoid); // получение id в числовом виде
                    Infoname = Info.getString(name);// получение информации или имени

                    if(Infoid.equals(Infoname)){ Infoname +=" курс";} // Для курса

                    catalogue.add(new Catalogue(Infoname, IntInfoid));// Добавление в Каталог
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ListAdapter listAdapter = new ListAdapter(catalogue);// Создание Адаптера
            listButton.setAdapter(listAdapter);// Подключение Адаптера


        }
    }
    
    //////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GGManager.setContext(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        listButton = findViewById(R.id.list_button);// Нахождение RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this); // Отображение Вью
        listButton.setLayoutManager(layoutManager); // Подключение Менеджера
        listButton.setHasFixedSize(true);// Добавление фиксированной длинны

        String one = "";


        t = CreateNewListOfButton.CreateButton( one);
        // Определение констант для генерации url

    }
    public static class GGManager {
        private static Context context;

        public static Context getContext() {
            return context;
        }

        public static void setContext(Context context) {
            GGManager.context = context;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (t > 1){
                    three = three_group;
                    t--;
                    new QueryTask().execute(CreateNewListOfButton.url_adress[t-1]);
                } else
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