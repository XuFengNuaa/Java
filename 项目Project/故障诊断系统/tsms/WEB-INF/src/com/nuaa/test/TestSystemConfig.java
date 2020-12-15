/**
 * 
 */
package com.nuaa.test;

import com.nuaa.sys.util.SystemConfig;

/**
 * @author mahao
 *
 */
public class TestSystemConfig {
	public void printConfig(){
		System.out.println("——————————————————————");
		System.out.println("检测日志开关状态Begin");
		System.out.println("DEBUG开关是否打开:"+SystemConfig.getConfig("DEBUG"));
		System.out.println("INFO开关是否打开:"+SystemConfig.getConfig("INFO"));
		System.out.println("WARN开关是否打开:"+SystemConfig.getConfig("WARN"));
		System.out.println("ERROR开关是否打开:"+SystemConfig.getConfig("ERROR"));
		System.out.println("检测日志开关状态End");
		System.out.println("——————————————————————");
	}
}
