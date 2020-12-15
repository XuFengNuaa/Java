package org.apache.jsp.web.sms.storemgtmod.fenqie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.commons.fileupload.*;
import org.json.JSONObject;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.MillSim_Mod;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;
import java.util.*;
import java.io.*;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IStatementProcessor;;

public final class logic_005ffenqie_005fcheck_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	response.setContentType("text/html;charset=UTF-8");
	String type = request.getParameter("type");	
	MillSim_Mod um=(MillSim_Mod)AppInsFactory.getBean("MillSim_Mod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
		if("QUERYDALEI".equals(type)){
		result = um.QueryAllDalei().toString();
		}else if("QUERYXIAOLEI".equals(type)){
			HashMap hashmap = new HashMap();
			hashmap.put("dalei",request.getParameter("dalei"));
			result = um.QueryAllXiaoleiByDalei(hashmap).toString();
		}else if("QUERYMATERIAL".equals(type)){
			result=um.getQueryMaterialAll().toString();
		}else if("QUERYTUHAO".equals(type)){
			result=um.getQuerytuhaoAll().toString();
		}else if("QUERYMACHINE".equals(type)){
			result=um.getQueryMachineAll().toString();
		}else if("QUERYSIM".equals(type)){
			result=um.getQuerySimAll().toString();
		}else if("QUERYSIMALL".equals(type)){
			HashMap hashmap=new HashMap();
			if(request.getParameter("start")!=null){
				hashmap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashmap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashmap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashmap.put("order",request.getParameter("order"));
			}
			result = um.QueryMilldetail(hashmap).toString();
		}else if("QUERYSIMVIEW".equals(type)){
			String part_num = request.getParameter("part_num");
			HashMap hashmap = new HashMap();
			hashmap.put("part_num",part_num);
			result = um.SimView(hashmap).toString();	
		}
	}catch(Exception e){
		e.printStackTrace();
		result="{success:false}";
	}finally{
		response.setContentType("text/html;charset=UTF-8");
		writer.write(result);		
		writer.close();
	}
	

      out.write(' ');
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
