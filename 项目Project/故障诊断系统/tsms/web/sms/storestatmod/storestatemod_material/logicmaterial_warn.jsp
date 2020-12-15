<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,org.json.*,java.io.*,
com.nuaa.sys.util.DbUtil,
org.json.JSONObject, 
org.json.JSONArray,
com.nuaa.sys.util.PublicUtil,
java.util.UUID,
java.sql.*,
com.nuaa.sys.util.AppInsFactory,
java.sql.Date,
com.nuaa.app.Material_Warn"
%>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Expires","0");
response.setContentType("text/html;charset=UTF-8");
String type = request.getParameter("type");
String result = "";
String userlevel = (String)session.getAttribute("userlevel");
PrintWriter writer = response.getWriter();
Material_Warn un = (Material_Warn)AppInsFactory.getBean("Material_Warn");
System.out.println(type);
try{
	if("QUERY".equals(type)){
		HashMap hashmap = new HashMap();
		hashmap.put("filter", request.getParameter("filter"));
		hashmap.put("order", request.getParameter("order"));
		hashmap.put("text", request.getParameter("text"));
		hashmap.put("userlevel",userlevel);
		System.out.println("*******");
		//result = um.queryAll(hashmap).toString();
		result = un.Material_Warn(hashmap).toString();
		System.out.println(request.getParameter("text"));
	}
}catch(Exception e){
	e.printStackTrace();
}finally{
	response.setContentType("text/html;charset=UTF-8");
	writer.write(result);		
	writer.close();
}
%>