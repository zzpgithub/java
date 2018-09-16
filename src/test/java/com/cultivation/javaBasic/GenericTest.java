package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.Employee;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.Manager;
import com.cultivation.javaBasic.util.Pair;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GenericTest {
    @SuppressWarnings("unused")

    @Test
    void should_auto_resolve_generic_method() {
        final String[] words = {"Hello", "Good", "Morning"};
        final int[] numbers = {2,3,4};
        //getLast(numbers)  ..error 泛型不能接基本类型
        // TODO: please call getMiddle method for string
        // <--start
        final String middle = getLast(words);
        // --end-->

        assertEquals("Morning", middle);
    }



    @Test
    void should_specify_a_type_restriction_on_typed_parameters() {
        int minimumInteger = min(new Integer[]{1, 2, 3});
        String stringsTest = min(new String[]{"abc","acd"});
//        double minimumReal = min(new Double[]{1.2, 2.2, -1d});

        assertEquals(1, minimumInteger);
        assertEquals("abc", stringsTest);
//        assertEquals(-1d, minimumReal, 1.0E-05);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_not_know_generic_type_parameters_at_runtime() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");
        System.out.println(pairWithDifferentTypeParameter.getClass());
        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->


        assertEquals(expected.get(), pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));
    }

    @Test
    ////证明泛型类 在运行时 被擦成object类
    void should_erase_type() throws NoSuchFieldException {  ///泛型，编译过程中将类型擦除， 在运行时会擦除为object. 但是如果是有边界的<T extend Number>，会擦成其边界Number

        GenericClassTest<Integer> genericClassTest = new GenericClassTest<>(new Integer(10));

        //Object result = genericClassTest.getClass();

        Field field = genericClassTest.getClass().getDeclaredField("field");
        //Field field = genericClassTest.getClass().getField("field");  只能获取public类型的

        assertEquals(Object.class, field.getType());

    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked", "unused", "ConstantConditions"})
    @Test
    void should_be_careful_of_raw_type_generic() {  ////////////////////
        Pair<Manager> managerPair = new Pair<>(); ////会有类型推断

        Pair rawPair = managerPair;   ////raw是用来set的，

        rawPair.setFirst(new Employee());  /////

        boolean willThrow = false;
        try {
            Manager first = managerPair.getFirst(); ////managerPair.getFirst()引用的是Manager,实际获取的是Employee, 不能给Manager
        } catch (ClassCastException error) {
            willThrow = true;
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), willThrow);
    }

    @Test
    void should_swap() {
        Pair<String> pair = new Pair<>("Hello", "World");

        swap(pair);

        assertEquals("World", pair.getFirst());
        assertEquals("Hello", pair.getSecond());
    }

    @SuppressWarnings("unused")
    private static <T> T getMiddle(T[] args) {
        return args[args.length / 2];
    }

//    @Test
//    void should_test_extend_class() {
//    MyInterface
//    }
    private static <T extends MyInterface<T>> void testMyclass(){
        MyInteger<T> myInteger = new MyInteger<>();
        myInteger.compareTo(new MyInteger<T>());
    }

    // TODO: please implement the following code to pass the test. It should be generic after all.
    // The method should only accept `Number` and the number should implement `Comparable<T>`
    // <--start
    private static <T  extends Comparable<T>> T min(T[] value) {
        if(value == null || value.length == 0) return null;
        T result = value[0];
        for (int i = 0; i < value.length -1; i++) {
            if (result.compareTo(value[i + 1]) > 0) {
                result = value[i + 1];
            }
        }
        return result;
    }
    // --end-->

    // TODO: please implement following method to pass the test. But you cannot change the signature
    // <--start
    @SuppressWarnings("unused")

    private static void swap(Pair<?> pair) {
        Object temp = pair.getFirst(); ///取出来是个Object
        //pair.setSecond(temp);  error, 不能被赋值， 应为不知道？是什么类型。
        swapHelper(pair);
        //throw new NotImplementedException();
    }
    // TODO: You can add additional method within the range if you like
    // <--start
    private static <T> void swapHelper(Pair<T> pair) {  ///将 ？视为一种 不知道是什么类型的 类型 传给T进行处理。
        T temp = pair.getFirst();
        pair.setFirst(pair.getSecond());
        pair.setSecond(temp);
    }
    // --end-->


    private static <T> T getLast(T value[]){
        return value[value.length-1];
    }

//    public Object identify(Object a){
//        return a;
//    }

//    public <T > T identify(T a){  ////擦完之后为object  所以会报错
////    return a;
////  }

    public int identify(int a){ ////int可以
        return a;
    }

/*    public Integer identify(Integer a){ ////
        return a;
    }*/
    public <T extends Integer> T identify(T a){  ////上边界  定义时，T为某个类的子类
        return a;
    }

//    public <? super Integer> T identify(T a){  ////error ，下边界一般用在参数，使用 通配符?
//        return a;
//    }

}


/*
 * - Can you give an example when you have to specify a typed parameter in generic method call?
 * - Can type parameter have more than one bounds? Can type parameter bounds to class? Can type parameter bounds to both
 *   class and interface?
 * - What if you have 2 class that one is generic called `KeyValuePair<K, V>` and the second is a non-generic method
 *   called `KeyValuePair` in the same package?
 * - Can you use primitive types as type parameter?
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   String key = value.getKey();
 *   ```
 * - Please describe the wildcard generic type.
 *
 * https://docs.oracle.com/javase/tutorial/java/generics/wildcardGuidelines.html
 */


