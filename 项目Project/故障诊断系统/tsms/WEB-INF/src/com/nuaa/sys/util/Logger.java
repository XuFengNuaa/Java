/**
 * 
 */
package com.nuaa.sys.util;

/**
 * @author mahao
 *
 */
public class Logger {
	private static boolean isDebug =(SystemConfig.getConfig("DEBUG")!=null)&&(new Boolean(SystemConfig.getConfig("DEBUG")).booleanValue());
	private static boolean isWarn =(SystemConfig.getConfig("WARN")!=null)&&(new Boolean(SystemConfig.getConfig("WARN")).booleanValue());
	private static boolean isInfo =(SystemConfig.getConfig("INFO")!=null)&&(new Boolean(SystemConfig.getConfig("INFO")).booleanValue());
	private static boolean isError =(SystemConfig.getConfig("ERROR")!=null)&&(new Boolean(SystemConfig.getConfig("ERROR")).booleanValue());
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getRootLogger();
	// 对取出的日志对象进行分析，为空则说明没对日志配置文件进行加载出现问题，这个时候程序终止
	public static  boolean getIsDebug(){
		return isDebug;
	}
	public static  void setIsDebug(boolean value){
		isDebug = value;
	}
	public  static  boolean getIsWarn(){
		return isWarn;
	}
	public  static void setIsWarn(boolean value){
		isWarn = value;
	}
	public  static  boolean getIsInfo(){
		return isInfo;
	}
	public  static  void setIsInfo(boolean value){
		isInfo = value;
	}
	public  static  boolean getIsError(){
		return isError;
	}
	public  static  void setIsError(boolean value){
		isError = value;
	}
	public static void debug(Object msg) {
		if (!isDebug) return;
		log.debug(msg);
	}
	public static void warn(Object msg) {
		if (!isWarn) return;
		log.warn(msg);
	}
	public static void info(Object msg) {
		if (!isInfo) return;
		log.info(msg);
	}
	public static void error(Object msg) {
		if (!isError) return;
		if (msg instanceof Throwable) {
			String message = ((Throwable) msg).getMessage();
			log.error(message, (Throwable) msg);
		} else {
			log.error(msg);
		}
	}
	public static void error(String message, Throwable exception) {
		if (!isError) return;
		log.error(message, exception);
	}
}