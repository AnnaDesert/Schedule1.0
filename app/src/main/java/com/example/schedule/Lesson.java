package com.example.schedule;

import android.util.Log;

public class Lesson {
    private String subgroup = "";
    private String   name_lesson = "";
    private String type_lesson = "";
    private String   building = "";
    private String room = "";
    private String   Family = "";
    private String   Name = "";
    private String   Secondname = "";
    private String special = "";

    private String full_name_lesson;
    private String station;

    private int day;
    private int number;

    public Lesson(String subgroup,String   name_lesson, String special,
                  String type_lesson,String   building,
                  String room,String   Family,String   Name, String   Secondname, int day, int number){

        this.name_lesson = name_lesson + " ";

        if(!special.equals("")){
            this.special = "(" + special + ") ";
        }
        if(subgroup.equals("0"))
            this.subgroup = "";
        else
            this.subgroup = "подгруппа " + subgroup;

        if(!type_lesson.equals(""))
            this.type_lesson = "(" + type_lesson + ")";
        if(building.equals("0"))
            this.building = "";
        else
            this.building = "Кор. " + building + "\n";

        if(!room.equals("ДОТ")&&!room.equals(""))
            this.room = "ауд " + room;
        else
            this.room = room;
        this.Family = Family + "\n";


        if(!Name.equals(""))
            this.Name = Name.charAt(0) + ". ";
        if(!Secondname.equals(""))
            this.Secondname = Secondname.charAt(0) + ".";

        this.day = day;
        this.number = number;

            //Log.i("myTag","Lesson add " + name_lesson);

        full_name_lesson = "<b>" + this.name_lesson + "</b> " + this.special + this.type_lesson + "<br><b>" + this.Family + this.Name + this.Secondname + "</b>\n"+ this.subgroup;
        station = this.building + this.room;

            //Log.i("myTag","Full name " + full_name_lesson + "\nstation " + station);
    }

    public String getFull_name_lesson(){
        return full_name_lesson;
    }

    public  String getStation(){
        return station;
    }

    public int getDay(){ return day; }

    public  int getNumber(){ return number; }

    public void addLesson(String name, String place){ full_name_lesson+="<br>---------<br>"; full_name_lesson+=name; station+="<br>-----<br>"; station+=place;}


}
