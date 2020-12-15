/**
 * 
 */
package com.nuaa.sys.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.nuaa.sys.util.base.StringUtil;

/**
 * @author mahao
 *
 */
public class StrUtil {
	public final static int UTF8 = 0;

	public final static int GBK = 1;

	public final static int ISO8859 = 2;

	public final static int DEFAULT = -1;

	private final static ArrayList els = new ArrayList();

	static {
		els.add("UTF-8");
		els.add("GBK");
		els.add("ISO-8859-1");
	}

	/**
	 * 将src从fromEnc编码转换成toEnc编码
	 * 
	 * @param src
	 * @param fromEnc
	 * @param toEnc
	 * @return String
	 */
	public static String convertEncoding(String src, int fromEnc, int toEnc) {
		if (null == src)
			return null;
		String ret = null;
		try {
			switch (fromEnc) {
			case DEFAULT:
				ret = new String(src.getBytes(), getEncodingName(toEnc));
				break;
			default:
				ret = new String(src.getBytes(getEncodingName(fromEnc)),
						getEncodingName(toEnc));
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}

	public static String getEncodingName(int enc) {
		if (enc >= els.size() || enc < 0)
			return "";
		return (String) els.get(enc);
	}

	/**
	 * 字符串分割
	 * 
	 * @param str
	 * @param delim
	 * @return String[]
	 */
	public static String[] split(String str, String delim) {
		if (str == null)
			return null;
		StringTokenizer st = new StringTokenizer(str, delim);
		ArrayList al = new ArrayList();
		while (st.hasMoreTokens()) {
			al.add(st.nextToken());
		}
		return (String[]) al.toArray(new String[] {});
	}

	public static String join(String[] list, String add) {
		if (list == null)
			return null;
		return StringUtil.join(list, add);
	}

	public static String[] trimStringArray(String[] arr) {
		if (arr == null)
			return arr;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				arr[i] = arr[i].trim();
			}
		}
		return arr;
	}

	public static String[] upperCaseStringArray(String[] arr) {
		if (arr == null)
			return arr;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				arr[i] = arr[i].toUpperCase();
			}
		}
		return arr;
	}

	public static String[] lowerCaseStringArray(String[] arr) {
		if (arr == null)
			return arr;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				arr[i] = arr[i].toLowerCase();
			}
		}
		return arr;
	}
}
