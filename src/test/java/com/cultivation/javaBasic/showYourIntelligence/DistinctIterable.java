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
    private Set<E> resultSet = new HashSet<>();
    private final Iterator<E> iterator;
    private E currentElement;

    DistinctIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        //currentElement = iterator.next();
        while (iterator.hasNext()){
            currentElement = iterator.next();
            if(resultSet.contains(currentElement)){
            }else{
                resultSet.add(currentElement);
                return true;
            }
        }
        return false;
        //return iterator.hasNext();
        //throw new NotImplementedException();
    }

    @Override
    public E next() {
        return currentElement;
        //return iterator.next();
        //throw new NotImplementedException();
    }
    // --end->
}