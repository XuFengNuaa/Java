<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,org.json.*,java.io.*,com.nuaa.sys.util.DbUtil,org.json.JSONObject, org.json.JSONArray,com.nuaa.sys.util.PublicUtil,java.util.UUID,java.sql.*,com.nuaa.sys.util.AppInsFactory,java.sql.Date,com.nuaa.app.Model_In"%>

<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");
	String userid=(String)session.getAttribute("userid");
	PrintWriter writer = response.getWriter();
	Model_In sd=(Model_In)AppInsFactory.getBean("Model_In");
	String result="0";
%>
<%
 try{if("QUERYALLNUM".equals(type)){
	result=sd.queryAllModelnum().toString();
}else if("getNowtime".equals(type)){
	   String nowtime="";
	   nowtime = PublicUtil.getSqlDate().toString();
//	   nowtime=UUID.randomUUID().toString();
	   result="{success:true,nowtime:'"+nowtime+"'}";
	   System.out.println("生成时间"+nowtime);			   
}else if("TOOLALL".equals(type)){
	HashMap hashmap = new HashMap();
	hashmap.put("modelnum",request.getParameter("modelnum"));
	hashmap.put("indate",request.getParameter("indate"));
	hashmap.put("incount",request.getParameter("incount"));
	if(sd.model_In_Record(hashmap).equals("true")){
		result="1";
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
