<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,org.json.*,java.io.*,com.nuaa.sys.util.DbUtil,com.nuaa.sys.util.PublicUtil,java.util.UUID,java.sql.*,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.ManufactureMod"%>
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
    ManufactureMod pm=(ManufactureMod)AppInsFactory.getBean("ManufactureMod");
    String treeid="treeid";
	if(request.getParameter("treeid")!=null){
		treeid=request.getParameter("treeid");
	};
    //取得Sessoin中的树对象
	ITree tree = (Tree)session.getAttribute(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+treeid);

    try{	    
    	if("ADD".equals(type)){
	final String[] pa = new String[14];
	HashMap hashMap=new HashMap();
	for (int i = 0; i < pa.length; i++) {
		pa[i] = "";
	}
	if (request.getParameter("dalei_sim") != null) {
		pa[1] = request.getParameter("dalei_sim");
		System.out.println(pa[1]);
	}//大类
	if (request.getParameter("xiaolei_sim") != null) {
		pa[2] = request.getParameter("xiaolei_sim");
	}//小类
    if (request.getParameter("machine_sim") != null) {
		pa[3] = request.getParameter("machine_sim");	
	}//设备
	if (request.getParameter("material_sim") != null) {
		pa[4] = request.getParameter("material_sim");
	}//材料                                                          
	if (request.getParameter("daihao_sim") != null) {
		pa[5] = request.getParameter("daihao_sim");
	}//代号
	if (request.getParameter("mingcheng_sim") != null) {
		pa[6] = request.getParameter("mingcheng_sim");
	}//零件名称
	if (request.getParameter("tuhao_sim") != null) {
		pa[7] = request.getParameter("tuhao_sim");
	}//图号
	if (request.getParameter("maopilong_sim") != null) {
		pa[8] = request.getParameter("maopilong_sim");
	}//长
	if (request.getParameter("maopiwidth_sim") != null) {
		pa[9] = request.getParameter("maopiwidth_sim");
	}//宽
	if (request.getParameter("maopihigh_sim") != null) {
		pa[10] = request.getParameter("maopihigh_sim");
	}//高		
	if (request.getParameter("qxtj_sim") != null){
		pa[11] = request.getParameter("qxtj_sim");
	}//体积
	if (request.getParameter("qxmj_sim") != null){
		pa[12] = request.getParameter("qxmj_sim");
	}//面积
	if (request.getParameter("zxd_sim") != null){
		pa[13] = request.getParameter("zxd_sim");
	}//置信度
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
	hashMap.put("pa[8]",pa[8]);
	hashMap.put("pa[9]",pa[9]);
	hashMap.put("pa[10]",pa[10]);
	hashMap.put("pa[11]",pa[11]);
	hashMap.put("pa[12]",pa[12]);
	hashMap.put("pa[13]",pa[13]);
	if(pm.addFile(hashMap)){
		result ="1";
	}
		}else if("QUERY".equals(type)){
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
	if(request.getParameter("order")!=null){
		hashMap.put("order",request.getParameter("order"));
	}
	
	result=pm.getQueryFile(hashMap).toString();
		}else if("uploadimg".equals(type)){
	response.setContentType("text/plain");
    	    String dir=request.getRealPath("");
    	    final HashMap fileHashMap=new HashMap();
		  	fileHashMap.put("dir",dir);
		  	DiskFileUpload fu = new DiskFileUpload();
	fu.setSizeMax(919430400);
	fu.setSizeThreshold(4096);
	List fileItems=fu.parseRequest(request);
	fileHashMap.put("fileItems",fileItems);
	fileHashMap.put("ljth",request.getParameter("ljth"));
	HashMap HashMapNew=pm.uploadFile2(fileHashMap);
    	    result="{success:true,type:'"+(String)HashMapNew.get("types")+"',fileid:'"+(String)HashMapNew.get("fileid")+"'}";
    		System.out.println("上传文件"+result);
		}else if("ADD2".equals(type)){
			if (request.getParameter("tuhao") != null) {
				String tuhao = request.getParameter("tuhao");
				 String dir=request.getRealPath("");
				result =  pm.getFile2(dir,tuhao);
				
				System.out.println("sassg"+result);
			}//材料                                                          
			
				}else if("ADDIMG".equals(type)){
			
/*	HashMap hashMap=new HashMap();
	hashMap.put("scsj",request.getParameter("scsj"));
	hashMap.put("scr",request.getParameter("scr"));
	hashMap.put("tpmc",request.getParameter("tpmc"));
	hashMap.put("ljth",request.getParameter("ljth"));
	if(pm.addImg(hashMap)){*/
		result ="1";
	/*}else{
		result ="2";
	}
	*/
		 }else if("DEL".equals(type)){
	HashMap hashMap=new HashMap();
	if(request.getParameter("id")!=null){
		hashMap.put("id",request.getParameter("id"));
	}
	String returnValue = pm.delPart(hashMap);
	
	if ("true".equals(returnValue)){
		result="1";
	}
		}
	}catch(Exception e){
		
	}finally{
	response.setContentType("text/html;charset=UTF-8");
	writer.write(result);
	writer.close();
	}
%>