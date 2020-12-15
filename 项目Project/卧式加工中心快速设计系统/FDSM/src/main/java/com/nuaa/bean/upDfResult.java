package com.nuaa.bean;


public class upDfResult {
    private Integer zjId;

    private String name;

    private String dfDate;

      
    public upDfResult() {
		super();
	}

	public upDfResult(Integer zjId, String name, String dfDate) {
		this.zjId = zjId;
		this.name = name;
		this.dfDate = dfDate;
	}

	public Integer getZjId() {
        return zjId;
    }

    public void setZjId(Integer zjId) {
        this.zjId = zjId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDfDate() {
        return dfDate;
    }

    public void setDfDate(String dfDate) {
        this.dfDate = dfDate;
    }
}