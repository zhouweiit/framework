package org.zhouwei.test.lambda;

import java.util.Arrays;
import java.util.List;

public class FuncionTest {

    public static List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);

    public static void main(String[] args) {
        supplierInteger();
    }

    public static void supplierString() {
        List<String> strs = Arrays.asList("a","bb","ccc");
        String concat = strs.stream().collect(StringBuilder::new, StringBuilder::append,StringBuilder::append).toString();
        System.out.println(concat);
    }

    public static void supplierInteger() {
        Integer result = nums.stream().collect(()->{return new IntegerAdd();},(a,b)->{a.result = a.result + b;},(a,b)->{a.result = a.getResult() + b.getResult();}).getResult();
        System.out.println(result);
    }

}

class IntegerAdd {

    public Integer result = 0;

    public IntegerAdd() {
    }

    public Integer getResult() {
        return result;
    }

}