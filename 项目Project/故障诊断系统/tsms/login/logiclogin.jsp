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
				String userInfArray=lg.getUserInf(userid);
				JSONArray jsonArr=new JSONArray(userInfArray);				
				if(jsonArr.length()==1){
					session.setAttribute("multrole","false");
					for(int i = 0;i<jsonArr.length();i++){
						 jsonObj = jsonArr.getJSONObject(i); 
							result="{success:true,msg:'ok',multrole:false,usrlevel:"+jsonObj.getString("userlevel")+"}";
							session.setAttribute("username",jsonObj.get("username"));
							session.setAttribute("userlevel",jsonObj.get("userlevel"));						
							
							String rolename = "0";
							String userlevel = jsonObj.getString("userlevel");
							if(userlevel.equals("1"))
								rolename = "用户";
							else if(userlevel.equals("2"))
								rolename = "库房管理员";
							else if(userlevel.equals("3"))
								rolename = "班组长";
							else if(userlevel.equals("4"))
								rolename = "领导";
							else if(userlevel.equals("5"))
								rolename = "系统管理员";
							else if(userlevel.equals("0"))
								rolename = "超级管理员";
							else if(userlevel.equals("6"))
								rolename = "程序员";
							else if(userlevel.equals("7"))
								rolename = "生产准备组";
							else if(userlevel.equals("8"))
								rolename = "总库管理员";
							else if(userlevel.equals("9"))
								rolename = "分库管理员";
							else if(userlevel.equals("10"))
								rolename = "外场计划员";
							else if(userlevel.equals("11"))
								rolename = "模具管理员";
							else if(userlevel.equals("12"))
								rolename = "仪表管理员";
							else
								rolename="未知角色";
							session.setAttribute("rolename",rolename);							
					}
						
				}else{	
					    session.setAttribute("multrole","true");					 
					    result="{success:true,msg:'ok',multrole:true,userid:'"+userid+"'";					   
					    for(int i = 0;i<jsonArr.length();i++){
					    	jsonObj = jsonArr.getJSONObject(i); 					    	
					    	if(jsonObj.get("userlevel").equals("1")){					    		
					    		result+=",yh:true";
					    	}else if(jsonObj.get("userlevel").equals("2")){
					    		result+=",kf:true";
					    	}else if(jsonObj.get("userlevel").equals("3")){
					    		result+=",bz:true";
					    	}else if(jsonObj.get("userlevel").equals("4")){
					    		result+=",ld:true";
					    	}else if(jsonObj.get("userlevel").equals("5")){
					    		result+=",xt:true";
					    	}else if(jsonObj.get("userlevel").equals("6")){
					    		result+=",cx:true";
					    	}else if(jsonObj.get("userlevel").equals("7")){
					    		result+=",zb:true";
					    	}else if(jsonObj.get("userlevel").equals("8")){
					    		result+=",zk:true";
					    	}else if(jsonObj.get("userlevel").equals("9")){
					    		result+=",fk:true";
					    	}else if(jsonObj.get("userlevel").equals("10")){
					    		result+=",wc:true";
					    	}else if(jsonObj.get("userlevel").equals("11")){
					    		result+=",mj:true";
					    	}else if(jsonObj.get("userlevel").equals("12")){
					    		result+=",yb:true";
					    	}
					    }
					    result+="}";					 
				}	
			}else{
				result="{success:true,msg:'2'}";
			}
		}else if("queryRoleInf".equals(type)){		
			 if(request.getParameter("userlevel")!=null && request.getParameter("userid")!=null){
				 String userlevel = request.getParameter("userlevel"); 
				 String userid = request.getParameter("userid"); 
				 String rolename = "0";
				 String userInfArrayTwo=lg.getUserInfTwo(userid,userlevel);
				 System.out.println(userInfArrayTwo);
				 JSONArray jsonArrTwo=new JSONArray(userInfArrayTwo);				
					for(int i = 0;i<jsonArrTwo.length();i++){
						 jsonObj = jsonArrTwo.getJSONObject(i); 
							result="{success:true,msg:'ok'}";							
							
							session.setAttribute("username",jsonObj.get("username"));
							session.setAttribute("userid",jsonObj.get("userid"));
							session.setAttribute("userlevel",jsonObj.get("userlevel"));
					}
					
					
					if(userlevel.equals("1"))
						rolename = "用户";
					else if(userlevel.equals("2"))
						rolename = "库房管理员";
					else if(userlevel.equals("3"))
						rolename = "班组长";
					else if(userlevel.equals("4"))
						rolename = "领导";
					else if(userlevel.equals("5"))
						rolename = "系统管理员";
					else if(userlevel.equals("0"))
						rolename = "超级管理员";
					else if(userlevel.equals("6"))
						rolename = "程序员";
					else if(userlevel.equals("7"))
						rolename = "生产准备组";
					else if(userlevel.equals("8"))
						rolename = "总库管理员";
					else if(userlevel.equals("9"))
						rolename = "分库管理员";
					else if(userlevel.equals("10"))
						rolename = "外场计划员";
					else if(userlevel.equals("11"))
						rolename = "模具管理员";
					else if(userlevel.equals("12"))
						rolename = "仪表管理员";
					else
						rolename="未知角色";
					
					session.setAttribute("rolename",rolename);						
			 }		
		}
	}catch(Exception e){
		result="{success:false,msg:'0'}";
	}finally{
		response.setContentType("text/html;charset=UTF-8");
		writer.write(result);writer.close();
	}
%>