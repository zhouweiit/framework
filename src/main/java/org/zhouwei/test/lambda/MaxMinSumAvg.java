package org.zhouwei.test.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class MaxMinSumAvg {

    public static List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9);

    public static void main(String[] args) {
        IntSummaryStatistics intSummaryStatistics = nums.stream().mapToInt(n -> n.intValue()).summaryStatistics();
        System.out.println(intSummaryStatistics.getAverage());
        System.out.println(intSummaryStatistics.getCount());
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getSum());
    }

}
