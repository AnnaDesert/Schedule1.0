package com.example.schedule;

public class Lesson {
    private String subgroup;
    private String   name_lesson;
    private String type_lesson;
    private String   building;
    private String room;
    private String   Family;
    private String   Name;
    private String   Secondname;
    private String full_name_lesson;
    private String station;
    private int day;
    private int number;

    public Lesson(String subgroup,String   name_lesson,
                  String type_lesson,String   building,
                  String room,String   Family,String   Name, String   Secondname, int day, int number){
        this.subgroup = subgroup;
        this.name_lesson =name_lesson;
        this.type_lesson = type_lesson;
        this.building = building;
        this.room = room;
        this.Family = Family;
        this.Name = Name;
        this.Secondname = Secondname;
        this.day = day;
        this.number = number;

        if (subgroup.equals("0"))
            full_name_lesson = name_lesson + " (" + type_lesson + ")\n" + Family + " " + Name.charAt(0) + "." + Secondname.charAt(0) + ".";
        else if(type_lesson.equals(""))
            full_name_lesson = name_lesson +  "\n" + Family + " " + Name.charAt(0) + "." + Secondname.charAt(0) + ".\n" + "подгруппа " + subgroup;
        else if(subgroup.equals("0")&&type_lesson.equals(""))
            full_name_lesson = name_lesson +  "\n" + Family + " " + Name.charAt(0) + "." + Secondname.charAt(0) + ".";
        else
            full_name_lesson = name_lesson + " (" + type_lesson + ")\n" + Family + " " + Name.charAt(0) + "." + Secondname.charAt(0) + ".\n" + "подгруппа " + subgroup;

        station = "Корпус " + building + "\nаудитория" + room;
    }

    public String getFull_name_lesson(){
        return full_name_lesson;
    }

    public  String getStation(){
        return station;
    }

    public int getDay(){ return day; }

    public  int getNumber(){ return number; }


}
