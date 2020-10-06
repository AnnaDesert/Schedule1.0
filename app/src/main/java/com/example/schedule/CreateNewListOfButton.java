package com.example.schedule;

import android.content.Intent;
import android.util.Log;

import java.net.URL;
import java.util.ArrayList;

import static com.example.schedule.utils.NetworkUtils.generateURL;

public class CreateNewListOfButton {
    static String gr = null;
    static URL[] url_adress = new URL[3];


    public static int CreateButton( String one) {
        switch (MainActivity.t){
            case 0: gr = "divisionlistforstuds"; one = ""; MainActivity.t++; break;
            case 1: gr = "kurslist";  MainActivity.three = one; MainActivity.three_group = MainActivity.three;  MainActivity.t++; break;
            case 2: gr = "grouplist"; MainActivity.three += one; one = MainActivity.three; MainActivity.t++; break;
            case 3: MainActivity.three = ""; MainActivity.t = 0; break;
        }
        if(MainActivity.t > 0) {
            URL generatedUrl = generateURL(one, gr); // Генерация url
            Log.i("myTag","Add " + url_adress[MainActivity.t-1] + " three = " + MainActivity.three + "; one = " + one + " t = " + MainActivity.t);
            url_adress[MainActivity.t-1] = generatedUrl;
            new MainActivity.QueryTask().execute(generatedUrl); //Обращение к классу выше//
        }
        else {
            Intent intent = new Intent( MainActivity.GGManager.getContext(), Table.class);
            intent.putExtra("GROUPURL", one);
            MainActivity.GGManager.getContext().startActivity(intent);
        }

        return MainActivity.t;// Возвращает счётчик НУЖНО ЛИ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????:?аааааааааааааааааааААААААААААААААААААААА
    }
}
