package com.nuaa.bean;

public class Atc {
	private Integer toolid;

    private String tooldaihao;

    private String xingshi;

    private String qudong;

    private String daobing;

    private Integer capacity;

    private String maxd;

    private String maxdNull;

    private Integer length;

    private String tWeight;

    private double tooltime;

    private String zuanhole;

    private String gongsi;

    private String xixue;

    private String course;
    
    private String showSimi;
    
    

    public void setTooltime(double tooltime) {
		this.tooltime = tooltime;
	}

	public String getShowSimi() {
		return showSimi;
	}

	public void setShowSimi(String showSimi) {
		this.showSimi = showSimi;
	}

	public Integer getToolid() {
        return toolid;
    }

    public void setToolid(Integer toolid) {
        this.toolid = toolid;
    }

    public String getTooldaihao() {
        return tooldaihao;
    }

    public void setTooldaihao(String tooldaihao) {
        this.tooldaihao = tooldaihao == null ? null : tooldaihao.trim();
    }

    public String getXingshi() {
        return xingshi;
    }

    public void setXingshi(String xingshi) {
        this.xingshi = xingshi == null ? null : xingshi.trim();
    }

    public String getQudong() {
        return qudong;
    }

    public void setQudong(String qudong) {
        this.qudong = qudong == null ? null : qudong.trim();
    }

    public String getDaobing() {
        return daobing;
    }

    public void setDaobing(String daobing) {
        this.daobing = daobing == null ? null : daobing.trim();
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMaxd() {
        return maxd;
    }

    public void setMaxd(String maxd) {
        this.maxd = maxd == null ? null : maxd.trim();
    }

    public String getMaxdNull() {
        return maxdNull;
    }

    public void setMaxdNull(String maxdNull) {
        this.maxdNull = maxdNull == null ? null : maxdNull.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length ;
    }

    public String gettWeight() {
        return tWeight;
    }

    public void settWeight(String tWeight) {
        this.tWeight = tWeight == null ? null : tWeight.trim();
    }

    public Double getTooltime() {
        return tooltime;
    }

    public void setTooltime(Double tooltime) {
        this.tooltime = tooltime;
    }

    public String getZuanhole() {
        return zuanhole;
    }

    public void setZuanhole(String zuanhole) {
        this.zuanhole = zuanhole == null ? null : zuanhole.trim();
    }

    public String getGongsi() {
        return gongsi;
    }

    public void setGongsi(String gongsi) {
        this.gongsi = gongsi == null ? null : gongsi.trim();
    }

    public String getXixue() {
        return xixue;
    }

    public void setXixue(String xixue) {
        this.xixue = xixue == null ? null : xixue.trim();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }
}