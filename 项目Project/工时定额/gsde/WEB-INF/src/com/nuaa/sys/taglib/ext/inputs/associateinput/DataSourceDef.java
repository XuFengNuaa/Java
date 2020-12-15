package com.nuaa.sys.taglib.ext.inputs.associateinput;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.jenkov.prizetags.tree.impl.TreeNode;
import com.jenkov.prizetags.tree.itf.ITreeNode;
import com.nuaa.sys.util.DbUtil;
import com.nuaa.sys.util.base.TreeTableMapper;
import com.nuaa.sys.util.IResultSetProcessor;
import com.nuaa.sys.util.StrUtil;
import com.nuaa.sys.util.Logger;

public class DataSourceDef extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 804651317935737863L;

	// 数据库
	private String database = null;

	// 表
	private String table = null;

	// 节点ID
	private String nodeId = null;

	// 节点TEXT
	private String nodeText = null;

	// 节点父ID
	private String parentNodeId = null;

	// condition
	private String condition = "";

	// 自用的LIST
	private List fieldsList = null;

	// 其它字段
	private String otherFields = null;

	// 绑定的目标页面组件ID
	private String showCompId = null;

	public String getShowCompId() {
		return showCompId;
	}

	public void setShowCompId(String showCompId) {
		this.showCompId = showCompId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public int doStartTag() throws JspException {

		return SKIP_BODY;
	}

	public int doEndTag() {
		try {
			fieldsList = new ArrayList();
			String[] strs = null;
			if (this.otherFields != null && !"".equals(this.otherFields)) {
				strs = StrUtil.split(this.otherFields, ";");

				for (int i = 0; i < strs.length; i++) {
					fieldsList.add(strs[i]);
				}
			}
			// pageContext.getOut().println("进子标签来了");
			getNodesByDataSourceDef();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("doEndTag出错!");
		}

		return EVAL_PAGE;
	}

	// 根据datasourceinfo组织SQL,返回树节点
	public Map getNodesByDataSourceDef() {
		AssociateField af = (AssociateField) this.getParent();
		af.resetNodeMap();
		final Map theNodeMap = af.getNodeMap();
		// 根据DatasourceInfo组织SQL语句
		String sql = "";
		final TreeTableMapper ttm = new TreeTableMapper();
		ttm.setIdColumnName("id");
		ttm.setParentIdColumnName("parentId");
		ttm.setNameColumnName("name");

		String tableString = null;
		tableString = this.getTable();		
		if (this.parentNodeId != null && !"".equals(this.parentNodeId)) {
			sql = "select " + this.nodeId + " as id, " + this.nodeText
					+ " as name, " + this.parentNodeId + " as parentId ";
			for (int i = 0; i < fieldsList.size(); i++)
				sql += " , " + fieldsList.get(i);
			sql += "  from " + tableString + " where 1=1 " + this.getCondition();
		} else {
			sql = "select " + this.nodeId + " as id, " + this.nodeText
					+ " as name, '' as parentId ";
			for (int i = 0; i < fieldsList.size(); i++)
				sql += " , " + fieldsList.get(i);
			sql += " from " + tableString + " where 1=1 " + this.getCondition();
		}
		Logger.debug("根据datasourceinfo组织SQL:" + sql);

		// 查库获得此源的节点
		try {
			DbUtil.execute(sql,new IResultSetProcessor() {
				public void process(ResultSet rs) throws SQLException {					
					while (rs.next()) {						
						theNodeMap.put(rs.getString("id"),readTreeNode(rs, ttm));						
					}					
				}
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("根据datasourceinfo获取节点失败");
		}
		return theNodeMap;
	}

	private ITreeNode readTreeNode(ResultSet rs, TreeTableMapper ttm)
			throws SQLException {
		ITreeNode node = new TreeNode();
		if (null != ttm.getIdColumnName())
			node.setId(rs.getString(ttm.getIdColumnName()));
		if (null != ttm.getNameColumnName())
			node.setName(rs.getString(ttm.getNameColumnName()));
		// if (null != ttm.getTypeColumnName())
		// node.setType(rs.getString(ttm.getTypeColumnName()));
		if (null != ttm.getParentIdColumnName())
			node.setToolTip(rs.getString(ttm.getParentIdColumnName()));
		// node.setObject(this);
		//System.out.println("here2");
		Map bindMap = new HashMap();
		bindMap.put("inputId", this.showCompId);
		// 加入其它字段值
		for (int i = 0; i < fieldsList.size(); i++)
			bindMap.put(fieldsList.get(i).toString(), rs.getString(fieldsList
					.get(i).toString()));
		node.setObject((Serializable) bindMap);
		//System.out.println("here");
		return node;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeText() {
		return nodeText;
	}

	public void setNodeText(String nodeText) {
		this.nodeText = nodeText;
	}

	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	/**
	 * @return otherFields
	 */
	public String getOtherFields() {
		return otherFields;
	}

	/**
	 * @param otherFields 要设置的 otherFields
	 */
	public void setOtherFields(String otherFields) {
		this.otherFields = otherFields;
	}

}
