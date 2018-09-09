package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LambdaCharTest {
    @Test
    void should_method() {
        CharSupplier lambda = () -> '0';
        final int expect ='0';
        assertEquals(expect, lambda.getChar());
    }
}
