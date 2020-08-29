package com.example.schedule;
// Класс для парсированных данных
public class Catalogue {
    private String text;
    private int id;

    public Catalogue(String text, int id){
        this.text = text;
        this.id = id;
    }


    public String getTextForButton() {
        return this.text;
    }

    public int getIdForButton() {
        return this.id;
    }
}
