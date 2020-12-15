package org.apache.jsp.web.sms.storemgtmod.manufacture;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import org.json.*;
import java.io.*;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.PublicUtil;
import java.util.UUID;
import java.sql.*;
import com.nuaa.sys.util.AppInsFactory;
import com.nuaa.app.ManufactureMod;
import org.apache.commons.fileupload.*;
import javax.sql.DataSource;
import com.nuaa.sys.util.Logger;
import com.nuaa.app.RoleLevel;
import com.nuaa.app.FileState;
import com.jenkov.prizetags.tree.impl.Tree;
import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.sys.StarTree;
import com.nuaa.sys.util.Logger;
import javax.servlet.http.HttpServletResponse;

public final class logic_005fmanufacture_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");

	//不允许浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	String type = request.getParameter("type");
    PrintWriter writer = response.getWriter();
    String result=""; 
    ManufactureMod pm=(ManufactureMod)AppInsFactory.getBean("ManufactureMod");
    String treeid="treeid";
	if(request.getParameter("treeid")!=null){
		treeid=request.getParameter("treeid");
	};
    //取得Sessoin中的树对象
	ITree tree = (Tree)session.getAttribute(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+treeid);

    try{	    
    	if("ADD".equals(type)){
	final String[] pa = new String[14];
	HashMap hashMap=new HashMap();
	for (int i = 0; i < pa.length; i++) {
		pa[i] = "";
	}
	if (request.getParameter("dalei_sim") != null) {
		pa[1] = request.getParameter("dalei_sim");
		System.out.println(pa[1]);
	}//大类
	if (request.getParameter("xiaolei_sim") != null) {
		pa[2] = request.getParameter("xiaolei_sim");
	}//小类
    if (request.getParameter("machine_sim") != null) {
		pa[3] = request.getParameter("machine_sim");	
	}//设备
	if (request.getParameter("material_sim") != null) {
		pa[4] = request.getParameter("material_sim");
	}//材料                                                          
	if (request.getParameter("daihao_sim") != null) {
		pa[5] = request.getParameter("daihao_sim");
	}//代号
	if (request.getParameter("mingcheng_sim") != null) {
		pa[6] = request.getParameter("mingcheng_sim");
	}//零件名称
	if (request.getParameter("tuhao_sim") != null) {
		pa[7] = request.getParameter("tuhao_sim");
	}//图号
	if (request.getParameter("maopilong_sim") != null) {
		pa[8] = request.getParameter("maopilong_sim");
	}//长
	if (request.getParameter("maopiwidth_sim") != null) {
		pa[9] = request.getParameter("maopiwidth_sim");
	}//宽
	if (request.getParameter("maopihigh_sim") != null) {
		pa[10] = request.getParameter("maopihigh_sim");
	}//高		
	if (request.getParameter("qxtj_sim") != null){
		pa[11] = request.getParameter("qxtj_sim");
	}//体积
	if (request.getParameter("qxmj_sim") != null){
		pa[12] = request.getParameter("qxmj_sim");
	}//面积
	if (request.getParameter("zxd_sim") != null){
		pa[13] = request.getParameter("zxd_sim");
	}//置信度
	pa[0] = UUID.randomUUID().toString();
	for (int i = 0; i < pa.length; i++) {
		System.out.println("我是logic页面"+pa[i]);
	}
	hashMap.put("pa[0]",pa[0]);
	hashMap.put("pa[1]",pa[1]);
	hashMap.put("pa[2]",pa[2]);
	hashMap.put("pa[3]",pa[3]);
	hashMap.put("pa[4]",pa[4]);
	hashMap.put("pa[5]",pa[5]);
	hashMap.put("pa[6]",pa[6]);
	hashMap.put("pa[7]",pa[7]);
	hashMap.put("pa[8]",pa[8]);
	hashMap.put("pa[9]",pa[9]);
	hashMap.put("pa[10]",pa[10]);
	hashMap.put("pa[11]",pa[11]);
	hashMap.put("pa[12]",pa[12]);
	hashMap.put("pa[13]",pa[13]);
	if(pm.addFile(hashMap)){
		result ="1";
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
	
	result=pm.getQueryFile(hashMap).toString();
		}else if("uploadimg".equals(type)){
	response.setContentType("text/plain");
    	    String dir=request.getRealPath("");
    	    final HashMap fileHashMap=new HashMap();
		  	fileHashMap.put("dir",dir);
		  	DiskFileUpload fu = new DiskFileUpload();
	fu.setSizeMax(919430400);
	fu.setSizeThreshold(4096);
	List fileItems=fu.parseRequest(request);
	fileHashMap.put("fileItems",fileItems);
	fileHashMap.put("ljth",request.getParameter("ljth"));
	HashMap HashMapNew=pm.uploadFile2(fileHashMap);
    	    result="{success:true,type:'"+(String)HashMapNew.get("types")+"',fileid:'"+(String)HashMapNew.get("fileid")+"'}";
    		System.out.println("上传文件"+result);
		}else if("ADD2".equals(type)){
			if (request.getParameter("tuhao") != null) {
				String tuhao = request.getParameter("tuhao");
				 String dir=request.getRealPath("");
				result =  pm.getFile2(dir,tuhao);
				
				System.out.println("sassg"+result);
			}//材料                                                          
			
				}else if("ADDIMG".equals(type)){
			
/*	HashMap hashMap=new HashMap();
	hashMap.put("scsj",request.getParameter("scsj"));
	hashMap.put("scr",request.getParameter("scr"));
	hashMap.put("tpmc",request.getParameter("tpmc"));
	hashMap.put("ljth",request.getParameter("ljth"));
	if(pm.addImg(hashMap)){*/
		result ="1";
	/*}else{
		result ="2";
	}
	*/
		 }else if("DEL".equals(type)){
	HashMap hashMap=new HashMap();
	if(request.getParameter("id")!=null){
		hashMap.put("id",request.getParameter("id"));
	}
	String returnValue = pm.delPart(hashMap);
	
	if ("true".equals(returnValue)){
		result="1";
	}
		}
	}catch(Exception e){
		
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
