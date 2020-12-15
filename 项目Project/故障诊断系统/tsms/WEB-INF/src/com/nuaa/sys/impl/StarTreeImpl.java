/**
 * 
 */
package com.nuaa.sys.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jenkov.prizetags.tree.impl.TreeNode;
import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.sys.StarTree;

/**
 * @author mahao
 *
 */
public class StarTreeImpl implements StarTree {
	private ITreeNode nodeTemp=null;
	private String parentsId="";
	private String childrenId="";
	private int i=0;
	/* （非 Javadoc）
	 * @see com.nuaa.sys.Tree#getParents(com.jenkov.prizetags.tree.itf.ITree, com.jenkov.prizetags.tree.impl.TreeNode)
	 */
	public String getParentsId(ITree tree, ITreeNode node) {
		// TODO 自动生成方法存根
		parentsId="";
		return getParentId(tree,node);		
	}
	public String getParentId(ITree tree, ITreeNode node) {
		// TODO 自动生成方法存根
		//System.out.println("parentsId:"+node.getId());
		if((node!=null)&&(node.getParent()!=null)){
			if(parentsId.equals("")){
				parentsId=node.getId();
			}else{
				parentsId=node.getId()+"$"+parentsId;
			}
			nodeTemp=node.getParent();
			System.out.println("parentsId:"+parentsId);
			return getParentId(tree,nodeTemp);
		}else{
			return parentsId;
		}		
	}
	public String getChildrenId(ITree tree,ITreeNode node){
		//System.out.println("here");
		//System.out.println(node);
		childrenId="'"+node.getId()+"'";
		//System.out.println(childrenId);
		addChildrenId(tree,node);
		//System.out.println(childrenId);
		return childrenId;
	}
	
	public void addChildrenId(ITree tree,ITreeNode node){
		List childList=node.getChildren();			
		for(int i=0;i<childList.size();i++){
			ITreeNode nodeTemp = null;
			nodeTemp=(ITreeNode)childList.get(i);
			childrenId="'"+nodeTemp.getId()+"',"+childrenId;
			if(nodeTemp.hasChildren()){
				addChildrenId(tree,nodeTemp);
			}				
		}			
	}
	
	public ITreeNode getNode(HashMap nodeHashMap){
		ITreeNode node = new TreeNode();
		node.setId((String)nodeHashMap.get("id"));
		node.setName((String)nodeHashMap.get("menuname"));
		node.setToolTip((String)nodeHashMap.get("parentmenuid"));
		// node.setObject(this);
		Map bindMap = new HashMap();
		bindMap.put("isfolder",(String)nodeHashMap.get("isfolder"));
		if(nodeHashMap.get("type") != null){
			bindMap.put("type",(String)nodeHashMap.get("type"));
		}
		if(nodeHashMap.get("state") != null){
			bindMap.put("state",(String)nodeHashMap.get("state"));
		}
		if(nodeHashMap.get("shortcutid") != null){
			bindMap.put("shortcutid",(String)nodeHashMap.get("shortcutid"));
		}
		node.setObject((Serializable) bindMap);
		return node;	
	}
	
	public void addChildToNode(ITreeNode node,HashMap nodeHashMap){		
		ITreeNode childNode=this.getNode(nodeHashMap);
		node.addChild(childNode);		
	}
	/* （非 Javadoc）
	 * @see com.nuaa.sys.StarTree#getTreeNodeCnt(com.jenkov.prizetags.tree.itf.ITree)
	 */
	public int getTreeNodeCnt(ITree tree) {
		// TODO 自动生成方法存根
		i=1;
		addChildCnt(tree.getRoot());
		return i;
	}
	public void addChildCnt(ITreeNode node){
		List childList=node.getChildren();			
		for(int i=0;i<childList.size();i++){
			ITreeNode nodeTemp = null;
			nodeTemp=(ITreeNode)childList.get(i);
			if(nodeTemp.hasChildren()){
				addChildCnt(nodeTemp);
			}else{
				i++;
			}				
		}
	}
}
