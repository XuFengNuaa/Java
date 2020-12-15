<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.HashMap,java.io.PrintWriter,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.Login,org.json.JSONObject, org.json.JSONArray"%>
<%
	String type = request.getParameter("type");    
	PrintWriter writer = response.getWriter();
	String result="0";
	Login lg=(Login)AppInsFactory.getBean("Login");
	JSONObject jsonObj;
	try{
		if("LOGIN".equals(type)){
			String userid="";
			String password="";
			if(request.getParameter("username")!=null){
				userid=request.getParameter("username");		
			}else{
				userid=(String)session.getAttribute("userid");
			}
			if(request.getParameter("password")!=null){
				password=request.getParameter("password");		
			}else{
				password=(String)session.getAttribute("password");
			}
			System.out.println(userid +""+password);
			if(lg.isValid(userid,password)){
				session.setAttribute("userid",userid);
				session.setAttribute("password",password);
//				writer.write("123");
//				String userInfArray=lg.getUserInf(userid);
//				JSONArray jsonArr=new JSONArray(userInfArray);
//				session.setAttribute("multrole","false");
				result="{success:true,msg:'ok'}";
			}else{
				result="{success:true,msg:'2'}";
			}
		}//else if("queryRoleInf".equals(type)){}
	}catch(Exception e){
		result="{success:false,msg:'0'}";
	}finally{
		response.setContentType("text/html;charset=UTF-8");
		writer.write(result);writer.close();
	}
%>