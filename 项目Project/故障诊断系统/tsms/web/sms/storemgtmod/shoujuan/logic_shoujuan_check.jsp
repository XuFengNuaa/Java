<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.MillSim_Mod,
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
	MillSim_Mod um=(MillSim_Mod)AppInsFactory.getBean("MillSim_Mod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
		if("QUERYDALEI".equals(type)){
		result = um.QueryAllDalei().toString();
		}else if("QUERYXIAOLEI".equals(type)){
			HashMap hashmap = new HashMap();
			hashmap.put("dalei",request.getParameter("dalei"));
			result = um.QueryAllXiaoleiByDalei(hashmap).toString();
		}else if("QUERYMATERIAL".equals(type)){
			result=um.getQueryMaterialAll().toString();
		}else if("QUERYTUHAO".equals(type)){
			result=um.getQuerytuhaoAll().toString();
		}else if("QUERYMACHINE".equals(type)){
			result=um.getQueryMachineAll().toString();
		}else if("QUERYSIM".equals(type)){
			result=um.getQuerySimAll().toString();
		}else if("QUERYSIMALL".equals(type)){
			HashMap hashmap=new HashMap();
			if(request.getParameter("start")!=null){
				hashmap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashmap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashmap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashmap.put("order",request.getParameter("order"));
			}
			result = um.QueryMilldetail(hashmap).toString();
		}else if("QUERYSIMVIEW".equals(type)){
			String part_num = request.getParameter("part_num");
			HashMap hashmap = new HashMap();
			hashmap.put("part_num",part_num);
			result = um.SimView(hashmap).toString();	
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