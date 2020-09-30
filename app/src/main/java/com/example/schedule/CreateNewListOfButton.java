package com.example.schedule;

import android.content.Intent;

import java.net.URL;

import static com.example.schedule.utils.NetworkUtils.generateURL;

public class CreateNewListOfButton {
    static String gr = null;

    public static int CreateButton( String one) {
        switch (MainActivity.t){
            case 0: gr = "divisionlistforstuds"; one = ""; MainActivity.t++; break;
            case 1: gr = "kurslist";  MainActivity.three = one;  MainActivity.t++; break;
            case 2: gr = "grouplist"; MainActivity.three += one; one = MainActivity.three; MainActivity.t++; break;
            case 3: gr = "///1597017600865/printschedule"; MainActivity.three = ""; MainActivity.t = 0;
                Intent intent = new Intent( MainActivity.GGManager.getContext(), Table.class);
                intent.putExtra("GROUPURL", one);
                MainActivity.GGManager.getContext().startActivity(intent); break;
        }
       // System.out.println("three  " + MainActivity.three);

        URL generatedUrl = generateURL(one, gr); // Генерация url
        new MainActivity.QueryTask().execute(generatedUrl); //Обращение к классу выше

        return MainActivity.t;// Возвращает счётчик НУЖНО ЛИ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????:?аааааааааааааааааааААААААААААААААААААААА
    }
}
