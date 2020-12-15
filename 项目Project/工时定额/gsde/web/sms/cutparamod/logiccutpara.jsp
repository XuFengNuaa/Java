<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.CutterParaMod,
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
CutterParaMod um = (CutterParaMod)AppInsFactory.getBean("CutterParaMod");
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
	if(request.getParameter("bianhao")!=null){
		hashmap.put("bianhao",request.getParameter("bianhao"));
	}
	 if(request.getParameter("jgyl")!=null){
		hashmap.put("jgyl",request.getParameter("jgyl"));
	} 
	if(request.getParameter("jcxh")!=null){
		hashmap.put("jcxh",request.getParameter("jcxh"));
	}
	if(request.getParameter("clph")!=null){
		hashmap.put("clph",request.getParameter("clph"));
	}
	if(request.getParameter("djcs")!=null){
		hashmap.put("djcs",request.getParameter("djcs"));
	}
	if(request.getParameter("djxlh")!=null){
		hashmap.put("djxlh",request.getParameter("djxlh"));
	}
	if(request.getParameter("zhijing")!=null){
		hashmap.put("zhijing",request.getParameter("zhijing"));
	}
	if(request.getParameter("rjbj")!=null){
		hashmap.put("rjbj",request.getParameter("rjbj"));
	}
	if(request.getParameter("jgfs")!=null){
		hashmap.put("jgfs",request.getParameter("jgfs"));
	}
	if(request.getParameter("ap")!=null){
		hashmap.put("ap",request.getParameter("ap"));
	}
	if(request.getParameter("ae")!=null){
		hashmap.put("ae",request.getParameter("ae"));
	}
	if(request.getParameter("zzzs")!=null){
		hashmap.put("zzzs",request.getParameter("zzzs"));
	}
	if(request.getParameter("xsl")!=null){
		hashmap.put("xsl",request.getParameter("xsl"));
	}
	if(request.getParameter("mcjg")!=null){
		hashmap.put("mcjg",request.getParameter("mcjg"));
	}
	if(request.getParameter("dtlx")!=null){
		hashmap.put("dtlx",request.getParameter("dtlx"));
	}
	if(request.getParameter("lqy")!=null){
		hashmap.put("lqy",request.getParameter("lqy"));
	}if(request.getParameter("lqfs")!=null){
		hashmap.put("lqfs",request.getParameter("lqfs"));
	}
	if(request.getParameter("clqcl")!=null){
		hashmap.put("clqcl",request.getParameter("clqcl"));
	}
	if(request.getParameter("fx")!=null){
		hashmap.put("fx",request.getParameter("fx"));
	}
	if(request.getParameter("fy")!=null){
		hashmap.put("fy",request.getParameter("fy"));
	}
	if(request.getParameter("fz")!=null){
		hashmap.put("fz",request.getParameter("fz"));
	}
	if(request.getParameter("gl")!=null){
		hashmap.put("gl",request.getParameter("gl"));
	}
	if(request.getParameter("nj")!=null){
		hashmap.put("nj",request.getParameter("nj"));
	}
	if(request.getParameter("bx")!=null){
		hashmap.put("bx",request.getParameter("bx"));
	}
	if(request.getParameter("jcbw")!=null){
		hashmap.put("jcbw",request.getParameter("jcbw"));
	}
	if(request.getParameter("rbwq")!=null){
		hashmap.put("rbwq",request.getParameter("rbwq"));
	}
	if(request.getParameter("dtyl")!=null){
		hashmap.put("dtyl",request.getParameter("dtyl"));
	}
	if(request.getParameter("ccddj")!=null){
		hashmap.put("ccddj",request.getParameter("ccddj"));
	}
	if(request.getParameter("qxwd")!=null){
		hashmap.put("qxwd",request.getParameter("qxwd"));
	}
	if(request.getParameter("yzr")!=null){
		hashmap.put("yzr",request.getParameter("yzr"));
	}
	if(request.getParameter("sjly")!=null){
		hashmap.put("sjly",request.getParameter("sjly"));
	}
	if(request.getParameter("xxfs")!=null){
		hashmap.put("xxfs",request.getParameter("xxfs"));
	}
	if(request.getParameter("gxtj")!=null){
		hashmap.put("gxtj",request.getParameter("gxtj"));
	}
	if(request.getParameter("yyzt")!=null){
		hashmap.put("yyzt",request.getParameter("yyzt"));
	}
	if(request.getParameter("djsm")!=null){
		hashmap.put("djsm",request.getParameter("djsm"));
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
}
else if("DEL".equals(type)){
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
	if(request.getParameter("bianhao")!=null){
		hashmap.put("bianhao",request.getParameter("bianhao"));
	}
	 if(request.getParameter("jgyl")!=null){
		hashmap.put("jgyl",request.getParameter("jgyl"));
	} 
	if(request.getParameter("jcxh")!=null){
		hashmap.put("jcxh",request.getParameter("jcxh"));
	}
	if(request.getParameter("clph")!=null){
		hashmap.put("clph",request.getParameter("clph"));
	}
	if(request.getParameter("djcs")!=null){
		hashmap.put("djcs",request.getParameter("djcs"));
	}
	if(request.getParameter("djxlh")!=null){
		hashmap.put("djxlh",request.getParameter("djxlh"));
	}
	if(request.getParameter("zhijing")!=null){
		hashmap.put("zhijing",request.getParameter("zhijing"));
	}
	if(request.getParameter("rjbj")!=null){
		hashmap.put("rjbj",request.getParameter("rjbj"));
	}
	if(request.getParameter("jgfs")!=null){
		hashmap.put("jgfs",request.getParameter("jgfs"));
	}
	if(request.getParameter("ap")!=null){
		hashmap.put("ap",request.getParameter("ap"));
	}
	if(request.getParameter("ae")!=null){
		hashmap.put("ae",request.getParameter("ae"));
	}
	if(request.getParameter("zzzs")!=null){
		hashmap.put("zzzs",request.getParameter("zzzs"));
	}
	if(request.getParameter("xsl")!=null){
		hashmap.put("xsl",request.getParameter("xsl"));
	}
	if(request.getParameter("mcjg")!=null){
		hashmap.put("mcjg",request.getParameter("mcjg"));
	}
	if(request.getParameter("dtlx")!=null){
		hashmap.put("dtlx",request.getParameter("dtlx"));
	}
	if(request.getParameter("lqy")!=null){
		hashmap.put("lqy",request.getParameter("lqy"));
	}if(request.getParameter("lqfs")!=null){
		hashmap.put("lqfs",request.getParameter("lqfs"));
	}
	if(request.getParameter("clqcl")!=null){
		hashmap.put("clqcl",request.getParameter("clqcl"));
	}
	if(request.getParameter("fx")!=null){
		hashmap.put("fx",request.getParameter("fx"));
	}
	if(request.getParameter("fy")!=null){
		hashmap.put("fy",request.getParameter("fy"));
	}
	if(request.getParameter("fz")!=null){
		hashmap.put("fz",request.getParameter("fz"));
	}
	if(request.getParameter("gl")!=null){
		hashmap.put("gl",request.getParameter("gl"));
	}
	if(request.getParameter("nj")!=null){
		hashmap.put("nj",request.getParameter("nj"));
	}
	if(request.getParameter("bx")!=null){
		hashmap.put("bx",request.getParameter("bx"));
	}
	if(request.getParameter("jcbw")!=null){
		hashmap.put("jcbw",request.getParameter("jcbw"));
	}
	if(request.getParameter("rbwq")!=null){
		hashmap.put("rbwq",request.getParameter("rbwq"));
	}
	if(request.getParameter("dtyl")!=null){
		hashmap.put("dtyl",request.getParameter("dtyl"));
	}
	if(request.getParameter("ccddj")!=null){
		hashmap.put("ccddj",request.getParameter("ccddj"));
	}
	if(request.getParameter("qxwd")!=null){
		hashmap.put("qxwd",request.getParameter("qxwd"));
	}
	if(request.getParameter("yzr")!=null){
		hashmap.put("yzr",request.getParameter("yzr"));
	}
	if(request.getParameter("sjly")!=null){
		hashmap.put("sjly",request.getParameter("sjly"));
	}
	if(request.getParameter("xxfs")!=null){
		hashmap.put("xxfs",request.getParameter("xxfs"));
	}
	if(request.getParameter("gxtj")!=null){
		hashmap.put("gxtj",request.getParameter("gxtj"));
	}
	if(request.getParameter("yyzt")!=null){
		hashmap.put("yyzt",request.getParameter("yyzt"));
	}
	if(request.getParameter("djsm")!=null){
		hashmap.put("djsm",request.getParameter("djsm"));
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