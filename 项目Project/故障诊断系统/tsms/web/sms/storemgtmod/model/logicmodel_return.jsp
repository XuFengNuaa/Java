<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.StoreMeatool,com.nuaa.app.Model_Return,
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
	Model_Return un = (Model_Return)AppInsFactory.getBean("Model_Return");
	PrintWriter writer = response.getWriter();
	String result="0";
	
	try{
		if("getNowtime".equals(type)){
			   String nowtime="";
			   nowtime = PublicUtil.getSqlDate().toString();
			   result="{success:true,nowtime:'"+nowtime+"'}";
		}else if("QUERY".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashMap.put("order",request.getParameter("order"));
			}
			System.out.println(request.getParameter("filter")+""+request.getParameter("order"));
			result = un.queryAllModel_Br(hashMap).toString();
		}else if("RETURN".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("outid")!=null){
				hashMap.put("outid",request.getParameter("outid"));			
			}
			if(request.getParameter("modelnum")!=null){
				hashMap.put("modelnum",request.getParameter("modelnum"));
			}
			if(request.getParameter("returncount")!=null){
				hashMap.put("returncount",request.getParameter("returncount"));			
			}
			if(request.getParameter("returndate")!=null){
				hashMap.put("returndate",request.getParameter("returndate"));
			}
			if(request.getParameter("returnperson")!=null){
				hashMap.put("returnperson",request.getParameter("returnperson"));
			}
			if(request.getParameter("state")!=null){
				hashMap.put("state",request.getParameter("state"));
			}
			String resultValue = un.modelReturn_Record(hashMap).toString();
			if("true".equals(resultValue)){
				result = "success";
			}else{
				result = "false";
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