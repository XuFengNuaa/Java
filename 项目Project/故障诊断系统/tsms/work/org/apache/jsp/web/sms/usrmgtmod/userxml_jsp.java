package org.apache.jsp.web.sms.usrmgtmod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.HashMap;
import com.nuaa.app.impl.UserXmlImpl;

public final class userxml_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	String fname = "用户信息";
	OutputStream os = response.getOutputStream();//取得输出流
	response.reset();//清空输出流
	HashMap hashMap=new HashMap();
	if(request.getParameter("stuff_num1")!=null){
		String stuff_num1=new String(request.getParameter("stuff_num1").getBytes( "iso-8859-1" ), "UTF-8" );
		hashMap.put("stuff_num",stuff_num1);			
	}
	if(request.getParameter("filter")!=null){
		String filter1=new String(request.getParameter("filter").getBytes( "iso-8859-1" ), "UTF-8" );
		hashMap.put("filter",filter1);			
	}
	if(request.getParameter("result")!=null){
		String result1=new String(request.getParameter("result").getBytes( "iso-8859-1" ), "UTF-8" );
		hashMap.put("result",result1);			
	}
	
	System.out.println("1111111111111111111111111111111111111111"+request.getParameter("stuff_num1"));
    //下面是对中文文件名的处理
	response.setCharacterEncoding("UTF-8");  //设置导出excel整体内容编码
    fname = java.net.URLEncoder.encode(fname, "UTF-8"); //设置文件名编码
    //设置下载功能
   	response.setHeader("Content-Disposition", "attachment; filename="+ new String(fname.getBytes("UTF-8"), "UTF-8") + ".xls");
    response.setContentType("application/msexcel");//定义输出类型
    UserXmlImpl sw = new UserXmlImpl();
    sw.createExcelUser(hashMap,os);

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">   \r\n");
      out.write(" <body>  \r\n");
      out.write(" </body>\r\n");
      out.write("</html>\r\n");
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
