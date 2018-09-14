package com.cultivation.javaBasicExtended.wordProcessor;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class TextProcessor {
    private final TextProcessorSettings settings;

    TextProcessor(int width) {
        this(width, null);
    }

    TextProcessor(int width, char[] whitespaces) {
        if (width < 10 || width > 80) {
            throw new IllegalArgumentException("Width out of range.");
        }

        settings = new TextProcessorSettings(width, getWhitespaces(whitespaces));
    }

    private char[] getWhitespaces(char[] whitespaces) {
        return whitespaces == null ?
            new char[] {' '} :
            whitespaces;
    }

    String process(String text) {
        // TODO: Please implement the method to pass all the test
        // <--start
        if(text == null){throw new IllegalArgumentException();}
        char[] characters = text.toCharArray();
        for(char ch : characters){
            if(!isCharacter(ch) && settings.isWhitespace(ch))
                throw new IllegalArgumentException("ERROR: Invalid character detected!");
        }
        return processText(characters);
        // --end-->
    }

//    String processText(String text){
//        String[] splitFirst = text.split(" ",-1);
//        StringBuilder sb = new StringBuilder();
//        List<String> splitResult = new ArrayList<>();
//        for(int i = 0; i < splitFirst.length - 1; i++){
//            if(splitFirst[i].equals(splitFirst[i+1])){
//               sb.append(splitFirst[i]).append(splitFirst[i+1]);
//               i++;
//            }
//            splitResult.add(splitFirst[i]);
//            splitResult.add(" ");
//        }
//        return " ";

    boolean isCharacter(char ch){
        if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')){
            return true;
        }
        return false;
    }

    int getLine(int index){
        return index/settings.getWidth() + 1;
    }

//    StringBuilder getTotalLine(int start, int end){
//        StringBuilder line = new StringBuilder();
//        if(start > end) return null;
//        if(start == end)  return line.append(start);
//        else{
//            for(int number=start; number<end; number++)
//            line.append()
//        }
//
//    }

    String processText(char[] characters){
        StringBuilder result = new StringBuilder();
        int seg = 0;
        for(int i = 0; i < characters.length ; i++){
            if((i==characters.length-1)||(isCharacter(characters[i]) != isCharacter(characters[i + 1]))){
                int currentIndexLine = getLine(i);
                int beginLine = getLine(seg);

                if(currentIndexLine == beginLine){

                    result.append(characters[i]).append("(" + getLine(i) + ");");
                }
                else{
                    result.append(characters[i]).append("(" + beginLine + "," + getLine(i) + ");");
                    beginLine++;
                }
                seg = i + 1;
            }
            else
            {
                result.append(characters[i]);
            }
        }
        System.out.println(result.toString());
        return result.toString();
    }

//    String processText(char[] characters){
//        StringBuilder result = new StringBuilder();
//        int currentLine = 0;
//
//        for(int i = 0; i < characters.length -1 ; i++){
//            if((isCharacter(characters[i]) != isCharacter(characters[i + 1]))){
//
//                if((i>0)&&(i%settings.getWidth()==0) && (isCharacter(characters[i - 1]) == isCharacter(characters[i]))){
//
//                    result.append(characters[i]).append("(" + (getLine(i)-1) + ","+getLine(i) + ");");
//                    //continue;
//                }
//                else{
//                result.append(characters[i]).append("(" + getLine(i) + ");");}
//            }
//
//
//            else
//            {
//                result.append(characters[i]);
//            }
//            currentLine = getLine(i);
//        }
//        result.append(characters[characters.length -1]).append("(" + currentLine +");");
//        System.out.println(result.toString());
//        return result.toString();
//    }
}

