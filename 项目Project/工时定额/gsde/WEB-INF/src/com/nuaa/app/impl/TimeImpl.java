package com.nuaa.app.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.*;

/**
 * @author WQ
 *
 */

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

	public static boolean deletefile(String delpath) throws FileNotFoundException, IOException { 
		try { 
			File file = new File(delpath); 
			if (!file.isDirectory()) { 
				System.out.println("正在删除文件 " + file.getAbsolutePath()+"\\"+file.getName()); 
				file.delete(); 
				System.out.println("删除文件成功"); 
			} else if (file.isDirectory()) { 
				System.out.println("2"); 
				String[] filelist = file.list(); 
				for (int i = 0; i < filelist.length; i++) { 
					File delfile = new File(delpath + "\\" + filelist[i]); 
					if (!delfile.isDirectory()) { 
						//System.out.println("path=" + delfile.getPath()); 
						//System.out.println("absolutepath=" + delfile.getAbsolutePath()); 
						System.out.println("正在删除文件 " + delfile.getAbsolutePath()+"\\"+delfile.getName()); 
						delfile.delete(); 
						System.out.println("删除文件成功"); 
					}else if (delfile.isDirectory()) { 
						deletefile(delpath + "\\" + filelist[i]); 
					} 
				} 
				file.delete(); 
			} 
		} catch (FileNotFoundException e) { 
			System.out.println("deletefile() Exception:" + e.getMessage()); 
		} 
		return true; 
	}
	
	public void init(ServletConfig config) throws ServletException {
		Timer time = new Timer(); 
		String tempath = "\\web\\tempfile\\";
		final String filepath = config.getServletContext().getRealPath("") + tempath;
		System.out.println(filepath);
		//设置定时执行程序 
        TimerTask task = new TimerTask(){ 
	        public void run(){ 
	        	//指定删除目录路径构造一个文件对象
	        	File file = new File(filepath);//若文件夹不存在时则新建文件夹
	            if(!file.exists()) {
	            	file.mkdirs(); 
	 			}
	            
	            String filenamelist[] = file.list();
	            for (int i=0; i < filenamelist.length; i++) {
	            	System.out.println( filenamelist[i]);
	            	try{
	            		deletefile(filepath + filenamelist[i]);
	            	}catch (FileNotFoundException ex) { 
	        			
	        		} catch (IOException ex) { 
	        			
	        		}
	            }
            } 
        }; 
        //开始时间从当前时间第二天凌晨两点开始 
        Date start = new Date(); 
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