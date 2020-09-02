package com.example.schedule.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String SCHEDULE_URL = "http://oreluniver.ru/schedule/";
// Создаёт URL из трёх переменных. Перая и втроая - id, третья постоянная.
    public static URL generateURL(String userIdone,  String urlconst){
        Uri builtUri = Uri.parse(SCHEDULE_URL + userIdone + urlconst);

        URL url = null;
        try {

            url = new URL(builtUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    ///////////////////////////////////////////
    //////////////////// Подключение к url и получение данных
    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
