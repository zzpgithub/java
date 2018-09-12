package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.AnimeCharacter;
import com.cultivation.javaBasic.util.KeyValuePair;
import com.cultivation.javaBasic.util.ValueHolder;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StreamingTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_collection_into_stream() {
        List<String> words = Arrays.asList("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = words.stream();
        // --end-->
        {
            assertIterableEquals(
                words,
                wordStream.collect(Collectors.toList())
            );
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_turn_array_into_stream() {
        String[] words = {"a", "quick", "brown", "fox", "jumps", "over"};

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> wordStream = Arrays.stream(words);
        // --end-->
        {
            assertArrayEquals(
                words,
                wordStream.toArray(String[]::new)
            );
        }
    }
////////////********************************************************
    @Test
    void should_be_able_to_turn_array() {
        Float[] words = {1.0f, 2.0f, 3.0f};
        ////DoubleStream.of(0.2d);  ///可传入基本数据类型
        Stream<Float> wordStream = Arrays.stream(words); ////不能写成float, 需传入对象。
        assertArrayEquals(words, wordStream.toArray(Float[]::new));
    }

    @Test
    void should_test_stream_immutable() {   ///stream是不可变， filter会生成新的stream.  但原来的流并不会关闭
        Stream<String> originalWordStream = Stream.of("a","b","abcd");
        Stream<String> otherStream = originalWordStream.filter((str) -> (str.length()<3));  ////会产生新流

        assertNotSame(otherStream,originalWordStream);
        //assertFalse(otherStream == originalWordStream);
    }

    @Test
    void should_test_new_stream_original_stream_not_close() { ////filter不会关闭前一个stream.
        //final boolean flag = false;  /////lambda使用外面定义的变量，必须是final的，但这样写在lambda表达式里边不能被更改了！
        final int[] flg = new int[]{0};    //////可以使用对象或者数组， 指向的这个对象没有被改变， 但是对象的值是可以变化的！
                                            ///////或者使用field，使用外部类的filed
        Stream<String> originalWordStream = Stream.of("a","b","abcd");

        originalWordStream.onClose(()->flg[0]=1); ////
        originalWordStream.filter(str -> (str.length() < 3));

        assertEquals(0,flg[0]);

        ///originalWordStream.toArray(); toArray不会关闭流
        originalWordStream.close();
        assertEquals(1,flg[0]);
    }

    @Test
    void should_test_filter_lazy() {
        Integer[] integers = new Integer[]{1,2,3};

        Stream<Integer> original = Arrays.stream(integers);
        Stream<Integer> originalFilter = original.filter((x) -> {  ///filter返回一个新的对象
            System.out.println(x);
            return  x > 1;} );      ////没有打印值的.  lazy.

        ///调用取值， 才会执行。
        //originalFilter.findFirst();  ///输出1，2, findFirst里面找到第一个元素后，后面的不会执行。
        ///originalFilter.count();   ////输出1，2，3
        originalFilter.close();
    }
    /////////////******************************************************

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_empty_stream() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> emptyWordStream = Stream.empty();
        // --end-->
        {
            assertEquals(0, emptyWordStream.count());
        }
    }
////////////***************************
    @Test
    void should_test_skip_count() {
        final int[] count = new int[]{0};
        Stream<Integer> infiniteSequence = Stream.iterate(0, n -> {
            count[0]++;
            return n + 1;
        }).skip(10000);
        assertEquals(0,count[0]);
    }

    @Test
    void should_test_skip_count_findFirst() {
        final int[] count = new int[]{0};
        Stream<Integer> infiniteSequence = Stream.iterate(0, n -> { //////注意 IntStream 中 iterate 有区别  泛型中不能传入primitive类型，泛型传入的是object，
                                                                            //////Stream中用泛型传入Object。 IntStream做了一个原始类型的,其中传入的是int
            count[0]++;
            return n + 1;
        }).skip(10000);
        infiniteSequence.findFirst();
        assertEquals(10000,count[0]);
    }

    @Test
    void should_test_skip_count_generate() {
        final int[] count = new int[]{0};
        Stream<Integer> infiniteSequence = Stream.generate( () -> {
            count[0]++;
            return 2;
        }).skip(10000);
        infiniteSequence.findFirst();
        assertEquals(10001,count[0]);
    }

    @Test
    void should_test_skip_count_findFirst_get() {
        final int[] count = new int[]{0};
        Stream<Integer> infiniteSequence = Stream.iterate(0, n -> {
            count[0]++;
            return n + 1;
        }).skip(10000);
        infiniteSequence.findFirst().get(); ////Optional. get()方法
        assertEquals(10000,count[0]);
    }
////////////******************************
    @SuppressWarnings("ConstantConditions")


    @Test
    void should_be_able_to_generate_infinite_stream_with_same_items() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<String> infiniteEchos = Stream.generate(()->"Echo");
        // --end-->
        {
            assertEquals("Echo", infiniteEchos.skip(10000).findFirst().get());
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_be_able_to_generate_infinite_stream_of_sequence() {
        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> infiniteSequence = Stream.iterate(0,n -> n+1);
        // --end-->
        {
            assertEquals(10000, infiniteSequence.skip(10000).findFirst().get().intValue());
        }
    }

    @SuppressWarnings({"TryFinallyCanBeTryWithResources", "unused", "ConstantConditions"})
    @Test
    void should_be_able_to_filter_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.filter((str) -> str.length() > 4);
        // --end-->
        {
            assertArrayEquals(new String[]{"quick", "brown", "jumps"}, filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_stream() {
        Stream<String> wordStream = Stream.of("a", "quick", "brown", "fox", "jumps", "over");

        // TODO: please write code to filter word whose length is greater than 4
        // <--start
        Stream<String> filtered = wordStream.map((str) -> str.toUpperCase());
        // --end-->
        {
            assertArrayEquals(
                new String[]{"A", "QUICK", "BROWN", "FOX", "JUMPS", "OVER"},
                filtered.toArray(String[]::new));
        }
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_map_item_to_a_new_type() {
        Stream<String> nameStream = Stream.of("Naruto", "Kisuke", "Tomoya");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<AnimeCharacter> characters = nameStream.map(AnimeCharacter::new);
        //Stream<AnimeCharacter> characters = nameStream.map((name) -> new AnimeCharacter(name));
        // --end-->
        {
            AnimeCharacter[] actual = characters.toArray(AnimeCharacter[]::new);
            assertArrayEquals(
                new AnimeCharacter[] {
                    new AnimeCharacter("Naruto"),
                    new AnimeCharacter("Kisuke"),
                    new AnimeCharacter("Tomoya")
                },
                actual);
        }
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_flatten_stream_of_streams() {   ////flatten 变平
//        Stream<Stream<Character>> nameStream = Stream
//                .of("Naruto", "Kisuke", "Tomoya")
//                .map(StreamingTest::letters);         //////////////////////
//
//        // TODO: please modify the following code to pass the test
//        // <--start
//        Stream<Character> flatted = nameStream.flatMap(character -> character);
//        // --end-->
//        {
//            assertArrayEquals(
//                    new Character[] {
//                            'N', 'a', 'r', 'u', 't', 'o', 'K', 'i', 's', 'u', 'k',
//                            'e', 'T', 'o', 'm', 'o', 'y', 'a'
//                    },
//                    flatted.toArray(Character[]::new));
//        }

        Stream<Stream<Character>> str = Stream.of("abc","def","ghi").map(StreamingTest::letters);

        Stream<Character> expected = str.flatMap(character -> character);

        assertArrayEquals(new Character[]{'a','b','c','d','e','f','g','h','i'}, expected.toArray());/////toArray()不传参数，会默认传入Object[]
    }
    @Test
    void should_flatten_stream_of_streams_add_parameter_in_toArray() {

        Stream<Stream<Character>> str = Stream.of("abc","def","ghi").map(StreamingTest::letters);

        Character [] result = str.flatMap(character -> character).toArray(Character[]::new);///如果不传参会报错,因为result指定了类型。

        assertArrayEquals(new Character[]{'a','b','c','d','e','f','g','h','i'}, result);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_create_sequence_of_specified_size() {
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> i + 1);

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Integer> finiteStream = infiniteSequence.limit(10);
        // --end-->
        {
            assertArrayEquals(
                new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                finiteStream.toArray(Integer[]::new)
            );
        }
    }

    @Test
    void should_test_run_time_limit() {
        int[] flag = new int[]{0};
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> {
            flag[0]++;
            return i + 1;
        });
        assertEquals(0, flag[0]);
        infiniteSequence.limit(10);

        assertEquals(0, flag[0]);
    }
    @Test
    void should_test_run_time_limit_toArray() {
        int[] flag = new int[]{0};
        Stream<Integer> infiniteSequence = Stream.iterate(0, i -> {
           flag[0]++;
           return i + 1;
        });
        assertEquals(0, flag[0]);

        infiniteSequence.limit(10).toArray(Integer[]::new);  ////传参， 显>隐

        assertEquals(10-1, flag[0]);
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_concat_streams() {
        Stream<Character> helloStream = Stream.of('H', 'e', 'l', 'l', 'o');
        Stream<Character> worldStream = Stream.of('W', 'o', 'r', 'l', 'd');

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> concatStream = Stream.concat(helloStream,worldStream);
        // --end-->
        {
            assertArrayEquals(
                letters("HelloWorld").toArray(Character[]::new),
                concatStream.toArray(Character[]::new)
            );
        }
    }

    @Test
    void should_test_sort_stream() {
        Stream<Character> ahbStream = Stream.of('a','h','b');
        Stream<Character> cfestream = Stream.of('c','f','e');

        Stream<Character> concatStream = Stream.concat(ahbStream.sorted(), cfestream.sorted());

        Character[] result = concatStream.toArray(Character[]::new);


        assertArrayEquals(letters("abhcef").toArray(Character[]::new), result);

        assertNotEquals(letters("abcefh").toArray(Character[]::new), result);

//        assertArrayEquals(letters("abcefh").toArray(Character::new), concatStream.toArray(Character[]::new));
    }


    @SuppressWarnings({"SpellCheckingInspection", "unused", "ConstantConditions"})
    @Test
    void should_get_unique_items() {
        Stream<Character> characterStream = letters("aquickbrownfox");

        // TODO: please modify the following code to pass the test
        // <--start
        Stream<Character> distinct = characterStream.distinct();
        // --end-->
        {
            Character[] characters = distinct.sorted().toArray(Character[]::new);

            assertArrayEquals(
                new Character[] {'a', 'b', 'c', 'f', 'i', 'k', 'n', 'o', 'q', 'r', 'u', 'w', 'x'},
                characters
            );
        }
    }

    @Test
    void should_hook_stream_generation() {
        ValueHolder<Integer> holder = new ValueHolder<>();
        holder.setValue(0);

        Stream<Integer> hookStream = Stream
            .iterate(0, i -> i + 1)
            .limit(20)
            .filter(v -> v % 2 == 0)
            .peek(v -> holder.setValue(holder.getValue() + v));  ////

        hookStream.forEach(i -> {});   //////forEach terminal operation

        // TODO: please modify the following code to pass the test
        // <--start
        final int expected = 90;
        // --end-->

        assertEquals(expected, holder.getValue().intValue());
    }

    @Test
    void should_test_stream_peek() {

        ValueHolder<Integer> valueHolder = new ValueHolder();
        valueHolder.setValue(0);

        Stream.of(valueHolder).peek(v -> v.setValue(1)).toArray(ValueHolder[]::new);

        assertEquals(1, valueHolder.getValue().intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unchecked", "OptionalAssignedToNull"})
    @Test
    void should_throws_if_get_value_of_empty_optional() {
        // TODO: please create an empty optional and specify the concrete exception type.
        // <--start
        Optional<String> empty = Optional.empty();
        Class errorType = new NoSuchElementException().getClass();
        // --end-->

        assertThrows(errorType, empty::get);
    }

    @Test
    void should_provide_a_default_value_using_or_else() {
        Optional<String> empty = Optional.empty();
        Optional<String> nonEmpty = Optional.of("great");

        String emptyResult = getValue(empty, "default value");
        String nonEmptyResult = getValue(nonEmpty, "default value");

        assertEquals("default value", emptyResult);
        assertEquals("great", nonEmptyResult);
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_be_able_to_throw_for_empty_optional() {
        Optional<String> empty = Optional.empty();

        // TODO: In the `Runnable` object. Please throw IllegalStateException when `empty` is not present.
        // <--start
        Runnable emptyRunnable = () -> empty.orElseThrow(IllegalStateException::new);
        // --end-->

        assertThrows(IllegalStateException.class, emptyRunnable::run);
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "ConstantConditions"})
    @Test
    void should_process_value_if_present() {
        Optional<String> optional = Optional.of("word");
        List<String> result = new ArrayList<>();

        // TODO: please add the upper-cased value to result if `optional` is present in `Consumer<Optional<String>>`
        // TODO: implementation.
        // <--start
        Consumer<Optional<String>> optionalConsumer = (x) -> {
            result.add(x.get().toUpperCase());
        };
        // --end-->

        optionalConsumer.accept(optional);

        assertEquals("WORD", result.get(0));
    }

    @SuppressWarnings({"ConstantConditions", "MismatchedQueryAndUpdateOfCollection"})
    @Test
    void should_map_value_if_present() {   ///
        Optional<String> optional = Optional.of("word");
        Optional<String> empty = Optional.empty();

        List<String> result = new ArrayList<>();

        // TODO: The `Function<Optional<String>, Optional<Boolean>>` will map `Optional<String>` to `Optional<Boolean>`
        // TODO: please add the upper-cased value to `result` list if optional is present. Then return the boolean
        // TODO: mapping result of `result.add`.
        // <--start
        Function<Optional<String>, Optional<Boolean>> mapping = (optStr) -> {
            Optional<Boolean> finalResult = Optional.empty();
            if(optStr.isPresent()){
                result.add(optStr.get().toUpperCase());
                return finalResult.of(true);
            }
            return finalResult;
        };
        // --end-->

        Optional<Boolean> mappingResult = mapping.apply(optional);
        Optional<Boolean> emptyMappingResult = mapping.apply(empty);

        assertTrue(mappingResult.orElseThrow(IllegalStateException::new));
        assertEquals("WORD", result.get(0));
        assertFalse(emptyMappingResult.isPresent());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_flat_map_optional_value_do_chain_method() {
        Stream<YieldOptional> emptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 4)
            .map(i -> new YieldOptional());
        Stream<YieldOptional> nonEmptyStream = Stream.of(1, 2, 3)
            .filter(i -> i > 1)
            .map(i -> new YieldOptional());

        // TODO: The `findFirstAndGet` interface will find first item in stream. If it can be found, map it with
        // TODO: `YieldOptional.get` method. Otherwise, returns empty Optional.
        // <--start
        Function<Stream<YieldOptional>, Optional<String>> findFirstAndGet = null;
        // --end-->

        Optional<String> emptyStreamResult = findFirstAndGet.apply(emptyStream);
        Optional<String> nonEmptyStreamResult = findFirstAndGet.apply(nonEmptyStream);

        assertFalse(emptyStreamResult.isPresent());
        assertTrue(nonEmptyStreamResult.isPresent());
        assertEquals("Hello", nonEmptyStreamResult.get());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_result() {
        Stream<String> stream = Stream.of("Hello", "What", "is", "your", "name");

        // TODO: please implement toList collector using `stream.collect`. You cannot use existing `toList` collector.
        // <--start
        ArrayList<String> list = null;
        // --end-->

        assertEquals(ArrayList.class, list.getClass());
        assertIterableEquals(
            Arrays.asList("Hello", "What", "is", "your", "name"),
            list
        );
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_map() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: please implement toMap collector using `stream.collect`. You cannot use existing `toMap` collector.
        // <--start
        HashMap<String, Integer> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertTrue(map.containsKey("Harry"));
        assertEquals(2033, map.get("Harry").intValue());
        assertTrue(map.containsKey("Bob"));
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_collect_to_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You cannot use existing `groupingBy` collector.
        // <--start
        HashMap<String, List<Integer>> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_collect_to_group_continued() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. This time please use `Collectors.groupingBy`
        // <--start
        Map<String, List<Integer>> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertIterableEquals(Arrays.asList(2002, 2033), map.get("Harry"));
        assertIterableEquals(Collections.singletonList(2014), map.get("Bob"));
    }

    @SuppressWarnings({"unused", "ConstantConditions"})
    @Test
    void should_calculate_number_in_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Long> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertEquals(2, map.get("Harry").longValue());
        assertEquals(1, map.get("Bob").longValue());
    }

    @SuppressWarnings({"ConstantConditions", "unused"})
    @Test
    void should_calculate_sum_of_each_group() {
        Stream<KeyValuePair<String, Integer>> stream = Stream.of(
            new KeyValuePair<>("Harry", 2002),
            new KeyValuePair<>("Bob", 2014),
            new KeyValuePair<>("Harry", 2033)
        ).parallel();

        // TODO: implement grouping collector using `stream.collect`. You should use `Collectors.groupingBy` and
        // TODO: downstream collector.
        // <--start
        Map<String, Integer> map = null;
        // --end-->

        assertEquals(2, map.size());
        assertEquals(4035, map.get("Harry").intValue());
        assertEquals(2014, map.get("Bob").intValue());
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "OptionalAssignedToNull"})
    @Test
    void should_calculate_sum_using_reduce() {
        List<Integer> numbers = new ArrayList<>();
        Stream
            .iterate(1, i -> i + 1)
            .limit(100)
            .forEach(numbers::add);

        // TODO: please modify the following code to pass the test
        // <--start
        Optional<Integer> reduced = null;
        // --end-->

        //noinspection ConstantConditions
        assertEquals(5050, reduced.get().intValue());
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_calculate_total_character_lengths() {
        List<String> words = Arrays.asList("The", "future", "is", "ours");

        // TODO: please calculate the total number of characters using `reduce`.
        // <--start
        Integer total = null;
        // --end-->

        assertEquals(15, total.intValue());
    }

    @SuppressWarnings({"SameParameterValue", "OptionalUsedAsFieldOrParameterType"})
    private static <T> T getValue(Optional<T> optional, T defaultValue) {
        // TODO: please implement the following method to pass the test
        // <--start
        return optional.orElse(defaultValue);
        //throw new NotImplementedException();
        // --end-->
    }

    private static Stream<Character> letters(String text) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < text.length(); ++i) {
            characters.add(text.charAt(i));
        }
        return characters.stream();
    }
}

class YieldOptional {
    Optional<String> get() {
        return Optional.of("Hello");
    }
}

/*
 * - Can you use `collect` method to implement `joining(String delimiter)` method?
 * - What can you do using primitive types with streams?
 */