package com.example.myapplication.multi;

public enum Sex {

    MAN("男人"),
    WOMAN("女人"),
    BOY("男孩"),
    GIRL("女孩"),
    NOT_MAN("不是人");

    public String sex;

    Sex(String sex) {
        this.sex = sex;
    }
}
