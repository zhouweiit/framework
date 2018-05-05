package org.zhouwei.framework.util;

import java.lang.reflect.Field;

import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanPropertyUtil {

	/**
	 * 实现不同实例的相同属性赋值
	 * 
	 * @param src
	 * @param des
	 */
	public static void copyPropery(Object src, Object des){
		if(null == src){
			return;
		}
		try {
			Assert.notNull(des, "des object can't be null.");
			Class<? extends Object> srcClass = src.getClass();
			Class<? extends Object> descClass = des.getClass();
			Field[] srcFields = srcClass.getDeclaredFields();
			Field[] desFields = descClass.getDeclaredFields();
			for(int i=0; i<srcFields.length; i++){
				Field tmpSrcFiled = srcFields[i];
				tmpSrcFiled.setAccessible(true);
				String srcPropertyname = tmpSrcFiled.getName();
					Object value = tmpSrcFiled.get(src);
				for(int j=0; j<desFields.length; j++){
					Field tmpDesFiled = desFields[j];
					tmpDesFiled.setAccessible(true);
					String desPropertyName = tmpDesFiled.getName();
					if(desPropertyName.equals(srcPropertyname)){
						tmpDesFiled.set(des, value);
					}
				}
			}
		} catch (IllegalArgumentException e) {
			log.error("visit property error", e);
		} catch (IllegalAccessException e) {
			log.error("access property error",e);
		}
	}
	
}
