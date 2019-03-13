package org.zhouwei.test.lambda;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String args[]) {
        Map<String, People> peopleMap = new HashMap<String,People>();
        peopleMap.put("a", new People("a",1));
        peopleMap.put("b", new People("b",2));

        peopleMap.forEach((key,value) -> {System.out.println(key + ":" + value.getAge());});

    }

}