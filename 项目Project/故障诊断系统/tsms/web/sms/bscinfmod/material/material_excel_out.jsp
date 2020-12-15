<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="com.nuaa.app.impl.createMaterialExcelImpl" %>
<%
	String fname = "材料基本信息导出";
	OutputStream os = response.getOutputStream();//取得输出流
	response.reset();//清空输出流

    //下面是对中文文件名的处理
	response.setCharacterEncoding("UTF-8");
    fname = java.net.URLEncoder.encode(fname, "UTF-8");
	response.setHeader("Content-Disposition", "attachment; filename="+ new String(fname.getBytes("UTF-8"), "GBK") + ".xls");
    response.setContentType("application/msexcel");//定义输出类型
    
    createMaterialExcelImpl sw = new createMaterialExcelImpl();
    sw.createMaterialxls(os);
%>
<html>
 <body>  
 </body>
</html>