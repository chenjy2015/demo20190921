package com.example.myapplication.multi;

public class Teacher extends People implements IDoSomething{

    public Teacher(){

    }

    public Teacher(String name, int age, String sex, String work, String occupation){
        super(name, age, sex, work, occupation);
    }


    @Override
    public String work() {
        return "批阅作业";
    }
}
