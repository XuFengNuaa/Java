<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.Milling_Mod,
java.io.PrintWriter,
java.net.URLEncoder,
java.sql.PreparedStatement,
java.sql.ResultSet,
java.sql.Statement,
java.sql.SQLException,
java.util.HashMap,
java.util.UUID,
java.util.*,
java.io.*,
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
	Milling_Mod um=(Milling_Mod)AppInsFactory.getBean("Milling_Mod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
			if("QUERY".equals(type)){
				HashMap hashMap=new HashMap();
				if(request.getParameter("beishu")!=null){
					hashMap.put("beishu",request.getParameter("beishu"));				
				}
				result=um.getQueryMillingAll(hashMap).toString();
		}else if("ADDALL".equals(type)){
			HashMap hashmap=new HashMap();
			hashmap.put("goodname",request.getParameter("goodname"));
			hashmap.put("partname",request.getParameter("partname")); 
			hashmap.put("goodnum",request.getParameter("goodnum")); 
			hashmap.put("partnum",request.getParameter("partnum")); 
			hashmap.put("materialname",request.getParameter("materialname")); 
			hashmap.put("materialcard",request.getParameter("materialcard")); 
			hashmap.put("materialnum",request.getParameter("materialnum")); 
			hashmap.put("materialstandard",request.getParameter("materialstandard"));  
			hashmap.put("millthick",request.getParameter("millthick")); 
			hashmap.put("milllong",request.getParameter("milllong")); 
			hashmap.put("millwide",request.getParameter("millwide"));
			hashmap.put("millnum",request.getParameter("millnum"));
			hashmap.put("milldate",request.getParameter("milldate"));
			
			String returnValue = um.allEntry(hashmap);
			if("true".equals(returnValue)){
				result = "1";
			}else{
				result = "2";
			}
		}else if("ADD".equals(type)){
			HashMap hashmap=new HashMap();
			hashmap.put("goodnum",request.getParameter("goodnum"));
			hashmap.put("partnum",request.getParameter("partnum")); 
			hashmap.put("processnum",request.getParameter("processnum")); 
			hashmap.put("process",request.getParameter("process")); 
			hashmap.put("worktype",request.getParameter("worktype")); 
			hashmap.put("farm",request.getParameter("farm")); 
			hashmap.put("readytime",request.getParameter("readytime")); 
			hashmap.put("worktime",request.getParameter("worktime"));  
			hashmap.put("groupnum",request.getParameter("groupnum")); 
			
			String returnValue = um.addEntry(hashmap);
			if("true".equals(returnValue)){
				result = "1";
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