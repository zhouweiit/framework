package org.zhouwei.test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListTest {

    public static List<People> ps = Arrays.asList(new People("a",1),new People("b",2),new People("c",3),new People("a",3));

    public static List<People> psNull = Arrays.asList(new People("a",1),new People("b",2),null);

    public static void main(String args[]) {
        foreach();
    }

    public static void foreachFilterNull() {
        psNull.forEach(p -> {if (null == p) {return;} System.out.println(p.getName());});
        psNull.stream().filter(p -> {if (null == p) {return false;} else {return true;}}).collect(Collectors.toList()).forEach(p -> {System.out.println(p.getName());});
        psNull.stream().filter(p -> {if (null == p) {return false;} else {return true;}}).forEach(p -> {System.out.println(p.getName());});
        psNull.stream().filter(p -> {return null != p;}).map(p -> {p.setAge(p.getAge() + 100);return p;}).forEach(System.out::println);
    }

    public static void foreach() {
        for (People p : ps) {
            System.out.println("foreach: " + p.getName());
        }

        ps.forEach(p -> System.out.println("lambda: " + p.getName()));

        ps.forEach(p -> {
            if (p.getName().equals("a")) {
                System.out.println("lambda: " + p.getName());
            }
        });
    }

    public static void groupBy() {
        Map<String,List<People>> nameMap = ps.stream().collect(Collectors.groupingBy(p -> p.getName()));
        System.out.println(nameMap);
        Map<String,List<People>> nameMapNew = ps.stream().collect(Collectors.groupingBy(People::getName));
        System.out.println(nameMapNew);
    }

    public static void listToMap() {
        Map<String,Integer> nameMap = ps.stream().collect(Collectors.toMap((k -> k.getName()),(v -> v.getAge()),(o,t)->{if (t > o);return t;}));
        //Map<String,Integer> nameMap = ps.stream().collect(Collectors.toMap((People::getName),(People::getAge)));
        System.out.println(nameMap);
    }

}