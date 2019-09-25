package com.example.myapplication.multi;

import android.os.Parcel;
import android.os.Parcelable;

public  class People implements Parcelable{

    String name;

    int age;

    String sex;

    String work;

    String occupation;

    public People() {
    }

    public People(String name, int age, String sex, String work, String occupation) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.work = work;
        this.occupation = occupation;
    }

    protected People(Parcel in) {
        name = in.readString();
        age = in.readInt();
        work = in.readString();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public String getName() {
        return name;
    }

    public People setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public People setAge(int age) {
        this.age = age;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public People setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getWork() {
        return work;
    }

    public People setWork(String work) {
        this.work = work;
        return this;
    }

    public String getOccupation() {
        return occupation;
    }

    public People setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(work);
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", work='" + work + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }
}
