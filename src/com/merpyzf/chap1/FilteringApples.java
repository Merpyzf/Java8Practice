package com.merpyzf.chap1;

import sun.applet.AppletListener;

import javax.xml.stream.events.StartDocument;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by 春水碧于天 on 2017/10/19.
 */
public class FilteringApples {

    private static List<Apple> inventory;

    public static void main(String[] args) {



        new FilteringApples().start();





    }


    /**
     * 逻辑代码执行的方法
     */
    public void start(){

        initData();

        List<Apple> appleList = filterApples(inventory, this::isHeavyApple);
        //与上面的写法等价，使用lambda表达式的写法，针对一个功能的代码行数较少，并且只使用一次，这样写会比较简洁
        filterApples(inventory, (Apple a)->a.weight>150);

        //使用串行进行筛选处理
        List<Apple> heavyApples = inventory.stream()
                .filter((Apple a)->a.getWeight()>150)
                .collect(Collectors.toList());

        System.out.println("串行处理结果: "+heavyApples);

        /**
         * 使用并行进行筛选处理
         *
         * 对应 图 1-6
         * 在两个CPU上进行列表筛选，可以让一个CPU处理列表的前一半，
         * 第二个CPU处理后一半，这称为分支步骤。CPU随后对各自半个列表做筛选，
         * 最后一个CPU会把两个结果合并起来
         *
         */
        List<Apple> greenApples = inventory.parallelStream()
                .filter((Apple a)-> "green".equals(a.getColor()))
                .collect(Collectors.toList());

        System.out.println("并行处理结果: "+greenApples);






    }

    /**
     * 进行筛选的方法
     * @param inventory
     * @param p 谓词 在数学上用来代表一个类似函数的东西，它接受一个参数值，并返回true或false
     * @return
     */
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * 判断是否是绿色的苹果
     *
     * @param apple
     * @return
     */
    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    /**
     * 按照重量筛选
     *
     * @param apple
     * @return
     */
    public  boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }


    private static void initData() {

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
