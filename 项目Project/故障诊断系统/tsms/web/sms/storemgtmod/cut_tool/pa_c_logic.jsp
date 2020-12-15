<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,org.json.*,java.io.*,com.nuaa.sys.util.DbUtil,org.json.JSONObject, org.json.JSONArray,com.nuaa.sys.util.PublicUtil,java.util.UUID,java.sql.*,com.nuaa.sys.util.AppInsFactory,java.sql.Date,com.nuaa.app.PacEnStandar"%>
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
	response.flushBuffer();	
	String userid=(String)session.getAttribute("userid");
	PrintWriter writer = response.getWriter();
	PacEnStandar sd=(PacEnStandar)AppInsFactory.getBean("PacEnStandar");
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
			fileHashMap.put("id",request.getParameter("id1"));
			HashMap HashMapNew=sd.uploadFile2(fileHashMap);
			  result="{success:true,type:'"+(String)HashMapNew.get("types")+"',fileid:'"+(String)HashMapNew.get("fileid")+"'}"; 
		    		System.out.println("上传文件"+result);
		}else if("uploadimg".equals(type)){
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
		}else if("ADD".equals(type)){
			if (request.getParameter("mnum") != null) {
				//String tuhao = request.getParameter("tuhao_sim");
				 String dir=request.getRealPath("");
				 //System.out.println("1111111111"+dir);
				result =  sd.getFile(dir,mnum);
				
				System.out.println("sassg"+result);
			}//材料                                                          
		}else if("ADD1".equals(type)){
			if (request.getParameter("mnum") != null) {
				//String tuhao = request.getParameter("tuhao_sim");
				 String dir=request.getRealPath("");
				 //System.out.println("1111111111"+dir);
				result =  sd.getFile1(dir,mnum);
				
				System.out.println("sassg"+result);
			}//材料    
				}else if("ADDIMG".equals(type)){
			result ="1";}
		else if("getNowtime".equals(type)){ 
			   String nowtime="";
			   nowtime = PublicUtil.getSqlDate().toString();
//			   nowtime=UUID.randomUUID().toString();
			   result="{success:true,nowtime:'"+nowtime+"'}";
			   System.out.println("生成时间"+nowtime);			   
	    }
		else if("QUERYALL".equals(type)){
			result=sd.getQueryMnumAll().toString();
		}
		else if("QUERYALLNUM".equals(type)){
            HashMap hashMap=new HashMap();
			
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashMap.put("order",request.getParameter("order"));			
			}
			if(request.getParameter("hq")!=null){
				hashMap.put("hq",request.getParameter("hq"));			
			}
			result=sd.getQuerynumAll(hashMap).toString();
		}
		else if("QUERYALLCOM".equals(type)){
             HashMap hashMap=new HashMap();
			
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			} 
			if(request.getParameter("hq")!=null){
				hashMap.put("hq",request.getParameter("hq"));			
			} 
			result=sd.getQuerycomAll(hashMap).toString();
		}
		else if("QUERY".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));			
			}
		    System.out.println("bengin find........");
			result=sd.getQueryFile(hashMap).toString();
	        System.out.println("******kkkkkkkk*****"+result);
		}else if("QUERYSCORE".equals(type)){
			 System.out.println("999999999999999999999999999999999");
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));			
			}
		    System.out.println("bengin find........");
		    result=sd.getQueryFileScore(hashMap).toString();
	        System.out.println("******kkkkkkkk*****"+result);
		}else if("QUERY2".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));			
			}
		    System.out.println("bengin find........");
		    result=sd.getQueryFile2(hashMap).toString();
	        System.out.println("******kkkkkkkk*****"+result);
		}
		else if("QUERY3".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));			
			}
		    System.out.println("bengin find........");
		    result=sd.getQueryFile3(hashMap).toString();
	        System.out.println("******kkkkkkkk*****"+result);
		}
		else if("QUERY4".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));			
			}
		    System.out.println("bengin find........");
		    result=sd.getQueryFile4(hashMap).toString();
	        System.out.println("******kkkkkkkk*****"+result);
		}
		else if("QUERY1".equals(type)){
			HashMap hashMap=new HashMap();
			
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));			
			}
		    System.out.println("bengin find........");
			result=sd.getQueryFile1(hashMap).toString();
	        System.out.println("******kkkkkkkk*****"+result);
		} 
		else if("CHEX".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("mnum")!=null){
				hashMap.put("mnum",request.getParameter("mnum"));			
			}
			if(sd.chexFile(hashMap)){
				result ="1";
			}
		}else if("CHEX1".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("mnum")!=null){
				hashMap.put("mnum",request.getParameter("mnum"));			
			}
			if(sd.chexFile1(hashMap)){
				result ="1";
			}
		}
		else if("TOOLALL".equals(type)){
			System.out.println("..........................");
			final String[] pa = new String[14];
			HashMap hashMap=new HashMap();
			for (int i = 0; i < pa.length; i++) {
				pa[i] = "";
			}
			
			if (request.getParameter("special") != null) {
				pa[0] = request.getParameter("special");
			}
			if (request.getParameter("mnum") != null) {
				pa[1] = request.getParameter("mnum");
			}
			if (request.getParameter("in_count") != null) {
				pa[2] = request.getParameter("in_count");
			}
			if (request.getParameter("in_time") != null) {
				pa[3] = request.getParameter("in_time");
			}
        	if (request.getParameter("new1") != null) {
				pa[4] = request.getParameter("new1");	
			}
        	if (request.getParameter("status") != null) {
				pa[5] = request.getParameter("status");	
			}
        	if (request.getParameter("in_problem") != null) {
				pa[6] = request.getParameter("in_problem");	
			} 
        	if (request.getParameter("user_num") != null) {
				pa[7] = request.getParameter("user_num");	
			} 
        	if (request.getParameter("company") != null) {
				pa[8] = request.getParameter("company");
			}
			if (request.getParameter("city") != null) {
				pa[9] = request.getParameter("city");
			}
			if (request.getParameter("pclass") != null) {
				pa[10] = request.getParameter("pclass");
			}
			if (request.getParameter("bclass") != null) {
				pa[11] = request.getParameter("bclass");
			}
			if (request.getParameter("bill_num") != null) {
				pa[12] = request.getParameter("bill_num");
			}
			if (request.getParameter("denglu") != null) {
				pa[13] = request.getParameter("denglu");
			}
        	for (int i = 0; i < pa.length; i++) {
				System.out.println("我是logic页面"+pa[i]);
			}
        	hashMap.put("pa[0]",pa[0]);
			hashMap.put("pa[1]",pa[1]);
			hashMap.put("pa[2]",pa[2]);
			hashMap.put("pa[3]",pa[3]);
			hashMap.put("pa[4]",pa[4]);
			hashMap.put("pa[5]",pa[5]);
			hashMap.put("pa[6]",pa[6]);
			hashMap.put("pa[7]",pa[7]);
			hashMap.put("pa[8]",pa[8]);
			hashMap.put("pa[9]",pa[9]);
			hashMap.put("pa[10]",pa[10]);
			hashMap.put("pa[11]",pa[11]);
			hashMap.put("pa[12]",pa[12]);
			hashMap.put("pa[13]",pa[13]);
			String resu = sd.addFile(hashMap);
			
			if(resu.equals("true")){
				result ="1";    //记录完成，数据添加完成
			}
			else if(resu.equals("false")){
				result = "2";   //不明原因失败
			}
		}
		else if("TOOLALL1".equals(type)){
			System.out.println("..........................");
			final String[] pa = new String[7];
			HashMap hashMap=new HashMap();
			for (int i = 0; i < pa.length; i++) {
				pa[i] = "";
			}
			if (request.getParameter("mnum") != null) {
				pa[1] = request.getParameter("mnum");
			}
			if (request.getParameter("answer") != null) {
				pa[2] = request.getParameter("answer");
			}
			
        	pa[0] = UUID.randomUUID().toString();
        	for (int i = 0; i < pa.length; i++) {
				System.out.println("我是logic页面"+pa[i]);
			}
        	hashMap.put("pa[0]",pa[0]);
			hashMap.put("pa[1]",pa[1]);
			hashMap.put("pa[2]",pa[2]);
			
			String resu = sd.addFile1(hashMap);
			
			if(resu.equals("true")){
				result ="1";    //记录完成，数据添加完成
			}
			else if(resu.equals("false")){
				result = "2";   //不明原因失败
			}
		}else if("ADDMARK".equals(type)){
			System.out.println("..........................");
			final String[] pa = new String[8];
			HashMap hashMap=new HashMap();
			for (int i = 0; i < pa.length; i++) {
				pa[i] = "";
			}
			if (request.getParameter("entry_nums") != null) {
				pa[1] = request.getParameter("entry_nums");
			}
			if (request.getParameter("score") != null) {
				pa[2] = request.getParameter("score");
			}
			if (request.getParameter("sta") != null) {
				pa[3] = request.getParameter("sta");
			}
			if (request.getParameter("sta1") != null) {
				pa[4] = request.getParameter("sta1");
			}
			if (request.getParameter("sta2") != null) {
				pa[5] = request.getParameter("sta2");
			}
			if (request.getParameter("sta3") != null) {
				pa[6] = request.getParameter("sta3");
			}
			if (request.getParameter("remarkjs") != null) {
				pa[7] = request.getParameter("remarkjs");
			}
        	pa[0] = UUID.randomUUID().toString();
        	for (int i = 0; i < pa.length; i++) {
				System.out.println("我是logic页面"+pa[i]);
			}
        	hashMap.put("pa[0]",pa[0]);
			hashMap.put("pa[1]",pa[1]);
			hashMap.put("pa[2]",pa[2]);
			hashMap.put("pa[3]",pa[3]);
			hashMap.put("pa[4]",pa[4]);
			hashMap.put("pa[5]",pa[5]);
			hashMap.put("pa[6]",pa[6]);
			hashMap.put("pa[7]",pa[7]);
			String resu = sd.addFile2(hashMap);
			
			if(resu.equals("true")){
				result ="1";    //记录完成，数据添加完成
			}
			else if(resu.equals("false")){
				result = "2";   //不明原因失败
			}
		}else if("QUERYCT".equals(type)){
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
			result=sd.getQueryApprove(hashMap).toString();
		}
		else if("QUERYCT1".equals(type)){
			System.out.println("我 ........................");
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
			if(request.getParameter("pclassjs")!=null){
				hashMap.put("pclass",request.getParameter("pclassjs"));			
			}
			if(request.getParameter("bclassjs")!=null){
				hashMap.put("bclass",request.getParameter("bclassjs"));			
			}
			//System.out.println(request.getParameter("pclassjs"));
			result=sd.getQueryApprove1(hashMap).toString();
		}
		else if("APPTOOLALL".equals(type)){
			System.out.println("..........................");
			final String[] pa = new String[7];
			HashMap hashMap=new HashMap();
			for (int i = 0; i < pa.length; i++) {
				pa[i] = "";
			}
			if (request.getParameter("entry_num") != null) {
				pa[0] = request.getParameter("entry_num");
			}
			if (request.getParameter("type_id") != null) {
				pa[1] = request.getParameter("type_id");
			}
			if (request.getParameter("in_count") != null) {
				pa[2] = request.getParameter("in_count");
			}
			if (request.getParameter("in_time") != null) {
				pa[3] = request.getParameter("in_time");
			}
        	if (request.getParameter("newstatus") != null) {
				pa[4] = request.getParameter("newstatus");	
			}
        	if (request.getParameter("status") != null) {
				pa[5] = request.getParameter("status");	
			}
        	if (request.getParameter("in_problem") != null) {
				pa[6] = request.getParameter("in_problem");	
			}
        	for (int i = 0; i < pa.length; i++) {
				System.out.println("我是logic页面"+pa[i]);
			}
        	hashMap.put("pa[0]",pa[0]);
			hashMap.put("pa[1]",pa[1]);
			hashMap.put("pa[2]",pa[2]);
			hashMap.put("pa[3]",pa[3]);
			hashMap.put("pa[4]",pa[4]);
			hashMap.put("pa[5]",pa[5]);
			hashMap.put("pa[6]",pa[6]);
			
			String resu = sd.addFileApp(hashMap);
			
			if(resu.equals("truetrue")){
				result ="1";    //记录完成，数据添加完成
			}
			else if(resu.equals("true")){
				result = "2";  //记录完成，未进行数据更改
			}
			else if(resu.equals("truefalse")){
				result = "3";    //记录完成，数据更改失败
			}
			else if(resu.equals("falsetrue")){
				result = "4";   //记录失败，数据更改完成
			}
			else if(resu.equals("false")){
				result = "5";   //不明原因失败
			}
		}
		else if("DELETE".equals(type)){
			System.out.println("..........................");
			String deleteid = "";
			HashMap hashMap=new HashMap();
			if (request.getParameter("deleteid") != null) {
				deleteid = request.getParameter("deleteid");
			}
				System.out.println("我是logic页面delete="+deleteid);
				
        	hashMap.put("deleteid",deleteid);
        	
			String resu = sd.deleteRe(hashMap);
			
			if(resu.equals("true")){
				result ="1";    //记录完成，数据添加完成
			}
			else if(resu.equals("false")){
				result = "2";   //不明原因失败
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