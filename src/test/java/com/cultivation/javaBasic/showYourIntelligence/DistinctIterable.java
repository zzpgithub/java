package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class DistinctIterable<T> implements Iterable<T> {
    private Iterable<T> iterable;

    public DistinctIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return new DistinctIterator<>(iterable.iterator());
    }

    public List<T> toList() {
        ArrayList<T> result = new ArrayList<>();
        this.forEach(result::add);
        return result;
    }
}

class DistinctIterator<E> implements Iterator<E> {
    // TODO: Implement the class to pass the test. Note that you cannot put all items into memory or you will fail.
    // <--start
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private List<E> list = new ArrayList<>();
    private final Iterator<E> iterator;

    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
        while(this.iterator.hasNext()){
            E element = this.iterator.next();
            if(!element.equals(this.iterator.next())){
                list.add(element);
            }

        }

    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
        //throw new NotImplementedException();
    }

    @Override
    public E next() {
        return iterator.next();
        //throw new NotImplementedException();
    }
    // --end->
}