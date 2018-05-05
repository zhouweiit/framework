package org.zhouwei.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

public class ListUtils {
	
	public static <T> List<List<T>> subList(List<T> lists,int partNum){
		List<List<T>> newLists = Lists.newArrayList();
		for (int i = 0;i < partNum;i++){
			newLists.add(new ArrayList<T>());
		}
		for (int i = 0;i < lists.size();i++){
			int index = i % partNum;
			newLists.get(index).add(lists.get(i));
		}
		return newLists;
	}
	
	public static <T> List<List<T>> subCollection(Collection<T> sets,int partNum){
		List<T> listTmp = Lists.newArrayList(sets);
		List<List<T>> newList = Lists.newArrayList();
		List<T> list = null;
		for (int i = 0; i < listTmp.size() ; i++){
			if (i % 4 == 0){
				list = Lists.newArrayList();
				newList.add(list);
			}
			list.add(listTmp.get(i));
		}
		return newList;
	}

}
