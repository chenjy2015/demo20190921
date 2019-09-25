package com.example.myapplication.multi;

public class Student extends People implements IDoSomething {

    public Student(){

    }

    public Student(String name, int age, String sex, String work, String occupation){
        super(name, age, sex, work, occupation);
    }

    @Override
    public String work() {
        return "写作业";
    }
}
