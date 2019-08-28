package com.istudy.dada;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * predicate 用来判断一个值，见java.util.function.Predicate
 */
public class SupplierTest {

    public static void main(String[] args) {

//        Supplier<Student> studen = Student::new;

        List<String> list = Lists.newArrayList("hello", "world", "hello world");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }
}