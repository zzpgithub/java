package com.cultivation.javaBasicExtended.reverseString;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class StringReverser {
    @SuppressWarnings({"WeakerAccess", "unused"})
    public static String[] reverse(String input) {
        // TODO: please implement the method to pass all the tests.
        // <--start
        if(input == null){
            throw new IllegalArgumentException();
        }

        if(input.isEmpty() || input.trim().isEmpty()){
            return new String[0];
        }

        String[] result = input.split(" ");
        for (int i = 0; i < result.length/2; i++) {
            String temp = result[i];
            result[i] = result[result.length-1-i];
            result[result.length-1-i] = temp;
        }
        return result;
        //throw new NotImplementedException();
        // --end-->
    }
}
