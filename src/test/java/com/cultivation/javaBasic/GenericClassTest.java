package com.cultivation.javaBasic;

public class GenericClassTest<T> {
    private T field;

    public GenericClassTest() {
    }

    public GenericClassTest(T field) {
        this.field = field;
    }

    public T getField() {
        return field;
    }
}
