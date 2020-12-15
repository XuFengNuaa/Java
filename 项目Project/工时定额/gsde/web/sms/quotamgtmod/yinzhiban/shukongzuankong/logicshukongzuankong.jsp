<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.shukongzuankongMod,
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
shukongzuankongMod um = (shukongzuankongMod)AppInsFactory.getBean("shukongzuankongMod");
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
		hashmap.put("zk2",request.getParameter("zk2"));
		hashmap.put("zk4",request.getParameter("zk4"));
		hashmap.put("zk6",request.getParameter("zk6"));
		hashmap.put("js1",request.getParameter("js1"));
		hashmap.put("js2",request.getParameter("js2"));
		hashmap.put("js3",request.getParameter("js3"));
		hashmap.put("sd",request.getParameter("sd"));
		hashmap.put("jd",request.getParameter("jd"));
		hashmap.put("dk",request.getParameter("dk"));
		hashmap.put("zj",request.getParameter("zj"));
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