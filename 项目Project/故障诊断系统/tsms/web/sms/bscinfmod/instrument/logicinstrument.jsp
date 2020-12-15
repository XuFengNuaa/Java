<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.io.PrintWriter,
java.util.HashMap,
java.net.URLEncoder,
java.sql.PreparedStatement,
java.sql.ResultSet,
java.sql.Statement,
java.sql.SQLException,
java.util.UUID,
org.json.JSONObject,
com.nuaa.sys.util.AppInsFactory,
org.apache.commons.fileupload.*,
java.util.*,
com.nuaa.app.InstrumentMod,
com.nuaa.sys.util.DbUtil,
com.nuaa.sys.util.PublicUtil,
com.nuaa.sys.util.IResultSetProcessor,
com.nuaa.sys.util.IArrayPreparedStatementProcessor,
com.nuaa.sys.util.IStatementProcessor;"%>

<%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Expires","0");
response.setContentType("text/html;charset=UTF-8");
String type = request.getParameter("type");
PrintWriter writer = response.getWriter();
String result="0";
InstrumentMod um=(InstrumentMod)AppInsFactory.getBean("InstrumentMod");
try{
	if("ADD".equals(type)){
		HashMap hashmap=new HashMap();
		hashmap.put("serialnum", request.getParameter("serialnum"));
		hashmap.put("enterprisenum", request.getParameter("enterprisenum"));
		hashmap.put("outnum", request.getParameter("outnum"));
		hashmap.put("modelnum", request.getParameter("modelnum"));
		hashmap.put("name", request.getParameter("name"));
		hashmap.put("classname", request.getParameter("classname"));
		hashmap.put("nowclass", request.getParameter("nowclass"));
		hashmap.put("detectdate", request.getParameter("detectdate"));
		hashmap.put("nextdate", request.getParameter("nextdate"));
		hashmap.put("detectmodel", request.getParameter("detectmode"));
		hashmap.put("state", request.getParameter("state"));
		hashmap.put("execution", request.getParameter("execution"));
		hashmap.put("belongteam", request.getParameter("belongteam"));
		hashmap.put("person", request.getParameter("person"));
		if("true".equals(um.addInstrument(hashmap))){
			result="{success:true,msg:'ok'}";
		}else if("repeat".equals(um.addInstrument(hashmap))){
			result="{success:true,msg:'repeat'}";
		}else{
			result="{success:fasle,msg:'2'}";
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
		result = um.queryInstrument(hashMap).toString();
	}if("getNowtime".equals(type)){
		   String nowtime="";
		   nowtime = PublicUtil.getSqlDate().toString();
		   result="{success:true,nowtime:'"+nowtime+"'}";
	}else if("VIEW".equals(type)){
		HashMap hashmap=new HashMap();
		hashmap.put("id", request.getParameter("id"));
		result = um.viewInstrument(hashmap).toString();
	}else if("EDIT".equals(type)){
		HashMap hashmap=new HashMap();
		hashmap.put("id", request.getParameter("id"));
		hashmap.put("serialnum", request.getParameter("serialnum"));
		hashmap.put("enterprisenum", request.getParameter("enterprisenum"));
		hashmap.put("outnum", request.getParameter("outnum"));
		hashmap.put("modelnum", request.getParameter("modelnum"));
		hashmap.put("name", request.getParameter("name"));
		hashmap.put("classname", request.getParameter("classname"));
		hashmap.put("nowclass", request.getParameter("nowclass"));
		hashmap.put("detectdate", request.getParameter("detectdate"));
		hashmap.put("nextdate", request.getParameter("nextdate"));
		System.out.println(request.getParameter("nextdate"));
		System.out.println(request.getParameter("detectdate"));
		hashmap.put("detectmodel", request.getParameter("detectmode"));
		hashmap.put("state", request.getParameter("state"));
		hashmap.put("execution", request.getParameter("execution"));
		hashmap.put("belongteam", request.getParameter("belongteam"));
		hashmap.put("person", request.getParameter("person"));
		
		if("true".equals(um.editInstrument(hashmap))){
			result = "{success:true,msg:'ok'}";
		} else if("repeat".equals(um.editInstrument(hashmap))){
			result = "{success:true,msg:'repeat'}";
			
		}else{
			result = "{success:false,msg:'2'}";
		}
		
	}else if("DEL".equals(type)){
		HashMap hashmap=new HashMap();
		hashmap.put("id", request.getParameter("id"));
		if("true".equals(um.delInstrument(hashmap))){
			result = "1";
		}
	}else if("UPLOAD".equals(type)){
		String dir = request.getRealPath("");
		final HashMap fileHashMap = new HashMap();
		fileHashMap.put("dir", dir);
		DiskFileUpload fu = new DiskFileUpload();
		fu.setSizeMax(919430400);
		fu.setSizeThreshold(4096);
		List fileItems = fu.parseRequest(request);
		fileHashMap.put("fileItems",fileItems);
		HashMap returnHashmap = um.uploadfile(fileHashMap);
		result="{success:true,filename:'"+(String)returnHashmap.get("filename")+"'}";
		System.out.println("上传文件"+result);
	}else if("INPUT".equals(type)){
		HashMap hashMap=new HashMap();
		String dir=request.getRealPath("");
		response.reset();
		response.setContentType("application/vnd.ms-excel"); 
		hashMap.put("dir",dir);	
		out.clear();
		out = pageContext.pushBody();
		String returnValue = um.getExcel(hashMap);
		if("datefalse".equals(returnValue)){
			result = "3";//日期格式错误
		}else if("nullfalse".equals(returnValue)){
			result = "2";//存在内容为空
		}else if("true".equals(returnValue)){
			result = "1";//成功
		}else if("false".equals(returnValue)){
			result = "0";//失败
		}else if("repeat".equals(returnValue)){
			result = "5";//失败
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