/**
 * 
 */
package com.nuaa.test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;


/**
 * @author mahao
 *
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成方法存根
		//测试日志类
		TestLog logger=new TestLog();
		logger.printLog();
		
		//测试数据库操作类DbUtil
		//TestDbUtil dbUtil=new TestDbUtil();
		//dbUtil.select();
		
		//测试系统config
		TestSystemConfig systemConfig=new TestSystemConfig();
		systemConfig.printConfig();
		
		
		/*Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());*/
		
	}
}
