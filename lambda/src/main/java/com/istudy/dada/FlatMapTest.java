package com.istudy.dada;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapTest {

    public static void main(String[] args) {

        List<String> lists = Lists.newArrayList("hello welcome", "world hello", "hello world hello", "hello welcome");

        // TODO: 2019/8/17   flatMap的应用是什么样的
        lists.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().collect(Collectors.toList()).forEach(System.out::println);
    }
}
