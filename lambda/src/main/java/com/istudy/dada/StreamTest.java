package com.istudy.dada;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 */
public class StreamTest {

    public static void main(String[] args) {

        List<String> lists = Lists.newArrayList("hello", "world", "hello world");

        // 将数据，运行一个一个函数后，判断是否是被输出的元素，如果是则运行函数，惰性求值不会运行，终止操作才会最后一算
        lists.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);

    }
}