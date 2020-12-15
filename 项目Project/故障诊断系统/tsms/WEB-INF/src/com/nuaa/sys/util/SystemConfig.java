/**
 * 
 */
package com.nuaa.sys.util;

import java.util.Properties;

/**
 * @author mahao
 *
 */
public class SystemConfig {
	private final static Properties prpts = new Properties();

	static {
		try {
			prpts.load(Logger.class
					.getResourceAsStream("../../../../config.properties"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static String getConfig(String name) {
		return prpts.getProperty(name);
	}

	public static void setConfig(String name, String value) {
		prpts.setProperty(name, value);
	}

	public static Object getImplInstance(Class itfClass) {
		return getImplInstance(itfClass.getName());
	}

	public static Object getImplInstance(String configName) {
		Object ret = null;
		try {
			String cfg = getConfig(configName);
			if (cfg == null){
				System.out.println("在config.properties中没有找到 " + configName + " 配置项");
			}
			Class cls = Class.forName(getConfig(configName));
			ret = cls.newInstance();
		} catch (Exception ex) {
			//ex.printStackTrace();
			System.out.println("获取" + configName + "实例失败！");
		}
		return ret;
	}
}
