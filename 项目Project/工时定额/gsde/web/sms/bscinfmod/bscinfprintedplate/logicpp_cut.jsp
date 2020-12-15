<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.PPcutMod,
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
PPcutMod um = (PPcutMod)AppInsFactory.getBean("PPcutMod");
String type = request.getParameter("type");

String result="0";
try{
if("QUERY".equals(type)){
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
}else if("ADD".equals(type)){
	HashMap hashmap = new HashMap();
	if(request.getParameter("zhijing")!=null){
		hashmap.put("zhijing",request.getParameter("zhijing"));
	}
	if(request.getParameter("ap")!=null){
		hashmap.put("ap",request.getParameter("ap"));
	}
	if(request.getParameter("ae")!=null){
		hashmap.put("ae",request.getParameter("ae"));
	}
	if(request.getParameter("chishu")!=null){
		hashmap.put("chishu",request.getParameter("chishu"));
	}
	if(request.getParameter("zzzs")!=null){
		hashmap.put("zzzs",request.getParameter("zzzs"));
	}
	if(request.getParameter("mcjg")!=null){
		hashmap.put("mcjg",request.getParameter("mcjg"));
	}
	if(request.getParameter("vf")!=null){
		hashmap.put("vf",request.getParameter("vf"));
	}
	if(request.getParameter("vc")!=null){
		hashmap.put("vc",request.getParameter("vc"));
	}
	if(request.getParameter("beizhu")!=null){
		hashmap.put("beizhu",request.getParameter("beizhu"));
	}
	String returnValue = um.addCutPara(hashmap);
	if("true".equals(returnValue)){
		result = "{success:true,msg:'ok'}";
	}else if("repeat".equals(returnValue)){
		result = "{success:true,msg:'repeat'}";
	}else{
		result = "{failure:true}";
	}
}else if("DEL".equals(type)){
	String id = request.getParameter("id");
	HashMap hashmap = new HashMap();
	hashmap.put("id", id);
	String returnValue = um.delCutPara(hashmap);
	if("true".equals(returnValue)){
		result="1";
	}else{
		result="0";
	}
}else if("EDIT".equals(type)){
	HashMap hashmap = new HashMap();
	hashmap.put("id",request.getParameter("id"));
	if(request.getParameter("zhijing")!=null){
		hashmap.put("zhijing",request.getParameter("zhijing"));
	}
	if(request.getParameter("ap")!=null){
		hashmap.put("ap",request.getParameter("ap"));
	}
	if(request.getParameter("ae")!=null){
		hashmap.put("ae",request.getParameter("ae"));
	}
	if(request.getParameter("chishu")!=null){
		hashmap.put("chishu",request.getParameter("chishu"));
	}
	if(request.getParameter("zzzs")!=null){
		hashmap.put("zzzs",request.getParameter("zzzs"));
	}
	if(request.getParameter("mcjg")!=null){
		hashmap.put("mcjg",request.getParameter("mcjg"));
	}
	if(request.getParameter("vf")!=null){
		hashmap.put("vf",request.getParameter("vf"));
	}
	if(request.getParameter("vc")!=null){
		hashmap.put("vc",request.getParameter("vc"));
	}
	if(request.getParameter("beizhu")!=null){
		hashmap.put("beizhu",request.getParameter("beizhu"));
	}
	String returnValue = um.editCutPara(hashmap);
	if("true".equals(returnValue)){
		result = "{success:true,msg:'ok'}";
	}else if("repeat".equals(returnValue)){
		result = "{success:true,msg:'repeat'}";
	}else{
		result = "{failure:true,msg:'2'}";
	}
}
}
catch(Exception e){
	e.printStackTrace();
	result="{success:false}";
}finally{
	response.setContentType("text/html;charset=UTF-8");
	writer.write(result);		
	writer.close();
}

%>