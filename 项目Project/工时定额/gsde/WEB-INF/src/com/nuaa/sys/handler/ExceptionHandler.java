/*
 * Created on 2006-3-3
 * TODO
 * author:gf
 */
package com.nuaa.sys.handler;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

import com.nuaa.sys.util.Logger;

public class ExceptionHandler implements ThrowsAdvice{	
    
    public void afterThrowing(Method method, Object[] args, Object target, Throwable subclass) throws Throwable { 
    	if(args.length == 0){
    	    Logger.info(" 执行 " + method.getName() + " 时有异常抛出...." + subclass);
    	}else{
    		Logger.info(" 执行参数为 "+args[0] +" 的 "+ method.getName() + " 时有异常抛出...." + subclass);
    	}			
   }
}
