package com.cultivation.javaBasic.showYourIntelligence;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        return stackTraceElements[1].getClassName()+"."+stackTraceElements[1].getMethodName();
        //throw new NotImplementedException();
        // --end-->
    }
}
