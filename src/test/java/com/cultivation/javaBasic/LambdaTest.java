package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LambdaTest {
    @Test
    void should_apply_to_interface_with_single_abstract_method() {

//        StringFunc lambda = new StringFunc() {
//            public String getString() {
//                return "Hello";
//            }
//        };
        StringFunc lambda = () -> "Hello";

        // TODO: please modify the following code to pass the test
        // <--start
        final String expect = "Hello";
        // --end-->

        assertEquals(expect, lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_instance_method() {
        // TODO: please bind(捆绑) lambda to instanceMethod.
        // <--start

        //StringFunc lambda = () -> instanceMethod();

        StringFunc lambda = new StringFunc() {
            @Override
            public String getString() {
                return LambdaTest.this.instanceMethod();
            }
        };
        // StringFunc lambda = this::instanceMethod; 没执行 ///this指向实例  ::得到方法的引用    this.instanceMethod()指向方法的返回结果，执行结果。



        // --end-->

        assertEquals("instanceMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_bind_to_static_method() {
        // TODO: please bind lambda to staticMethod
        // <--start

        StringFunc lambda = () -> staticMethod();
        // --end-->

        assertEquals("staticMethod", lambda.getString());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_bind_to_constructor() {
        // TODO: please bind lambda to constructor of ArrayList<Integer>
        // <--start
        GenericFunc<ArrayList<Integer>> lambda =  ArrayList::new;
        //GenericFunc<ArrayList<Integer>> lambda = () -> new ArrayList<>();
        // --end-->

        ArrayList<Integer> value = lambda.getValue();

        assertEquals(0, value.size());
    }

    @Test
    void should_capture_variable_in_a_closure() { ///关闭。 闭包（function + context 函数环境） // 在闭包里被capture的变量之后是不能被更改的
        int captured = 5;

        StringFunc lambda = () -> captured + " has been captured.";

        final String message = lambda.getString();

        /////int captured = 2;   error. 定义时决定的。

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "5 has been captured.";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_evaluate_captured_variable_when_executing() { ////evaluate  求值      lazy
        ValueHolder<String> value = new ValueHolder<>();
        value.setValue("I am the King of the world!");

        StringFunc lambda = () -> "The length of captured value is: " + value.getValue().length();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "The length of captured value is: 4";
        // --end-->

        value.setValue("Blah");
        assertEquals(expected, lambda.getString());
    }

    @Test
    void should_extend_variable_scope() {   ///////测试year变量作用返回扩大， Lambda表达式capture的变量范围被扩大到被调用的范围。
        StringFunc stringFunc = returnLambda();
        String message = stringFunc.getString();

        // TODO: please write down the expected string directly.
        // <--start
        final String expected = "In the year 2019";
        // --end-->

        assertEquals(expected, message);
    }

    @Test
    void should_capture_this_variable() {////this是闭包的那个this.  定义闭包时有个this.捕获定义时的This.
        ThisInClosure instance = new ThisInClosure();
        StringFunc stringFunc = instance.getLambda();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = "ThisInClosure";
        // --end-->

        assertEquals(expected, stringFunc.getString());
    }

    private static StringFunc returnLambda() {
        int year = 2019;
        return () -> "In the year " + year; ////year是引用， 不是通过return 传的值，函数返回后， 还能找到year，并没有被销毁。即扩大了使用范围
    }

    @SuppressWarnings("unused")
    private static String staticMethod() {
        return "staticMethod";
    }

    @SuppressWarnings("unused")
    private String instanceMethod() {
        return "instanceMethod";
    }

//    @Test
//    void should_not_assign_lambda_to_object() {
//        //StringFunc stringFunc = () -> "";
//
//        Object obj = () -> "test";  ///不能将lambda赋值给对象
//    }
}

/*
 * - Do you think you can assign a lambda expression to an Object instance?
 */
