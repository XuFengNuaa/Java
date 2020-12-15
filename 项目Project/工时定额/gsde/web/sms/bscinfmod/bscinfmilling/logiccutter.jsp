<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.CutterMod,
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
CutterMod um = (CutterMod)AppInsFactory.getBean("CutterMod");
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
		result = um.QueryAllCutter(hashmap).toString();
	}else if("ADD".equals(type)){
		HashMap hashmap = new HashMap();
		if(request.getParameter("cuttertype")!=null){
			hashmap.put("leixing",request.getParameter("cuttertype"));
		}
		if(request.getParameter("mingcheng")!=null){
			hashmap.put("mingcheng",request.getParameter("mingcheng"));
		}
		if(request.getParameter("bianhao")!=null){
			hashmap.put("bianhao",request.getParameter("bianhao"));
		}
		if(request.getParameter("company")!=null){
			hashmap.put("company",request.getParameter("company"));
		}
		if(request.getParameter("xinghao")!=null){
			hashmap.put("xinghao",request.getParameter("xinghao"));
		}
		if(request.getParameter("daoti")!=null){
			hashmap.put("daoti",request.getParameter("daoti"));
		}
		if(request.getParameter("tuceng")!=null){
			hashmap.put("tuceng",request.getParameter("tuceng"));
		}
		if(request.getParameter("zhijing")!=null){
			hashmap.put("zhijing",request.getParameter("zhijing"));
		}
		if(request.getParameter("zhijinggc")!=null){
			hashmap.put("zhijinggc",request.getParameter("zhijinggc"));
		}
		if(request.getParameter("daobing")!=null){
			hashmap.put("daobing",request.getParameter("daobing"));
		}
		if(request.getParameter("dbgg")!=null){
			hashmap.put("dbgg",request.getParameter("dbgg"));
		}
		if(request.getParameter("quanchang")!=null){
			hashmap.put("quanchang",request.getParameter("quanchang"));
		}
		if(request.getParameter("renchang")!=null){
			hashmap.put("renchang",request.getParameter("renchang"));
		}
		if(request.getParameter("renshu")!=null){
			hashmap.put("renshu",request.getParameter("renshu"));
		}
		if(request.getParameter("lx")!=null){
			hashmap.put("lx",request.getParameter("lx"));
		}
		if(request.getParameter("chishu")!=null){
			hashmap.put("chishu",request.getParameter("chishu"));
		}
		if(request.getParameter("djjd")!=null){
			hashmap.put("djjd",request.getParameter("djjd"));
		}
		if(request.getParameter("maxv")!=null){
			hashmap.put("maxv",request.getParameter("maxv"));
		}
		if(request.getParameter("shjg1")!=null){
			hashmap.put("shjg1",request.getParameter("shjg1"));
		}
		if(request.getParameter("shjg2")!=null){
			hashmap.put("shjg2",request.getParameter("shjg2"));
		}
		if(request.getParameter("shjg3")!=null){
			hashmap.put("shjg3",request.getParameter("shjg3"));
		}
		if(request.getParameter("xlh")!=null){
			hashmap.put("xlh",request.getParameter("xlh"));
		}
		if(request.getParameter("daojiao")!=null){
			hashmap.put("daojiao",request.getParameter("daojiao"));
		}
		if(request.getParameter("qj")!=null){
			hashmap.put("qj",request.getParameter("qj"));
		}
		if(request.getParameter("hj")!=null){
			hashmap.put("hj",request.getParameter("hj"));
		}
		if(request.getParameter("zp")!=null){
			hashmap.put("zp",request.getParameter("zp"));
		}
		if(request.getParameter("fp")!=null){
			hashmap.put("fp",request.getParameter("fp"));
		}
		if(request.getParameter("djc")!=null){
			hashmap.put("djc",request.getParameter("djc"));
		}
		if(request.getParameter("qj")!=null){
			hashmap.put("qj",request.getParameter("qj"));
		}
		if(request.getParameter("rjbj")!=null){
			hashmap.put("rjbj",request.getParameter("rjbj"));
		}
		if(request.getParameter("beizhu")!=null){
			hashmap.put("beizhu",request.getParameter("beizhu"));
		}
		String returnValue = um.AddCutter(hashmap);
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
		result = um.ViewCutter(hashmap).toString();
	}else if("DEL".equals(type)){
		String id = request.getParameter("id");
		HashMap hashmap = new HashMap();
		hashmap.put("id", id);
		String returnValue = um.DelCutter(hashmap);
		if("true".equals(returnValue)){
			result="1";
		}else{
			result="0";
		}
	}else if("EDIT".equals(type)){
		HashMap hashmap = new HashMap();
		hashmap.put("id",request.getParameter("id"));
		if(request.getParameter("cuttertype")!=null){
			hashmap.put("leixing",request.getParameter("cuttertype"));
		}
		if(request.getParameter("mingcheng")!=null){
			hashmap.put("mingcheng",request.getParameter("mingcheng"));
		}
		if(request.getParameter("bianhao")!=null){
			hashmap.put("bianhao",request.getParameter("bianhao"));
		}
		if(request.getParameter("company")!=null){
			hashmap.put("company",request.getParameter("company"));
		}
		if(request.getParameter("xinghao")!=null){
			hashmap.put("xinghao",request.getParameter("xinghao"));
		}
		if(request.getParameter("daoti")!=null){
			hashmap.put("daoti",request.getParameter("daoti"));
		}
		if(request.getParameter("tuceng")!=null){
			hashmap.put("tuceng",request.getParameter("tuceng"));
		}
		if(request.getParameter("zhijing")!=null){
			hashmap.put("zhijing",request.getParameter("zhijing"));
		}
		if(request.getParameter("zhijinggc")!=null){
			hashmap.put("zhijinggc",request.getParameter("zhijinggc"));
		}
		if(request.getParameter("daobing")!=null){
			hashmap.put("daobing",request.getParameter("daobing"));
		}
		if(request.getParameter("dbgg")!=null){
			hashmap.put("dbgg",request.getParameter("dbgg"));
		}
		if(request.getParameter("quanchang")!=null){
			hashmap.put("quanchang",request.getParameter("quanchang"));
		}
		if(request.getParameter("renchang")!=null){
			hashmap.put("renchang",request.getParameter("renchang"));
		}
		if(request.getParameter("renshu")!=null){
			hashmap.put("renshu",request.getParameter("renshu"));
		}
		if(request.getParameter("lx")!=null){
			hashmap.put("lx",request.getParameter("lx"));
		}
		if(request.getParameter("chishu")!=null){
			hashmap.put("chishu",request.getParameter("chishu"));
		}
		if(request.getParameter("djjd")!=null){
			hashmap.put("djjd",request.getParameter("djjd"));
		}
		if(request.getParameter("maxv")!=null){
			hashmap.put("maxv",request.getParameter("maxv"));
		}
		if(request.getParameter("shjg1")!=null){
			hashmap.put("shjg1",request.getParameter("shjg1"));
		}
		if(request.getParameter("shjg2")!=null){
			hashmap.put("shjg2",request.getParameter("shjg2"));
		}
		if(request.getParameter("shjg3")!=null){
			hashmap.put("shjg3",request.getParameter("shjg3"));
		}
		if(request.getParameter("xlh")!=null){
			hashmap.put("xlh",request.getParameter("xlh"));
		}
		if(request.getParameter("daojiao")!=null){
			hashmap.put("daojiao",request.getParameter("daojiao"));
		}
		if(request.getParameter("qj")!=null){
			hashmap.put("qj",request.getParameter("qj"));
		}
		if(request.getParameter("hj")!=null){
			hashmap.put("hj",request.getParameter("hj"));
		}
		if(request.getParameter("zp")!=null){
			hashmap.put("zp",request.getParameter("zp"));
		}
		if(request.getParameter("fp")!=null){
			hashmap.put("fp",request.getParameter("fp"));
		}
		if(request.getParameter("djc")!=null){
			hashmap.put("djc",request.getParameter("djc"));
		}
		if(request.getParameter("qj")!=null){
			hashmap.put("qj",request.getParameter("qj"));
		}
		if(request.getParameter("rjbj")!=null){
			hashmap.put("rjbj",request.getParameter("rjbj"));
		}
		if(request.getParameter("beizhu")!=null){
			hashmap.put("beizhu",request.getParameter("beizhu"));
		}
		String returnValue = um.EditCutter(hashmap);
		if("true".equals(returnValue)){
			result = "{success:true,msg:'ok'}";
		}else if("repeat".equals(returnValue)){
			result = "{success:true,msg:'repeat'}";
		}else{
			result = "{failure:true,msg:'2'}";
		}
	}else if("QUERYCOM".equals(type)){
		result = um.QueryAllCompany().toString();
		System.out.println(result);
	}else if("XLH".equals(type)){
		String company = request.getParameter("company");
		HashMap hashmap = new HashMap();
		hashmap.put("company",company);
		result = um.QueryXLByCom(hashmap).toString();
	}else if("ZHIJING".equals(type)){
		System.out.println(request.getParameter("xlh"));
		HashMap hashmap = new HashMap();
		String xlh = request.getParameter("xlh");
		String company = request.getParameter("company");
		hashmap.put("xlh",xlh);
		hashmap.put("company",company);
		result = um.QueryZHIJINGByXLH(hashmap).toString();
	}else if("RJBJ".equals(type)){
		System.out.println(request.getParameter("zhijing"));
		HashMap hashmap = new HashMap();
		String xlh = request.getParameter("xlh");
		String company = request.getParameter("company");
		String zhijing = request.getParameter("zhijing");
		hashmap.put("xlh",xlh);
		hashmap.put("company",company);
		hashmap.put("zhijing",zhijing);
		result = um.QueryRJBJByZJ(hashmap).toString();
	}else if("QUERYALL".equals(type)){
		HashMap hashmap = new HashMap();
		String xlh = request.getParameter("xlh");
		String company = request.getParameter("company");
		String zhijing = request.getParameter("zhijing");
		String rjbj = request.getParameter("rjbj");
		hashmap.put("xlh",xlh);
		hashmap.put("company",company);
		hashmap.put("zhijing",zhijing);
		hashmap.put("rjbj",rjbj);
		result = um.QueryDJXH(hashmap).toString();
		
	}else if("ZHIJING_COMPANY".equals(type)){
		HashMap hashmap = new HashMap();
		String company = request.getParameter("company");
		hashmap.put("company",company);
		result = um.QueryZHIJINGByCOM(hashmap).toString();
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