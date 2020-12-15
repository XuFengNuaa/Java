package org.apache.jsp.web.sms.ldappmod.check;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.JSONObject;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.LeaderCheck;
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

public final class logic_005fcheckmod_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	String stuff_num=(String)session.getAttribute("userid");
	LeaderCheck um=(LeaderCheck)AppInsFactory.getBean("LeaderCheck");
	PrintWriter writer = response.getWriter();
	String result="0";
	
	try{
		if("QUERYC".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
     		if(request.getParameter("start")!=null){
				hashMap.put("start",request.getParameter("start"));				
			}			
			if(request.getParameter("limit")!=null){
				hashMap.put("limit",request.getParameter("limit"));
			}
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			result=um.getQuerycut(hashMap).toString();
		
		}else if("QUERYCT".equals(type)){
				
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
			result=um.getQueryct(hashMap).toString();
		
		}else if("QUERYM".equals(type)){
			
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
			result=um.getQuerym(hashMap).toString();
		
		}else if("QUERYMT".equals(type)){
			
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
			result=um.getQuerymt(hashMap).toString();
		
		}else if("QUERYCG".equals(type)){
		
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
			result=um.getQueryCgout(hashMap).toString();
			
		}else if("DETAILGRIND".equals(type)){
		
			HashMap hashMap=new HashMap();			
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashMap.put("order",request.getParameter("order"));
			}
			result=um.detail_do_togrind(hashMap).toString();
			
		}else if("QUERYCGIN".equals(type)){
		
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
			result=um.getQueryCgin(hashMap).toString();
		
		}else if("grd_numDetail".equals(type)){
			
			HashMap hashMap=new HashMap();			
			if(request.getParameter("filter")!=null){
				hashMap.put("filter",request.getParameter("filter"));			
			}
			if(request.getParameter("order")!=null){
				hashMap.put("order",request.getParameter("order"));
			}
			result=um.detail_do_ingrind(hashMap).toString();	
			
		}else if("APPLY".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.cutCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCut(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
		
		}else if("CRefuse".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.cutCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refCut(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYCT".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.ctCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCt(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
		
		}else if("CTRefuse".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.ctCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refCt(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYM".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.mCheck(hashMap);
				
			if(returncheck){
				String returnValue = um.appM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("MRefuse".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.mCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYMT".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.mtCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appMt(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
	
		}else if("MTRefuse".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("entry_nums")!=null){
				hashMap.put("entry_nums",request.getParameter("entry_nums"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.mtCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refMt(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
				
			
		}else if("APPLYCG".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("grd_num")!=null){
				hashMap.put("grd_num",request.getParameter("grd_num"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCgout(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYCGm".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("Rid")!=null){
				hashMap.put("Rid",request.getParameter("Rid"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdMCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCgoutM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("RefuseM".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("Rid")!=null){
				hashMap.put("Rid",request.getParameter("Rid"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdMCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refCgoutM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("Refuse".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("grd_num")!=null){
				hashMap.put("grd_num",request.getParameter("grd_num"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.refCgout(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
			
		}else if("APPLYCGIN".equals(type)){
		
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("grd_num")!=null){
				hashMap.put("grd_num",request.getParameter("grd_num"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdinCheck(hashMap);
			System.out.println("returnchcek=="+returncheck);
			if(returncheck){
				String returnValue = um.appCgin(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
			}
		
		}else if("APPLYCGINm".equals(type)){
			
			HashMap hashMap=new HashMap();
			hashMap.put("leader_num",stuff_num);
			if(request.getParameter("Rid")!=null){
				hashMap.put("Rid",request.getParameter("Rid"));
			}
			if(request.getParameter("length")!=null){
				hashMap.put("length",request.getParameter("length"));
			}
			boolean returncheck = um.grdinMCheck(hashMap);
			
			if(returncheck){
				String returnValue = um.appCginM(hashMap);
				if ("true".equals(returnValue)){
					result="{success:true}";
				}else{
					result="{failure:true}";
				}
			}else{
				result = "{failure:true}";
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
