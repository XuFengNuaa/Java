<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.MaterialMod,
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
MaterialMod um = (MaterialMod)AppInsFactory.getBean("MaterialMod");
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
	result = um.QueryAllMaterial(hashmap).toString();
}else if("ADD".equals(type)){
	HashMap hashmap = new HashMap();
	if(request.getParameter("bianhao")!=null){
		hashmap.put("bianhao",request.getParameter("bianhao"));
	}
	if(request.getParameter("mingcheng")!=null){
		hashmap.put("mingcheng",request.getParameter("mingcheng"));
	}
	if(request.getParameter("rechuli")!=null){
		hashmap.put("rechuli",request.getParameter("rechuli"));
	}
	if(request.getParameter("yinyong")!=null){
		hashmap.put("yinyong",request.getParameter("yinyong"));
	}
	if(request.getParameter("leixing")!=null){
		hashmap.put("leixing",request.getParameter("leixing"));
	}
	if(request.getParameter("yingdu")!=null){
		hashmap.put("yingdu",request.getParameter("yingdu"));
	}
	if(request.getParameter("kangla")!=null){
		hashmap.put("kangla",request.getParameter("kangla"));
	}
	if(request.getParameter("yanshen")!=null){
		hashmap.put("yanshen",request.getParameter("yanshen"));
	}
	if(request.getParameter("chongji")!=null){
		hashmap.put("chongji",request.getParameter("chongji"));
	}
	if(request.getParameter("midu")!=null){
		hashmap.put("midu",request.getParameter("midu"));
	}
	if(request.getParameter("repengzhang")!=null){
		hashmap.put("repengzhang",request.getParameter("repengzhang"));
	}
	if(request.getParameter("daore")!=null){
		hashmap.put("daore",request.getParameter("daore"));
	}
	if(request.getParameter("qufu")!=null){
		hashmap.put("qufu",request.getParameter("qufu"));
	}
	if(request.getParameter("company")!=null){
		hashmap.put("company",request.getParameter("company"));
	}
	if(request.getParameter("beizhu")!=null){
		hashmap.put("beizhu",request.getParameter("beizhu"));
	}
	if(request.getParameter("paihao")!=null){
		hashmap.put("paihao",request.getParameter("paihao"));
	}
	String returnValue = um.AddMaterial(hashmap);
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
	result = um.ViewMaterial(hashmap).toString();
}else if("DEL".equals(type)){
	String id = request.getParameter("id");
	HashMap hashmap = new HashMap();
	hashmap.put("id", id);
	String returnValue = um.DelMaterial(hashmap);
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
	if(request.getParameter("mingcheng")!=null){
		hashmap.put("mingcheng",request.getParameter("mingcheng"));
	}
	if(request.getParameter("rechuli")!=null){
		hashmap.put("rechuli",request.getParameter("rechuli"));
	}
	if(request.getParameter("yinyong")!=null){
		hashmap.put("yinyong",request.getParameter("yinyong"));
	}
	if(request.getParameter("leixing")!=null){
		hashmap.put("leixing",request.getParameter("leixing"));
	}
	if(request.getParameter("yingdu")!=null){
		hashmap.put("yingdu",request.getParameter("yingdu"));
	}
	if(request.getParameter("kangla")!=null){
		hashmap.put("kangla",request.getParameter("kangla"));
	}
	if(request.getParameter("yanshen")!=null){
		hashmap.put("yanshen",request.getParameter("yanshen"));
	}
	if(request.getParameter("chongji")!=null){
		hashmap.put("chongji",request.getParameter("chongji"));
	}
	if(request.getParameter("midu")!=null){
		hashmap.put("midu",request.getParameter("midu"));
	}
	if(request.getParameter("repengzhang")!=null){
		hashmap.put("repengzhang",request.getParameter("repengzhang"));
	}
	if(request.getParameter("daore")!=null){
		hashmap.put("daore",request.getParameter("daore"));
	}
	if(request.getParameter("qufu")!=null){
		hashmap.put("qufu",request.getParameter("qufu"));
	}
	if(request.getParameter("company")!=null){
		hashmap.put("company",request.getParameter("company"));
	}
	if(request.getParameter("beizhu")!=null){
		hashmap.put("beizhu",request.getParameter("beizhu"));
	}
	if(request.getParameter("paihao")!=null){
		hashmap.put("paihao",request.getParameter("paihao"));
	}
	String returnValue = um.EditMaterial(hashmap);
	if("true".equals(returnValue)){
		result = "{success:true,msg:'ok'}";
	}else if("repeat".equals(returnValue)){
		result = "{success:true,msg:'repeat'}";
	}else{
		result = "{failure:true,msg:'2'}";
	}
}else if("QUERYALL".equals(type)){
	result=um.getQueryMaterialAll().toString();
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