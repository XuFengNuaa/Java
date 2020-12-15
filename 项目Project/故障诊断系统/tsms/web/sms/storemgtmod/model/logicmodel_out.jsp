<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.StoreMeatool,com.nuaa.app.Model_Out,
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
	Model_Out un = (Model_Out)AppInsFactory.getBean("Model_Out");
	PrintWriter writer = response.getWriter();
	String result="0";
	
	try{
		if("getNowtime".equals(type)){
			   String nowtime="";
			   nowtime = PublicUtil.getSqlDate().toString();
			   result="{success:true,nowtime:'"+nowtime+"'}";
		}else if("QUERY".equals(type)){
			HashMap hashMap=new HashMap();
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashMap.put("order",request.getParameter("order"));
			}
			result = un.queryAllModel(hashMap).toString();
		}else if("OUT".equals(type)){
			HashMap hashMap=new HashMap();
     		if(request.getParameter("modelnum")!=null){
				hashMap.put("modelnum",request.getParameter("modelnum"));				
			}			
			if(request.getParameter("inid")!=null){
				hashMap.put("inid",request.getParameter("inid"));
			}
			if(request.getParameter("outcount")!=null){
				hashMap.put("outcount",request.getParameter("outcount"));			
			}
			if(request.getParameter("outdate")!=null){
				hashMap.put("outdate",request.getParameter("outdate"));
			}if(request.getParameter("getperson")!=null){
				hashMap.put("getperson",request.getParameter("getperson"));
			}
			if("true".equals(un.model_Out(hashMap))){
				result = "success";
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