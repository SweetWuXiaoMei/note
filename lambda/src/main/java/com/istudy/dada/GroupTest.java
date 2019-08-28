package com.istudy.dada;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分组
 */
public class GroupTest {

    public static void main(String[] args) {

        List<String> lists = Lists.newArrayList("hello", "world", "hello world");

        Map<Integer, List<String>> collect = lists.stream().collect(Collectors.groupingBy(str -> str.length()));
        collect.forEach((key, strList) -> {
            System.out.println(key);
            System.out.println(strList.size());
        });

        Map<Integer, Long> collect1 = lists.stream().collect(Collectors.groupingBy(String::length, Collectors.counting()));
        for (Map.Entry<Integer, Long> entry : collect1.entrySet()) {

            System.out.println(entry.getValue());
            System.out.println(entry.getValue());
        }

        Map<Boolean, List<String>> collect2 = lists.stream().collect(Collectors.partitioningBy(str -> str.length() > 5));
        for (Map.Entry<Boolean, List<String>> entry : collect2.entrySet()) {

            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }
}