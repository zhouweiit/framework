package org.zhouwei.framework.util;

public class MathUtils {
	
	/**
	 * 保留小数位，注意，这个不是四舍五入，只是保留小数后几位，根据传进来的参数绝对
	 * @param num	
	 * @param numDecimal	保留的小数位
	 * @return
	 */
	public static Double retainDecimals(Double num,int numDecimal){
		if (num == null){
			return 0d;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("#.");
		for (int i = 0;i < numDecimal ;i++){
			sb.append("0");
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(sb.toString());
		return Double.valueOf(df.format(num));
	}

}
