package com.cultivation.javaBasicExtended.matrixMultiplication;

import java.util.Arrays;

@SuppressWarnings({"WeakerAccess", "unused"})
class Matrix {
    private final int[][] storage;

    public int rows() {return storage.length;}

    public int columns() {return storage[0].length;}

    public Matrix(int[][] matrixArray) {
        // TODO: please implement the constructor of a matrix.
        // <--start
        if(matrixArray == null){
            throw new IllegalArgumentException("Raw matrix is null");
        }
        else if(matrixArray.length == 0){
            throw new IllegalArgumentException("Raw matrix contains 0 row");
        }

        for(int i = 0; i< matrixArray.length; i++) {
            if (matrixArray[i] == null) {
                throw new IllegalArgumentException("Raw matrix contains null row");
            }
            else if(matrixArray[i].length == 0){
                throw new IllegalArgumentException("At least one row of raw matrix contains 0 column");
            }
            else if(matrixArray[i].length != matrixArray[0].length){
                throw new IllegalArgumentException("Raw matrix is not rectangle");
            }
        }
        storage = matrixArray;
        //throw new NotImplementedException();
        // --end-->
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        // TODO: please implement the method to pass the tests.
        // <--start
        if (left == null || right == null)
            throw new IllegalArgumentException();

        else if (left.columns() != right.rows()) {
            throw new IllegalArgumentException();

        }
        Matrix result = new Matrix(new int [left.rows()][right.columns()]);
        for (int row = 0; row < left.rows(); row++){
            for (int column = 0; column < right.columns(); column++) {
                for (int index = 0; index < left.columns(); index++) {
                    result.getStorage()[row][column] = result.getStorage()[row][column] + left.getStorage()[row][index] * right.getStorage()[index][column];
                }
            }
        }
        return result;
        // --end-->
    }

    // TODO: you can add some helper method if you like.
    // <--start

    public int[][] getStorage() {
        return storage;
    }

    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) { throw new IllegalArgumentException(); }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (this == obj) { return true; }
        if (Matrix.class != obj.getClass()) { return false; }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) { return false; }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
            .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}
