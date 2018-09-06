package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class Person {

}
class Student extends Person{

}
class ReflectionTest {
    @Test
    void should_be_able_to_get_class_object() {
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final Class<? extends Employee> expected = Employee.class;
        // --end-->

        assertEquals(expected, employeeClass);
    }

    @Test
    void should_be_able_to_get_full_qualified_name() {  ////qualified 限定  full_qualified_name 全名，包名+类名
        Employee employee = new Employee();
        Class<? extends Employee> employeeClass = employee.getClass();

        // TODO: please modify the following code to pass the test
        // <--start
        final String expected = Employee.class.getName();/////可以直接写全名 com.cultivation.javaBasic.util.Employee
        // --end-->

        assertEquals(expected, employeeClass.getName());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_instantiate_types_at_runtime() throws Exception {
        Class<?> theClass = Class.forName("com.cultivation.javaBasic.util.Employee"); //////直接调用该包
        /////？ 通配。

        // TODO: please created an instance described by `theClass`
        // <--start
        Employee instance = (Employee) theClass.newInstance();////直接返回的是Object类型， 需要强转    （如果？改为Employ， 那么new出来的实例也就是Employ）
        // --end-->

        assertEquals("Employee", instance.getTitle());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_print_all_static_methods_of_double() {
        Class<Double> doubleClass = Double.class;

        // TODO: please get all public static declared methods of Double. Sorted in an ascending order
        // <--start
        Method[] methods = doubleClass.getDeclaredMethods();
        List<String> publicStaticMethods = new ArrayList<>();
        for(Method method : methods){
            //int modifyOfMethod = method.getModifiers();  int 类型的
            if (Modifier.isPublic(method.getModifiers()) && Modifier.isStatic(method.getModifiers())){
                publicStaticMethods.add(method.getName());
            }
        }
        String[] publicStaticMethodsToString = publicStaticMethods.toArray(new String[0]);
        Arrays.sort(publicStaticMethodsToString);
        // --end-->


        final String[] expected = {
            "compare", "doubleToLongBits", "doubleToRawLongBits", "hashCode",
            "isFinite", "isInfinite", "isNaN", "longBitsToDouble", "max",
            "min", "parseDouble", "sum", "toHexString", "toString", "valueOf",
            "valueOf"
        };

        assertArrayEquals(expected, publicStaticMethodsToString);
    }

    @SuppressWarnings({"unused", "ConstantConditions", "RedundantThrows"})
    @Test
    void should_be_able_to_evaluate_object_field_values_at_runtime() throws Exception {
        Object employee = new Employee();

        // TODO: please get the value of `getTitle` method using reflection. No casting to Employee is allowed.
        // <--start
        Object result = employee.getClass().getDeclaredMethod("getTitle").invoke(employee);
        // --end-->

        assertEquals("Employee", result);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_be_able_to_get_the_item_class_of_the_array() {
        Object employees = new Employee[0];

        // TODO: please get the class of array item `employees`
        // <--start
        Class<?> itemClass = employees.getClass().getComponentType();///获取数组元素的类型
        // --end-->

        assertEquals(Employee.class, itemClass);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_get_the_methods_who_contains_MyAnnotation_annotation() {
        Class<MyTestAnnotation> theClass = MyTestAnnotation.class;
        Method[] methods = theClass.getDeclaredMethods();
        List<String> annotationMethodName = new ArrayList<>();
        for(Method method : methods){
            Annotation[] annotations = method.getAnnotations();
            for(Annotation annotation : annotations){
                if(annotation.annotationType() == TestMyAnnotation.class)
                    annotationMethodName.add(method.getName());
            }
        }

        String[] strings = annotationMethodName.toArray(new String[0]);

        assertArrayEquals(new String[] {"testMyAnnotationMethod"}, strings);
    }

    @Test
    void should_test_array_() {
        Person[] persons = new Person[10];
        Student[] students = new Student[10];

        //Class<?> studentsClass = students.getClass();
        Class<?>  studentsSuperClass =  students.getClass().getSuperclass();

        assertNotEquals( persons.getClass(), studentsSuperClass);


        Class<?> studentSuperClass = students.getClass().getComponentType().getSuperclass();
        assertEquals(persons.getClass().getComponentType(), studentSuperClass);
        //assertNotEquals(Person.class, studentClass);

    }
}

/*
 * - What is the class name of array type?
 * - How to compare 2 classes?
 * - What if the class does not contain a default constructor when you call `newInstance()`?
 * - What is source only annotation? Can we get source only annotations via reflection?
 */
