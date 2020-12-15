package com.nuaa.app.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.*;

//用于控制tempfile文件夹中的临时文件的定时删除（定时时间为凌晨两点） 
public class TimeImpl implements Servlet {
	public void destroy() {
		// TODO 自动生成方法存根
		
	}

	public ServletConfig getServletConfig() {
		// TODO 自动生成方法存根
		return null;
	}

	public String getServletInfo() {
		// TODO 自动生成方法存根
		return null;
	}


	public void init(ServletConfig config) throws ServletException {
		Timer time = new Timer(); 
		String tempath = "\\web\\tempfile\\";
		final String filepath = config.getServletContext().getRealPath("") + tempath;
		System.out.println(filepath);
		//设置定时执行程序 
        TimerTask task = new TimerTask(){ 
	        public void run(){ 
	       	 /** * 指定删除目录路径构造一个文件对象*/
	        	File file = new File(filepath);//若文件夹不存在时则新建文件夹
	            if(!file.exists()) {
	            	file.mkdirs(); 
	 			}
	            System.out.println( filepath); 
	            File[] fileList = file.listFiles();
	            if(fileList!=null){
	                for(int i = 0 ; i < fileList.length; i++)
	                {
	                    /*** 如果是文件就将其删除*/
	                        fileList[i].delete();
	                }
	            }
            } 
        }; 
        //开始时间从当前时间第二天凌晨两点开始 
        Date start=new Date(); 
        start.setDate(start.getDate()+1); 
        start.setHours(2); 
        start.setMinutes(0); 
        start.setSeconds(0); 
        System.out.println(start.toLocaleString()); 
        //时间间隔一天 
        long period=60*60*24*1000; 
        time.schedule(task,start,period);
	}

	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO 自动生成方法存根
		
	}
}