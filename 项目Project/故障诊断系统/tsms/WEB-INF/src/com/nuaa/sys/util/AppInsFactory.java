/**
 * 
 */
package com.nuaa.sys.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author mahao
 *
 */
public class AppInsFactory {
	private static Map services = Collections.synchronizedMap(new HashMap());
	public static ApplicationContext AppContext=new ClassPathXmlApplicationContext("config.xml");
	public static Object getBean(String beanId){
		return AppContext.getBean(beanId);
	}
	public static Object getServiceInstance(Class claz) {
		Object svc = services.get(claz);
		if (svc == null) {
			svc =AppConfig.getImplInstance(claz);
			services.put(claz, svc);
		}
		return svc;
	}
}
