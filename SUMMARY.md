## 第一周 ：培训

四个问题：

- 1.测试的内容， 在测试什么
- 2.为什么测试挂了
- 3.是怎么改的（操作）
- 4.为什么这么改

JDK 1.8最新版、 IDEA 2018.2



- java underscores 规范： <https://docs.oracle.com/javase/8/docs/technotes/guides/language/underscores-literals.html>



- 基本数据类型：

| 基本类型     | short | int     | long | byte | boolean | float | double | char      |
| ------------ | ----- | ------- | ---- | ---- | ------- | ----- | ------ | --------- |
| 对象类型     | Short | Integer | Long | Byte | Boolean | Float | Double | Character |
| 字节数(byte) | 2     | 4       | 8    | 1    | 1       | 4     | 8      | 2         |
| bits         | 16    | 32      | 64   | 8    | 8       | 32    | 64     | 16        |
|              |       |         |      |      |         |       |        |           |

       包装类：java中有时候运算时两个对象之间进行的，不允许对象和数字之间进行运算，所以需要一个对象。这个对象就是把数字进行一下包装，这样这个对象就可以和另一个对象进行运算了。

- 对象类型 基本类型区别

- long类型变量final long maximum = 0x7fffffffffffffffL 最后有L(小写也可以，但是看不清，推荐大写).

- 为什么有的有 “-”， 有的没有

- ```java
  final int maximum = 0x7fffffff;( = 2147483647 = 2^31 - 1)
  final int minimum = 0x80000000;
  final short maximum = 32767;
  final short minimum = -32768; 
  ```

- 对int型变量 16位 

   - 写一个纯数字，<u>默认是int</u>。   所以写long = 0x8000000000000000L ，这里需要加L表明为long类型

   - 6在计算机中存储形式：0x0000_0006.  -6在计算机中为：对0x0000_0006(0x0000_0000_0000_0000__0000_0000_0000_0110)取反

     0xffff_fff9(0x1111_1111_1111_1111__1111_1111_1111_1001)加1得到0xffff_fffa.

     ```java
     assertEquals(-6,0xffff_fffa);  ///ok
     //负数为对应正数的补码形式存放
     ```

   - ```java
         void should_overflow_silently() {
             int theNumberWillOverflow = Integer.MAX_VALUE;
             ++theNumberWillOverflow;
     		///0x7fff_ffff + 1 = 0x8000_0000 （已经是对应补码形式）; 对应最小值
             // TODO: Please correct the value to pass the test.
             // <--start
             final int expectedResult = Integer.MIN_VALUE;
             // --end-->
     
             assertEquals(expectedResult, theNumberWillOverflow); ///OK  
             //如果接着再加1结果为：0x8000_0001（内存中的存放结果），那么对应实际十进制数字为：-2147483647
         }
     ```

- ```java
   void should_underflow_silently() {
       int theNumberWillUnderflow = Integer.MIN_VALUE;
       --theNumberWillUnderflow;
   
       // TODO: Please correct the value to pass the test.
       // <--start
       final int expectedResult = Integer.MAX_VALUE;
       // --end-->
   
       assertEquals(expectedResult, theNumberWillUnderflow); ////ok
   }
   
     内存中存放的就是：
   	Integer.MIN_VALUE = 0x8000_0000
       Integer.MIN_VALUE - 1 = 0x8000_0000 + (-1)(-1的补码)
       0x8000_0000 + 0xffff_ffff = 0x1_7fff_ffff. 只取第32位，所以为 Integer.MAX_VALUE.
   ```

   Integer 转 Short 如何截取？

   直接截取低16位，不带符号。 截取数据符号根据第16位截取结果来定。

- Float:
  - float 转 int 小数取舍

  - Math中 round(+0.5后向下取整)。  ceil（向上取整）  floor （向下取整）

  - Double  : NAN /Infinite 判断Double.NaN与任何值都不相等。


```java
realNumber = 1d / 0d     realNumber = -1d / 0d
private boolean isInfinity(double realNumber) {
        // TODO: please implement the method to pass the test.
        if(Double.isInfinite(realNumber))
            return true;  ///OK 
        if(realNumber==Double.POSITIVE_INFINITY || realNumber == Double.NEGATIVE_INFINITY)
            return true; ///OK
        else
            return false;
    }
```

- Catch 异常：抛出异常了，会根据栈的顺序接着往上抛异常，若一直不处理该异常，程序中断。
  - 栈溢出：调用的栈太深了（例如递归）。
  - 算术异常：超出可表示范围

1. ```java
   @SuppressWarnings("PointlessArithmeticExpression") 抑制警告的注解 
   ```

- 运算符优先级

- float int short 等类型转换显示还是隐式。

| from\to | byte | short | int  | long | float | double |
| ------- | ---- | ----- | ---- | ---- | ----- | ------ |
| byte    | \    | 隐    | 隐   | 隐   | 隐    | 隐     |
| short   | 显   | \     | 隐   | 隐   | 隐    | 隐     |
| int     | 显   | 显    | \    | 隐   | 隐    | 隐     |
| long    | 显   | 显    | 显   | \    | 隐    | 隐     |
| float   | 显   | 显    | 显   | 显   | \     | 隐     |
| double  | 显   | 显    | 显   | 显   | 显    | \      |



- unicode(2字节)，UTF-8()，UTF-16， UTF-32. UCS-2（2个字节，但是并不能涵盖现实中的所有符号）,	1.8java采用utf-16（2字节4byte实现）。

   ```java
   @Test
   void should_get_code_point_count() {
       final String withSurrogatePairs =   /////  0x20B9F int型，4字节，一个char放不下，需要两个char（4个字节）表示一个codepoint.
           //codepoint(码点，int型数字)
               new String(Character.toChars(0x20B9F)) + " is a character that you may not know";
   
       // TODO: please modify the following code to pass the test
       // <--start
       // TODO: please write down the result directly.
       final int expectedCharLength = 39;
       // TODO: please call some method to calculate the result.
       final int actualCodePointLength = withSurrogatePairs.codePointCount(0,withSurrogatePairs.length());
       // --end-->
   
       assertEquals(expectedCharLength, withSurrogatePairs.length());////char的个数（长度）
       assertEquals(38, actualCodePointLength); /////codepoint的个数（长度）
   }
   
   //原因：一个char并不能包含所有的codepoint，有的字符对应的codepoint需要两个字节来存放(对应一个int)。 
   ```

- &   &&   |.     ||区别.

  &&逻辑与，||逻辑或， 在判断时，从左到右进行逻辑运算，如果已经能确定运算结果，那么后边的逻辑运算将不会执行。

- & | 按位运算。 >>>（无符号右移）.  与 >> （右移运算符）



   ## 9.4

   1. StringTest：

      - immutable   adj.不可改变的

      - customized  定制

      - checksum 校验和

      - delimiters 分隔符

      - Surrogate 代理（java中指不能被ascii 代理的符号？）

      - java8 Optional类：

        1. Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。

        2. Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。

        3. Optional 类的引入很好的解决空指针异常。

        <http://www.runoob.com/java/java8-optional-class.html>

        ```java
        import java.util.Optional;
        
        public class Java8Tester {
           public static void main(String args[]){
               Java8Tester java8Tester = new Java8Tester();
               Integer value1 = null;
               Integer value2 = new Integer(10);
        	  // Optional.ofNullable - 允许传递为 null 参数
               Optional<Integer> a = Optional.ofNullable(value1);
              // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
               Optional<Integer> b = Optional.of(value2);
               System.out.println(java8Tester.sum(a,b));
            }
            public Integer sum(Optional<Integer> a, Optional<Integer> b){
        	   // Optional.isPresent - 判断值是否存在
               System.out.println("第一个参数值存在: " + a.isPresent());
               System.out.println("第二个参数值存在: " + b.isPresent());
        	   // Optional.orElse - 如果值存在，返回它，否则返回默认值
               Integer value1 = a.orElse(new Integer(0));
        	   //Optional.get - 获取值，值需要存在
               Integer value2 = b.get();
               return value1 + value2;
               }
          }
        
        ////输出：
        第一个参数值存在: false
        第二个参数值存在: true
        10
        ```

      - String.trim() 删除字符串<u>首尾</u>空格。  trim:修剪

      - String.substring();

      - String.split()字符串分割;

      - String.chatAt();

      - StringBuilder.  StringBuffer

        StringBuilder：线程非安全的

        StringBuffer：线程安全的  

- Java类初始化顺序说明https://docs.oracle.com/javase/tutorial/java/TOC.html(java 官方教程)
  - 一个类中包含如下几类东西，他们前后是有顺序关系的
    - 1. 静态属性：static 开头定义的属性
      2. 静态方法块： static {} 圈起来的方法块
      3. 普通属性： 未带static定义的属性
      4. 普通方法块： {} 圈起来的方法块
      5. 构造函数： 类名相同的方法
      6. 方法： 普通方法

#### 初始化顺序

```java
public class LifeCycle {
    // 静态属性
    private static String staticField = getStaticField();
    // 静态方法块
    static {
        System.out.println(staticField);
        System.out.println("静态方法块初始化");  
        System.out.println("Static Patch Initial");
    }
    // 普通属性
    private String field = getField();
    // 普通方法块
    {
        System.out.println(field);
        System.out.println("普通方法块初始化");
        System.out.println("Field Patch Initial");
    }
    // 构造函数
    public LifeCycle() {
        System.out.println("构造函数初始化");
        System.out.println("Structure Initial ");
    }

    public static String getStaticField() {
        String statiFiled = "Static Field Initial";
        System.out.println("静态属性初始化");
        return statiFiled;
    }

    public static String getField() {
        String filed = "Field Initial";
        System.out.println("普通属性初始化");
        return filed;
    }   
    // 主函数
    public static void main(String[] argc) {
        new LifeCycle();
    }
}
```

执行结果：

```java
静态属性初始化
Static Field Initial
静态方法块初始化
Static Patch Initial
普通属性初始化
Field Initial
普通方法块初始化
Field Patch Initial
构造函数初始化
Structure Initial 
```




# 9.5

1. 优先级 "~"  > "&"  > "|"   添加代码 ，测试优先级。

2. 注意变量命名规范。

3. 定义数组 需要指定大小， 且之后不会被改变。

4. String:

   初始化变量不给初始值是不能使用的， 但是在field里会默认给初值，初始化的数组可视为对象，会有初始值。

   注意不同类型数据默认初始值区别。

   ```java
   @Test
   void test() {
       char value[] = new char[10]; ////这是数组，对象（class 里面）。 会给初始值。
       ////char valueee;   error. 在method里面，会抱未初始化错误。
       assertEquals('\u0000',value[0]);
       ///assertEquals(null, value[0]);   ///error 基本数据类型char，默认初始值'\u0000'  null属于对象的。
   
       String valueString[] = new String[10];
       String str = valueString[0];
       assertEquals(null, str); ///对象没赋值前是null,
   }
   ```

   String是个对象。

   ```java
   String originalString = "The original string";
   String modifiedString = originalString.replace("original", "new");
   //"The original string" 本身不会被改变，这里会出现新的字符串常量"The new string"并赋值给modifiedString
   originalString = "new string"
   //将originalString指向"new string"常量，原来的 "The original string"还是在内存中。
   //这样会导致很多没用的常量占据内存。
   ///可以改变originalString变量的值， 但是"The original string"本省不会被改变。。（在originalString前加final修饰， 那么originalString将也不能改变。）
   ```

```java
boolean[] test = new boolean[2];
assertEquals(test[0],false);
///new 的时候，该数组实际是一个对象。
///原始类型 声明但不初始化，是不能用的，会报错误。但是类里边的是会初始化的。

        final String originalString = "Java is great";
        System.out.println(originalString.length());  /////输出13


        char value[] = new char[10];

        assertEquals("",value[0]);
```

5.final: 修饰变量，变量的指向不能更改， 但是指向的地址中的内容是可更改的。

```java
@Test
void should_test_final_array_reverse() {
    final int[] value = new int[]{1,2,3,4,5,6};
    for (int i = 0; i < value.length/2; i++) {
        int temp = value[i];
        value[i] = value[value.length-1-i];
        value[value.length-1-i] = temp;
    }
    assertArrayEquals(new int[]{6,5,4,3,2,1},value);
}////value中的指是可以改变的，但是value指向的地址不能改变
```



IDEA代码：1.看代码 查单词看字面意思。  2.放到上下文理解含义

Parameter: 形参

Argument: 实参

Java方法基本数据类型是传值，对象类型传引用。

cast ：强制转换

filed：成员变量

polymorphism：多态

modifiers: 修饰符

https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html 不同修饰符访问权限。



#9.6

InheritanceTest:

Recursive递归。 

 invocation调用

在子类的构造函数中，只能在第一句调用父类的构造函数。即 只能有一个调用父类构造函数。

java中几乎所有的类都是Object的子类。在实例化子类对象时，肯定会调用父类的构造函数。

- 一个类在实例化时，构造函数的调用顺序。

Nested: 嵌套的

```java
class B{
    public static String printMessage(){
        return "This is B";
    }
}
class A extends B{
}
class test{
    @Test
    void should_test_call_static_method(){
        A a = new A();
        assertEquals("This is B", A.printMessage());/////OK
    }
}
////子类可直接调用父类的静态方法
```

```java
public class SimpleEmptyClass {
}

SimpleEmptyClass.class  拿到该类的描述信息（这是一个实例）

```

- 不能用Instance of 判断一个对象是不是另一个类的实例。`instance of`中， 只要能转换成功就返回true。 判断类型相等时，不要用instance of，用 getClass.

- 父类 super ， 子类 deriveFrom =>父子类关系。

- 对象数组 super[] 与 deriveFrom[]  =>无父子关系

- 子类实例化对象可以赋给父类引用（苹果 是 水果）, 反过来不可以（水果 是 苹果）

- 子类构造函数与父类构造函数。




- perfect equals,  Equal. hashcode  什么叫相等。
- ==比较的是引用（地址相等）， String是对象不是基础类型，比较的时候要用equals（比较的是字符串的内容相等，不是地址相等），不能直接==

- final修饰方法，该方法不能写重写，修饰类 类不能被继承。

- overload与overrated装载与重写。

- 如何定义注解

- ```java
  @Target({ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)   ///////////运行时期。
  public @interface TestMyAnnotation {
  }
  ```

https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html.   access Modifier(修饰符访问权限)

Access level modifiers determine whether other classes can use a particular field or invoke（调用） a particular method. There are two levels of access control:

- At the top level—`public`, or *package-private* (no explicit modifier，即默认不写时，就按照no modifier来访问，如下表：).
- At the member level—`public`, `private`, `protected`, or *package-private* (no explicit modifier).

| Modifier    | Class | Package | Subclass | World |
| ----------- | ----- | ------- | -------- | ----- |
| `public`    | Y     | Y       | Y        | Y     |
| `protected` | Y     | Y       | Y        | N     |
| no modifier | Y     | Y       | N        | N     |
| `private`   | Y     | N       | N        | N     |





- ###反射： 注意函数区别

  - getDeclearedMethod()不包含继承等的，只包含该类本身的所有类型的方法
  - getMethods() 只获取public的方法 （包括继承的，接口的 自生的所有Public方法）



### 相等

Identify 相等时，两个对象一定相等  >  equal  > hashcode().

- equal: 1. 判断null。 2. 自反性。 3.一致性。 4.传递性。 5. 对称性。
- hashcode相等，两个对象不一定相等。







###JDK1.8 ： interface中加入了default方法。

- interface:

  - Multi-implement可以多实现（class A implents interfaceA,interfaceB），但是类是单一继承，一个类不会有两个父亲

    ```Java
    class A extends B,C不能这样写，因为java不支持多继承，
    ```

  - No self-implement(Interface 里边只能定义方法，不能实现方法， 除了static ，default方法以外,即static 和default方法可以再接口里面写实现)

  - Field to be static,final,public（接口里边定义的变量类型）

    ```java
    public interface InterfaceWithDefaultMethod {
        public int a = 0;//必须初始化
        final int b = 0;
        static int c = 0;
        
        private int d = 0;////error
        default int d = 0; ///error
        
        default String getTheTruthOfTheUniverse() {
            return "42";
        }
    }
    ```

  - No instance(不能实例化对象)

  - 接口可以继承接口， 但是不能在子接口中实现方法

  - Static method can NOT inherit 静态方法不能被继承(接口中有静态方法，另一个接口继承自该接口， 子接口中不会继承该静态方法)

  - 默认是public 方法  method to be public

  - default 方法可以被继承,default只能用在接口中， 跟类没有关系。

  - to be abstract, 接口是抽象的。

  - 方法签名不包括返回值（方法签名：函数名字（参数列表））

  - ```java
    public void meth(int a){}
    public int meth(int a){  
        return 10;
    }/////error  只根据返回值， 不能overload
    ```

type在JAVA中即指interface和class



### 匿名类与lambda表达式：

lamdba表达式等价于一个匿名类。 lambda只能赋值给一个functional interface（单抽象方法接口，这里面有且只有一个抽象方法，也不能是static的）。

```Java
public interface StringFunc {
    String getString();
}

StringFunc lambda = new StringFunc() {
    @Override  ///可以去掉。
    public String getString() {
        return "Hello";
    }
};
///上下等价
StringFunc lambda = () -> "Hello"；
()->相当于类. 

///StringFunc是一个接口， 但是new StringFunc()，接口是不能实例化对象的，所以这里紧跟着{}，然后在{}中加入实现。  {}相当于一个类，该类实现了StringFunc接口。 写的这个类没有名字，所以是匿名类。
```

- java提供  函数式接口



快捷键：

command + shift +  [  ]   左右切换打开的文档

Command + option + 上下  返回上一个看的位置

command + w  关闭文件

包围代码： command + option + t

在文件上为安全删除文件，弹出确认框 cmd + delete  (删文件)

编译选择的包或模块 cmd + shf+F9

覆盖方法 control+O

查找文件 cmd + shift + O

文件内查找 cmd+f

跳转到申明处 cmd + B

显示方法层次： cmd+shift+H        cmd+F12

前往当前光标所在方法的父类

打开项目结构对话框 : cmd + ;

大小写切换： cmd+ shft+ U

从工具窗口进入代码文件窗口 ： ESC

基本代码补全（不全任何类）: control + space

自动加分号: cmd + shift + enter

展开所有代码块: cmd + shift + +

打开相应编号窗口 :cmd+ 1...9

自动缩进行:  Control + Option + L

**调出类中所有的方法: cmd + 7**

<u>调到错误地方Next/previous highlighted error ： F2 / shift + F2</u>

<u>查看错误提示 commd + F1</u>

删除一个单词 ： option + delete

**编译  cmd+F9**

抽取变量：cmd + option +v

左右交换参数位置 cmd + option + shift +   ←  →.

将语句上下移动 commd + shit + ↑  ↓

Command + option + R 恢复程序运行，如果该断点下面代码还有断点则停在下一个断点上

Command + option + T 包围代码（使用if..else, try..catch, for, synchronized等包围选中的代码）



#第二周
### Exception

- Throwable  --------> Error : unchecked异常

- Throwable  --------> Exception -------> RuntimeException

  其中Exception为checked异常，Exception中包含RuntimeException（如：ArrayIndexOutOfBoundsException，ArithmeticException）等为unchecked异常

  unchecked异常：not specify，无法实现预料的，未知的。

  checked异常： 可以预先预料出现的异常，如文件找不到。

- 方法签名不包括throws exception.

- 抛出异常：specify a cathch.  unchecked异常一般不抛出

- **try** ：try{…}语句块选定捕获异常的范围，将可能出现异常的代码放在try语句块中。

  **catch ( )**： 在catch语句块中是对异常对象进行处理的代码。可以有一个或多个catch语句

  - 捕获异常的有关信息
    - getMessage( ) 方法，用来得到有关异常事件的信息
    - printStackTrace( )用来跟踪异常事件发生时执行堆栈的内容。

- **finally**

  - 不论在try、catch代码块中是否发生了异常事件，finally块中的语句都会被执行。

- Try-with-source :链接https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html

  - 资源（source）用完必须关闭
  - 注意关闭资源顺序 The close methods of resources are called in the opposite order of their creation.
  - 只会关闭非空资源

### 泛型

- 泛型不能传入基本数据类型。
- 泛型，编译过程中将类型擦除， 擦除为object. 但是如果是有边界的，例如<T extend Number>，会擦除成其边界Number
- lower bounded . 例如：List<? extends Number>.     ？为Number的子类型。
- upper bounded. 例如： <? super A>.      ? 为A的类型的父类型。
- https://docs.oracle.com/javase/tutorial/java/generics/unboundedWildcards.html

### 集合

- iterable接口: 可迭代的， 在iterable中含有iterator接口
- 重写iterator方法， 实现通过foreach方式遍历对象。
- `ArrayList`顺序表存储, `LinkedList`内部以链表的形式来保存元素, `ArrayDeque`双向顺序存储
- `HashSet`元素没有以某种特定顺序来存放.   `LinkedHashSet`,按元素插入顺序存放，有序的set
- https://docs.oracle.com/javase/8/docs/technotes/guides/language/foreach.html

### stream

1.将数组，list转化为stream.

2 Stream中用泛型传入Object。 IntStream做了一个原始类型的,其中传入的是int

3.stream是不可变， filter会生成新的stream.

4.通过terminal operation，才会执行处理stream的中间操作。









