package com.merpyzf.chap3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by 春水碧于天 on 2017/10/23.
 */
public class Test1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // Predicate返回了一个boolean,下面都符合其规则
        Predicate<String> p = (s) -> list.add("hello");
        Predicate<String> p1 = (s) -> true;

        //void accept(T t) Consumer返回了一个void
        Consumer<String> c = (s) -> list.add("hello");
//        Consumer<String> c1 = (s) -> false;

        Consumer<String> c2 = System.out::print;

        c2.accept("hello");

//        "hello".length()


    }

}
