package org.apache.jsp.web.sms.bscinfmod.gen_005ftool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.nuaa.app.GuiStr;
import com.nuaa.app.RoleLevel;

public final class rapair_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/web/inc/jsp/head_ext.jsp");
  }

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
      			"/system/file/errorpage.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Ext -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/tsms/web/inc/resources/css/ext-all.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/tsms/web/inc/resources/css/add.css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/adapter/ext/ext-base.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/ext-all.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/ext-cg.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/ext.search.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/calendar.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/calendar-zh.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/calendar-setup.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/ext-lang-zh_CN.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/Ext.ux.ToastWindow.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/Ext.ux.InlineToolbarTabPanel.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/tsms/web/inc/resources/UploadDialog/css/Ext.ux.UploadDialog.css\" />\r\n");
      out.write("<script type='text/javascript' src='/tsms/web/inc/resources/UploadDialog/Ext.ux.UploadDialog.js' ></script>\r\n");
      out.write("<script type='text/javascript' src='/tsms/web/inc/resources/UploadDialog/Ext.ux.UploadDialog.packed.js' ></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/keyboard.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/tsms/web/inc/resources/css/win_keyboard.css\" />\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/jquery-1.4.4.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"/tsms/web/inc/resources/js/jquery.keypad.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/tsms/web/inc/resources/css/jquery.keypad.css\" />\r\n");
      out.write("\r\n");

	String rolename=(String)session.getAttribute("rolename");
	String username=(String)session.getAttribute("username");
	String userid=(String)session.getAttribute("userid");
	String userlevel=(String)session.getAttribute("userlevel");
	String multrole=(String)session.getAttribute("multrole");

      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var rolename=\"");
      out.print(rolename);
      out.write("\";\r\n");
      out.write("var username=\"");
      out.print(username);
      out.write("\";\r\n");
      out.write("var userid=\"");
      out.print(userid);
      out.write("\";\r\n");
      out.write("var userlevel=\"");
      out.print(userlevel);
      out.write("\";\r\n");
      out.write("var multrole=\"");
      out.print(multrole);
      out.write("\";\r\n");
      out.write("\r\n");
      out.write("//状态对应级别 此处配置级别\r\n");
      out.write("var RoleLevel={};//用户级别\r\n");
      out.write("RoleLevel.UserLev=1;//用户\r\n");
      out.write("RoleLevel.StoreManagerLevel=2;//库房管理员\r\n");
      out.write("RoleLevel.TeamLeaderLevel=3;//专家\r\n");
      out.write("RoleLevel.BossLevel=4;//领导\r\n");
      out.write("RoleLevel.SysLevel=5;//系统管理员\r\n");
      out.write("RoleLevel.SuperLevel=0;//超级管理员\r\n");
      out.write("RoleLevel.ProgrammerLevel=6;//程序员\r\n");
      out.write("RoleLevel.PrepareLevel=7;//生产准备组\r\n");
      out.write("RoleLevel.zkLevel=8;//总库管理员\r\n");
      out.write("RoleLevel.fkLevel=9;//分库管理员\r\n");
      out.write("RoleLevel.wcLevel=10;//技术人员\r\n");
      out.write("RoleLevel.mjLevel=11;//模具管理员\r\n");
      out.write("RoleLevel.ybLevel=12;//仪表管理员\r\n");
      out.write("//\r\n");
      out.write("\r\n");
      out.write("function getlengthB(str){\r\n");
      out.write("\treturn str.replace(/[^\\x00-\\xff]/g,\"**\").length;\r\n");
      out.write("}\r\n");
      out.write("function test(){\r\n");
      out.write("\talert(\"test alert\");\r\n");
      out.write("}\r\n");
      out.write("//是否是一般用户\r\n");
      out.write("function isUser(){\r\n");
      out.write("\treturn userlevel == RoleLevel.UserLev ? true : false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//是否是库房管理员\r\n");
      out.write("function isStoreManager(){\r\n");
      out.write("\treturn userlevel == RoleLevel.StoreManagerLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//是否是班组长\r\n");
      out.write("function isTeamLeader(){\r\n");
      out.write("\treturn userlevel == RoleLevel.TeamLeaderLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//是否是领导\r\n");
      out.write("function isBoss(){\r\n");
      out.write("\treturn userlevel == RoleLevel.BossLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//是否是系统管理员\r\n");
      out.write("function isSys(){\r\n");
      out.write("\treturn userlevel == RoleLevel.SysLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//是否是程序员\r\n");
      out.write("function isPro() {\r\n");
      out.write("\treturn userlevel == RoleLevel.ProgrammerLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//是否是超级管理员\r\n");
      out.write("function isSuper(){\r\n");
      out.write("\treturn userlevel == RoleLevel.SuperLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("//是否是生产准备组\r\n");
      out.write("function isPre() {\r\n");
      out.write("\treturn userlevel == RoleLevel.PrepareLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("//是否是总库管理员\r\n");
      out.write("function iszk() {\r\n");
      out.write("\treturn userlevel == RoleLevel.zkLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("//是否分库管理员\r\n");
      out.write("function isfk() {\r\n");
      out.write("\treturn userlevel == RoleLevel.fkLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("//是否技术人员\r\n");
      out.write("function iswc() {\r\n");
      out.write("\treturn userlevel == RoleLevel.wcLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("//是否是模具管理员\r\n");
      out.write("function ismj() {\r\n");
      out.write("\treturn userlevel == RoleLevel.mjLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("//是否是仪表管理员\r\n");
      out.write("function isyb() {\r\n");
      out.write("\treturn userlevel == RoleLevel.ybLevel ? true : false;\r\n");
      out.write("}\r\n");
      out.write("/* //set  CookieProvider 对应后面stateId：filetable\r\n");
      out.write("Ext.state.Manager.setProvider(new Ext.state.CookieProvider({\r\n");
      out.write("\texpires: new Date(new Date().getTime()+(1000*60*60*24*365))}\r\n");
      out.write(")); */\r\n");
      out.write("</script>\r\n");

	//不允许浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	session.setMaxInactiveInterval(-1);

      out.write("\r\n");
      out.write("<!-- END Ext -->");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("//检索类型选择\r\n");
      out.write("var Stringquery = [];\r\n");
      out.write("Stringquery[0] = [\"mnum\",\"name\"];\r\n");
      out.write("Stringquery[1] = [\"设备名称\",\"设备编号\"];\r\n");
      out.write("\r\n");
      out.write("//状态选择\r\n");
      out.write("var Stringstage = [];\r\n");
      out.write("Stringstage[0] = [\"all\",\"passing\",\"passed\",\"rejected\"];\r\n");
      out.write("Stringstage[1] = [\"所有\",\"待批准\",\"已批准\",\"已驳回\"];\r\n");
      out.write("Stringstage[2] = [\"all\",\"null\",\"passed\",\"rejected\"];\r\n");
      out.write("\r\n");
      out.write("//排序选择\r\n");
      out.write("var Stringorder = [];\r\n");
      out.write("Stringorder[0] = [\"mnum\",\"name\"];\r\n");
      out.write("Stringorder[1] = [\"设备名称\",\"设备编号\"];\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<div id=\"querydiv\"></div>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\" src=\"rapair.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
