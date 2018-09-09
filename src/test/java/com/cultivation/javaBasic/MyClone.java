package com.cultivation.javaBasic;

public class MyClone{
    private String name;
    private int age;


    public MyClone(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MyClone myClone = new MyClone(name,age);
        return myClone;
    }
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        MyClone myClone = new MyClone(name,age);
//        return myClone;
//    }
}
