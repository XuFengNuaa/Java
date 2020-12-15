package org.apache.jsp.web.sms.usrinfmod.usrinfmod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.JSONObject;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.UsrInfMod;
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

public final class logicusrinfmod_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	String userid=(String)session.getAttribute("userid");
	String stuff_num=(String)session.getAttribute("userid");
	UsrInfMod uim=(UsrInfMod)AppInsFactory.getBean("UsrInfMod");
	PrintWriter writer = response.getWriter();
	String result="0";
if ("USREDITPWD".equals(type)){
		try {
			HashMap hashMap=new HashMap();
			hashMap.put("userid",userid);
			if(request.getParameter("pwd")!=null){
				hashMap.put("pwd",request.getParameter("pwd"));
			}
			if(request.getParameter("pwd1")!=null){
				hashMap.put("pwd1",request.getParameter("pwd1"));
			}
			boolean returnValue=uim.editpwdUsr(hashMap);
			if (returnValue){
				result="{success:true,wid:'ok'}";
			}else{
				result="{success:true,wid:'旧密码错误'}";
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			response.getWriter().write(result);
			response.getWriter().close();	
		}

        }else if("USRVIEW".equals(type)){
			try {			
				HashMap hashMap=new HashMap();
				hashMap.put("stuff_num",stuff_num);
				result=uim.viewUsr(hashMap).toString();	
			}catch (Exception e){
				e.printStackTrace();
			}finally{	
				response.getWriter().write(result);
				response.getWriter().close();	
			}
		}
         
	

      out.write('\r');
      out.write('\n');
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
