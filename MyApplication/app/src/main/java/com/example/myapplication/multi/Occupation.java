package com.example.myapplication.multi;

public enum Occupation {

    TEACHER("老师"),
    STUDENT("学生"),
    PROFESSOR("教授");

    public String occupation;

    Occupation(String occupation){
        this.occupation = occupation;
    }
}
