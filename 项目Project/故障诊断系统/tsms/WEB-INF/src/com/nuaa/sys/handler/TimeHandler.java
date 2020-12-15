/*
 * Created on 2006-3-3
 * TODO
 * author:gf
 */
package com.nuaa.sys.handler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import com.nuaa.sys.util.Logger;

public class TimeHandler implements MethodInterceptor{
 
    public Object invoke(MethodInvocation methodInvocation) throws Throwable { 
		long procTime = System.currentTimeMillis();
		if(methodInvocation.getArguments().length == 0){
			Logger.info(" 开始执行 " + methodInvocation.getMethod() + " 方法");
		}else{			
			Logger.info(" 开始执行 " + methodInvocation.getMethod() + " 方法"+"此方法的参数为"+methodInvocation.getArguments()[0]); 
		}		  
        try { 
          Object result = methodInvocation.proceed(); 
          return result; 
        }
        finally { 
			procTime = System.currentTimeMillis() - procTime;
			if(methodInvocation.getArguments().length == 0){
				Logger.info(" 执行 " + methodInvocation.getMethod() + " 方法结束");
			}else{
				Logger.info(" 执行 " + methodInvocation.getMethod() + " 方法结束"+"此方法的参数为"+methodInvocation.getArguments()[0]);
			}
			Logger.info("执行 " + methodInvocation.getMethod().getName() + " 方法共用了 " + procTime + "毫秒");
        }        
   } 
}