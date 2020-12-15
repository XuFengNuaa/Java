<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.CircuitBoard,
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
	CircuitBoard um=(CircuitBoard)AppInsFactory.getBean("CircuitBoard");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
			if ("ADD".equals(type)){
				HashMap hashMap=new HashMap();
				if(request.getParameter("name")!=null){
					hashMap.put("name",request.getParameter("name"));
				}
				if(request.getParameter("time_one")!=null){
					hashMap.put("time_one",request.getParameter("time_one"));
				}
				if(request.getParameter("time_two")!=null){
					hashMap.put("time_two",request.getParameter("time_two"));
				}
				if(request.getParameter("remark")!=null){
					hashMap.put("remark",request.getParameter("remark"));				
				}
				String returnValue = um.addCitcuitBoard(hashMap);
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
				result=um.getQueryCircuitBoard(hashMap).toString();
			}else if("DEL".equals(type)){
				HashMap hashMap=new HashMap();
				if(request.getParameter("id")!=null){
					hashMap.put("id",request.getParameter("id"));
				}
				String returnValue = um.delCircuitBoard(hashMap);
				if ("true".equals(returnValue)){
					result="1";
				}
			}else if("VIEW".equals(type)){
				HashMap hashMap=new HashMap();
				if(request.getParameter("id")!=null){	
					hashMap.put("id",request.getParameter("id"));
				}
				result=um.viewCircuitBoard(hashMap).toString();	
			}else if("EDIT".equals(type)){
				HashMap hashMap=new HashMap();
				if(request.getParameter("name")!=null){
					hashMap.put("name",request.getParameter("name"));
				}
				if(request.getParameter("time_one")!=null){
					hashMap.put("time_one",request.getParameter("time_one"));
				}
				if(request.getParameter("time_two")!=null){
					hashMap.put("time_two",request.getParameter("time_two"));
				}
				if(request.getParameter("remark")!=null){
					hashMap.put("remark",request.getParameter("remark"));				
				}
			   if(request.getParameter("id")!=null){
					hashMap.put("id",request.getParameter("id"));
				}
				String returnEditvalue = um.editCircuitBoard(hashMap);
				if ("true".equals(returnEditvalue)){
					result="{success:true,msg:'ok'}";
				}else if("Exception".equals(returnEditvalue)){
					result="{failure:true}";
				}else{
					result="{success:true,msg:'repeat'}";
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