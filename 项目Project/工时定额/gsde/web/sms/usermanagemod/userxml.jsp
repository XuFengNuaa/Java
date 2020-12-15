<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.io.*,java.util.HashMap" %>
<%@ page import="com.nuaa.app.impl.UserXmlImpl" %>
<%
	String fname = "用户信息";
	OutputStream os = response.getOutputStream();//取得输出流
	response.reset();//清空输出流
	HashMap hashMap=new HashMap();
	if(request.getParameter("filter")!=null){
		hashMap.put("filter",request.getParameter("filter"));			
	}
	if(request.getParameter("result")!=null){
		hashMap.put("result",request.getParameter("result"));			
	}
	
	
    //下面是对中文文件名的处理
	response.setCharacterEncoding("UTF-8");  //设置导出excel整体内容编码
    fname = java.net.URLEncoder.encode(fname, "UTF-8"); //设置文件名编码
    //设置下载功能
   	response.setHeader("Content-Disposition", "attachment; filename="+ new String(fname.getBytes("UTF-8"), "UTF-8") + ".xls");
    response.setContentType("application/msexcel");//定义输出类型
    UserXmlImpl sw = new UserXmlImpl();
    sw.createExcelUser(hashMap,os);
%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
 <body>  
 </body>
</html>
