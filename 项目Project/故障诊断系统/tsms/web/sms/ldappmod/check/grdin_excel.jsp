<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.HashMap" %>
<%@ page import="com.nuaa.app.impl.Leader_checkxml" %>
<%
	String fname = "刀具送磨返库审批";
	OutputStream os = response.getOutputStream();//取得输出流
	response.reset();//清空输出流
	HashMap hashMap=new HashMap();
	String stuff_num=(String)session.getAttribute("userid");
	String leadername=(String)session.getAttribute("username");
	if(request.getParameter("grd_num")!=null){
		hashMap.put("grd_num",request.getParameter("grd_num"));			
	}

    //下面是对中文文件名的处理
	response.setCharacterEncoding("UTF-8");
    fname = java.net.URLEncoder.encode(fname, "UTF-8");
	response.setHeader("Content-Disposition", "attachment; filename="+ new String(fname.getBytes("UTF-8"), "UTF-8") + ".xls");
    response.setContentType("application/msexcel");//定义输出类型
    Leader_checkxml sw = new Leader_checkxml();
    sw.createExcelIn(stuff_num,leadername,hashMap,os);
%>
<html>
 <body>  
 </body>
</html>
