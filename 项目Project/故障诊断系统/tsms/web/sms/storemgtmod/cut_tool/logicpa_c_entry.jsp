<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,org.json.*,java.io.*,com.nuaa.sys.util.DbUtil,com.nuaa.sys.util.PublicUtil,java.util.UUID,java.sql.*,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.PartProcessMod"%>
<%@ page import="org.apache.commons.fileupload.*,javax.sql.DataSource" %>
<%@ page import="com.nuaa.sys.util.Logger,com.nuaa.app.RoleLevel,com.nuaa.app.FileState"%>
<%@ page import="com.jenkov.prizetags.tree.impl.Tree,com.jenkov.prizetags.tree.itf.ITree,com.jenkov.prizetags.tree.itf.ITreeNode,com.nuaa.sys.StarTree,com.nuaa.sys.util.Logger,javax.servlet.http.HttpServletResponse"
%>
<%
	//不允许浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	String type = request.getParameter("type");
    PrintWriter writer = response.getWriter();
    String result=""; 
    PartProcessMod pm=(PartProcessMod)AppInsFactory.getBean("PartProcessMod");
    String treeid="treeid";
	if(request.getParameter("treeid")!=null){
		treeid=request.getParameter("treeid");
	};
    //取得Sessoin中的树对象
	ITree tree = (Tree)session.getAttribute(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+treeid);

    try{	    
if("uploadimg".equals(type)){
	response.setContentType("text/plain");
    	    String dir=request.getRealPath("");
    	    System.out.println(dir);    
    	    final HashMap fileHashMap=new HashMap();
		  	fileHashMap.put("dir",dir);
		  	DiskFileUpload fu = new DiskFileUpload();
	fu.setSizeMax(919430400);
	fu.setSizeThreshold(4096);
	List fileItems=fu.parseRequest(request);
	fileHashMap.put("fileItems",fileItems);
	fileHashMap.put("mnum",request.getParameter("mnum"));
	HashMap HashMapNew=pm.uploadFile1(fileHashMap);
    	    result="{success:true,type:'"+(String)HashMapNew.get("types")+"',fileid:'"+(String)HashMapNew.get("fileid")+"'}";
    		System.out.println("上传文件"+result);
    		
}}catch(Exception e){
	}finally{
	response.setContentType("text/html;charset=UTF-8");
	writer.write(result);
	writer.close();
	}
%>