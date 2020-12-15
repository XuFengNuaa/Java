package org.apache.jsp.web.sms.bscinfmod.company;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.JSONObject;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.CompanyMod;
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

public final class logiccompany_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	CompanyMod um=(CompanyMod)AppInsFactory.getBean("CompanyMod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
			if ("ADD".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("company")!=null){
				hashMap.put("company",request.getParameter("company"));
			}
			if(request.getParameter("address")!=null){
				hashMap.put("address",request.getParameter("address"));
			}
			if(request.getParameter("contact_person")!=null){
				hashMap.put("contact_person",request.getParameter("contact_person"));
			}
			if(request.getParameter("phone")!=null){
				hashMap.put("phone",request.getParameter("phone"));
			}
			if(request.getParameter("email")!=null){
				hashMap.put("email",request.getParameter("email"));
			}
			if(request.getParameter("remark")!=null){
				hashMap.put("remark",request.getParameter("remark"));				
			}
			String returnValue = um.addCompany(hashMap);
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
			result=um.getQueryCompany(hashMap).toString();
		}else if("QUERYALL".equals(type)){
			result=um.getQueryCompanyAll().toString();
		}else if("DEL".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));
			}
			String returnValue = um.delCompany(hashMap);
			if ("true".equals(returnValue)){
				result="1";
			}
		}else if("EDIT".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("company")!=null){
				hashMap.put("company",request.getParameter("company"));
			}
			if(request.getParameter("address")!=null){
				hashMap.put("address",request.getParameter("address"));
			}
			if(request.getParameter("contact_person")!=null){
				hashMap.put("contact_person",request.getParameter("contact_person"));
			}
			if(request.getParameter("phone")!=null){
				hashMap.put("phone",request.getParameter("phone"));
			}
			if(request.getParameter("email")!=null){
				hashMap.put("email",request.getParameter("email"));
			}
			if(request.getParameter("remark")!=null){
				hashMap.put("remark",request.getParameter("remark"));				
			}
		   if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));
			}
			String returnEditvalue = um.editCompany(hashMap);
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
			result=um.viewCompany(hashMap).toString();	
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
