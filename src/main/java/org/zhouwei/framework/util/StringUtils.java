package org.zhouwei.framework.util;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 字符串工具类
 * 
 * @author ZHOUWEI4
 */
public class StringUtils {
	
	/**
	 * 将一个字符串数据使用一个字符相连
	 * @param infos
	 * @param str
	 * @return
	 * @author ZHOUWEI4
	 */
	public static String implode(List<String> infos,String str){
		if (infos == null || infos.size() == 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (String info : infos){
			if (sb.length() > 0){
				sb.append(str);
			}
			sb.append(info);
		}
		return sb.toString();
	}
	
	/**
	 * 将一个字符串数据使用一个字符相连
	 * @param infos
	 * @param str
	 * @return
	 * @author ZHOUWEI4
	 */
	public static String implode(String[] infos,String str){
		if (infos == null || infos.length == 0){
			return "";
		}
		return implode(Lists.newArrayList(infos),str);
	}
	
	/**
	 * 切割一个字符串为list
	 * @param infos
	 * @param str
	 * @return
	 * @author ZHOUWEI4
	 */
	public static String[] splitString(String infos,String str){
		if (infos == null || infos.trim().length() == 0){
			return null;
		}
		String[] infosArray = infos.split(str);
		return infosArray;
	}

	/**
	 * 切割一个字符串为List
	 * @param infos
	 * @param str
	 * @return
	 */
	public static List<String> splitStringList(String infos,String str){
		String[] result = splitString(infos, str);
		if (result == null){
			return null;
		}
		return Lists.newArrayList(result);
	}
}
