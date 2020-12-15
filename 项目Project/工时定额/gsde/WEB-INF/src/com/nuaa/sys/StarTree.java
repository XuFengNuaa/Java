/**
 * 
 */
package com.nuaa.sys;

import java.util.HashMap;

import com.jenkov.prizetags.tree.itf.ITree;
import com.jenkov.prizetags.tree.itf.ITreeNode;

/**
 * @author mahao
 *
 */
public interface StarTree {
	public String getParentsId(ITree tree,ITreeNode node);
	public String getChildrenId(ITree tree,ITreeNode node);	
	public ITreeNode getNode(HashMap nodeHashMap);
	public void addChildToNode(ITreeNode node,HashMap nodeHashMap);
	public int getTreeNodeCnt(ITree tree);
}
