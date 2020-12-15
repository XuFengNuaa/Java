package com.nuaa.sys.taglib.ext.inputs.associateinput;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.jenkov.prizetags.tree.impl.Tree;
import com.jenkov.prizetags.tree.impl.TreeNode;
import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.sys.util.StrUtil;
import com.nuaa.sys.util.base.taglib.exception.TagException;
import com.nuaa.sys.util.base.ExtConstant;
import com.nuaa.sys.util.Logger;

public class AssociateField extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4945456189973862580L;

	private String id = null;

	private String dataSources = null;

	private String root = "root";

	private String labels = null;

	// 根是否可见
	private boolean rootVisible = true;

	private String rootShowCompId = null;

	// 根节点ID
	private String rootIdentifier = null;

	private final Map nodeMap = new HashMap();	

	/***************************************************************************
	 * dataSources参数分解 public List getDataSourceList() { List dsList = new
	 * ArrayList(); int index = 0; String[] strs =
	 * StringUtil.split(this.dataSources, ";"); // 数据源是通过;号来分隔的 for (int i = 0;
	 * i < strs.length; i++) { DataSourceDef dsi = new DataSourceDef(); // 以@来分隔 //
	 * 数据库:表:id,name,parentid:condition@showCompId index = strs[i].indexOf("@"); //
	 * 设定数据源的页面绑定组件ID dsi.setShowCompId(strs[i].substring(index + 1,
	 * strs[i].length())); String tempStr = strs[i].substring(0, index);
	 * Logger.debug("\nLinkField中数据源串:" + tempStr); // 分解其它数据源信息 String[]
	 * subStrs = StringUtil.split(tempStr, ":"); if (subStrs.length != 4) throw
	 * new NestedUncheckedException("数据源信息不全,请查阅相关参考手册核对!");
	 * dsi.setDatabase(subStrs[0]); dsi.setTable(subStrs[1]);
	 * dsi.setSelectFields(subStrs[2]); dsi.setCondition(subStrs[3]);
	 * dsList.add(dsi); } return dsList; }
	 **************************************************************************/
	public Map getNodeMap() {
		return this.nodeMap;
	}
	public void resetNodeMap(){
		this.nodeMap.clear();
	}
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	// 在标签结束时组合成树
	public int doEndTag() {
		try {
			// nodeList已有所有节点啦

			// pageContext.getOut().println(this.getBodyContent().toString());

			// System.out.println("\n不打印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?印?");
			renderTree(pageContext.getOut());
		} catch (TagException tagException) {

			System.out.println("在processxml中获取xml包出错doEndBody中出错");
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return EVAL_PAGE;
	}

	// 种树,返回根节点
	public ITreeNode seedTree() {
		// System.out.println("\n刚进seedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTreeseedTree"+nodeMap.size());

		List rootNodeList = new ArrayList();
        //可指定多个根节点ID,它们用;隔开
		List rootIdentifierList = new ArrayList();
        String[] rootIdentifiers = null;
        if(this.rootIdentifier!=null && !"".equals(this.rootIdentifier))
        {
        	rootIdentifiers = StrUtil.split(rootIdentifier, ";");
			for (int i = 0; i < rootIdentifiers.length; i++) {
				rootIdentifierList.add(rootIdentifiers[i]);
			}        	
        }
        
        for(int i = 0 ;i<nodeMap.size();i++)
        {
        	Logger.debug("\nnodeMapnodeMap:");
        }
        System.out.println("nodeMap.size()"+nodeMap.size());
		Set entries = nodeMap.entrySet();
		if (entries != null) {
			Iterator it = entries.iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = entry.getKey().toString();
				ITreeNode tNode = (ITreeNode) entry.getValue();
				ITreeNode pNode = null;

				if ((this.rootIdentifier != null &&rootIdentifierList.contains(key))|| (this.rootIdentifier == null&&tNode.getToolTip() == null)||(this.rootIdentifier == null&&"".equals(tNode.getToolTip()))) {
					rootNodeList.add(tNode);
				} else {
					if (nodeMap.get(tNode.getToolTip()) != null) {

						pNode = (ITreeNode) nodeMap.get(tNode.getToolTip());
 						pNode.addChild(tNode);

					}else if(this.rootIdentifier == null){
						rootNodeList.add(tNode);
					}

				}

			}

		}
		// System.out.println("\nrootNoderootNoderootNoderootNoderootNode:"+rootNodeList.size());
		// 构造根节点
		ITreeNode rootNode = new TreeNode();
		rootNode.setId("000000000");
		rootNode.setName(this.root.equals("") ? "root" : this.root);
		rootNode.setToolTip(null);
		Map bindMap = new HashMap();
		bindMap.put("inputId", this.rootShowCompId);
		rootNode.setObject((Serializable) bindMap);

		if (rootNodeList.size() == 0) {
			return rootNode;

		} else if (rootNodeList.size() == 1) {
			rootNode.addChild((ITreeNode) rootNodeList.get(0));
			return rootNode;
		} else if (rootNodeList.size() > 1) {
			for (int i = 0; i < rootNodeList.size(); i++) {
				// 父加子
				rootNode.addChild((ITreeNode) rootNodeList.get(i));

			}
			return rootNode;
		}

		return null;
	}

	// 打印树的JS
	public void renderTree(JspWriter jw) {
		try {
			ITree tree = new Tree();
			// jw.println("<br>开始打印:");
			ITreeNode tempRootNode = null;
			// jw.println("<br>图的大小:"+this.nodeMap.size());
			tempRootNode = seedTree();
			// jw.println("tempRootNodetempRootNodetempRootNodetempRootNodetempRootNode:"+tempRootNode);
			if (tempRootNode == null)
				System.out.println(
						"\n根节点不存在,请检查AssociateField标签的数据源子标签!");
			else
				tree.setRoot(tempRootNode);
			// jw.println("<br>种树结束");
			// jw.println("<br>我的树我的树我的树我的树我的树我的树我的树我的树我的树我的树我的树我的树我的树我的树我的树:"+tree.getRoot()+"<br>有无子节点:"+tree.getRoot().getChildCount());
			String sid=ExtConstant.TreeTag_INSESSION+this.id;
			pageContext.getSession().setAttribute(sid, tree);
			//System.out.println(tree.findNode("bb1e7ffb-b8fa-4c0e-8def-9793691c6b09").getName());
			System.out.println(tree);
			// tree.setRoot(((ITree)pageContext.getSession().getAttribute(SessionKeys.USER_TREE_MODEL)).getRoot());
			StringBuffer sb = new StringBuffer();
			String inputStr = "";
			if (this.rootShowCompId != null && !"".equals(this.rootShowCompId)) {
				inputStr = ",inputId:'" + this.rootShowCompId + "'";
			}
			sb
					.append("<div id=\"" + this.id + "\"style=\"overflow:auto;\"></div>")
					.append("<script language=\"javascript\">")
					.append("var "+this.id+";")
					.append("Ext.onReady(function(){")
					.append("var Tree = Ext.tree; 			var tree = new Tree.TreePanel("
									+"{el:'"+ this.id+"',autoScroll:true,width:200,rootVisible: "
									+ this.rootVisible
									+ ",animate:false,loader: new Tree.TreeLoader({dataUrl:'/tsms/web/inc/jsp/getTree.jsp?treeid="+this.id+"'}),containerScroll:true}); 			 			 		 			var root = new Tree.AsyncTreeNode({ 				text: '"
									+ tree.getRoot().getName()
									+ "',cls:'folder-node',draggable:true,id: '"
									+ tree.getRoot().getId() + "' 		"
									+ inputStr
									+ "});tree.setRootNode(root);")
					.append("			 			tree.render();tree.expand();"+this.id+"=tree;new Tree.TreeSorter("+this.id+", {folderSort:true});});</script>");

			jw.println(sb.toString());
			Logger.debug(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("写树展现的JS出错!");
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getRootShowCompId() {
		return rootShowCompId;
	}

	public void setRootShowCompId(String rootShowCompId) {
		this.rootShowCompId = rootShowCompId;
	}

	public boolean isRootVisible() {
		return rootVisible;
	}

	public void setRootVisible(boolean rootVisible) {
		this.rootVisible = rootVisible;
	}

	public String getRootIdentifier() {
		return rootIdentifier;
	}

	public void setRootIdentifier(String rootIdentifier) {
		this.rootIdentifier = rootIdentifier;
	}

	public String getDataSources() {
		return dataSources;
	}

	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

}
