package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import static com.example.schedule.utils.NetworkUtils.generateURL;
import static com.example.schedule.utils.NetworkUtils.getResponseFromURL;

public class MainActivity extends AppCompatActivity {
    private TextView text;

    class QueryTask extends AsyncTask<URL, Void, String> {
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
            String Infoid = null;
            String Infoname = null;
            String id = null;
            String name = null;

// повышение универсальности
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

            String Inforesponse= "";

            try {
                JSONArray jsonArray = new JSONArray(response);// Получение массива

                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject Info = jsonArray.getJSONObject(i);// Получение iого обьекта

                    Infoid = Info.getString(id); // получение id
                    Infoname = Info.getString(name);// получение информации или имени

                    Inforesponse += "Id" + Infoid + "Name" + Infoname + "\n";// Строка с парсированными данными
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            text.setText(Inforesponse);
        }
    }
 

    //////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text_url);
        // Проверка вывода URL
        String one = "154/", two = "2/", gr = null;
        int t = 2;

        switch (t){
            case 0: gr = "divisionlistforstuds"; break;
            case 1: gr = "kurslist"; break;
            case 2: gr = "grouplist"; break;
            case 3: gr = "///1597017600865/printschedule"; break;
        }

        URL generatedUrl = generateURL(one, two, gr);

        new QueryTask().execute(generatedUrl); //Обращение к классу выше

        //
    }
}