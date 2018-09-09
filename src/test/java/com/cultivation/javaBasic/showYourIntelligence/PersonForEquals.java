package com.cultivation.javaBasic.showYourIntelligence;

import com.cultivation.javaBasic.util.Person;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;
import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals implements Comparable {
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }


    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || this.getClass() != obj.getClass()) return false;

        PersonForEquals that = (PersonForEquals) obj;
        return this.name.equals(that.name)
                && this.yearOfBirth==that.yearOfBirth;
    }


    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return name.hashCode() + yearOfBirth;
        //throw new NotImplementedException();
        // --end-->
    }


    @Override
    public int compareTo(Object obj) {

        if(obj ==null) throw new NullPointerException();

        PersonForEquals personForEquals = (PersonForEquals)obj;

        int nameCompare = name.compareTo(personForEquals.name);
        if(nameCompare == 0){
            return yearOfBirth - personForEquals.yearOfBirth;
        }
        return nameCompare;
    }
}
