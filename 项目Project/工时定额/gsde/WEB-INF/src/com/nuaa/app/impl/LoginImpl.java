/**
 * 
 */
package com.nuaa.app.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nuaa.app.FileState;
import com.nuaa.app.Login;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.Logger;

/**
 * @author mahao
 *
 */
public class LoginImpl implements Login {

	public boolean isValid(String userid, String password) {
		// TODO 自动生成方法存根
		String sql="select * from t_user where stuff_num='"+userid+"' and password='"+password+"'";
		
		final int []flag=new int[1];
		flag[0]=0;
		
		try {			
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					if(rs.next()){
						flag[0]=1;
					}
				}
			});
			//System.out.print("$$$$$$$"+flag[0]);
			if(flag[0]==1){
				return true;
			}else{
				return false;
			}
		}catch (SQLException e) {
		// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return false;
	}
	public String getUserInf(String userid){
		String sql="select stuff_num, realname,group_num,role from t_user where stuff_num='"+userid+"'";
		//final HashMap userInfMap = new HashMap();
		final String[] content = new String[1];
		content[0] = "[";
		try {
			DbUtil.execute(sql, new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					int i = 0;
					while (rs.next()) {
						String s_temp = "";
						if (i != 0) {
							s_temp += ",";
						}
						i++;
						s_temp += "{'userid':'" + rs.getString("stuff_num") + "'";
						s_temp += ",'username':'" + rs.getString("realname") + "'";			
						s_temp += ",'group_num':'" + rs.getString("group_num")+ "'";
						s_temp += ",'userlevel':'" + rs.getString("role") + "'}";
						content[0] += s_temp;
					}
				}
			});
		} catch (Exception ex1) {
			ex1.printStackTrace();
		}
		content[0] +="]";
		return content[0];	
	}
	public String getUserInfTwo(String userid,String userlevel){
		String sql="select stuff_num,realname,remark,group_num,userlevel from v_user2role where stuff_num='"+userid+"' and userlevel='"+userlevel+"'";
		System.out.println(sql);
		//final HashMap userInfMap = new HashMap();
		final String[] content = new String[1];
		content[0] = "[";
		try {
			DbUtil.execute(sql, new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {
					int i = 0;
					while (rs.next()) {		
						String s_temp = "";
						if (i != 0) {
							s_temp += ",";
						}
						i++;
						s_temp += "{'userid':'" + rs.getString("stuff_num") + "'";
						s_temp += ",'username':'" + rs.getString("realname") + "'";
						s_temp += ",'remark':'" + rs.getString("remark") + "'";					
						s_temp += ",'group_num':'" + rs.getString("group_num")+ "'";
						s_temp += ",'userlevel':'" + rs.getString("userlevel") + "'}";
						content[0] += s_temp;
					}
				}
			});
		} catch (Exception ex1) {
			ex1.printStackTrace();
		}
		content[0] +="]";
		return content[0];	
	}
	/* （非 Javadoc）
	 * @see com.nuaa.app.Login#backtoLoginPage(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	public void backtoLoginPage(HttpServletRequest req, HttpServletResponse resp, String loginPage) {
		// TODO 自动生成方法存根
		Logger.debug("\n:::::"+"http://" + req.getServerName().trim()	+ ":" + req.getServerPort()+loginPage);
		try {
			//System.out.println("转至登录界面");
			RequestDispatcher rd = req.getRequestDispatcher(loginPage);
			rd.forward(req,resp);
			//resp.sendRedirect("http://" + req.getServerName().trim()	+ ":" + req.getServerPort()+loginPage);
		}catch (Exception e){
			Logger.error("Login.backtoLoginPage中执行失败");
			System.out.println("Login.backtoLoginPage中执行失败");
		}
	}
	/* （非 Javadoc）
	 * @see com.nuaa.app.Login#logout(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
	 */
	public void logout(HttpServletRequest req, HttpServletResponse resp, String loginPage) {
		// TODO 自动生成方法存根
		req.getSession().invalidate();		
		//req.getSession().removeAttribute("usrlevel");
		try {
			RequestDispatcher rd = req.getRequestDispatcher(loginPage);
			rd.forward(req,resp);				
			//resp.sendRedirect(loginPage);
			Logger.error("注销成功");
		}catch (Exception e) {
			Logger.error("注销失败");
		}
	}
	public boolean checkSession(HttpServletRequest req,HttpServletResponse resp) {
		HttpSession session=req.getSession(false);
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		requestURI = requestURI.substring(contextPath.length());
		System.out.println("requestURI:"+requestURI);
		if (requestURI.equals("/login/login.jsp")
		|| requestURI.equals("/login/logiclogin.jsp")){
			System.out.println("URL符合要求");
			return true;
		}
		if (session!= null){
		//	System.out.println(session.getAttribute("userlevel"));
			System.out.println("userlevel符合要求");
			return true;
		}else{
			System.out.println("返回false");
			return false;
		}		
	}
}
