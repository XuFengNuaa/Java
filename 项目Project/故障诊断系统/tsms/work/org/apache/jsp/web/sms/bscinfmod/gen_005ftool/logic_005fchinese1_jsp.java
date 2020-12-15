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
import com.nuaa.app.chinese;
import org.apache.commons.fileupload.*;
import javax.sql.DataSource;
import com.nuaa.sys.util.Logger;
import com.nuaa.app.RoleLevel;
import com.nuaa.app.FileState;
import com.jenkov.prizetags.tree.impl.Tree;
import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.sys.StarTree;
import com.nuaa.sys.util.Logger;
import javax.servlet.http.HttpServletResponse;

public final class logic_005fchinese1_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");
	String userid=(String)session.getAttribute("userid");
	PrintWriter writer = response.getWriter();
	chinese sd=(chinese)AppInsFactory.getBean("chinese");
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
