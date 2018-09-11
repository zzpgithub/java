package com.cultivation.javaBasic.util;

public class InnerClassUpdateField {

    private int year;

    public int getYear() {
        return year;
    }

    public InnerClassUpdateField() {
        year = 2018;
    }

    public InnerClassUpdateField(int year) {
        this.year = year;
    }

    public void add(){

//        class InnerClass{  ///local class
//            private static int year = 0;
//        }

        InnerClass innerClass = this.new InnerClass(2); /////外部类调用内部类， 必须new对象  这里new的时候加上this.
        innerClass.add();
    }

    public class InnerClass {

        //private int year;
        //private static int year;  ///error
        private final static int year = 2;

        public InnerClass() {
        }

        public InnerClass(int year) {
            InnerClassUpdateField.this.year += year;
        }

        public void method(){
            InnerClassUpdateField.this.year += 2;
        }

        public void add(){
            InnerClassUpdateField.this.year += this.year; ////内部类调外部类， 可以通过类名. 的方式 获取fields
        }
    }
}
