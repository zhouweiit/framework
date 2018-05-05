
package org.zhouwei.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MD5Util {

	private static MD5Util mD5Util;

	public static MD5Util getInstance() {

		if (mD5Util == null) {
			mD5Util = new MD5Util();
		}
		return mD5Util;
	}

	public String md5(String str, String signType, String charset) {

		if (str == null) {
			return null;
		}
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(signType);
			messageDigest.reset();
			messageDigest.update(str.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			return str;
		} catch (UnsupportedEncodingException e) {
			return str;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; ++i)
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		return md5StrBuff.toString();
	}

	public String md5(byte[] bytes, String signType, String charset) {

		if (bytes == null) {
			return null;
		}
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance(signType);
			messageDigest.reset();
			messageDigest.update(bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; ++i) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}

	public String md5(String str) {

		String key = this.md5(str, "MD5", "UTF-8");
		return key;
	}

	public String md5(byte[] bytes) {

		String key = this.md5(bytes, "MD5", "UTF-8");
		return key;
	}

	public String getKeyedDigest(String strSrc, String signType, String key, String charset) {

		try {
			MessageDigest md5 = MessageDigest.getInstance(signType);
			md5.update(strSrc.getBytes(charset));
			if (key == null) key = "";
			String result = "";
			byte[] temp = md5.digest(key.getBytes(charset));
			for (int i = 0; i < temp.length; ++i) {
				result = result + Integer.toHexString(0xFF & temp[i] | 0xFFFFFF00).substring(6);
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String parseByte2HexStr(byte buf[]) {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			sb.append(Integer.toHexString(0xFF & buf[i] | 0xFFFFFF00).substring(6));
		}
		return sb.toString().toUpperCase();
	}
}
