<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@page import="
org.json.JSONObject,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.CutterMod,
java.io.PrintWriter,
java.net.URLEncoder,
java.sql.PreparedStatement,
java.sql.ResultSet,
java.sql.Statement,
java.sql.SQLException,
java.util.HashMap,
java.util.UUID,
java.util.*,
java.io.*,
com.nuaa.app.HQBorrow,
com.nuaa.sys.util.DbUtil,
com.nuaa.sys.util.PublicUtil,
com.nuaa.sys.util.IResultSetProcessor,
com.nuaa.sys.util.IArrayPreparedStatementProcessor,
com.nuaa.sys.util.IStatementProcessor;"
%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");	
	String stuff_num=(String)session.getAttribute("userid");
	HQBorrow sd=(HQBorrow)AppInsFactory.getBean("HQBorrow");
	CutterMod um=(CutterMod)AppInsFactory.getBean("CutterMod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
			if ("ADD".equals(type)){
			HashMap hashMap=new HashMap();	
			
			if(request.getParameter("mnum1")!=null){//零件编码
				hashMap.put("mnum",request.getParameter("mnum1"));
			}
			if(request.getParameter("spec1")!=null){
				hashMap.put("spec",request.getParameter("spec1"));
			}
			if(request.getParameter("name1")!=null){
				hashMap.put("name",request.getParameter("name1"));
			}
			if(request.getParameter("pclass1")!=null){
				hashMap.put("pclass",request.getParameter("pclass1"));
			}
			if(request.getParameter("bclass1")!=null){
				hashMap.put("bclass",request.getParameter("bclass1"));
			}
			if(request.getParameter("iso_num1")!=null){
				hashMap.put("iso_num",request.getParameter("iso_num1"));
				System.out.println("aaaaaaaaaaaaaaaaa"+request.getParameter("iso_num1"));
			}
			if(request.getParameter("series_num1")!=null){
				hashMap.put("series_num",request.getParameter("series_num1"));
				System.out.println("bvbbbbbbbbbbbbbbb"+request.getParameter("series_num1"));
			}
			if(request.getParameter("remark1")!=null){
				hashMap.put("remark",request.getParameter("remark1"));				
				}		
			if(request.getParameter("type1")!=null){
				hashMap.put("type",request.getParameter("type1"));
			}
			
			String returnValue = um.add_cutter(hashMap);
			if ("true".equals(returnValue)){
				result="{success:true,msg:'ok'}";
			}else if("Exception".equals(returnValue)){
				result="{failure:true}";
			}else{
				result="{success:true,msg:'repeat'}";
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
			result=um.getQuery_cutter(hashMap).toString();
			System.out.println("ret"+result+"hashmap:" + hashMap);			
		}else if("DEL".equals(type)){
			String id = request.getParameter("id");
			HashMap hashmap = new HashMap();
			hashmap.put("id", id);
			String returnValue = um.del_cutter(hashmap);
			if("true".equals(returnValue)){
				result="1";
			}else{
				result="0";
			}
		}else if("EDIT".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("id",request.getParameter("id"));
			if(request.getParameter("los_mnum")!=null){//零件编码
				hashMap.put("mnum",request.getParameter("los_mnum"));
			}
			if(request.getParameter("los_spec")!=null){
				hashMap.put("spec",request.getParameter("los_spec"));
			}
			if(request.getParameter("los_name")!=null){
				hashMap.put("name",request.getParameter("los_name"));
			}
			if(request.getParameter("los_pclass")!=null){
				hashMap.put("pclass",request.getParameter("los_pclass"));
			}
			if(request.getParameter("los_bclass")!=null){
				hashMap.put("bclass",request.getParameter("los_bclass"));
			}
			if(request.getParameter("los_type")!=null){
				hashMap.put("type",request.getParameter("los_type"));
			}
			if(request.getParameter("los_remark")!=null){
				hashMap.put("remark",request.getParameter("los_remark"));
			}
			if(request.getParameter("los_iso_num")!=null){
				hashMap.put("iso_num",request.getParameter("los_iso_num"));
			}
			if(request.getParameter("los_series_num")!=null){
				hashMap.put("series_num",request.getParameter("los_series_num"));
			}
			String returnEditvalue = um.edit_cutter(hashMap);
			if ("true".equals(returnEditvalue)){
				result="{success:true,msg:'ok'}";
			}else if("Exception".equals(returnEditvalue)){
				result="{failure:true}";
			}else{
				result="{success:true,msg:'repeat'}";
			}
		}else if("VIEW".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){	
				hashMap.put("id",request.getParameter("id"));
			}			
			result=um.view_cutter(hashMap).toString();	
		}else if("GRINDEDIT".equals(type)){
			HashMap hashMap=new HashMap();			
			if(request.getParameter("los_pclass")!=null){
				hashMap.put("pclass",request.getParameter("los_pclass"));
			}
			if(request.getParameter("los_bclass")!=null){
				hashMap.put("bclass",request.getParameter("los_bclass"));
			}			
			if(request.getParameter("los_name")!=null){
				hashMap.put("name",request.getParameter("los_name"));
			}
			if(request.getParameter("com_id")!=null){
				hashMap.put("companyid",request.getParameter("com_id"));
			}			
			if(request.getParameter("los_gc_count_in")!=null){
				hashMap.put("gc_count_in",request.getParameter("los_gc_count_in"));				
			}			
			if(request.getParameter("los_suit_mate1")!=null){
				hashMap.put("suit_mate1",request.getParameter("los_suit_mate1"));
			}
			if(request.getParameter("los_suit_mate2")!=null){
				hashMap.put("suit_mate2",request.getParameter("los_suit_mate2"));
			}
			if(request.getParameter("los_suit_mate3")!=null){
				hashMap.put("suit_mate3",request.getParameter("los_suit_mate3"));
			}			
			if(request.getParameter("los_rank_angle")!=null){
				hashMap.put("rank_angle",request.getParameter("los_rank_angle"));
			}
			if(request.getParameter("los_c_mate")!=null){
				hashMap.put("c_mate",request.getParameter("los_c_mate"));
			}
			if(request.getParameter("los_coat_mate")!=null){
				hashMap.put("coat_mate",request.getParameter("los_coat_mate"));
			}
			if(request.getParameter("los_e_diam")!=null){
				hashMap.put("e_diam",request.getParameter("los_e_diam"));
			}
			if(request.getParameter("los_h_diam")!=null){
				hashMap.put("h_diam",request.getParameter("los_h_diam"));
			}
			if(request.getParameter("los_e_leng")!=null){
				hashMap.put("e_leng",request.getParameter("los_e_leng"));
			}
			if(request.getParameter("los_t_leng")!=null){
				hashMap.put("t_leng",request.getParameter("los_t_leng"));				
			}			
			if(request.getParameter("los_e_count")!=null){
				hashMap.put("e_count",request.getParameter("los_e_count"));
			}			
			if(request.getParameter("los_remark")!=null){
				hashMap.put("remark",request.getParameter("los_remark"));	
			}			
			if(request.getParameter("hq")!=null){
				hashMap.put("hq",request.getParameter("hq"));
			}
			if(request.getParameter("location")!=null){
				hashMap.put("location",request.getParameter("location"));				
			}
			if(request.getParameter("los_effect_length")!=null){
				hashMap.put("effect_length",request.getParameter("los_effect_length"));				
			}
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));				
			}			
			String returnEditvalue = um.edit_grind_cutter(hashMap);
			if ("1".equals(returnEditvalue)){
				result="1";
			}else{				
				result="0";
			}
		}else if("getNowtime".equals(type)){
    	   String nowtime="";
    	   nowtime = PublicUtil.getSqlDate().toString();
    	   result="{success:true,nowtime:'"+nowtime+"'}";
	    }else if("NEEDPOST".equals(type)){
				HashMap hashMap=new HashMap();
				hashMap.put("worker_num",stuff_num);
				if(request.getParameter("id")!=null){
					hashMap.put("id",request.getParameter("id"));
				}
				if(request.getParameter("drawing_num_need")!=null){
					hashMap.put("drawing_num_need",request.getParameter("drawing_num_need"));
				}
				if(request.getParameter("need_count")!=null){
					hashMap.put("need_count",request.getParameter("need_count"));
				
					if (sd.needC(hashMap)){
						result="{success:true}";
					}else{
						result="{success:false}";
					}
				}
		}else if("UPLOAD".equals(type)){
			String dir=request.getRealPath("");
    		final HashMap fileHashMap=new HashMap();
    		fileHashMap.put("dir",dir);
    		DiskFileUpload fu = new DiskFileUpload();
    		fu.setSizeMax(919430400);                 
    		fu.setSizeThreshold(4096);
    		List fileItems=fu.parseRequest(request);
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
			if(request.getParameter("dalei")!=null){
				hashMap.put("dalei",request.getParameter("dalei"));
			}
			if(request.getParameter("xiaolei")!=null){
				hashMap.put("xiaolei",request.getParameter("xiaolei"));
			}			
    		out.clear();
    		out = pageContext.pushBody();    		
    		String returnValue = um.getExcel(hashMap);
			if ("true".equals(returnValue)){
				result="1";
			}else if("mnumfalse".equals(returnValue)){
				result="2";
			}else if("namefalse".equals(returnValue)){
				result="3";
			}else if("hqfalse".equals(returnValue)){
				result="4";
			}else if("mininull".equals(returnValue)){
				result="5";
			}else if("locationnull".equals(returnValue)){
				result="6";
			}else if("locationfalse".equals(returnValue)){
				result="7";
			}else if("daleinull".equals(returnValue)){
				result="8";
			}else if("xiaoleinull".equals(returnValue)){
				result="9";
			}else if("xiaoleifalse".equals(returnValue)){
				result="10";
			}else if("daleifalse".equals(returnValue)){
				result="11";
			}else if("classfalse".equals(returnValue)){
				result="12";
			}else if("newstorefalse".equals(returnValue)){
				result="13";
			}else if("oldstorefalse".equals(returnValue)){
				result="14";
			}else if("companyfalse".equals(returnValue)){
				result="15";
			}else if("ministorefalse".equals(returnValue)){
				result="16";
			}else if("orderfalse".equals(returnValue)){
				result="17";
			}else if("grindstorefalse".equals(returnValue)){
				result="18";
			}else if("e_leng_false".equals(returnValue)){
				result="19";
			}else if("t_leng_false".equals(returnValue)){
				result="20";
			}else if("effect_length_false".equals(returnValue)){
				result="21";
			}else if("e_count_false".equals(returnValue)){
				result="22";
			}else if("tipHeig_false".equals(returnValue)){
				result="23";
			}else if("blaWide_false".equals(returnValue)){
				result="24";
			}else if("c_leng_false".equals(returnValue)){
				result="25";
			}else if("e_wide_false".equals(returnValue)){
				result="26";
			}else if("tee_count_false".equals(returnValue)){
				result="27";
			}else{
				result="0";
			}    
			System.out.println("hrerererererer"+result);	
		}else if("APPLYEDIT".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("edit_id")!=null){	
				hashMap.put("id",request.getParameter("edit_id"));
			}
			if(request.getParameter("amount")!=null){	
				hashMap.put("amount",request.getParameter("amount"));
			}
			if(request.getParameter("k")!=null){	
				hashMap.put("k",request.getParameter("k"));
			}
			if(request.getParameter("before_amount")!=null){	
				hashMap.put("before_amount",request.getParameter("before_amount"));
			}
			result=um.correct_cutter(hashMap).toString();	
		}else if("COMBINE".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("first_id")!=null){	
				hashMap.put("first_id",request.getParameter("first_id"));
			}
			if(request.getParameter("second_id")!=null){	
				hashMap.put("second_id",request.getParameter("second_id"));
			}
			if(request.getParameter("combine_nc")!=null){	
				hashMap.put("combine_nc",request.getParameter("combine_nc"));
			}
			if(request.getParameter("combine_uc")!=null){	
				hashMap.put("combine_uc",request.getParameter("combine_uc"));
			}			
			result=um.combine_cutter(hashMap).toString();	
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