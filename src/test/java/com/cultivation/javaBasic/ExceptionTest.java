package com.cultivation.javaBasic;

import com.cultivation.javaBasic.showYourIntelligence.StackFrameHelper;
import com.cultivation.javaBasic.util.ClosableStateReference;
import com.cultivation.javaBasic.util.ClosableWithException;
import com.cultivation.javaBasic.util.ClosableWithoutException;
import com.cultivation.javaBasic.util.MyClosableType;
import com.cultivation.javaBasic.showYourIntelligence.StringFormatException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionTest {
    @Test
    void should_customize_exception() {
        try {
            throw new StringFormatException("the message");
        } catch (StringFormatException err) {
            assertEquals("the message", err.getMessage());
            assertEquals(null , err.getCause());
        }
    }

    @Test
    void should_customize_exception_continued() {
        Exception innerError = new Exception("inner error");

        try {
            throw new StringFormatException("the message", innerError);
        } catch (StringFormatException error) {
            assertEquals("the message", error.getMessage());
            assertEquals(innerError, error.getCause());  //////加入cause的原因
        }
    }

    @Test
    void should_be_careful_of_the_order_of_finally_block() {
        int confusedResult = confuse(2);

        // TODO: please modify the following code to pass the test
        // <--start
        final int expectedResult = Integer.valueOf(0);
        // --end-->

        assertEquals(expectedResult, confusedResult);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_use_the_try_pattern() {
        ClosableStateReference closableStateReference = new ClosableStateReference();
        //https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        MyClosableType closableTest = null;
        try (MyClosableType closable = new MyClosableType(closableStateReference))  ////try-with-resources.
        {
            assertFalse(closable.isClosed());
            closableTest = closable;
            throw new RuntimeException();
            /// it will be closed regardless of whether the try statement completes normally or abruptly
        }
        catch(RuntimeException err){
            assertTrue(closableTest.isClosed()); ///已经关闭
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), closableStateReference.isClosed());
    }

    @SuppressWarnings({"EmptyTryBlock", "unused"})
    @Test
    void should_call_closing_even_if_exception_throws() throws Exception {
        ArrayList<String> logger = new ArrayList<>();

        try {
            try (AutoCloseable withoutThrow = new ClosableWithoutException(logger);
                 AutoCloseable withThrow = new ClosableWithException(logger)) {
                /////1. 注意关闭顺序，The close methods of resources are called in the opposite order of their creation.
                /////2. 只会关闭非空的资源。  eg: AutoCloseable withThrow = null;  不会调用close(),不会抛出异常
                /////资源的开关： 用完资源需要关闭。 还有其他进程可能会占用到该资源
            }
        } catch (Exception e) {
            // It is okay!
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final String[] expected = {"ClosableWithException.close","ClosableWithoutException.close"};
        // --end-->

        assertArrayEquals(
            expected,
            logger.toArray());
    }

    @Test
    void should_get_method_name_in_stack_frame() {
        String methodName = StackFrameHelper.getCurrentMethodName();

        assertEquals(
            "com.cultivation.javaBasic.ExceptionTest.should_get_method_name_in_stack_frame",
            methodName);
    }

    @SuppressWarnings({"ReturnInsideFinallyBlock", "SameParameterValue"})
    private int confuse(int value) {
        try {
            return value * value;
        } finally {
            if (value == 2) {
                return 0;
            }
        }
    }

//
    class A {

        public void method(){ }

//        public void method1(){ }


    }

    class B extends A{
        @Override
        public void method() throws RuntimeException{  ////uncheck异常， 不可预测。
        }

//        @Override
//        public void method1() throws Exception{  ////Exception check异常（）， 父类没有抛异常，子类却抛了。
//        }
    }



//    public int test(){
//        return 0;
//    }
//
//    public void test(){
//    }
//
//    public void test()throws Exception {
//    }

//    @Test
//    public void method1() {
//        method2();
//        assertEquals(1,1);
//    }
//
//     void method2()  {
//        throw new Error();
//        //method3();
//    }
//
//     void method3(){}

}

/*
 * - Please draw the hibachi of `Throwable` and explain the main purpose for each type.
 * - When do you have to declare a exception in the method signature.
 * - When you declare a class A in package PA, and A contains a method
 *   `callMeToDeath() throw FileNotFoundException`. Package PB imports PA and uses
 *   `PA.A.callMeToDeath()`. Both PA and PB are built and deployed. Now PA is updated and
 *   the method `callMeToDeath()` throws another exception. Can we just build and replace
 *   PA?
 * - Can you declare a method throws unchecked exceptions?
 * - If a super class method throws no checked exception, could a derived class override its
 *   method and throw checked exceptions?
 * - Which constructor do you want to implement if you provide your own exception.
 */
