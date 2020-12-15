<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,
com.nuaa.app.QueryPlate,
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
QueryPlate um=(QueryPlate)AppInsFactory.getBean("QueryPlate");
PrintWriter writer = response.getWriter();
String result="0";
try{
	if("QUERY".equals(type)){
		HashMap hashmap=new HashMap();
		if(request.getParameter("start")!=null){
			hashmap.put("start",request.getParameter("start"));				
		}			
		if(request.getParameter("limit")!=null){
			hashmap.put("limit",request.getParameter("limit"));
		}
		if(request.getParameter("filter")!=null){
			hashmap.put("filter",request.getParameter("filter"));			
		}
		if(request.getParameter("order")!=null){
			hashmap.put("order",request.getParameter("order"));
		}
	result = um.QueryPlateall(hashmap).toString();
	}else if("QUERYDETAIL".equals(type)){
		HashMap hashmap=new HashMap();
		if(request.getParameter("start")!=null){
			hashmap.put("start",request.getParameter("start"));				
		}			
		if(request.getParameter("limit")!=null){
			hashmap.put("limit",request.getParameter("limit"));
		}
		if(request.getParameter("filter")!=null){
			hashmap.put("filter",request.getParameter("filter"));			
		}
		if(request.getParameter("order")!=null){
			hashmap.put("order",request.getParameter("order"));
		}
	result = um.QueryPlatedetail(hashmap).toString();
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