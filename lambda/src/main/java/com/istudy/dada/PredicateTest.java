package com.istudy.dada;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * predicate 用来判断一个值，见java.util.function.Predicate
 */
public class PredicateTest {

    public static void main(String[] args) {

        List<Integer> lists = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);

        List<Integer> collect = lists.stream().filter(val -> val % 2 == 0).collect(Collectors.<Integer>toList());

        collect.stream().forEach(System.out::println);

        System.out.println(or(100, val -> val > 0, val -> val < 10));
        System.out.println(and(1, val -> val > 0, val -> val < 10));
    }

    public static Boolean or(Integer val, Predicate<Integer> first, Predicate<Integer> after) {

        return first.or(after).test(val);
    }

    public static Boolean and(Integer val, Predicate<Integer> first, Predicate<Integer> after) {

        return first.and(after).test(val);
    }
}