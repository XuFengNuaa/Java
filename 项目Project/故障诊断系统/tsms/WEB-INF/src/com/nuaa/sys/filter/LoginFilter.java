/**
 * 
 */
package com.nuaa.sys.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nuaa.app.Login;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.sys.util.Logger;
import com.nuaa.sys.util.base.SessionKeys;

/**
 * @author mahao
 *
 */
public class LoginFilter implements Filter {
	private String loginPage = null;

	private String failPage = null;

	private String homePage = null;

	private FilterConfig fc = null;
	/* （非 Javadoc）
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO 自动生成方法存根

	}

	/* （非 Javadoc）
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest sreq, ServletResponse sresp,
			FilterChain chain) throws IOException, ServletException {
		// TODO 自动生成方法存根
		Logger.debug("LoginFilter begin...");
		HttpServletRequest req = (HttpServletRequest) sreq;
		// 设置Http编码
		req.setCharacterEncoding("UTF-8");
		Login lg=(Login)AppInsFactory.getBean("Login");
		HttpServletResponse resp = (HttpServletResponse) sresp;
		// TODO instance operation must to do here
		try {
			if (this.homePage == null || this.homePage.equals(""))
				throw new Exception("请在web.xml里配置LoginFilter的homePage参数!");
			//判断url是否是/epstar/login/logout.jsp,是则登出
			String uri = req.getRequestURI();
			//System.out.println("\nuri是:"+uri+"\nloginPage是:"+loginPage);
			if ("/tsms/login/logout.jsp".equals(uri)) {
				System.out.println("here");				
				lg.logout(req,resp,loginPage);				
			}else if("/tsms/login/logiclogin.jsp".equals(uri)){
				System.out.println("访问loginlogin页面");
			}else{
				System.out.println("url:"+uri);
				if (!lg.checkSession(req,resp)) {
					System.out.println("进入login");
					lg.backtoLoginPage(req, resp,loginPage);
				}
			}
		} catch (Exception ex) {
			//			List errs = new ArrayList();
			//			errs.add(ex.getMessage());
			//			req.setAttribute(SessionKeys.REQUEST_ERROR_MSG, errs);
			//SessionKeys.clearSession(session);
			//			this.fc.getServletContext().getRequestDispatcher(this.failPage)
			//					.forward(req, resp);
		}
		chain.doFilter(req, resp);
		Logger.debug("LoginFilter end...");
	}
	/* （非 Javadoc）
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		// TODO 自动生成方法存根
		System.out.println("System LoginFilter init...");
		this.fc = config;
		String gp = config.getInitParameter("LoginPage");
		if (null != gp) {
			gp = gp.trim();
			if (gp.length() > 0){
				this.loginPage = gp;
			}			
		}
		System.out.println(this.loginPage);
		this.failPage = config.getInitParameter("FailPage");
		this.homePage = config.getInitParameter("HomePage");
	}
}
