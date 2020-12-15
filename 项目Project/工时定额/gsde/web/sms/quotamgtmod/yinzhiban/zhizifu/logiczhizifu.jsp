<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.tuxingzhuanyiMod,
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
PrintWriter writer = response.getWriter();
tuxingzhuanyiMod um = (tuxingzhuanyiMod)AppInsFactory.getBean("tuxingzhuanyiMod");
String type = request.getParameter("type");
String result="0";
//String feature = request.getParameter("feature");

try{
	if("LOAD".equals(type)){
		//HashMap hashmap = new HashMap();
		//hashmap.put("feature",feature);
		result = um.queryWeight().toString();
	}else if("EDIT".equals(type)){
		result = "1";
		}
	
}catch(Exception e){
	e.printStackTrace();
	result="{success:false}";
}finally{
	response.setContentType("text/html;charset=UTF-8");
	writer.write(result);		
	writer.close();
}
%>