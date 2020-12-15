<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.WeightMod,
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
WeightMod um = (WeightMod)AppInsFactory.getBean("WeightMod");
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
		hashmap.put("chang",request.getParameter("chang"));
		hashmap.put("kuan",request.getParameter("kuan"));
		hashmap.put("gao",request.getParameter("gao"));
		hashmap.put("tiji",request.getParameter("tiji"));
		hashmap.put("mianji",request.getParameter("mianji"));
		hashmap.put("clqiangdu",request.getParameter("clqiangdu"));
		hashmap.put("clrenxing",request.getParameter("clrenxing"));
		hashmap.put("clyingdu",request.getParameter("clyingdu"));
		hashmap.put("clredaolv",request.getParameter("clredaolv"));
		hashmap.put("xiangsidu",request.getParameter("xiangsidu"));
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