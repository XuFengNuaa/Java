package com.nuaa.bean;

public class Workpiece {
    private Integer workid;

    private String type;

    private String zhuantaiMaxd;

    private String zhuantaiH;

    private Integer gongjianMaxd;

    private Integer gongjianH;

    private String overHeight;

    private String fendu;

    private String reRatio;

    private Integer zaihe;

    private String maxSpeed;

    private String cuttingF;
    
    private double similarity;

    private String course;
    
    private String showSimi;

    public Workpiece() {
		super();
	}

	public Workpiece(Integer workid, String type, String zhuantaiMaxd, String zhuantaiH, Integer gongjianMaxd,
			Integer gongjianH, String overHeight, String fendu, String reRatio, Integer zaihe, String maxSpeed,
			String cuttingF, String course) {
		this.workid = workid;
		this.type = type;
		this.zhuantaiMaxd = zhuantaiMaxd;
		this.zhuantaiH = zhuantaiH;
		this.gongjianMaxd = gongjianMaxd;
		this.gongjianH = gongjianH;
		this.overHeight = overHeight;
		this.fendu = fendu;
		this.reRatio = reRatio;
		this.zaihe = zaihe;
		this.maxSpeed = maxSpeed;
		this.cuttingF = cuttingF;
		this.course = course;
	}

	public String getShowSimi() {
		return showSimi;
	}

	public void setShowSimi(String showSimi) {
		this.showSimi = showSimi;
	}

	public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    
    
    public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getZhuantaiMaxd() {
        return zhuantaiMaxd;
    }

    public void setZhuantaiMaxd(String zhuantaiMaxd) {
        this.zhuantaiMaxd = zhuantaiMaxd == null ? null : zhuantaiMaxd.trim();
    }

    public String getZhuantaiH() {
        return zhuantaiH;
    }

    public void setZhuantaiH(String zhuantaiH) {
        this.zhuantaiH = zhuantaiH == null ? null : zhuantaiH.trim();
    }

    public Integer getGongjianMaxd() {
        return gongjianMaxd;
    }

    public void setGongjianMaxd(Integer gongjianMaxd) {
        this.gongjianMaxd = gongjianMaxd;
    }

    public Integer getGongjianH() {
        return gongjianH;
    }

    public void setGongjianH(Integer gongjianH) {
        this.gongjianH = gongjianH;
    }

    public String getOverHeight() {
        return overHeight;
    }

    public void setOverHeight(String overHeight) {
        this.overHeight = overHeight == null ? null : overHeight.trim();
    }

    public String getFendu() {
        return fendu;
    }

    public void setFendu(String fendu) {
        this.fendu = fendu == null ? null : fendu.trim();
    }

    public String getReRatio() {
        return reRatio;
    }

    public void setReRatio(String reRatio) {
        this.reRatio = reRatio == null ? null : reRatio.trim();
    }

    public Integer getZaihe() {
        return zaihe;
    }

    public void setZaihe(Integer zaihe) {
        this.zaihe = zaihe;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed == null ? null : maxSpeed.trim();
    }

    public String getCuttingF() {
        return cuttingF;
    }

    public void setCuttingF(String cuttingF) {
        this.cuttingF = cuttingF == null ? null : cuttingF.trim();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }
}