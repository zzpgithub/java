package com.cultivation.javaBasicExtended.wordProcessor;

import java.util.Arrays;

public class Word {
    private String singleWord;
    private int[] lineNumber;

    public Word(String singleWord) {
        this.singleWord = singleWord;
    }

    public String getSingleWord() {
        return singleWord;
    }

    public void setSingleWord(String singleWord) {
        this.singleWord = singleWord;
    }

    public int[] getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int[] lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return  singleWord  + "(" + Arrays.toString(lineNumber) + ')';
    }
}
