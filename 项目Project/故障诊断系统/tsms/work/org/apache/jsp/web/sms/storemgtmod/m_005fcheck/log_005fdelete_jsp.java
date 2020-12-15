package org.apache.jsp.web.sms.storemgtmod.m_005fcheck;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import org.json.*;
import java.io.*;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.PublicUtil;
import java.util.UUID;
import java.sql.*;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.PartProcessMod;
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

public final class log_005fdelete_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	//不允许浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	String type = request.getParameter("type");
	String tuhao = request.getParameter("tuhao");
    PrintWriter writer = response.getWriter();
    String result=""; 
    String res ="";
    PartProcessMod pm=(PartProcessMod)AppInsFactory.getBean("PartProcessMod");
    String treeid="treeid";
	if(request.getParameter("treeid")!=null){
		treeid=request.getParameter("treeid");
	};
    //取得Sessoin中的树对象
	ITree tree = (Tree)session.getAttribute(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+treeid);

    try{
    	if("ADD".equals(type)){
	if (request.getParameter("tuhao") != null) {
		//String tuhao = request.getParameter("tuhao_sim");
		 String dir=request.getRealPath("");
		 result =  pm.getFile(dir,tuhao);
		
		System.out.println("sassg"+result);
	}//材料                                                          
	
		}
	}catch(Exception e){
		
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
