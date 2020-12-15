/*
 * Created on 2006-3-3
 * TODO
 * author:gf
 */
package com.nuaa.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


//import com.nuaa.app.ProcMod;

public class TestAop {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException { 
		ApplicationContext actx=new ClassPathXmlApplicationContext("exception_config.xml");
		//LogicInterface logic = (LogicInterface)actx.getBean("logic1");
		//Logic2 logic2 = (Logic2)actx.getBean("logic2");
		 // ProcMod procMod=(ProcMod)actx.getBean("procMod");
		  System.out.println("dsfhshhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		try {
			  //procMod.queryFile("123");
			//logic.doInsert("张三");
			//logic.doUpdate("李四");
			//logic.doDelete("王五");
		} catch (Exception ex) {
			
		}
	}
}
