package com.cultivation.javaBasic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class YouttabIter implements Iterable<Integer> {

    public YouttabIter() {
    }

    @Override
    public Iterator<Integer> iterator() {
        return new inIterable();
    }
}

class inIterable implements Iterator<Integer>{
    //private List<Integer> list = new ArrayList<>();
    private int number;
    inIterable(){
       //list.add((int)Math.pow(number,10));
    }

    @Override
    public boolean hasNext() {
        return Math.pow(++number,10) < Math.pow(2,40);
    }

    @Override
    public Integer next() {
        return (int)Math.pow(number,10);
    }
}