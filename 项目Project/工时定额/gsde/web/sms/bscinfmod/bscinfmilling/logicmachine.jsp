<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.MachineMod,
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
MachineMod um = (MachineMod)AppInsFactory.getBean("MachineMod");
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
	result = um.QueryAllMachine(hashmap).toString();
}else if("ADD".equals(type)){
	HashMap hashmap = new HashMap();
	if(request.getParameter("bianhao")!=null){
		hashmap.put("bianhao",request.getParameter("bianhao"));
	}
	if(request.getParameter("xinghao")!=null){
		hashmap.put("xinghao",request.getParameter("xinghao"));
	}
	if(request.getParameter("xingcheng")!=null){
		hashmap.put("xingcheng",request.getParameter("xingcheng"));
	}
	if(request.getParameter("chicun")!=null){
		hashmap.put("chicun",request.getParameter("chicun"));
	}
	if(request.getParameter("zhoushu")!=null){
		hashmap.put("zhoushu",request.getParameter("zhoushu"));
	}
	if(request.getParameter("niuju")!=null){
		hashmap.put("niuju",request.getParameter("niuju"));
	}
	if(request.getParameter("jingeili")!=null){
		hashmap.put("jingeili",request.getParameter("jingeili"));
	}
	if(request.getParameter("zhuansumax")!=null){
		hashmap.put("maxzhuansu",request.getParameter("zhuansumax"));
	}
	if(request.getParameter("zhuansumin")!=null){
		hashmap.put("minzhuansu",request.getParameter("zhuansumin"));
	}
	if(request.getParameter("vmax")!=null){
		hashmap.put("vmax",request.getParameter("vmax"));
	}
	if(request.getParameter("vmin")!=null){
		hashmap.put("vmin",request.getParameter("vmin"));
	}
	if(request.getParameter("jingdu")!=null){
		hashmap.put("jingdu",request.getParameter("jingdu"));
	}
	if(request.getParameter("power")!=null){
		hashmap.put("power",request.getParameter("power"));
	}
	if(request.getParameter("company")!=null){
		hashmap.put("company",request.getParameter("company"));
	}
	if(request.getParameter("beizhu")!=null){
		hashmap.put("beizhu",request.getParameter("beizhu"));
	}
	String returnValue = um.AddMachine(hashmap);
	if("true".equals(returnValue)){
		result = "{success:true,msg:'ok'}";
	}else if("repeat".equals(returnValue)){
		result = "{success:true,msg:'repeat'}";
	}else{
		result = "{failure:true}";
	}
}else if("VIEW".equals(type)){
	String id = request.getParameter("id");
	HashMap hashmap = new HashMap();
	hashmap.put("id",id);
	result = um.ViewMachine(hashmap).toString();
}else if("DEL".equals(type)){
	String id = request.getParameter("id");
	HashMap hashmap = new HashMap();
	hashmap.put("id", id);
	String returnValue = um.DelMachine(hashmap);
	if("true".equals(returnValue)){
		result="1";
	}else{
		result="0";
	}
}else if("EDIT".equals(type)){
	HashMap hashmap = new HashMap();
	hashmap.put("id",request.getParameter("id"));
	if(request.getParameter("bianhao")!=null){
		hashmap.put("bianhao",request.getParameter("bianhao"));
	}
	if(request.getParameter("xinghao")!=null){
		hashmap.put("xinghao",request.getParameter("xinghao"));
	}
	if(request.getParameter("xingcheng")!=null){
		hashmap.put("xingcheng",request.getParameter("xingcheng"));
	}
	if(request.getParameter("chicun")!=null){
		hashmap.put("chicun",request.getParameter("chicun"));
	}
	if(request.getParameter("zhoushu")!=null){
		hashmap.put("zhoushu",request.getParameter("zhoushu"));
	}
	if(request.getParameter("niuju")!=null){
		hashmap.put("niuju",request.getParameter("niuju"));
	}
	if(request.getParameter("jingeili")!=null){
		hashmap.put("jingeili",request.getParameter("jingeili"));
	}
	if(request.getParameter("zhuansumax")!=null){
		hashmap.put("maxzhuansu",request.getParameter("zhuansumax"));
	}
	if(request.getParameter("zhuansumin")!=null){
		hashmap.put("minzhuansu",request.getParameter("zhuansumin"));
	}
	if(request.getParameter("vmax")!=null){
		hashmap.put("vmax",request.getParameter("vmax"));
	}
	if(request.getParameter("vmin")!=null){
		hashmap.put("vmin",request.getParameter("vmin"));
	}
	if(request.getParameter("jingdu")!=null){
		hashmap.put("jingdu",request.getParameter("jingdu"));
	}
	if(request.getParameter("power")!=null){
		hashmap.put("power",request.getParameter("power"));
	}
	if(request.getParameter("company")!=null){
		hashmap.put("company",request.getParameter("company"));
	}
	if(request.getParameter("beizhu")!=null){
		hashmap.put("beizhu",request.getParameter("beizhu"));
	}
	String returnValue = um.EditMachine(hashmap);
	if("true".equals(returnValue)){
		result = "{success:true,msg:'ok'}";
	}else if("repeat".equals(returnValue)){
		result = "{success:true,msg:'repeat'}";
	}else{
		result = "{failure:true,msg:'2'}";
	}
}else if("QUERYALL".equals(type)){
	HashMap hashmap = new HashMap();
	result=um.getQueryMachineAll().toString();
	System.out.println(result);
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