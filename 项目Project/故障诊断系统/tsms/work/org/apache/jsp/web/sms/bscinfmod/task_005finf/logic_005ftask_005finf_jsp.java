package org.apache.jsp.web.sms.bscinfmod.task_005finf;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.io.*;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.Task_mod;
import com.nuaa.sys.StarTree;
import com.jenkov.prizetags.tree.impl.Tree;
import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;

public final class logic_005ftask_005finf_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	//不允许浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	//取得Sessoin中的树对象
	String treeid="structree";
	if(request.getParameter("treeid")!=null){
		treeid=request.getParameter("treeid");
	};
	ITree tree = (Tree)session.getAttribute(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+treeid);
	ITree tree_task = (Tree)session.getAttribute(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+"treeid");
	String type = request.getParameter("type");
    PrintWriter writer = response.getWriter();
    String result=""; 
    Task_mod tm=(Task_mod)AppInsFactory.getBean("Task_mod");
   // StarTree st=(StarTree)AppInsFactory.getBean("StarTree");
    try{	
	if("queryTaskList".equals(type)){
			HashMap HashMap=new HashMap();
			if(request.getParameter("userlevel")!=null){
				HashMap.put("userlevel",request.getParameter("userlevel"));				
			}
			if(request.getParameter("userid")!=null){
				HashMap.put("userid",request.getParameter("userid"));				
			}	
			String get_result = tm.Leader_query_togrind(HashMap).toString();
			
			if(get_result.equals("false")){
				result = "{failure:true,html:'<p style=\"text-align:center; line-height:20px;\" ><font size=\"2\" face=\"宋体\">任务加载失败！</font></p>',taskflag:''}";
			}else if(get_result.equals("")){
				result = "{success:true,html:'<p style=\"text-align:center; line-height:20px;\" ><font size=\"2\" face=\"宋体\">暂无任务</font></p>',taskflag:''}";
			}else{
				result = "{success:true,html:'" +get_result+"',taskflag:'flag'}";
			}
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		response.setContentType("text/html;charset=UTF-8");
		writer.write(result);
		writer.close();
	}			

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
