<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.UsrInfMod,
java.io.PrintWriter,
java.net.URLEncoder,
java.sql.PreparedStatement,
java.sql.ResultSet,
java.sql.Statement,
java.sql.SQLException,
java.util.HashMap,
java.util.UUID,
com.nuaa.sys.util.DbUtil,
com.nuaa.sys.util.PublicUtil,
com.nuaa.sys.util.IResultSetProcessor,
com.nuaa.sys.util.IArrayPreparedStatementProcessor,
com.nuaa.sys.util.IStatementProcessor;"
%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");
	String userid=(String)session.getAttribute("userid");
	String stuff_num=(String)session.getAttribute("userid");
	UsrInfMod uim=(UsrInfMod)AppInsFactory.getBean("UsrInfMod");
	PrintWriter writer = response.getWriter();
	String result="0";
if ("USREDITPWD".equals(type)){
		try {
			HashMap hashMap=new HashMap();
			hashMap.put("userid",userid);
			System.out.println(userid);
			if(request.getParameter("pwd")!=null){
				hashMap.put("pwd",request.getParameter("pwd"));
			}
			if(request.getParameter("pwd1")!=null){
				hashMap.put("pwd1",request.getParameter("pwd1"));
			}
			boolean returnValue=uim.editpwdUsr(hashMap);
			if (returnValue){
				result="{success:true,wid:'ok'}";
			}else{
				result="{success:true,wid:'旧密码错误'}";
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			response.getWriter().write(result);
			response.getWriter().close();	
		}

        }else if("USRVIEW".equals(type)){
			try {			
				HashMap hashMap=new HashMap();
				hashMap.put("stuff_num",stuff_num);
				result=uim.viewUsr(hashMap).toString();	
			}catch (Exception e){
				e.printStackTrace();
			}finally{	
				response.getWriter().write(result);
				response.getWriter().close();	
			}
		}
         
	
%>
