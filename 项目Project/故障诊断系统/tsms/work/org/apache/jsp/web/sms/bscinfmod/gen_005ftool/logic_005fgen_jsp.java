package org.apache.jsp.web.sms.bscinfmod.gen_005ftool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import org.json.*;
import java.io.*;
import com.nuaa.sys.util.DbUtil;
import org.json.JSONObject;
import org.json.JSONArray;
import com.nuaa.sys.util.PublicUtil;
import java.util.UUID;
import java.sql.*;
import com.nuaa.sys.util.AppInsFactory;
import java.sql.Date;
import com.nuaa.app.HQBorrow;
import com.nuaa.app.Standard;
import org.apache.commons.fileupload.*;

public final class logic_005fgen_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");
	String userid=(String)session.getAttribute("userid");
	HQBorrow um=(HQBorrow)AppInsFactory.getBean("HQBorrow");
	PrintWriter writer = response.getWriter();
	Standard sd=(Standard)AppInsFactory.getBean("Standard");
	String result="0";
	
	try{
		if("QUERY".equals(type)){
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
		
			//if(request.getParameter("nodeid")!=null){
			//	hashMap.put("nodeid",request.getParameter("nodeid"));
			//}
		    System.out.println("bengin find");
			result=sd.getQueryFile(hashMap).toString();
	 //	System.out.println("******kkkkkkkk*****"+request.getParameter("nodeid"));
	//	 result="{totalProperty:2,filedata:[{wid:'1',fileid:'1',filenum:'1',filename:'2',fileclass:'3',creator:'4',createtime:'5',remark:'6',model:'6',projnum:'6',projname:'6'}]}";
		} 
//  读取厂商名
     
		else if("QUERYALL".equals(type)){
			result=sd.getQueryCompanyAll().toString();
		}
		
		else if("addba".equals(type)){ 
			System.out.println("..........................");
			final String[] pa = new String[15];
			HashMap hashMap=new HashMap();
			//String dir=request.getRealPath("");
			//System.out.println("生成时间"+dir);
			//hashMap.put("dir",dir);
			for (int i = 0; i < pa.length; i++) {
				pa[i] = "";
			}
			if (request.getParameter("mnum") != null) {
				pa[1] = request.getParameter("mnum");
			}
			if (request.getParameter("pclass") != null) {
				pa[2] = request.getParameter("pclass");
			}
			if (request.getParameter("bclass") != null) {
				pa[3] = request.getParameter("bclass");
			}
        	if (request.getParameter("name") != null) {
				pa[4] = request.getParameter("name");	
			}
        	if (request.getParameter("spec") != null) {
				pa[5] = request.getParameter("spec");
			}
			if (request.getParameter("companyid") != null) {
				pa[6] = request.getParameter("companyid");
			}                                                          
			if (request.getParameter("location") != null) {
				pa[7] = request.getParameter("location");
			}
			if (request.getParameter("nct_count_in") != null) {
				pa[8] = request.getParameter("nct_count_in");
			}
			if (request.getParameter("uct_count_in") != null) {
				pa[9] = request.getParameter("uct_count_in");
			}
			if (request.getParameter("mini_qs") != null) {
				pa[10] = request.getParameter("mini_qs");
			}
			if (request.getParameter("hq") != null) {
				pa[11] = request.getParameter("hq");
			}
			if (request.getParameter("remark") != null){
				pa[12] = request.getParameter("remark");
			}
			if (request.getParameter("order_num") != null) {
				pa[13] = request.getParameter("order_num");
			}
			if (request.getParameter("use_freq") != null) {
				pa[14] = request.getParameter("use_freq");
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
			hashMap.put("pa[8]",pa[8]);
			hashMap.put("pa[9]",pa[9]);
			hashMap.put("pa[10]",pa[10]);
			hashMap.put("pa[11]",pa[11]);
			hashMap.put("pa[12]",pa[12]);
			hashMap.put("pa[13]",pa[13]);
			hashMap.put("pa[14]",pa[14]);
			if (request.getParameter("userid") != null) {
				hashMap.put("userid", request.getParameter("userid"));
			}
			if (request.getParameter("username") != null) {
				hashMap.put("username", request.getParameter("username"));
			}
			String returnValue = sd.addFile(hashMap);
			if("true".equals(returnValue)){
				result="1";
			}
			else if("false".equals(returnValue)){
				result="0";
			}else if("exit0".equals(returnValue)){
				result="2";
			}else if("exit1".equals(returnValue)){
				result="3";
			}else if("no".equals(returnValue)){
				result="4";
			}			
       } 
		else if("modifyba".equals(type)){ 
			System.out.println("..........................");
			final String[] pa = new String[14];
			HashMap hashMap=new HashMap();
			//String dir=request.getRealPath("");
			//hashMap.put("dir",dir);
			//if(request.getParameter("oldfileid")!=null){
				//hashMap.put("oldfileid",request.getParameter("oldfileid"));
			//}
			for (int i = 0; i < pa.length; i++) {
				pa[i] = "";
			}
			if (request.getParameter("mnum") != null) {
				pa[0] = request.getParameter("mnum");
			}
			if (request.getParameter("pclass") != null) {
				pa[1] = request.getParameter("pclass");
			}
			if (request.getParameter("bclass") != null) {
				pa[2] = request.getParameter("bclass");
			}
        	if (request.getParameter("name") != null) {
				pa[3] = request.getParameter("name");	
			}
        	if (request.getParameter("spec") != null) {
				pa[4] = request.getParameter("spec");
			}
			if (request.getParameter("companyid") != null) {
				pa[5] = request.getParameter("companyid");
			}                                                          
			if (request.getParameter("location") != null) {
				pa[6] = request.getParameter("location");
			}
			if (request.getParameter("mini_qs") != null) {
				pa[7] = request.getParameter("mini_qs");
			}
			if (request.getParameter("hq") != null) {
				pa[8] = request.getParameter("hq");
			}
			if (request.getParameter("remark") != null){
				pa[9] = request.getParameter("remark");
			}
			if (request.getParameter("id") != null){
				pa[10] = request.getParameter("id");
			}if (request.getParameter("finalmnum") != null){
				pa[11] = request.getParameter("finalmnum");
				System.out.println(pa[11]);
			}if (request.getParameter("order_num") != null){
				pa[12] = request.getParameter("order_num");
			}if (request.getParameter("finalhq") != null){
				pa[13] = request.getParameter("finalhq");
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
			if (request.getParameter("userid") != null) {
				hashMap.put("userid", request.getParameter("userid"));
			}
			if (request.getParameter("username") != null) {
				hashMap.put("username", request.getParameter("username"));
			}
			String returnValue = sd.modifyFile(hashMap);
			if("true".equals(returnValue)){
				result="1";
			}
			else if("false".equals(returnValue)){
				result="0";
			}else if("exit0".equals(returnValue)){
				result="2";
			}else if("exit1".equals(returnValue)){
				result="3";
			}else if("no".equals(returnValue)){
				result="4";
			}			
       }else if("getNowtime".equals(type)){
    	   String nowtime="";
    	   nowtime = PublicUtil.getSqlDate().toString();
    	   result="{success:true,nowtime:'"+nowtime+"'}";
       }else if("NEEDPOST".equals(type)){
			HashMap hashMap=new HashMap();
			hashMap.put("worker_num",userid);
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));
			}
			if(request.getParameter("drawing_num_need")!=null){
				hashMap.put("drawing_num_need",request.getParameter("drawing_num_need"));
			}
			if(request.getParameter("need_count")!=null){
				hashMap.put("need_count",request.getParameter("need_count"));
			
			if (um.needCT(hashMap)){
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
    		HashMap returnHashmap = sd.uploadfile(fileHashMap);
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
    		
    		String returnValue = sd.getExcel(hashMap);
			if ("true".equals(returnValue)){
				result="1";
			}else if("ministorefalse".equals(returnValue)){
				result="2";
			}else if("mininull".equals(returnValue)){
				result="3";
			}else if("newstorefalse".equals(returnValue)){
				result="4";
			}else if("oldstorefalse".equals(returnValue)){
				result="5";
			}else if("locationfalse".equals(returnValue)){
				result="6";
			}else if("locationnull".equals(returnValue)){
				result="7";
			}else if("daleinull".equals(returnValue)){
				result="8";
			}else if("xiaoleinull".equals(returnValue)){
				result="9";
			}else if("daleifalse".equals(returnValue)){
				result="10";
			}else if("xiaoleifalse".equals(returnValue)){
				result="11";
			}else if("classfalse".equals(returnValue)){
				result="12";
			}else if("mnumfalse".equals(returnValue)){
				result="13";
			}else if("companyfalse".equals(returnValue)){
				result="14";
			}else if("namefalse".equals(returnValue)){
				result="15";
			}else if("hqfalse".equals(returnValue)){
				result="16";
			}else if("orderfalse".equals(returnValue)){
				result="17";
			}else{
				result="0";
			}    
			System.out.println("*********************"+result);	
		}
	}catch(Exception e){
		e.printStackTrace();
		result="{success:false}";
	}finally{
		response.setContentType("text/html;charset=UTF-8");
		writer.write(result);		
		writer.close();
	}
	

    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
