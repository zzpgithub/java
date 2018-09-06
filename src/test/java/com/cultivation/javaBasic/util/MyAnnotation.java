package com.cultivation.javaBasic.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;/////import static 和 import区别？？

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME) ////注解保留时间    ----原注解
public @interface MyAnnotation { ///申明一个注解，如何写注解。
}
