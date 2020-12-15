<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,org.json.*,java.io.*,com.nuaa.sys.util.DbUtil,org.json.JSONObject, org.json.JSONArray,com.nuaa.sys.util.PublicUtil,java.util.UUID,java.sql.*,com.nuaa.sys.util.AppInsFactory,java.sql.Date,com.nuaa.app.english"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.nuaa.sys.util.Logger,com.nuaa.app.RoleLevel,com.nuaa.app.FileState"%>
<%@ page import="com.jenkov.prizetags.tree.impl.Tree,com.jenkov.prizetags.tree.itf.ITree,com.jenkov.prizetags.tree.itf.ITreeNode,com.nuaa.sys.StarTree,com.nuaa.sys.util.Logger,javax.servlet.http.HttpServletResponse"
%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");
	String userid=(String)session.getAttribute("userid");
	PrintWriter writer = response.getWriter();
	english sd=(english)AppInsFactory.getBean("english");
	String result="0";
	 String treeid="treeid";
		if(request.getParameter("treeid")!=null){
			treeid=request.getParameter("treeid");
		};
		String mnum = request.getParameter("mnum");
	ITree tree = (Tree)session.getAttribute(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+treeid);
try{
		if("uploadimg1".equals(type)){
			System.out.println("77777777777777777777");
			response.setContentType("text/plain");
		    	    String dir=request.getRealPath("");
		    	    System.out.println(dir);    //D:\apache-tomcat-6.0.16\webapps\tsms
		    	    final HashMap fileHashMap=new HashMap();
				  	fileHashMap.put("dir",dir);
				  	DiskFileUpload fu = new DiskFileUpload();
			fu.setSizeMax(919430400);
			fu.setSizeThreshold(4096);
			List fileItems=fu.parseRequest(request);
			fileHashMap.put("fileItems",fileItems);
			fileHashMap.put("special",request.getParameter("special"));
			HashMap HashMapNew=sd.uploadFile1(fileHashMap);
			  result="{success:true,type:'"+(String)HashMapNew.get("types")+"',fileid:'"+(String)HashMapNew.get("fileid")+"'}"; 
		    		System.out.println("上传文件"+result);
		}else if("getNowtime".equals(type)){ 
			   String nowtime="";
			   nowtime = PublicUtil.getSqlDate().toString();
//			   nowtime=UUID.randomUUID().toString();
			   result="{success:true,nowtime:'"+nowtime+"'}";
			   System.out.println("生成时间"+nowtime);			   
	    }else if("QUERYCH1".equals(type)){
			HashMap hashMap=new HashMap();
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			result=sd.getQueryApprove1(hashMap).toString();
		}else if("VIEW1".equals(type)){
			if (request.getParameter("tuhao") != null) {
				String tuhao = request.getParameter("tuhao");
				 String dir=request.getRealPath("");
				 result =  sd.getFile1(dir,tuhao);
				
				System.out.println("sassg"+result);
			}//材料                                                          
			
				}else if("DEL1".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));
			}
			String returnValue = sd.delPart1(hashMap);
			
			if ("true".equals(returnValue)){
				result="1";
			}
				}
        else if("ADD1".equals(type)){
				final String[] pa = new String[4];
				HashMap hashMap=new HashMap();
				for (int i = 0; i < pa.length; i++) {
					pa[i] = "";
				}
				if (request.getParameter("special") != null) {
					pa[0] = request.getParameter("special");
				}
				if (request.getParameter("scr") != null) {
					pa[1] = request.getParameter("scr");
					System.out.println(pa[1]);
				}
				if (request.getParameter("tpmc") != null) {
					pa[2] = request.getParameter("tpmc");
				}
				if (request.getParameter("time") != null) {
					pa[3] = request.getParameter("time");
				}
				for (int i = 0; i < pa.length; i++) {
					System.out.println("我是logic页面"+pa[i]);
				}
				hashMap.put("pa[0]",pa[0]);
				hashMap.put("pa[1]",pa[1]);
				hashMap.put("pa[2]",pa[2]);
				hashMap.put("pa[3]",pa[3]);
				if(sd.addFile1(hashMap)){
					result ="1";
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