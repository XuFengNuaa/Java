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
com.nuaa.app.Printedplate_Mod"
%>
<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Expires","0");
response.setContentType("text/html;charset=UTF-8");
String type = request.getParameter("type");
String result = "";

Printedplate_Mod um=(Printedplate_Mod)AppInsFactory.getBean("Printedplate_Mod");
//Material_Mod un=(Material_Mod)AppInsFactory.getBean("Material_Mod");
PrintWriter writer = response.getWriter();
try{
	if("ADDALL".equals(type)){
		HashMap hashmap=new HashMap();
		hashmap.put("goodname",request.getParameter("goodname"));
		hashmap.put("partname",request.getParameter("partname")); 
		hashmap.put("goodnum",request.getParameter("goodnum")); 
		hashmap.put("partnum",request.getParameter("partnum")); 
		hashmap.put("materialname",request.getParameter("materialname")); 
		hashmap.put("materialcard",request.getParameter("materialcard")); 
		hashmap.put("materialnum",request.getParameter("materialnum")); 
		hashmap.put("materialstandard",request.getParameter("materialstandard"));  
		hashmap.put("platethick",request.getParameter("platethick")); 
		hashmap.put("platelong",request.getParameter("platelong")); 
		hashmap.put("platewide",request.getParameter("platewide"));
		hashmap.put("platenum",request.getParameter("platenum"));
		hashmap.put("platedate",request.getParameter("platedate"));
		
		String returnValue = um.allEntry(hashmap);
		if("true".equals(returnValue)){
			result = "1";
		}else{
			result = "2";
		}
	}else if("ADD".equals(type)){
		HashMap hashmap=new HashMap();
		hashmap.put("goodnum",request.getParameter("goodnum"));
		hashmap.put("partnum",request.getParameter("partnum")); 
		hashmap.put("processnum",request.getParameter("processnum")); 
		hashmap.put("process",request.getParameter("process")); 
		hashmap.put("worktype",request.getParameter("worktype")); 
		hashmap.put("farm",request.getParameter("farm")); 
		hashmap.put("readytime",request.getParameter("readytime")); 
		hashmap.put("worktime",request.getParameter("worktime"));  
		hashmap.put("groupnum",request.getParameter("groupnum")); 
		
		String returnValue = um.addEntry(hashmap);
		if("true".equals(returnValue)){
			result = "1";
		}else{
			result = "2";
		}	
	}else if("QUERY".equals(type)){
		HashMap hashmap = new HashMap();
		if(request.getParameter("start")!=""){
			hashmap.put("start",request.getParameter("start"));
		}
		if(request.getParameter("limit")!=""){
			hashmap.put("limit",request.getParameter("limit"));
		}
		if(request.getParameter("filter")!=""){
			hashmap.put("filter",request.getParameter("filter"));
		}
		if(request.getParameter("order")!=""){
			hashmap.put("order",request.getParameter("order"));
		}
		result = um.QueryAllCutPara(hashmap).toString();
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