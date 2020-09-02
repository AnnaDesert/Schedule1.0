package com.example.schedule;

import java.net.URL;

import static com.example.schedule.utils.NetworkUtils.generateURL;

public class CreateNewListOfButton {
    public static int CreateButton(int t, String one, String two) {
        String gr = null;

        switch (t){
            case 0: gr = "divisionlistforstuds"; one = ""; t++; break;
            case 1: gr = "kurslist";  t++; break;
            case 2: gr = "grouplist"; one += two; t++; break;
            case 3: gr = "///1597017600865/printschedule"; one = ""; t = 0; break;
        }

        URL generatedUrl = generateURL(one, gr); // Генерация url
        new MainActivity.QueryTask().execute(generatedUrl); //Обращение к классу выше

        return t;// Возвращает счётчик
    }
}
