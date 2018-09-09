package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.NameImpl;
import com.cultivation.javaBasic.showYourIntelligence.PersonForEquals;
import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

    class InterfaceTest  {
    @Test
    void should_support_default_method() {
        InterfaceWithDefaultMethodImpl instance = new InterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is 42";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method() {
        InterfaceWithOverrideDefaultImpl instance = new InterfaceWithOverrideDefaultImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Anime";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_choose_override_method_continued() {
        InterfaceExtendsInterfaceWithDefaultMethod instance = new InterfaceExtendsInterfaceWithDefaultMethodImpl();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "The truth of the universe is Game";
        // --end-->

        assertEquals(expected, instance.tellMeTheTruthOfTheUniverse());
    }

    @Test
    void should_resolve_ambiguity_by_yourself() {
        NameImpl instance = new NameImpl();

        String name = instance.getName();

        assertEquals("Person", name);
    }

        @Test
        void should_clone_without_default_constructor() throws CloneNotSupportedException {
            MyClone myClone = new MyClone("jack",20);
            assertEquals("jack",((MyClone)myClone.clone()).getName());
            assertEquals(20,((MyClone)myClone.clone()).getAge());
        }

        @Test
        void should_compare_by_our_method() {
            PersonForEquals[] personForEquals = new PersonForEquals[]{new PersonForEquals("James", (short) 1990),
                    new PersonForEquals("Alice",(short)1990),
                    new PersonForEquals("James",(short) 1992)};

            Arrays.sort(personForEquals);

            PersonForEquals[] expected = new PersonForEquals[]{new PersonForEquals("Alice", (short) 1990),
                    new PersonForEquals("James",(short)1990),
                    new PersonForEquals("James",(short) 1992)};

            assertArrayEquals(expected,personForEquals);
        }
    }



/*
 * - Can you clone an object without a default constructor?
 */
