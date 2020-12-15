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
com.nuaa.app.Material_Change,
com.nuaa.app.Material_Out"
%>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Expires","0");
response.setContentType("text/html;charset=UTF-8");
String type = request.getParameter("type");
String result = "";
String userlevel = (String)session.getAttribute("userlevel");
String realname = (String)session.getAttribute("username");
PrintWriter writer = response.getWriter();
Material_Out um = (Material_Out)AppInsFactory.getBean("Material_Out");
Material_Change un = (Material_Change)AppInsFactory.getBean("Material_Change");
System.out.println(type);
try{
	if("QUERY".equals(type)){
		HashMap hashmap = new HashMap();
		hashmap.put("filter", request.getParameter("filter"));
		hashmap.put("order", request.getParameter("order"));
		//hashmap.put("start", request.getParameter("start"));
		//hashmap.put("limit", request.getParameter("limit"));
		hashmap.put("userlevel",userlevel);
		System.out.println("*******");
		result = um.queryAll(hashmap).toString();
	}else if("CHANGE".equals(type)){
		HashMap hashmap = new HashMap();
		hashmap.put("ordernum", request.getParameter("ordernum"));
		hashmap.put("kuname", request.getParameter("kuname"));
		hashmap.put("newkuname", request.getParameter("newkuname"));
		hashmap.put("shelfnum", request.getParameter("shelfnum"));
		hashmap.put("newshelfnum", request.getParameter("newshelfnum"));
		hashmap.put("changedate", request.getParameter("changedate"));
		hashmap.put("changecount", request.getParameter("changecount"));
		hashmap.put("realname", realname);
		hashmap.put("id", request.getParameter("id"));
		System.out.println("nininiiiiiiiiiiiiiii");
		if("true".equals(un.changeMaterial(hashmap).toString())){
			result = "1";
		}else{
			result = "2";
		}
	}
}catch(Exception e){
	e.printStackTrace();
}finally{
	response.setContentType("text/html;charset=UTF-8");
	writer.write(result);		
	writer.close();
}
%>