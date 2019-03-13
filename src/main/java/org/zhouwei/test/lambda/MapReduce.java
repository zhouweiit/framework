package org.zhouwei.test.lambda;

import java.util.Arrays;
import java.util.List;

public class MapReduce {

    public static List<People> ps = Arrays.asList(new People("a",1),new People("b",2),new People("c",3),new People("a",3));

    public static void main(String[] args) {
        map();
    }

    public static void map() {
        ps.stream().map(p -> {p.setAge(p.getAge() + 100);return p;}).forEach(System.out::println);
    }


}
