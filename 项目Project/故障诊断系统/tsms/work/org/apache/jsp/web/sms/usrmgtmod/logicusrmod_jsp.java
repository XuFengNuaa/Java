package org.apache.jsp.web.sms.usrmgtmod;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.JSONObject;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.UsrMod;
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

public final class logicusrmod_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	String user_num=(String)session.getAttribute("userid");
	UsrMod um=(UsrMod)AppInsFactory.getBean("UsrMod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
		if ("USRADD".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("stuff_num")!=null){
				hashMap.put("stuff_num",request.getParameter("stuff_num"));
			}
			if(request.getParameter("realname")!=null){
				hashMap.put("realname",request.getParameter("realname"));
			}
			if(request.getParameter("order_num")!=null){
				hashMap.put("order_num",request.getParameter("order_num"));
			}
			if(request.getParameter("password")!=null){
				hashMap.put("password",request.getParameter("password"));
			}
			if(request.getParameter("group_num")!=null){
				hashMap.put("group_num",request.getParameter("group_num"));
			}
			if(request.getParameter("remark")!=null){
				hashMap.put("remark",request.getParameter("remark"));
			}
			String userLevels = "";
			if("multiroles".equals(request.getParameter("role"))){
				if(request.getParameter("User")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("User");
					}else{
						userLevels += ","+request.getParameter("User");
					}
				}
				if(request.getParameter("TeamLeader")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("TeamLeader");
					}else{
						userLevels += ","+request.getParameter("TeamLeader");
					}
				}
				if(request.getParameter("Boss")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("Boss");
					}else{
						userLevels += ","+request.getParameter("Boss");
					}
				}
				if(request.getParameter("StoreManager")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("StoreManager");
					}else{
						userLevels += ","+request.getParameter("StoreManager");
					}
				}	
				if(request.getParameter("Sys")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("Sys");
					}else{
						userLevels += ","+request.getParameter("Sys");
					}
				}	
				if(request.getParameter("Programmer")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("Programmer");
					}else{
						userLevels += ","+request.getParameter("Programmer");
					}
				}
				if(request.getParameter("Prepare")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("Prepare");
					}else{
						userLevels += ","+request.getParameter("Prepare");
					}
				}
				if(request.getParameter("zongku")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("zongku");
					}else{
						userLevels += ","+request.getParameter("zongku");
					}
				}
				if(request.getParameter("fenku")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("fenku");
					}else{
						userLevels += ","+request.getParameter("fenku");
					}
				}if(request.getParameter("waichang")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("waichang");
					}else{
						userLevels += ","+request.getParameter("waichang");
					}
				}if(request.getParameter("moju")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("moju");
					}else{
						userLevels += ","+request.getParameter("moju");
					}
				}if(request.getParameter("yibiao")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("yibiao");
					}else{
						userLevels += ","+request.getParameter("yibiao");
					}
				}
			}else{
				userLevels = request.getParameter("role");
			}
			hashMap.put("userLevels",userLevels);
			String returnValue = um.addUsr(hashMap);
			if ("true".equals(returnValue)){
				result="{success:true,msg:'ok'}";
			}else if("Exception".equals(returnValue)){
				result="{failure:true}";
			}else{
				result="{success:true,msg:'该用户已经存在！'}";
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
			result=um.getQueryUsr(hashMap).toString();
		}else if("DEL".equals(type)){
			HashMap hashMap=new HashMap();
			hashMap.put("user_num",user_num);
			if(request.getParameter("stuff_num")!=null){
				hashMap.put("stuff_num",request.getParameter("stuff_num"));
			}
			boolean returnnum=um.checkNum(hashMap);
			if(returnnum){
				if (um.delUsr(hashMap)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result="{failure:true}";
			}
		}else if("EDIT".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("stuff_num")!=null){
				hashMap.put("stuff_num",request.getParameter("stuff_num"));
			}
			if(request.getParameter("realname")!=null){
				hashMap.put("realname",request.getParameter("realname"));
			}
			if(request.getParameter("password")!=null){
				hashMap.put("password",request.getParameter("password"));
			}
			if(request.getParameter("group_num")!=null){
				hashMap.put("group_num",request.getParameter("group_num"));
			}
			if(request.getParameter("order_num")!=null){
				hashMap.put("order_num",request.getParameter("order_num"));
			}
			if(request.getParameter("remark")!=null){
				hashMap.put("remark",request.getParameter("remark"));
			}
			String userLevels = "";
				if(request.getParameter("User")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("User");
					}else{
						userLevels += ","+request.getParameter("User");
					}
				}
				if(request.getParameter("TeamLeader")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("TeamLeader");
					}else{
						userLevels += ","+request.getParameter("TeamLeader");
					}
				}
				if(request.getParameter("Boss")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("Boss");
					}else{
						userLevels += ","+request.getParameter("Boss");
					}
				}
				if(request.getParameter("StoreManager")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("StoreManager");
					}else{
						userLevels += ","+request.getParameter("StoreManager");
					}
				}	
				if(request.getParameter("Sys")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("Sys");
					}else{
						userLevels += ","+request.getParameter("Sys");
					}
				}
				if(request.getParameter("Programmer")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("Programmer");
					}else{
						userLevels += ","+request.getParameter("Programmer");
					}
				}
				if(request.getParameter("Prepare")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("Prepare");
					}else{
						userLevels += ","+request.getParameter("Prepare");
					}
				}
				if(request.getParameter("fc")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("fc");
					}else{
						userLevels += ","+request.getParameter("fc");
					}
				}
				if(request.getParameter("zk")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("zk");
					}else{
						userLevels += ","+request.getParameter("zk");
					}
				}
				if(request.getParameter("fk")!=null){
					if(userLevels==""){
						userLevels = request.getParameter("fk");
					}else{
						userLevels += ","+request.getParameter("fk");
					}
				}
			hashMap.put("userLevels",userLevels);
			if(request.getParameter("uid")!=null){
				hashMap.put("id",request.getParameter("uid"));
			}			
			String returnValue = um.modiUser(hashMap);
			if("true".equals(returnValue)){
				result="{success:true}";
			}else{
				result="{success:false}";
			}
		}else if("VIEW".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("stuff_num")!=null){	
				hashMap.put("stuff_num",request.getParameter("stuff_num"));
			}
			result=um.viewUsr(hashMap).toString();	
		}else if("QUERYTEAM".equals(type)){			
			result=um.allTeamLeader().toString();	
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
