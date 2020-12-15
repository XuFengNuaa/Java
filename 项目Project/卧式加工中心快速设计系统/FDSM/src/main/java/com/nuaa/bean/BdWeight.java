package com.nuaa.bean;

public class BdWeight {
    private Integer bid;

    private Double bLength;

    private Double bWidth;

    private Double bHeight;

    private Double bCd;

    private Double bXl;

    private Double bXd;

    private Double bZl;

    private Double bZd;


    private Double bRibthick;

    private Double bHole;

    private Double bStructure;

    
    public BdWeight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BdWeight(Integer bid, Double bLength, Double bWidth, Double bHeight, Double bCd, Double bXl, Double bXd,
			Double bZl, Double bZd, Double bRibthick, Double bHole, Double bStructure) {
		this.bid = bid;
		this.bLength = bLength;
		this.bWidth = bWidth;
		this.bHeight = bHeight;
		this.bCd = bCd;
		this.bXl = bXl;
		this.bXd = bXd;
		this.bZl = bZl;
		this.bZd = bZd;
		this.bRibthick = bRibthick;
		this.bHole = bHole;
		this.bStructure = bStructure;
	}

	public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Double getbLength() {
        return bLength;
    }

    public void setbLength(Double bLength) {
        this.bLength = bLength;
    }

    public Double getbWidth() {
        return bWidth;
    }

    public void setbWidth(Double bWidth) {
        this.bWidth = bWidth;
    }

    public Double getbHeight() {
        return bHeight;
    }

    public void setbHeight(Double bHeight) {
        this.bHeight = bHeight;
    }

    public Double getbCd() {
        return bCd;
    }

    public void setbCd(Double bCd) {
        this.bCd = bCd;
    }

    public Double getbXl() {
        return bXl;
    }

    public void setbXl(Double bXl) {
        this.bXl = bXl;
    }

    public Double getbXd() {
        return bXd;
    }

    public void setbXd(Double bXd) {
        this.bXd = bXd;
    }

    public Double getbZl() {
        return bZl;
    }

    public void setbZl(Double bZl) {
        this.bZl = bZl;
    }

    public Double getbZd() {
        return bZd;
    }

    public void setbZd(Double bZd) {
        this.bZd = bZd;
    }


    public Double getbRibthick() {
        return bRibthick;
    }

    public void setbRibthick(Double bRibthick) {
        this.bRibthick = bRibthick;
    }

    public Double getbHole() {
        return bHole;
    }

    public void setbHole(Double bHole) {
        this.bHole = bHole;
    }

    public Double getbStructure() {
        return bStructure;
    }

    public void setbStructure(Double bStructure) {
        this.bStructure = bStructure;
    }
}