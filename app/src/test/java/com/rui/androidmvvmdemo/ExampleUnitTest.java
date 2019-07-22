package com.rui.androidmvvmdemo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    /**
     * 什么是函数式接口？
     * 1）只包含一个抽象方法的接口，称为函数式接口。
     * 2）可以通过Lambda表达式来创建该接口的对象（若Lambda表达式抛出一个受检异常，那么该异常需要在目标接口的抽象方法上进行声明）。
     * 3）我们可以在任意函数式接口上使用@FunctionalInterface注释，这样做可以检查它是否是一个函数式接口，同时javadoc也会包含一条声明，说明这个接口是一个函数式接口。
     */

    //todo JAVA内置四大核心函数式接口
    @Test
    public void run() {
        happy(500d, (m) -> {
            System.out.println("消费了" + m + "元");
        });

        happy(500d, m -> System.out.println("消费了" + m + "元"));
    }


    /**
     * 1、Consumer<T>：消费性接口 传进一个参数 然后对该参数进行操作 没有返回值 操作了也就纯操作
     * void accept(T t);
     *
     * @param money    传进来的参数
     * @param consumer 对该参数进行操作
     */
    public void happy(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    @Test
    public void run1() {
//        List<Integer> numberList = getNumberList(5, () -> {
//            return (int) (Math.random() * 100);
//        });
//        for (Integer integer : numberList) {
//            System.out.println(integer);
//        }

        List<Integer> numberList1 = getNumberList(5, () -> (int) (Math.random() * 100));
        for (Integer integer : numberList1) {
            System.out.println(integer);
        }

    }

    /**
     * 需求：产生指定个数的整数，并放入集合中
     *
     * @param num      指定个数
     * @param supplier 生产什么样的整数
     * @return
     */
    public List<Integer> getNumberList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integers = supplier.get();
            list.add(integers);
        }
        return list;
    }

    @Test
    public void run2() {
        String str = strHandler("   1231321321  ", s -> s.trim());
        System.out.println(str);
        String str1 = strHandler("123456789", s -> s.substring(0, 5));
        System.out.println(str1);
    }

    /**
     * 3、Function<T,R>函数型接口：传进去一个传参数，返回一个参数，可以通过定义泛型对其类型进行操作
     * R apply(T t);
     *
     * @param str
     * @param function 转换的逻辑
     * @return
     */
    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }


    @Test
    public void run3() {
        List<String> list = Arrays.asList("action", "string", "char", "shore");
        List<String> strings = filterStr(list, str -> str.length() > 5);
        for (String str : strings) {
            System.out.println(str);
        }
    }

    /**
     * 4、Predicate<T>: 断言型接口 用于一些做一些判断型操作
     * boolean test(T t);
     *
     * @param list
     * @param predicate
     * @return
     */
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> filterList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s))
                filterList.add(s);
        }
        return filterList;
    }

}