package org.zhouwei.test.jexl;

import lombok.Data;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        JexlContext jexlContext = new MapContext();
        JexlEngine jexlEngine = new JexlEngine();
        jexlContext.set("ktwelve", "PRIMARY_SCHOOL");
        jexlContext.set("createTime", 1534694400011l);
        jexlContext.set("is_authentication", 1);
        jexlContext.set("main_teacher_subject", "CHINESE");
        jexlContext.set("rookieReward", 1);
        String expressionStringa = "return ktwelve eq 'PRIMARY_SCHOOL' && createTime > 1534694400000 && is_authentication == 0 && (main_teacher_subject eq 'CHINESE' || main_teacher_subject eq 'ENGLISH') && rookieReward > 0;";
        System.out.println(jexlEngine.createExpression(expressionStringa).evaluate(jexlContext));
    }

    public static void fenhao() {
        JexlContext jexlContext = new MapContext();
        JexlEngine jexlEngine = new JexlEngine();
        jexlContext.set("a",10);
        String expressionStringa = "{var a = 1; var b = 2; return a + b;}";
        System.out.println(jexlEngine.createExpression(expressionStringa).evaluate(jexlContext));
    }

    public static void ifAndFor() {
        JexlContext jexlContext = new MapContext();
        JexlEngine jexlEngine = new JexlEngine();
        jexlContext.set("a",10);
        String expressionStringa = "if (empty(a)){return true;} else {return false;}";
        System.out.println(jexlEngine.createExpression(expressionStringa).evaluate(jexlContext));

        String expressionStringb = "while(a<10){a = a - 1; b = 1; c = a}";
        System.out.println(jexlEngine.createExpression(expressionStringb).evaluate(jexlContext));
    }

    public static void map() {
        JexlContext jexlContext = new MapContext();
        JexlEngine jexlEngine = new JexlEngine();
        Map<String,String> map = new HashMap();
        map.put("a","b");
        map.put("b","c");
        jexlContext.set("map",map);
        String expressionStringa = "map['c'] = 'd';";
        String expressionStringb = "map['cd'] = 'e';";
        Expression expressiona = jexlEngine.createExpression(expressionStringa);
        Expression expressionb = jexlEngine.createExpression(expressionStringb);
        expressiona.evaluate(jexlContext);
        expressionb.evaluate(jexlContext);
        System.out.println(jexlContext.get("map"));
    }

    public static void demo() {
        JexlContext jexlContext = new MapContext();
        JexlEngine jexlEngine = new JexlEngine();
        String expressionString = "return 0 >= 0;";
        Expression expression = jexlEngine.createExpression(expressionString);
        Object result = expression.evaluate(jexlContext);
        System.out.println(result);
    }

    public static void objectMethod() {
        Test.People p = new Test.People();
        p.setAge(1);
        p.setName("nihao");
        JexlEngine engine = new JexlEngine();
        JexlContext jexlContext = new MapContext();
        jexlContext.set("people", p);
        jexlContext.set("aa",11);
        String expressString = "return people.method()";
        Object result = engine.createExpression(expressString).evaluate(jexlContext);
        System.out.println(result);
    }

    @Data
    public static class People {

        private int age;

        private String name;

        public String method() {
            return "method";
        }

    }

}
