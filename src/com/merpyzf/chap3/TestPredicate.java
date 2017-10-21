package com.merpyzf.chap3;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Predicate;

/**
 * Created by 春水碧于天 on 2017/10/20.
 */
public class TestPredicate {

    public static void main(String[] args) {


        List<String> array = new ArrayList<>();

        array.add("hello");
        array.add("hello1");
        array.add("love");

        List<String> result = filterStr(array, s -> s.startsWith("h"));

        System.out.println(result);


    }


    public static List<String> filterStr(List<String> list, Predicate<String> p) {

        List<String> result = new ArrayList<>();

        for (String str : list) {

            if (p.test(str)) {

                result.add(str);
            }


        }

        return result;


    }


}
