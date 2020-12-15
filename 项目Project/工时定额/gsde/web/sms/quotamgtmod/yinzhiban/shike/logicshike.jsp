<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.shikeMod,
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
shikeMod um = (shikeMod)AppInsFactory.getBean("shikeMod");
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
		hashmap.put("fs1",request.getParameter("fs1"));
		hashmap.put("tm1",request.getParameter("tm1"));
		hashmap.put("xb1",request.getParameter("xb1"));
		hashmap.put("zj1",request.getParameter("zj1"));
		hashmap.put("fs2",request.getParameter("fs2"));
		hashmap.put("tm2",request.getParameter("tm2"));
		hashmap.put("xb2",request.getParameter("xb2"));
		hashmap.put("zj2",request.getParameter("zj2"));
		hashmap.put("fs3",request.getParameter("fs3"));
		hashmap.put("tm3",request.getParameter("tm3"));
		hashmap.put("xb3",request.getParameter("xb3"));
		hashmap.put("zj3",request.getParameter("zj3"));
		hashmap.put("fs4",request.getParameter("fs4"));
		hashmap.put("tm4",request.getParameter("tm4"));
		hashmap.put("xb4",request.getParameter("xb4"));
		hashmap.put("zj4",request.getParameter("zj4"));
		hashmap.put("fs5",request.getParameter("fs5"));
		hashmap.put("tm5",request.getParameter("tm5"));
		hashmap.put("xb5",request.getParameter("xb5"));
		hashmap.put("zj5",request.getParameter("zj5"));
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