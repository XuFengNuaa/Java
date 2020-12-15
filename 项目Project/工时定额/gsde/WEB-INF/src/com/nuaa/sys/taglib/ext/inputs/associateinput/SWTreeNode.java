package com.nuaa.sys.taglib.ext.inputs.associateinput;

import com.jenkov.prizetags.tree.impl.TreeNode;

public class SWTreeNode extends TreeNode {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String parentId = null;
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
