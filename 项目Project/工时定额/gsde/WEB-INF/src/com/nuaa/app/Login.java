/**
 * 
 */
package com.nuaa.app;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mahao
 *
 */
public interface Login {
	public boolean isValid(String userid,String password);
	public String getUserInf(String userid);//判断多重角色
	public String getUserInfTwo(String userid,String userlevel);	
	public void backtoLoginPage(HttpServletRequest req,
			HttpServletResponse resp, String loginPage);
	public void logout(HttpServletRequest req, HttpServletResponse resp,
			String loginPage);
	public boolean checkSession(HttpServletRequest req,HttpServletResponse resp);
}
