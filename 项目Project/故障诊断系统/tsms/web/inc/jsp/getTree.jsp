<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,  org.json.JSONObject, org.json.JSONArray,
				com.jenkov.prizetags.tree.impl.TreeNode,
				com.jenkov.prizetags.tree.impl.Tree,
				com.jenkov.prizetags.tree.itf.ITree,
				com.jenkov.prizetags.tree.itf.ITreeNode,
				com.nuaa.sys.util.base.SessionKeys,
				com.nuaa.sys.util.AppInsFactory,
				com.nuaa.sys.StarTree,
				com.nuaa.sys.util.Logger,com.nuaa.sys.util.base.ExtConstant"
%>
<%
//不允许浏览器缓存
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-Control", "no-cache");
response.setHeader("Expires", "0");
response.flushBuffer();
//取得传入的节点id参数
String nodeid = null;
String treeid = null;
if(request.getParameter("node") != null){
	nodeid = request.getParameter("node"); 
}
if(request.getParameter("treeid") != null){
	treeid = request.getParameter("treeid"); 
}
Logger.debug(nodeid);
Logger.debug(treeid);
//取得Sessoin中的树对象
//Logger.debug(com.nuaa.sys.util.base.ExtConstant.TreeTag_INSESSION+treeid);
ITree tree = (Tree)session.getAttribute(ExtConstant.TreeTag_INSESSION+treeid);
//当前节点默认为空
ITreeNode node = null;

//没有传入当前节点id，则当前节点默认为根节点

if (nodeid == null)
{
	node = tree.getRoot();
}
else
{
	node = tree.findNode(nodeid);
}
Logger.debug(node.getName());

//如果当前节点不为空且它有子节点
if (node != null && node.hasChildren())
{
	//取得当前节点的所有子节点
	List childList = node.getChildren();
	//取得子节点数量
	int childCount = childList.size();

	//临时节点变量
	ITreeNode tempNode = null;
	//节点附加object
	Map map = null;

	//节点css样式
	String css = "folder";
	//是否子节点
	boolean leaf = false;

	//建立json数组对象
	JSONArray jsonArray = new JSONArray();
	boolean hasChild=false;
	//取得所有子节点属性
	for (int i = 0; i < childCount; i ++)
	{
		tempNode = (ITreeNode)childList.get(i);	
		if(tempNode.getId().equals("haddel")){
			continue;
		}
		if (tempNode.hasChildren())
		{
			css = "folder-node";
			leaf = false;
			hasChild=true;
		}
		else
		{
			int isfolder=0;
			hasChild=false;
			if(((Map)tempNode.getObject()).get("isfolder")!=null){
				isfolder=Integer.parseInt(((Map)tempNode.getObject()).get("isfolder").toString());
			}
			if(isfolder==1){
				css ="folder-node";				
				leaf=false;
			}else{
				css = "file";
				leaf=true;			
			}			
		}	
		//新建json对象
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",tempNode.getId());
		jsonObject.put("text",tempNode.getName());
		if(hasChild){
			jsonObject.put("hasChild","1");
		}else{
			jsonObject.put("hasChild","0");
		}		
    	map = (HashMap)tempNode.getObject(); 
    	//jsonObject.put("inputId",map.get("inputId").toString());
    	//增加其它字段属性
		Set entries = map.entrySet();
		Iterator it = entries.iterator();
		if (entries != null) {
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				String value = null;
				if(entry.getValue()!=null){
					value = entry.getValue().toString();
				}else{
					value = "";	
				}
        		jsonObject.put(key,value);
			}
		}	
		//赋值此节点的css
		jsonObject.put("cls",css);
		//如果是子节点
		if (leaf){
			jsonObject.put("leaf", leaf);
		}		
		//增加到json数组
		jsonArray.put(jsonObject);
	}
	//输出json数据
	out.print(jsonArray.toString());
	Logger.debug("aaa:"+jsonArray.toString());
}
%>