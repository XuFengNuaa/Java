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
com.nuaa.app.Material_Mod,
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
Material_Mod um=(Material_Mod)AppInsFactory.getBean("Material_Mod");
try{
	if("ADD".equals(type)){
	HashMap hashmap=new HashMap();
	hashmap.put("mnum", request.getParameter("mnum"));
	hashmap.put("classname", request.getParameter("classname"));
	hashmap.put("name", request.getParameter("name"));
	hashmap.put("measure", request.getParameter("measure"));
	hashmap.put("spec", request.getParameter("ttspec"));
	hashmap.put("modelnum", request.getParameter("modelnum"));
	hashmap.put("monthuse", request.getParameter("monthuse"));
	hashmap.put("buycycle", request.getParameter("buycycle"));
	System.out.print((String)request.getParameter("buycycle"));
	hashmap.put("companyid", request.getParameter("companyid"));
	hashmap.put("guaranteedate", request.getParameter("guaranteedate"));
	hashmap.put("prewarndate", request.getParameter("prewarndate"));
	hashmap.put("remark", request.getParameter("remark"));
	if("true".equals(um.addMaterial(hashmap))){
		result="{success:true,msg:'ok'}";
	}else if("repeat".equals(um.addMaterial(hashmap))){
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
	result = um.getQueryMaterial(hashMap).toString();
}else if("EDIT".equals(type)){
	HashMap hashmap=new HashMap();
	hashmap.put("id", request.getParameter("id"));
	hashmap.put("mnum", request.getParameter("mnum"));
	hashmap.put("classname", request.getParameter("classname"));
	hashmap.put("name", request.getParameter("name"));
	hashmap.put("measure", request.getParameter("measure"));
	hashmap.put("spec", request.getParameter("ttspec"));
	hashmap.put("modelnum", request.getParameter("modelnum"));
	hashmap.put("monthuse", request.getParameter("monthuse"));
	hashmap.put("buycycle", request.getParameter("buycycle"));
	//System.out.print((String)request.getParameter("buycycle"));
	hashmap.put("companyid", request.getParameter("companyid"));
	hashmap.put("guaranteedate", request.getParameter("guaranteedate"));
/* 	hashmap.put("maxcycle", request.getParameter("maxcycle"));
	hashmap.put("mincycle", request.getParameter("mincycle")); */
	hashmap.put("prewarndate", request.getParameter("prewarndate"));
	hashmap.put("remark", request.getParameter("remark"));
	if("true".equals(um.editMaterial(hashmap))){
		result = "{success:true,msg:'ok'}";
	}else if("repeat".equals(um.editMaterial(hashmap))){
		result = "{success:true,msg:'repeat'}";
		
	}else{
		result = "{success:false,msg:'2'}";
	}
}else if("VIEW".equals(type)){
	HashMap hashmap=new HashMap();
	hashmap.put("id", request.getParameter("id"));
	result = um.viewMaterial(hashmap).toString();
}else if("DEL".equals(type)){
	HashMap hashmap=new HashMap();
	hashmap.put("id", request.getParameter("id"));
	if("true".equals(um.delMaterial(hashmap))){
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
	if("true".equals(returnValue)){
		result = "1";//成功
	}else if("error".equals(returnValue)){
		result = "0";//失败
	}else if("mnumnullfalse".equals(returnValue)){
		result = "2";//物资编码为空
	}else if("classnamenullfalse".equals(returnValue)){
		result = "3";//材料类别为空
	}else if("namenullfalse".equals(returnValue)){
		result = "4";//材料名称为空
	}else if("specnullfalse".equals(returnValue)){
		result = "5";//规格为空
	}else if("specunnumfalse".equals(returnValue)){
		result = "6";//规格必须为数字
	}else if("prenullfalse".equals(returnValue)){
		result = "7";//预警天数为空
	}else if("preunnumfalse".equals(returnValue)){
		result = "8";//预警天数不为数字
	}else if("companynullfalse".equals(returnValue)){
		result = "9";//厂商为空
	}else if("measurenullfalse".equals(returnValue)){
		result = "10";//计量单位为空
	}else if("guanullfalse".equals(returnValue)){
		result = "11";//保质期为空
	}else if("guaunnumfalse".equals(returnValue)){
		result = "12";//保质期不为数字
	}/* else if("minnullfalse".equals(returnValue)){
		result = "13";//最小安全周期为空
	}else if("minunnumfalse".equals(returnValue)){
		result = "14";//最小安全周期不为数字
	} *//* else if("maxnullfalse".equals(returnValue)){
		result = "15";//最大安全周期为空
	}else if("maxunnumfalse".equals(returnValue)){
		result = "16";//最大安全周期不为数字
	} */else if("cyclenullfalse".equals(returnValue)){
		result = "17";//采购周期为空
	}else if("cycleunnumfalse".equals(returnValue)){
		result = "18";//采购周期不为数字
	}else if("monthusenullfalse".equals(returnValue)){
		result = "19";//月用量为空
	}else if("monthuseunnumfalse".equals(returnValue)){
		result = "20";//月用量不为数字
	}
}else if("EDIT1".equals(type)){
	HashMap hashmap=new HashMap();
	hashmap.put("id", request.getParameter("id"));
	hashmap.put("mnum", request.getParameter("mnum"));
	hashmap.put("classname", request.getParameter("classname"));
	hashmap.put("name", request.getParameter("name"));
	hashmap.put("measure", request.getParameter("measure"));
	hashmap.put("spec", request.getParameter("ttspec"));
	hashmap.put("modelnum", request.getParameter("modelnum"));
	//hashmap.put("monthuse", request.getParameter("monthuse"));
	hashmap.put("buycycle", request.getParameter("buycycle"));
	//System.out.print((String)request.getParameter("buycycle"));
	hashmap.put("companyid", request.getParameter("companyid"));
	hashmap.put("guaranteedate", request.getParameter("guaranteedate"));
	/* hashmap.put("maxcycle", request.getParameter("maxcycle"));
	hashmap.put("mincycle", request.getParameter("mincycle")); */
	hashmap.put("prewarndate", request.getParameter("prewarndate"));
	hashmap.put("remark", request.getParameter("remark"));
	if("true".equals(um.editMaterial1(hashmap))){
		result = "{success:true,msg:'ok'}";
	}else if("repeat".equals(um.editMaterial(hashmap))){
		result = "{success:true,msg:'repeat'}";
		
	}else{
		result = "{success:false,msg:'2'}";
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