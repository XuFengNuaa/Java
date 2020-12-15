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
		HashMap hashmap = new HashMap();
		//hashmap.put("feature",feature);
		hashmap.put("ps1",request.getParameter("ps1"));
		hashmap.put("dm1",request.getParameter("dm1"));
		hashmap.put("sm1",request.getParameter("sm1"));
		hashmap.put("dc1",request.getParameter("dc1"));
		hashmap.put("bg1",request.getParameter("bg1"));
		hashmap.put("xb1",request.getParameter("xb1"));
		hashmap.put("ps2",request.getParameter("ps2"));
		hashmap.put("dm2",request.getParameter("dm2"));
		hashmap.put("sm2",request.getParameter("sm2"));
		hashmap.put("dc2",request.getParameter("dc2"));
		hashmap.put("bg2",request.getParameter("bg2"));
		hashmap.put("xb2",request.getParameter("xb2"));
		hashmap.put("ps3",request.getParameter("ps3"));
		hashmap.put("dm3",request.getParameter("dm3"));
		hashmap.put("sm3",request.getParameter("sm3"));
		hashmap.put("dc3",request.getParameter("dc3"));
		hashmap.put("bg3",request.getParameter("bg3"));
		hashmap.put("xb3",request.getParameter("xb3"));
		hashmap.put("ps4",request.getParameter("ps4"));
		hashmap.put("dm4",request.getParameter("dm4"));
		hashmap.put("sm4",request.getParameter("sm4"));
		hashmap.put("dc4",request.getParameter("dc4"));
		hashmap.put("bg4",request.getParameter("bg4"));
		hashmap.put("xb4",request.getParameter("xb4"));
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