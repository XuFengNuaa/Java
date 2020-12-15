<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.Circuit_board,
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
com.nuaa.app.HQBorrow,
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
	/* String stuff_num=(String)session.getAttribute("userid");
	HQBorrow sd=(HQBorrow)AppInsFactory.getBean("HQBorrow"); */
	Circuit_board um=(Circuit_board)AppInsFactory.getBean("Circuit_board");//bean的功能和new类似，不需要新建，只需要取出来用
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
			if("VIEW".equals(type)){
				HashMap hashMap=new HashMap();
				if(request.getParameter("name")!=null){	
					hashMap.put("name",request.getParameter("name"));
				}
				result=um.view_circuit_board(hashMap).toString();	
			}else if("VIEWONE".equals(type)){
				HashMap hashMap=new HashMap();
				if(request.getParameter("numn")!=null){	
					hashMap.put("numn",request.getParameter("numn"));
				}
				result=um.view_cleaning(hashMap).toString();	
			}
	}catch(Exception e){
		e.printStackTrace();
		result="{success:false}";
	}finally{
		System.out.println("结果"+result);
		response.setContentType("text/html;charset=UTF-8");
		writer.write(result);		
		writer.close();
	}
	
%>