/**
 * 
 */
package com.nuaa.test;


import com.nuaa.sys.util.Logger;

/**
 * @author mahao
 *
 */
public class TestLog{
	/**
	 * @param args
	 */
	//OFF、FATAL、ERROR、WARN、INFO、DEBUG、ALL
	//Log4j建议只使用四个级别，优先级从高到低分别是ERROR、WARN、INFO、DEBUG
	//
	public void printLog(){
		System.out.println("——————————————————————");
		System.out.println("日志系统测试Begin");
		Logger.debug("debug信息测试");
		Logger.error("error信息测试");
		Logger.warn("warn信息测试");
		Logger.info("info信息测试");
		System.out.println("日志系统测试End");
		System.out.println("——————————————————————");
	}
}
