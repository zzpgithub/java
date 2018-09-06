package com.cultivation.javaBasic.util;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)   ///////////运行时期。
public @interface TestMyAnnotation {
}
