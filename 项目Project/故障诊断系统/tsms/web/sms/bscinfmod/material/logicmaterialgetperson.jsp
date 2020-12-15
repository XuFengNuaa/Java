<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,
com.nuaa.sys.util.AppInsFactory,
com.nuaa.app.materialgetperson,
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
	materialgetperson um=(materialgetperson)AppInsFactory.getBean("materialgetperson");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
			if ("ADD".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("materialgetperson")!=null){
				hashMap.put("materialgetperson",request.getParameter("materialgetperson"));
			}
			if(request.getParameter("materialgetname")!=null){
				hashMap.put("materialgetname",request.getParameter("materialgetname"));
			}
			String returnValue = um.addmaterialgetperson(hashMap);
			if ("true".equals(returnValue)){
				result="{success:true,msg:'ok'}";
			}else if("Exception".equals(returnValue)){
				result="{failure:true}";
			}else{
				result="{success:true,msg:'repeat'}";
			}
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
			result=um.getQuerymaterialgetperson(hashMap).toString();
		}/* else if("QUERYALL".equals(type)){
			result=um.getQuerymaterialgetpersonAll().toString();
		}else if("EDIT".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("materialgetperson")!=null){
				hashMap.put("materialgetperson",request.getParameter("materialgetperson"));
			}
			if(request.getParameter("materialgetname")!=null){
				hashMap.put("materialgetname",request.getParameter("materialgetname"));
			}
		   if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));
			}
			String returnEditvalue = um.editmaterialgetperson(hashMap);
			if ("true".equals(returnEditvalue)){
				result="{success:true,msg:'ok'}";
			}else if("Exception".equals(returnEditvalue)){
				result="{failure:true}";
			}else{
				result="{success:true,msg:'repeat'}";
			}			
		}else if("VIEW".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){	
				hashMap.put("id",request.getParameter("id"));
			}
			result=um.viewmaterialgetperson(hashMap).toString();	
		} */
		else if("DEL".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));
			}
			String returnValue = um.delmaterialgetperson(hashMap);
			if ("true".equals(returnValue)){
				result="1";
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