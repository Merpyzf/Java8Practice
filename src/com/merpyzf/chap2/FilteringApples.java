package com.merpyzf.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 春水碧于天 on 2017/10/19.
 * <p>
 * 2.2 行为参数化
 *
 * 类似策略设计模式
 */
public class FilteringApples {

    private List<Apple> inventory;

    public static void main(String[] args) {


        new FilteringApples().start();


    }


    private void start() {

        initData();

        List<Apple> appleList1 = filterApple(inventory, new RedApplePredict());
        System.out.println("红苹果->"+appleList1);
        // 只需要给filterApples方法添加一个参数,让它接收ApplePredicate对象，就可以把迭代集合筛选元素的逻辑和要应用到集合中的每个
        //元素的行为(这里是一个谓词)区分开了

        // 这就是行为参数化，利用接口，根据不同的逻辑创建不同实现类，进而完成不同逻辑的处理
        List<Apple> appleList2 = filterApple(inventory, new RedAndHeavyApplePredict());
        System.out.println("大红苹果->"+appleList1);
    }

    private void initData() {

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


    public interface ApplePredict {

        boolean test(Apple apple);

    }

    /**
     * 筛选苹果的方法
     * @param apples
     * @param p
     * @return
     */
    public List<Apple> filterApple(List<Apple> apples, ApplePredict p) {

        List<Apple> result = new ArrayList<>();

        for (Apple apple : apples) {

            if (p.test(apple)) {

                result.add(apple);

            }

        }
        return result;
    }


    /**
     * 具体的筛选细节--> 仅按照颜色进行筛选
     */
    public class RedApplePredict implements ApplePredict {

        //筛选红苹果
        @Override
        public boolean test(Apple apple) {


            if ("red".equals(apple.getColor())) {

                return true;
            }

            return false;
        }
    }


    public class RedAndHeavyApplePredict implements ApplePredict {

        @Override
        public boolean test(Apple apple) {

            //筛选大红苹果
            if ("red".equals(apple.getColor()) && apple.getWeight() > 150) {

                return true;
            }

            return false;
        }
    }

}
