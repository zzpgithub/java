package com.cultivation.javaBasic;

class MyClass<T> implements MyInterface{
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

public class MyInteger<T> extends MyClass{
    private T a;


}

