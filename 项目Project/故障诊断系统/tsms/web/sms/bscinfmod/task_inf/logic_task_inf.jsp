<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,java.io.*,com.nuaa.sys.util.AppInsFactory,com.nuaa.app.Task_mod"%>
<%@ page import="com.nuaa.sys.StarTree,
				com.jenkov.prizetags.tree.impl.Tree,
				com.jenkov.prizetags.tree.itf.ITree,
				com.jenkov.prizetags.tree.itf.ITreeNode
		" %>
<%
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
%>


