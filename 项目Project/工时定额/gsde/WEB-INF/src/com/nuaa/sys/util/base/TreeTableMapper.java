/**
 * 
 */
package com.nuaa.sys.util.base;

/**
 * @author mahao
 *
 */
public class TreeTableMapper {
	private String idColumnName = "ID";

	private String parentIdColumnName = "PARENTID";

	private String nameColumnName = "NAME";
	
	private String typeColumnName = "TYPE";

	public String getIdColumnName() {
		return idColumnName;
	}

	public void setIdColumnName(String idColumnName) {
		this.idColumnName = idColumnName;
	}

	public String getNameColumnName() {
		return nameColumnName;
	}

	public void setNameColumnName(String nameColumnName) {
		this.nameColumnName = nameColumnName;
	}

	public String getParentIdColumnName() {
		return parentIdColumnName;
	}

	public void setParentIdColumnName(String parentIdColumnName) {
		this.parentIdColumnName = parentIdColumnName;
	}

	public String getTypeColumnName() {
		return typeColumnName;
	}

	public void setTypeColumnName(String typeColumnName) {
		this.typeColumnName = typeColumnName;
	}
}
