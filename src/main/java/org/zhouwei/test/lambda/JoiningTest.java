package org.zhouwei.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JoiningTest {

    public static List<String> names = Arrays.asList("a","b","c");

    public static void main(String[] args) {
        String result = names.stream().collect(Collectors.joining(","));
        System.out.println(result);
    }

}
