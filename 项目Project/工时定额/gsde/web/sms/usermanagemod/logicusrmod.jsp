<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.UsrMod,
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
	String user_num=(String)session.getAttribute("userid");
	UsrMod um=(UsrMod)AppInsFactory.getBean("UsrMod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
		if ("USRADD".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("stuff_num")!=null){
				hashMap.put("stuff_num",request.getParameter("stuff_num"));
			}
			if(request.getParameter("realname")!=null){
				hashMap.put("realname",request.getParameter("realname"));
			}
			if(request.getParameter("password")!=null){
				hashMap.put("password",request.getParameter("password"));
			}
			String returnValue = um.addUsr(hashMap);
			if ("true".equals(returnValue)){
				result="{success:true,msg:'ok'}";
			}else if("Exception".equals(returnValue)){
				result="{failure:true}";
			}else{
				result="{success:true,msg:'该用户已经存在！'}";
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
			result=um.getQueryUsr(hashMap).toString();
		}else if("DEL".equals(type)){
			HashMap hashMap=new HashMap();
			hashMap.put("user_num",user_num);
			if(request.getParameter("stuff_num")!=null){
				hashMap.put("stuff_num",request.getParameter("stuff_num"));
			}
			boolean returnnum=um.checkNum(hashMap);
			if(returnnum){
				if (um.delUsr(hashMap)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result="{failure:true}";
			}
		}else if("EDIT".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("stuff_num")!=null){
				hashMap.put("stuff_num",request.getParameter("stuff_num"));
			}
			if(request.getParameter("realname")!=null){
				hashMap.put("realname",request.getParameter("realname"));
			}
			if(request.getParameter("password")!=null){
				hashMap.put("password",request.getParameter("password"));
			}
			if(request.getParameter("uid")!=null){
				hashMap.put("id",request.getParameter("uid"));
			}			
			String returnValue = um.modiUser(hashMap);
			if("true".equals(returnValue)){
				result="{success:true}";
			}else{
				result="{success:false}";
			}
		}else if("VIEW".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("stuff_num")!=null){	
				hashMap.put("stuff_num",request.getParameter("stuff_num"));
			}
			result=um.viewUsr(hashMap).toString();	
			System.out.println(result);
		}else if("QUERYTEAM".equals(type)){			
			result=um.allTeamLeader().toString();	
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