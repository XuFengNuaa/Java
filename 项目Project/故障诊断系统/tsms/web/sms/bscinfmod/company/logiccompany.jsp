<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.CompanyMod,
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
	CompanyMod um=(CompanyMod)AppInsFactory.getBean("CompanyMod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
			if ("ADD".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("company")!=null){
				hashMap.put("company",request.getParameter("company"));
			}
			if(request.getParameter("address")!=null){
				hashMap.put("address",request.getParameter("address"));
			}
			if(request.getParameter("contact_person")!=null){
				hashMap.put("contact_person",request.getParameter("contact_person"));
			}
			if(request.getParameter("phone")!=null){
				hashMap.put("phone",request.getParameter("phone"));
			}
			if(request.getParameter("email")!=null){
				hashMap.put("email",request.getParameter("email"));
			}
			if(request.getParameter("remark")!=null){
				hashMap.put("remark",request.getParameter("remark"));				
			}
			String returnValue = um.addCompany(hashMap);
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
			result=um.getQueryCompany(hashMap).toString();
		}else if("QUERYALL".equals(type)){
			result=um.getQueryCompanyAll().toString();
		}else if("DEL".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));
			}
			String returnValue = um.delCompany(hashMap);
			if ("true".equals(returnValue)){
				result="1";
			}
		}else if("EDIT".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("company")!=null){
				hashMap.put("company",request.getParameter("company"));
			}
			if(request.getParameter("address")!=null){
				hashMap.put("address",request.getParameter("address"));
			}
			if(request.getParameter("contact_person")!=null){
				hashMap.put("contact_person",request.getParameter("contact_person"));
			}
			if(request.getParameter("phone")!=null){
				hashMap.put("phone",request.getParameter("phone"));
			}
			if(request.getParameter("email")!=null){
				hashMap.put("email",request.getParameter("email"));
			}
			if(request.getParameter("remark")!=null){
				hashMap.put("remark",request.getParameter("remark"));				
			}
		   if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));
			}
			String returnEditvalue = um.editCompany(hashMap);
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
			result=um.viewCompany(hashMap).toString();	
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