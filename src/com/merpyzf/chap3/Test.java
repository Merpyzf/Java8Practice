package com.merpyzf.chap3;

/**
 * 测试在Lambda表达式中使用局部变量
 */
public class Test {

    static int number = 100;

    public static void main(String[] args) {

        int portNumber = 1337;

//        这里的引用是实例变量，实例变量允许对值进行修改
        Runnable r = ()-> System.out.println(number);

        Runnable r1 = () -> System.out.println(portNumber);

        new Thread(r).start();

        //如果对portNumber的值进行修改则无法通过编译，会有以下错误提示:
        // 从lambda表达式引用的本变量必须是最终变量或实际上的最终变量
        //portNumber = 1999;

        number = 100;

        /*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        */

    }
}
