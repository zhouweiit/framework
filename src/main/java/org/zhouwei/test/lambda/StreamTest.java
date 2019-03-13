package org.zhouwei.test.lambda;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 函数接口(Function) 1.函数接口是行为的抽象 2.函数接口是数据转换器
 * 流(Stream)
 * 聚合器(Collector)
 */
public class StreamTest {

    public static List<Integer> nums = Arrays.asList(1,2,3,4);

    public static List<List<Integer>> numsFalt = Arrays.asList(Arrays.asList(1,2,3,4),Arrays.asList(4,5,6,7));

    public static List<List<String>> numsStringFalt = Arrays.asList(Arrays.asList("1","2"),Arrays.asList("2","3"));

    public static List<String> numString = Arrays.asList("1","2","3","4","5","6");

    public static Map<String,Integer> mapInfo = Maps.newHashMap();

    static {
        mapInfo.put("1",1);
        mapInfo.put("2",2);
        mapInfo.put("3",3);
    }

    public static void main(String[] args) {
        mapReduce();
    }

    public static void mapReduce() {
        int result = numString.stream().map(Integer::valueOf).reduce((u, t) -> u + t ).get();
        System.out.println(result);
    }

    public static void flatToInt() {
        int sum = numString.stream().mapToInt(v -> Integer.valueOf(v)).sum();
        System.out.println(sum);
    }

    public static void flatMapTest() {
        List<Integer> numsALL = numsFalt.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(numsALL);
    }

    @Data
    public static class Human {

        private String name;

        private int age;

        public Human(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
