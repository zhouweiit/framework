package org.zhouwei.test.lambda;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Filter {

    public static Map<String,People> pm = new HashMap<>();

    static {
        pm.put("a",new People("a",1));
        pm.put("b",new People("b",1));
        pm.put("c",new People("c",2));
        pm.put("d",new People("d",3));
    }

    public static void main(String[] args) {
        filterValueList();
        filterValueMap();
    }

    public static void filterValueList() {
        List<People> peopleList = pm.values().stream().filter(e -> {if (e.getAge() > 1) {return true;} else {return false;}}).collect(Collectors.toList());
        System.out.println(peopleList);
        System.out.println(pm);
    }

    public static void filterValueMap() {
        Map<String, People> pmNew = pm.entrySet().stream().filter(e -> {if (!e.getKey().equals("a")) {return true;} else {return false;}}).collect(Collectors.toMap((k -> k.getKey()),(k -> k.getValue())));
        System.out.println(pmNew);
        System.out.println(pm);
    }
}
