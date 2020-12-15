package org.apache.jsp.web.sms.storestatmod.storestatmod_005fc.storestatmod_005fc1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.JSONObject;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.StoreStatModC;
import com.nuaa.app.CutterMod;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IStatementProcessor;;

public final class logic_005fstorestatmod_005fc1_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
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

	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");
	StoreStatModC um=(StoreStatModC)AppInsFactory.getBean("StoreStatModC");
	CutterMod un=(CutterMod)AppInsFactory.getBean("CutterMod");
	PrintWriter writer = response.getWriter();
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
			result=um.getQueryStoreStatModC1(hashMap).toString();
		}else if("VIEW".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){	
				hashMap.put("id",request.getParameter("id"));
			}
			result=un.view_cutter(hashMap).toString();	
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
