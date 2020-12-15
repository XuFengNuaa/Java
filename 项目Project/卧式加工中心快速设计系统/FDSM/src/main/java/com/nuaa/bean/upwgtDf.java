package com.nuaa.bean;

public class upwgtDf {
    private Integer dfid;

    private Integer height;

    private Integer kuang;

    private Integer yroute;

    private Integer sdwidth;

    private Integer sdheight;

    private Integer sidethick;

    private Integer hole;

    private Integer ribthick;

    private Integer hnumber;

    private Integer snumber;

   
    
    public upwgtDf() {
		super();
	}

	public upwgtDf(Integer dfid, Integer height, Integer kuang, Integer yroute, Integer sdwidth, Integer sdheight,
			Integer sidethick, Integer hole, Integer ribthick, Integer hnumber, Integer snumber) {
		this.dfid = dfid;
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

	public Integer getDfid() {
        return dfid;
    }

    public void setDfid(Integer dfid) {
        this.dfid = dfid;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getKuang() {
        return kuang;
    }

    public void setKuang(Integer kuang) {
        this.kuang = kuang;
    }

    public Integer getYroute() {
        return yroute;
    }

    public void setYroute(Integer yroute) {
        this.yroute = yroute;
    }

    public Integer getSdwidth() {
        return sdwidth;
    }

    public void setSdwidth(Integer sdwidth) {
        this.sdwidth = sdwidth;
    }

    public Integer getSdheight() {
        return sdheight;
    }

    public void setSdheight(Integer sdheight) {
        this.sdheight = sdheight;
    }

    public Integer getSidethick() {
        return sidethick;
    }

    public void setSidethick(Integer sidethick) {
        this.sidethick = sidethick;
    }

    public Integer getHole() {
        return hole;
    }

    public void setHole(Integer hole) {
        this.hole = hole;
    }

    public Integer getRibthick() {
        return ribthick;
    }

    public void setRibthick(Integer ribthick) {
        this.ribthick = ribthick;
    }

    public Integer getHnumber() {
        return hnumber;
    }

    public void setHnumber(Integer hnumber) {
        this.hnumber = hnumber;
    }

    public Integer getSnumber() {
        return snumber;
    }

    public void setSnumber(Integer snumber) {
        this.snumber = snumber;
    }
}