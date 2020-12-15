package com.nuaa.bean;

public class Workbed {
    
	private Integer bedid;

    private String beddaihao;

    private String structure;

    private String material;

    private Integer length;

    private Integer width;

    private Integer height;

    private String xroute;

    private String yroute;

    private String zroute;

    private Integer zDistance;

    private Integer xDistance;

    private String caoshen;

    private Integer caokuan;

    private String caolength;

    private Integer holed;

    private String rib;

    private String kuanheng;

    private String kuanshu;

    private String zhaiheng;

    private String zhaishu;

    private Integer ribthick;

    private String chip;

    private String course;
    
    private double similarity;
    
    private String showSimi;

    public Integer getBedid() {
        return bedid;
    }

    public void setBedid(Integer bedid) {
        this.bedid = bedid;
    }

    public String getBeddaihao() {
        return beddaihao;
    }

    public void setBeddaihao(String beddaihao) {
        this.beddaihao = beddaihao == null ? null : beddaihao.trim();
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure == null ? null : structure.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getXroute() {
        return xroute;
    }

    public void setXroute(String xroute) {
        this.xroute = xroute == null ? null : xroute.trim();
    }

    public String getYroute() {
        return yroute;
    }

    public void setYroute(String yroute) {
        this.yroute = yroute == null ? null : yroute.trim();
    }

    public String getZroute() {
        return zroute;
    }

    public void setZroute(String zroute) {
        this.zroute = zroute == null ? null : zroute.trim();
    }

    public Integer getzDistance() {
        return zDistance;
    }

    public void setzDistance(Integer zDistance) {
        this.zDistance = zDistance;
    }

    public Integer getxDistance() {
        return xDistance;
    }

    public void setxDistance(Integer xDistance) {
        this.xDistance = xDistance;
    }

    public String getCaoshen() {
        return caoshen;
    }

    public void setCaoshen(String caoshen) {
        this.caoshen = caoshen == null ? null : caoshen.trim();
    }

    public Integer getCaokuan() {
        return caokuan;
    }

    public void setCaokuan(Integer caokuan) {
        this.caokuan = caokuan;
    }

    public String getCaolength() {
        return caolength;
    }

    public void setCaolength(String caolength) {
        this.caolength = caolength == null ? null : caolength.trim();
    }

    public Integer getHoled() {
        return holed;
    }

    public void setHoled(Integer holed) {
        this.holed = holed;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib == null ? null : rib.trim();
    }

    public String getKuanheng() {
        return kuanheng;
    }

    public void setKuanheng(String kuanheng) {
        this.kuanheng = kuanheng == null ? null : kuanheng.trim();
    }

    public String getKuanshu() {
        return kuanshu;
    }

    public void setKuanshu(String kuanshu) {
        this.kuanshu = kuanshu == null ? null : kuanshu.trim();
    }

    public String getZhaiheng() {
        return zhaiheng;
    }

    public void setZhaiheng(String zhaiheng) {
        this.zhaiheng = zhaiheng == null ? null : zhaiheng.trim();
    }

    public String getZhaishu() {
        return zhaishu;
    }

    public void setZhaishu(String zhaishu) {
        this.zhaishu = zhaishu == null ? null : zhaishu.trim();
    }

    public Integer getRibthick() {
        return ribthick;
    }

    public void setRibthick(Integer ribthick) {
        this.ribthick = ribthick;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip == null ? null : chip.trim();
    }

    
    public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public String getShowSimi() {
		return showSimi;
	}

	public void setShowSimi(String showSimi) {
		this.showSimi = showSimi;
	}

	public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }
}