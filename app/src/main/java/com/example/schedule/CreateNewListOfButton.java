package com.example.schedule;

import android.content.Intent;

import java.net.URL;
import java.util.ArrayList;

import static com.example.schedule.utils.NetworkUtils.generateURL;

public class CreateNewListOfButton {
    static String gr = null;



    public static int CreateButton( String one) {
        switch (MainActivity.t){
            case 0: gr = "divisionlistforstuds"; one = ""; MainActivity.t++; break;
            case 1: gr = "kurslist";  MainActivity.three = one;  MainActivity.t++; break;
            case 2: gr = "grouplist"; MainActivity.three += one; one = MainActivity.three; MainActivity.t++; break;
            case 3: MainActivity.three = ""; MainActivity.t = 0; break;
        }
        if(MainActivity.t > 0) {
            URL generatedUrl = generateURL(one, gr); // Генерация url
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
