<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.zhimubanMod,
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
zhimubanMod um = (zhimubanMod)AppInsFactory.getBean("zhimubanMod");
String type = request.getParameter("type");
String result="0";
try{
	if("LOAD".equals(type)){
		result = um.queryWeight().toString();
	}else if("EDIT".equals(type)){
		HashMap hashmap = new HashMap();
		hashmap.put("sheji",request.getParameter("sheji"));
		hashmap.put("gh",request.getParameter("gh"));
		hashmap.put("xiuban",request.getParameter("xiuban"));
		String returnValue = um.editWeight(hashmap);
		if("true".equals(returnValue)){
			result = "1";
		}else{
			result = "2";
		}
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