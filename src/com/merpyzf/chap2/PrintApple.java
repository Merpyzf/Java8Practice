package com.merpyzf.chap2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by 春水碧于天 on 2017/10/19.
 * 行为参数化的练习，可选的打印Apple中的参数
 */
public class PrintApple {


    private List<Apple> inventory;

    public static void main(String[] args) {


        new PrintApple().start();

    }


    public void start() {

        initData();


    }


    public void printApple(List<Apple> apples, PrintApplePredict p) {

        for (Apple apple : apples) {

            p.test(apple);

        }

    }


    public interface PrintApplePredict<T> {

        T test(Apple apple);

    }


    public class PrintAppleColor implements PrintApplePredict<String> {


        @Override
        public String test(Apple apple) {
            return apple.getColor();

        }
    }


    public class PrintAppleWeight implements PrintApplePredict<Integer> {


        @Override
        public Integer test(Apple apple) {
            return apple.getWeight();
        }
    }


    public void initData() {

        inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
    }


    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
