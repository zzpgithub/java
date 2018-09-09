package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LambdaIntFuncTest {
    @Test
    void should_return() {
       IntFunc lambda = (var) -> var;
       final int expect =10;
        assertEquals(10, lambda.getAsInt(10));
    }

    @Test
    void should_test_IntBiFunction() {
        IntBiFunction lambda = (x,y) -> x+y;
        assertEquals(10,lambda.apply(5,5));
    }

    @Test
    void should_test_array_change_element() {
        Integer arrayOne[] = new Integer[]{1};
        Integer arrayTwo[] = new Integer[]{1,2};
        Integer arrayThree[] = new Integer[]{1,2,3};
        Consumer<Integer> lambda = (testArray) ->{
            if(testArray == null || testArray.length==1) return;

            int temp = testArray[0];
            testArray[0] = testArray[1];
            testArray[1] = temp;
        };

        lambda.accept(arrayOne);
        lambda.accept(arrayTwo);
        lambda.accept(arrayThree);

        assertArrayEquals(new Integer[]{1},arrayOne);
        assertArrayEquals(new Integer[]{2,1},arrayTwo);
        assertArrayEquals(new Integer[]{2,1,3},arrayThree);
    }

    @Test
    void should_test_array_sum() {
        int [] array0 = null;
        int [] array1 = new int[]{1};
        int [] arrays = new int[]{1,2,3};

        ArraySumFunction lambda = (test) ->{
            if(test == null) return 0;
            return Arrays.stream(test).sum();
        };

        assertEquals(0,lambda.sum(array0));
        assertEquals(1,lambda.sum(array1));
        assertEquals(6,lambda.sum(arrays));
    }
}
