package com.cultivation.javaBasic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTest {
    @SuppressWarnings({"StringEquality", "ConstantConditions"})
    @Test
    void should_be_immutable() {
        String originalString = "The original string";
        String modifiedString = originalString.replace("original", "new");

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        // <--start
        final Optional<Boolean> areSame = Optional.of(false);
        // --end-->

        assertEquals("The new string", modifiedString);
        assertEquals(areSame.get(), originalString == modifiedString);
    }

    @SuppressWarnings({"StringEquality", "ConstantConditions"})
    @Test
    void all_modification_method_will_create_new_string() {
        String originalString = "The string with tailing space.     ";
        String modifiedString = originalString.trim();

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        // <--start
        final Optional<Boolean> areSame = Optional.of(false);
        // --end-->

        boolean[] test = new boolean[2];
        assertEquals(test[0],false);
        assertEquals("The string with tailing space.", modifiedString);
        assertEquals(areSame.get(), originalString == modifiedString);
    }

    @SuppressWarnings({"StringEquality", "ConstantConditions"})
    @Test
    void will_create_new_string_when_concat() {
        String originalString = "Part one. ";
        String copyOfOriginalString = originalString;
        originalString += "Part two.";

        // TODO: Please modify the following line to pass the test.
        //
        // It is really easy to pass the test. But you have to tell why.
        // <--start
        final Optional<Boolean> areSame = Optional.of(false);
        // --end-->

        assertEquals("Part one. Part two.", originalString);
        assertEquals(areSame.get(), originalString == copyOfOriginalString);
    }

    @SuppressWarnings("unused")
    @Test
    void should_taken_string_apart() {
        final String originalString = "Java is great";

        // TODO: Take part of the original string according to expectation.
        // <--start
        final String partOfString = originalString.substring(5, 13);
        // --end-->

        final String expectedString = "is great";

        assertEquals(expectedString, partOfString);
    }

    @SuppressWarnings("unused")
    @Test
    void should_taken_string_apart_continued() {
        final String originalString = "Java is great.";

        // TODO: Take part of the original string according to expectation.
        // <--start
        final String partOfString = originalString.substring(5, 7);
        // --end-->

        final String expectedString = "is";

        assertEquals(expectedString, partOfString);
    }

    /*
     * Questions on take string apart.
     *
     * - What if the input arguments is out of range of the string?
     * - What will happen if the the starting index is greater than the ending index?
     * - What will happen if the input string is of null reference?
     */

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_break_string_into_words() {
        final String sentence = "This is Mike";

        // TODO: Extract words in the sentence.
        // <--Start
        String[] words = sentence.split(" ");
        // --End-->

        assertArrayEquals(new String[]{"This", "is", "Mike"}, words);
    }

    @Test
    void test() {
        char value[] = new char[10];
        assertEquals('\u0000',value[0]);
        ///assertEquals(null, value[0]);   ///error 基本数据类型char，默认初始值'\u0000'  null属于对象的。

        String valueString[] = new String[10];
        String str = valueString[0];
        assertEquals(null, str); ///对象没赋值前是null,
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_break_string_into_words_customized() {
        final String sentence = "This/is/Mike";

        // TODO: Extract words in the sentence.
        // <--Start
        String[] words = sentence.split("/");
        // --End-->

        assertArrayEquals(new String[]{"This", "is", "Mike"}, words);
    }

    @SuppressWarnings({"unused", "StringBufferReplaceableByString", "MismatchedQueryAndUpdateOfStringBuilder"})
    @Test
    void should_create_ascii_art() {
        final int width = 5;
        final int height = 3;

        // TODO: Create string using StringBuilder
        // <--Start
        StringBuilder builder = new StringBuilder();
       // builder.append("|---|\n").append("|   |\n").append("|---|\n");

        for(int i=0; i< height; i++){
            builder.append("|");
            for(int j=1; j<width -1 ;j++){
                if(i==0 ||i==height-1){
                    builder.append("-");
                }else{
                    builder.append(" ");
                }
            }
            builder.append("|");
            builder.append("\n");
        }
        // --End-->

        final String expected =
                        "|---|\n" +
                        "|   |\n" +
                        "|---|\n";

        assertEquals(expected, builder.toString());
    }

    @SuppressWarnings("unused")
    @Test
    void should_calculate_checksum_of_a_string() {
        final String text = "A quick brown fox jumps over a lazy dog.";

        int sum = 0;
        // TODO: Write some code to calculate the checksum of the string. The checksum is the sum of each string char.
        // <--Start
        for (int i = 0; i < text.length(); i++) {
            sum += text.charAt(i);
            ////////codePointAt(i);???区别
        }
        // --End-->

        assertEquals(3655, sum);
    }

    @Test
    void should_convert_unicode_escape() {
        final String expected = "なにこれ";

        // TODO: Write actual string using unicode escape. The unicode is as follows:
        // な - U+306a        30 6a  占据1个字节,2个byte， 即一个char
        // に - U+306b
        // こ - U+3053
        // れ - U+308c
        // <--Start
        final String actual = "\u306a\u306b\u3053\u308c";
        // --End-->

        assertEquals(expected, actual);
    }

    @SuppressWarnings("unused")
    @Test
    void should_reverse_a_string() {
        final String original = "123456";

        // TODO: Modify the following code to create new string from original String
        // <--Start
        final String reversed = new StringBuilder(original).reverse().toString();
        // --End-->

        assertEquals("654321", reversed);
    }

    @Test
    void should_test_final_array_reverse() {
        final int[] value = new int[]{1,2,3,4,5,6};
        for (int i = 0; i < value.length/2; i++) {
            int temp = value[i];
            value[i] = value[value.length-1-i];
            value[value.length-1-i] = temp;
        }
        assertArrayEquals(new int[]{6,5,4,3,2,1},value);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_compare_string_with_different_cases() {
        final String upperCased = "HELLO";
        final String lowerCased = "hello";

        Optional<Boolean> equalResult = Optional.of(upperCased.equals(lowerCased));
        Optional<Boolean> equalIgnoreCaseResult = Optional.of(upperCased.equalsIgnoreCase(lowerCased));

        // TODO: Please change the value of the following 2 lines to pass the test.
        // <--start
        Optional<Boolean> actualResultOfEqual = Optional.of(false);
        Optional<Boolean> actualResultOfEqualIgnoreCase = Optional.of(true);
        // --end-->

        assertEquals(equalResult, actualResultOfEqual);
        assertEquals(equalIgnoreCaseResult, actualResultOfEqualIgnoreCase);
    }

    @Test
    void should_get_code_point_count() {
        final String withSurrogatePairs =   /////  0x20B9F int型4字节  两个char表示一个codepoint(码点，int型数字)
                new String(Character.toChars(0x20B9F)) + " is a character that you may not know";

        // TODO: please modify the following code to pass the test
        // <--start
        // TODO: please write down the result directly.
        final int expectedCharLength = 39;
        // TODO: please call some method to calculate the result.
        final int actualCodePointLength = withSurrogatePairs.codePointCount(0,withSurrogatePairs.length());
        // --end-->

        assertEquals(expectedCharLength, withSurrogatePairs.length());
        assertEquals(38, actualCodePointLength);
    }

    @Test
    void should_copy_all_code_point_to_array() {
        final String withSurrogatePairs =
                new String(Character.toChars(0x20B9F)) + " is funny";

        final int[] codePoints = getCodePointsFromString(withSurrogatePairs);

        assertArrayEquals(
                new int[]{0x20B9F, (int) ' ', (int) 'i', (int) 's', (int) ' ', (int) 'f', (int) 'u', (int) 'n', (int) 'n', (int) 'y'},
                codePoints);
    }

    @Test
    void should_format_string() {

        final String name = "Harry";
        final int age = 23;

        String text = String.format("Hello, %s. Next year, you will be %d.", name, age);

        // TODO: please modify the following code to pass the test
        // <--start
        final String expectedText = "Hello, Harry. Next year, you will be 23.";
        // --end-->

        assertEquals(expectedText, text);
    }

    private int[] getCodePointsFromString(String withSurrogatePairs) {

        // TODO: please implement the method to the pass the test

        int [] result1 = new int[withSurrogatePairs.codePointCount(0,withSurrogatePairs.length())];

        for (int index = 0, j=0;  index<withSurrogatePairs.length();
             index +=  Character.charCount(Character.codePointAt(withSurrogatePairs,index))){
            result1[j++] = Character.codePointAt(withSurrogatePairs,index);
        }
        return result1;
    }
    /*
     * - List other string format conversion chars.
     *   * d - decimal integer      十进制
     *   * x - hexadecimal integer  十六
     *   * o - octal integer         八
     *   * f - fixed-point floating point
     *   * e - exponential floating point
     *   * g - general floating point (the shorter of e and f)
     *   * a - hexadecimal floating point
     *   * s - string
     *   * c - character
     *   * b - boolean
     *   * h - hash code
     *   * n - platform dependent line separator
     */
}
