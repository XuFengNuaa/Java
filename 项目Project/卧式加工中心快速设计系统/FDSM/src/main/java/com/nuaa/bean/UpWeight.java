package com.nuaa.bean;

public class UpWeight {
    private Integer wid;

    private Double height;

    private Double kuang;

    private Double yroute;

    private Double sdwidth;

    private Double sdheight;

    private Double sidethick;

    private Double hole;

    private Double ribthick;

    private Double hnumber;

    private Double snumber;

    
    public UpWeight() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	public UpWeight(Integer wid, Double height, Double kuang, Double yroute, Double sdwidth, Double sdheight,
			Double sidethick, Double hole, Double ribthick, Double hnumber, Double snumber) {
		this.wid = wid;
		this.height = height;
		this.kuang = kuang;
		this.yroute = yroute;
		this.sdwidth = sdwidth;
		this.sdheight = sdheight;
		this.sidethick = sidethick;
		this.hole = hole;
		this.ribthick = ribthick;
		this.hnumber = hnumber;
		this.snumber = snumber;
	}


	public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getKuang() {
        return kuang;
    }

    public void setKuang(Double kuang) {
        this.kuang = kuang;
    }

    public Double getYroute() {
        return yroute;
    }

    public void setYroute(Double yroute) {
        this.yroute = yroute;
    }

    public Double getSdwidth() {
        return sdwidth;
    }

    public void setSdwidth(Double sdwidth) {
        this.sdwidth = sdwidth;
    }

    public Double getSdheight() {
        return sdheight;
    }

    public void setSdheight(Double sdheight) {
        this.sdheight = sdheight;
    }

    public Double getSidethick() {
        return sidethick;
    }

    public void setSidethick(Double sidethick) {
        this.sidethick = sidethick;
    }

    public Double getHole() {
        return hole;
    }

    public void setHole(Double hole) {
        this.hole = hole;
    }

    public Double getRibthick() {
        return ribthick;
    }

    public void setRibthick(Double ribthick) {
        this.ribthick = ribthick;
    }

    public Double getHnumber() {
        return hnumber;
    }

    public void setHnumber(Double hnumber) {
        this.hnumber = hnumber;
    }

    public Double getSnumber() {
        return snumber;
    }

    public void setSnumber(Double snumber) {
        this.snumber = snumber;
    }
}