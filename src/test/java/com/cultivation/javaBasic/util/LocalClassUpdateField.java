package com.cultivation.javaBasic.util;

public class LocalClassUpdateField {
    private int year;

    public LocalClassUpdateField() {
        year = 2018;
    }

    public int getYear() {
        return year;
    }

    public void somethingHappen() {
        //int year = 0;       /////
        class YearIncrementer {                /////localClass
            public void increment() {
                ++year;
            }
        }
        new YearIncrementer().increment();
    }
}