package org.apache.jsp.web.sms.bscinfmod.cutter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.commons.fileupload.*;
import org.json.JSONObject;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.CutterMod;
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
import com.nuaa.app.HQBorrow;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.PublicUtil;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.IArrayPreparedStatementProcessor;
import com.nuaa.sys.util.IStatementProcessor;;

public final class logic_005fcutter_jsp extends org.apache.jasper.runtime.HttpJspBase
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
	HQBorrow sd=(HQBorrow)AppInsFactory.getBean("HQBorrow");
	CutterMod um=(CutterMod)AppInsFactory.getBean("CutterMod");
	PrintWriter writer = response.getWriter();
	String result="0";
	try{
			if ("ADD".equals(type)){
			HashMap hashMap=new HashMap();	
			
			if(request.getParameter("los_mnum")!=null){
				hashMap.put("mnum",request.getParameter("los_mnum"));
			}
			if(request.getParameter("los_ISO")!=null){
				hashMap.put("iso_num",request.getParameter("los_ISO"));
			}
			if(request.getParameter("los_name")!=null){
				hashMap.put("name",request.getParameter("los_name"));
			}
			if(request.getParameter("los_pclass")!=null){
				hashMap.put("pclass",request.getParameter("los_pclass"));
			}
			if(request.getParameter("los_bclass")!=null){
				hashMap.put("bclass",request.getParameter("los_bclass"));
			}
			if(request.getParameter("companyid")!=null){
				hashMap.put("companyid",request.getParameter("companyid"));
			}
			if(request.getParameter("los_mini_qs")!=null){
				hashMap.put("mini_qs",request.getParameter("los_mini_qs"));
			}
			if(request.getParameter("los_rank_angle")!=null){
				hashMap.put("rank_angle",request.getParameter("los_rank_angle"));
			}
			if(request.getParameter("los_c_mate")!=null){
				hashMap.put("c_mate",request.getParameter("los_c_mate"));
			}
			if(request.getParameter("los_coat_mate")!=null){
				hashMap.put("coat_mate",request.getParameter("los_coat_mate"));
			}
			if(request.getParameter("hq")!=null){
				hashMap.put("hq",request.getParameter("hq"));
			}
			if(request.getParameter("los_suit_mate2")!=null){
				hashMap.put("suit_mate2",request.getParameter("los_suit_mate2"));				
			}
			if(request.getParameter("los_suit_mate3")!=null){
				hashMap.put("suit_mate3",request.getParameter("los_suit_mate3"));				
			}
			if(request.getParameter("los_use_freq")!=null){
				hashMap.put("use_freq",request.getParameter("los_use_freq"));
			}
			if(request.getParameter("location")!=null){
				hashMap.put("location",request.getParameter("location"));
			}
			if(request.getParameter("los_suit_mate1")!=null){
				hashMap.put("suit_mate1",request.getParameter("los_suit_mate1"));
			}
			if(request.getParameter("los_remark")!=null){
				hashMap.put("remark",request.getParameter("los_remark"));				
			}
			if(request.getParameter("los_e_diam")!=null){
				hashMap.put("e_diam",request.getParameter("los_e_diam"));
			}
			if(request.getParameter("los_h_diam")!=null){
				hashMap.put("h_diam",request.getParameter("los_h_diam"));
			}
			if(request.getParameter("los_e_leng")!=null){
				hashMap.put("e_leng",request.getParameter("los_e_leng"));
			}
			if(request.getParameter("los_t_leng")!=null){
				hashMap.put("t_leng",request.getParameter("los_t_leng"));
			}
			if(request.getParameter("los_tee_count")!=null){
				hashMap.put("tee_count",request.getParameter("los_tee_count"));
			}
			if(request.getParameter("los_s_ang")!=null){
				hashMap.put("s_ang",request.getParameter("los_s_ang"));
			}
			if(request.getParameter("los_tip_heig")!=null){
				hashMap.put("tip_heig",request.getParameter("los_tip_heig"));
			}
			if(request.getParameter("los_bla_wide")!=null){
				hashMap.put("bla_wide",request.getParameter("los_bla_wide"));
			}
			if(request.getParameter("los_c_leng")!=null){
				hashMap.put("c_leng",request.getParameter("los_c_leng"));
			}
			if(request.getParameter("los_e_count")!=null){
				hashMap.put("e_count",request.getParameter("los_e_count"));
			}
			if(request.getParameter("los_e_wide")!=null){
				hashMap.put("e_wide",request.getParameter("los_e_wide"));
			}
			if(request.getParameter("los_t_form")!=null){
				hashMap.put("t_form",request.getParameter("los_t_form"));				
			} 
			if(request.getParameter("los_c_way")!=null){
				hashMap.put("c_way",request.getParameter("los_c_way"));
			}
			if(request.getParameter("los_t_type")!=null){
				hashMap.put("t_type",request.getParameter("los_t_type"));
			}
			if(request.getParameter("los_spec")!=null){
				hashMap.put("spec",request.getParameter("los_spec"));				
			}			
			if(request.getParameter("los_type")!=null){
				hashMap.put("type",request.getParameter("los_type"));
			}
			if(request.getParameter("los_series_num")!=null){
				hashMap.put("series_num",request.getParameter("los_series_num"));
			}
			if(request.getParameter("los_order_num")!=null){
				hashMap.put("order_num",request.getParameter("los_order_num"));				
			} 
			if(request.getParameter("los_nc_count_in")!=null){
				hashMap.put("nc_count_in",request.getParameter("los_nc_count_in"));
			}
			if(request.getParameter("los_gc_count_in")!=null){
				hashMap.put("gc_count_in",request.getParameter("los_gc_count_in"));
			}
			if(request.getParameter("los_uc_count_in")!=null){
				hashMap.put("uc_count_in",request.getParameter("los_uc_count_in"));	
			}
			if(request.getParameter("effect_length")!=null){
				hashMap.put("effect_length",request.getParameter("effect_length"));	
			}
			
			String returnValue = um.add_cutter(hashMap);
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
			result=um.getQuery_cutter(hashMap).toString();
			System.out.println("ret"+result+"hashmap:" + hashMap);			
		}else if("EDIT".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("los_mnum")!=null){
				hashMap.put("mnum",request.getParameter("los_mnum"));
			}
			if(request.getParameter("los_pclass")!=null){
				hashMap.put("pclass",request.getParameter("los_pclass"));
			}
			if(request.getParameter("los_bclass")!=null){
				hashMap.put("bclass",request.getParameter("los_bclass"));
			}
			if(request.getParameter("los_ISO")!=null){
				hashMap.put("iso_num",request.getParameter("los_ISO"));
			}
			if(request.getParameter("los_name")!=null){
				hashMap.put("name",request.getParameter("los_name"));
			}
			if(request.getParameter("com_id")!=null){
				hashMap.put("companyid",request.getParameter("com_id"));
			}
			if(request.getParameter("los_nc_count_in")!=null){
				hashMap.put("nc_count_in",request.getParameter("los_nc_count_in"));
			}
			if(request.getParameter("los_uc_count_in")!=null){
				hashMap.put("uc_count_in",request.getParameter("los_uc_count_in"));
			}
			if(request.getParameter("los_gc_count_in")!=null){
				hashMap.put("gc_count_in",request.getParameter("los_gc_count_in"));				
			}
			if(request.getParameter("los_mini_qs")!=null){
				hashMap.put("mini_qs",request.getParameter("los_mini_qs"));				
			}
			if(request.getParameter("los_suit_mate1")!=null){
				hashMap.put("suit_mate1",request.getParameter("los_suit_mate1"));
			}
			if(request.getParameter("los_suit_mate2")!=null){
				hashMap.put("suit_mate2",request.getParameter("los_suit_mate2"));
			}
			if(request.getParameter("los_suit_mate3")!=null){
				hashMap.put("suit_mate3",request.getParameter("los_suit_mate3"));
			}
			if(request.getParameter("los_use_freq")!=null){
				hashMap.put("use_freq",request.getParameter("los_use_freq"));
			}
			if(request.getParameter("los_rank_angle")!=null){
				hashMap.put("rank_angle",request.getParameter("los_rank_angle"));
			}
			if(request.getParameter("los_c_mate")!=null){
				hashMap.put("c_mate",request.getParameter("los_c_mate"));
			}
			if(request.getParameter("los_coat_mate")!=null){
				hashMap.put("coat_mate",request.getParameter("los_coat_mate"));
			}
			if(request.getParameter("los_e_diam")!=null){
				hashMap.put("e_diam",request.getParameter("los_e_diam"));
			}
			if(request.getParameter("los_h_diam")!=null){
				hashMap.put("h_diam",request.getParameter("los_h_diam"));
			}
			if(request.getParameter("los_e_leng")!=null){
				hashMap.put("e_leng",request.getParameter("los_e_leng"));
			}
			if(request.getParameter("los_t_leng")!=null){
				hashMap.put("t_leng",request.getParameter("los_t_leng"));				
			}
			if(request.getParameter("los_tee_count")!=null){
				hashMap.put("tee_count",request.getParameter("los_tee_count"));				
			}		
			if(request.getParameter("los_s_ang")!=null){
				hashMap.put("s_ang",request.getParameter("los_s_ang"));				
			}
			if(request.getParameter("los_tip_heig")!=null){
				hashMap.put("tip_heig",request.getParameter("los_tip_heig"));				
			}
			if(request.getParameter("los_bla_wide")!=null){
				hashMap.put("bla_wide",request.getParameter("los_bla_wide"));
			}
			if(request.getParameter("los_c_leng")!=null){
				hashMap.put("c_leng",request.getParameter("los_c_leng"));
			}
			if(request.getParameter("los_e_count")!=null){
				hashMap.put("e_count",request.getParameter("los_e_count"));
			}
			if(request.getParameter("los_e_wide")!=null){
				hashMap.put("e_wide",request.getParameter("los_e_wide"));
			}
			if(request.getParameter("los_t_form")!=null){
				hashMap.put("t_form",request.getParameter("los_t_form"));
			}
			if(request.getParameter("los_c_way")!=null){
				hashMap.put("c_way",request.getParameter("los_c_way"));
			}
			if(request.getParameter("los_t_type")!=null){
				hashMap.put("t_type",request.getParameter("los_t_type"));
			}
			if(request.getParameter("los_spec")!=null){
				hashMap.put("spec",request.getParameter("los_spec"));				
			}
			if(request.getParameter("los_remark")!=null){
				hashMap.put("remark",request.getParameter("los_remark"));	
			}	
			if(request.getParameter("los_type")!=null){
				hashMap.put("type",request.getParameter("los_type"));
			}
			if(request.getParameter("los_series_num")!=null){
				hashMap.put("series_num",request.getParameter("los_series_num"));
			}
			if(request.getParameter("los_order_num")!=null){
				hashMap.put("order_num",request.getParameter("los_order_num"));
			}
			if(request.getParameter("hq")!=null){
				hashMap.put("hq",request.getParameter("hq"));
			}
			if(request.getParameter("location")!=null){
				hashMap.put("location",request.getParameter("location"));				
			}
			if(request.getParameter("los_effect_length")!=null){
				hashMap.put("effect_length",request.getParameter("los_effect_length"));				
			}
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));				
			}
			String returnEditvalue = um.edit_cutter(hashMap);
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
			result=um.view_cutter(hashMap).toString();	
		}else if("GRINDEDIT".equals(type)){
			HashMap hashMap=new HashMap();			
			if(request.getParameter("los_pclass")!=null){
				hashMap.put("pclass",request.getParameter("los_pclass"));
			}
			if(request.getParameter("los_bclass")!=null){
				hashMap.put("bclass",request.getParameter("los_bclass"));
			}			
			if(request.getParameter("los_name")!=null){
				hashMap.put("name",request.getParameter("los_name"));
			}
			if(request.getParameter("com_id")!=null){
				hashMap.put("companyid",request.getParameter("com_id"));
			}			
			if(request.getParameter("los_gc_count_in")!=null){
				hashMap.put("gc_count_in",request.getParameter("los_gc_count_in"));				
			}			
			if(request.getParameter("los_suit_mate1")!=null){
				hashMap.put("suit_mate1",request.getParameter("los_suit_mate1"));
			}
			if(request.getParameter("los_suit_mate2")!=null){
				hashMap.put("suit_mate2",request.getParameter("los_suit_mate2"));
			}
			if(request.getParameter("los_suit_mate3")!=null){
				hashMap.put("suit_mate3",request.getParameter("los_suit_mate3"));
			}			
			if(request.getParameter("los_rank_angle")!=null){
				hashMap.put("rank_angle",request.getParameter("los_rank_angle"));
			}
			if(request.getParameter("los_c_mate")!=null){
				hashMap.put("c_mate",request.getParameter("los_c_mate"));
			}
			if(request.getParameter("los_coat_mate")!=null){
				hashMap.put("coat_mate",request.getParameter("los_coat_mate"));
			}
			if(request.getParameter("los_e_diam")!=null){
				hashMap.put("e_diam",request.getParameter("los_e_diam"));
			}
			if(request.getParameter("los_h_diam")!=null){
				hashMap.put("h_diam",request.getParameter("los_h_diam"));
			}
			if(request.getParameter("los_e_leng")!=null){
				hashMap.put("e_leng",request.getParameter("los_e_leng"));
			}
			if(request.getParameter("los_t_leng")!=null){
				hashMap.put("t_leng",request.getParameter("los_t_leng"));				
			}			
			if(request.getParameter("los_e_count")!=null){
				hashMap.put("e_count",request.getParameter("los_e_count"));
			}			
			if(request.getParameter("los_remark")!=null){
				hashMap.put("remark",request.getParameter("los_remark"));	
			}			
			if(request.getParameter("hq")!=null){
				hashMap.put("hq",request.getParameter("hq"));
			}
			if(request.getParameter("location")!=null){
				hashMap.put("location",request.getParameter("location"));				
			}
			if(request.getParameter("los_effect_length")!=null){
				hashMap.put("effect_length",request.getParameter("los_effect_length"));				
			}
			if(request.getParameter("id")!=null){
				hashMap.put("id",request.getParameter("id"));				
			}			
			String returnEditvalue = um.edit_grind_cutter(hashMap);
			if ("1".equals(returnEditvalue)){
				result="1";
			}else{				
				result="0";
			}
		}else if("getNowtime".equals(type)){
    	   String nowtime="";
    	   nowtime = PublicUtil.getSqlDate().toString();
    	   result="{success:true,nowtime:'"+nowtime+"'}";
	    }else if("NEEDPOST".equals(type)){
				HashMap hashMap=new HashMap();
				hashMap.put("worker_num",stuff_num);
				if(request.getParameter("id")!=null){
					hashMap.put("id",request.getParameter("id"));
				}
				if(request.getParameter("drawing_num_need")!=null){
					hashMap.put("drawing_num_need",request.getParameter("drawing_num_need"));
				}
				if(request.getParameter("need_count")!=null){
					hashMap.put("need_count",request.getParameter("need_count"));
				
					if (sd.needC(hashMap)){
						result="{success:true}";
					}else{
						result="{success:false}";
					}
				}
		}else if("UPLOAD".equals(type)){
			String dir=request.getRealPath("");
    		final HashMap fileHashMap=new HashMap();
    		fileHashMap.put("dir",dir);
    		DiskFileUpload fu = new DiskFileUpload();
    		fu.setSizeMax(919430400);                 
    		fu.setSizeThreshold(4096);
    		List fileItems=fu.parseRequest(request);
    		fileHashMap.put("fileItems",fileItems);
    		HashMap returnHashmap = um.uploadfile(fileHashMap);
     		result="{success:true,filename:'"+(String)returnHashmap.get("filename")+"'}";
     		System.out.println("上传文件"+result);
	   }else if("INPUT".equals(type)){
			HashMap hashMap=new HashMap();
			String dir=request.getRealPath("");
    		response.reset();
    		response.setContentType("application/vnd.ms-excel"); 
			hashMap.put("dir",dir);				
			if(request.getParameter("dalei")!=null){
				hashMap.put("dalei",request.getParameter("dalei"));
			}
			if(request.getParameter("xiaolei")!=null){
				hashMap.put("xiaolei",request.getParameter("xiaolei"));
			}			
    		out.clear();
    		out = pageContext.pushBody();    		
    		String returnValue = um.getExcel(hashMap);
			if ("true".equals(returnValue)){
				result="1";
			}else if("mnumfalse".equals(returnValue)){
				result="2";
			}else if("namefalse".equals(returnValue)){
				result="3";
			}else if("hqfalse".equals(returnValue)){
				result="4";
			}else if("mininull".equals(returnValue)){
				result="5";
			}else if("locationnull".equals(returnValue)){
				result="6";
			}else if("locationfalse".equals(returnValue)){
				result="7";
			}else if("daleinull".equals(returnValue)){
				result="8";
			}else if("xiaoleinull".equals(returnValue)){
				result="9";
			}else if("xiaoleifalse".equals(returnValue)){
				result="10";
			}else if("daleifalse".equals(returnValue)){
				result="11";
			}else if("classfalse".equals(returnValue)){
				result="12";
			}else if("newstorefalse".equals(returnValue)){
				result="13";
			}else if("oldstorefalse".equals(returnValue)){
				result="14";
			}else if("companyfalse".equals(returnValue)){
				result="15";
			}else if("ministorefalse".equals(returnValue)){
				result="16";
			}else if("orderfalse".equals(returnValue)){
				result="17";
			}else if("grindstorefalse".equals(returnValue)){
				result="18";
			}else if("e_leng_false".equals(returnValue)){
				result="19";
			}else if("t_leng_false".equals(returnValue)){
				result="20";
			}else if("effect_length_false".equals(returnValue)){
				result="21";
			}else if("e_count_false".equals(returnValue)){
				result="22";
			}else if("tipHeig_false".equals(returnValue)){
				result="23";
			}else if("blaWide_false".equals(returnValue)){
				result="24";
			}else if("c_leng_false".equals(returnValue)){
				result="25";
			}else if("e_wide_false".equals(returnValue)){
				result="26";
			}else if("tee_count_false".equals(returnValue)){
				result="27";
			}else{
				result="0";
			}    
			System.out.println("hrerererererer"+result);	
		}else if("APPLYEDIT".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("edit_id")!=null){	
				hashMap.put("id",request.getParameter("edit_id"));
			}
			if(request.getParameter("amount")!=null){	
				hashMap.put("amount",request.getParameter("amount"));
			}
			if(request.getParameter("k")!=null){	
				hashMap.put("k",request.getParameter("k"));
			}
			if(request.getParameter("before_amount")!=null){	
				hashMap.put("before_amount",request.getParameter("before_amount"));
			}
			result=um.correct_cutter(hashMap).toString();	
		}else if("COMBINE".equals(type)){
			HashMap hashMap=new HashMap();
			if(request.getParameter("first_id")!=null){	
				hashMap.put("first_id",request.getParameter("first_id"));
			}
			if(request.getParameter("second_id")!=null){	
				hashMap.put("second_id",request.getParameter("second_id"));
			}
			if(request.getParameter("combine_nc")!=null){	
				hashMap.put("combine_nc",request.getParameter("combine_nc"));
			}
			if(request.getParameter("combine_uc")!=null){	
				hashMap.put("combine_uc",request.getParameter("combine_uc"));
			}			
			result=um.combine_cutter(hashMap).toString();	
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
