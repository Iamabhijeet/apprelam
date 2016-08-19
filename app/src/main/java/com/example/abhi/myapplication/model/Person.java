package com.example.abhi.myapplication.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by abhi on 19-08-2016.
 */
public class Person extends RealmObject {
    String PersonName;
    @PrimaryKey
    int Number;

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getPersonName() {
        return PersonName;
    }

    public int getNumber() {
        return Number;
    }

    @Override
    public String toString() {
        return "Person{" +
                "PersonName='" + PersonName + '\'' +
                ", Number=" + Number +
                '}';
    }


}

