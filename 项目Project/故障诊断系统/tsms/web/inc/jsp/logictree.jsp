<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter,com.nuaa.sys.util.AppInsFactory"%>
<%@ page import="com.jenkov.prizetags.tree.impl.Tree,
				com.jenkov.prizetags.tree.itf.ITree,
				com.jenkov.prizetags.tree.itf.ITreeNode,
				com.nuaa.sys.StarTree"
%>
<%
	//不允许浏览器缓存
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires","0");
	//取得Sessoin中的树对象
	String treeid="treeid";
	if(request.getParameter("treeid")!=null){
		treeid=request.getParameter("treeid");
	};
	ITree tree = (Tree)session.getAttribute(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+treeid);
	String type = request.getParameter("type");
	PrintWriter writer = response.getWriter();
	String result="0";
	StarTree st=(StarTree)AppInsFactory.getBean("StarTree");
	try{
		if("getNodeParendsId".equals(type)){
			String nodeId="";
			if(request.getParameter("nodeId")!=null){
				nodeId=request.getParameter("nodeId");		
			}
			//System.out.println("nodeId:"+nodeId);
			ITreeNode node= tree.findNode(nodeId);
			//System.out.println("nodeId:"+node.getId());
			result="{success:true,msg:'"+st.getParentsId(tree,node)+"'}";
			//result="{success:true,msg:'1$2$3$9'}";
		}else if("getNodeChildrenId".equals(type)){
			String nodeId="";
			if(request.getParameter("nodeId")!=null){
				nodeId=request.getParameter("nodeId");		
			}
			ITreeNode node= tree.findNode(nodeId);
			System.out.println("nodeId:"+node.getId());
			result="{success:true,msg:'"+st.getChildrenId(tree,node)+"'}";			
		}
	}catch(Exception e){
		result="{success:false,msg:'不成功'}";
	}finally{
		response.setContentType("text/html;charset=UTF-8");
		writer.write(result);writer.close();
	}
%>