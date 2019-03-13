package org.zhouwei.test.lambda;

import java.util.Arrays;
import java.util.List;

public class Ref {

    public static final int a = 1;
    public static int b = 2;
    public static List<Integer> nums = Arrays.asList(1,2,3,4);

    public static void main(String[] args) {
        localRefModify();
    }

    public static void classStaticRef() {
        nums.stream().map(n -> n = n + b).forEach(System.out::println);
    }

    //不能改变本地变量的值
    public static void localRef() {
        int d = 1;
        nums.stream().map(n -> n = n + d).forEach(System.out::println);
    }

    public static void localRefModify() {
        final People p = new People("a",1);
        nums.forEach(n -> {System.out.println(n);p.setAge(10);});
        System.out.println(p);
    }

}
