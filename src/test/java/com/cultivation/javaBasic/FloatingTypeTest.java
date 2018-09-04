package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FloatingTypeTest {
    @Test
    void should_test_implicit_explicit_conversion() {
        byte b = 5;
        short byte2short = b;
        int byte2int = b;
        long byte2long = b;
        float byte2float = b;
        double byte2double = b;

        short s = 100;
        byte short2byte = (byte)s;
        int short2int = s;
        long short2ling =s;
        float short2float = s;
        double short2double = s;

        int i = 1000;
        byte int2byte = (byte)i;
        short int2short = (short)i;
        long int2long = i;
        float int2float = i;
        double int2double = i;

        long l = 4L;
        byte long2byte = (byte)l;
        short long2short = (short)l;
        int long2int = (int)l;
        float long2float = l;
        double long2double = l;

        float f = 23.1f;
        byte float2byte = (byte)f;
        short float2short = (short)f;
        int float2int = (int)f;
        long float2long = (long)f;
        double float2double = f;

        double d = 21.01;
        byte double2byte = (byte)d;
        short double2short = (short)d;
        int double2int = (int)d;
        long double2long = (long)d;
        float double2float = (float)d;
    }

    @Test
    void should_not_get_rounded_result_if_convert_floating_number_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int)floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = Integer.valueOf(2);
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings({"divzero", "NumericOverflow"})
    @Test
    void should_judge_special_double_cases() {
        assertTrue(isInfinity(1d / 0d));
        assertTrue(isInfinity(-1d / 0d));
        assertFalse(isInfinity(2d));
        assertFalse(isInfinity(Double.NaN));

        assertTrue(isNan(0d / 0d));
        assertFalse(isNan(Double.NEGATIVE_INFINITY));
        assertFalse(isNan(Double.POSITIVE_INFINITY));
    }

    @Test
    void should_not_round_number_when_convert_to_integer() {
        final float floatingPointNumber = 2.75f;
        final int integer = (int)floatingPointNumber;

        // TODO: Please change the result to pass the test.
        // <!--start
        final int expected = Integer.valueOf(2);
        // --end-->

        assertEquals(expected, integer);
    }

    @SuppressWarnings("unused")
    @Test
    void should_round_number() {
        final double floatingPointNumber = 2.75;

        // TODO: Please call some method to round the floating point number.
        // <!--start
        final long rounded = Math.round(floatingPointNumber);
        // --end-->
        assertEquals(3L, rounded);
    }

    @SuppressWarnings("unused")
    private boolean isNan(double realNumber) {
        // TODO: please implement the method to pass the test.
        if(Double.isNaN(realNumber) ){
            return true;
        }
//        if(realNumber == Double.NaN)
//            return true;
        else
            return false;
    }


    @SuppressWarnings("unused")
    private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
//        if(Double.isInfinite(realNumber))
//            return true;
        if(realNumber==Double.POSITIVE_INFINITY || realNumber == Double.NEGATIVE_INFINITY)
            return true;
        else
            return false;
    }

    /*
     * The coach should ask the following questions for the correspond test method:
     *
     * - Can we compare NaN using == directly?  No
     * - Can we compare XXX_INFINITY using == directly?  Yes
     */
}
