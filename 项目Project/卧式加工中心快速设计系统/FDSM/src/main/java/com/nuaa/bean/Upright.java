package com.nuaa.bean;

public class Upright {
    
	private Integer uprightId;

    private String upDaihao;

    private String structure;

    private String material;

    private Integer sidingthick;

    private double shangdi;

    private String xiadi;

    private Integer kuangdu;

    private Integer yRoute;

	private Integer height;

    private String diheight;

    private String railDistance;

    private String reducehole;

    private Integer cehole;

    private String dinghole;

    private String rib;

    private String hengxiang;

    private String shuxiang;

    private Integer ribthinck;

    private String course;
    
    private double similarity;
    
    private String showSimi;
    
    
    public String getShowSimi() {
		return showSimi;
	}

	public void setShowSimi(String showSimi) {
		this.showSimi = showSimi;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public Integer getUprightId() {
        return uprightId;
    }

    public void setUprightId(Integer uprightId) {
        this.uprightId = uprightId;
    }

    public String getUpDaihao() {
        return upDaihao;
    }

    public void setUpDaihao(String upDaihao) {
        this.upDaihao = upDaihao == null ? null : upDaihao.trim();
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

    public Integer getSidingthick() {
        return sidingthick;
    }

    public void setSidingthick(Integer sidingthick) {
        this.sidingthick = sidingthick;
    }

    public double getShangdi() {
        return shangdi;
    }

    public void setShangdi(double shangdi) {
        this.shangdi = shangdi ;
    }

    public String getXiadi() {
        return xiadi;
    }

    public void setXiadi(String xiadi) {
        this.xiadi = xiadi;
    }

    public Integer getKuangdu() {
        return kuangdu;
    }

    public void setKuangdu(Integer kuangdu) {
        this.kuangdu = kuangdu;
    }

    public Integer getyRoute() {
		return yRoute;
	}

	public void setyRoute(Integer yRoute) {
		this.yRoute = yRoute;
	}
	
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getDiheight() {
        return diheight;
    }

    public void setDiheight(String diheight) {
        this.diheight = diheight == null ? null : diheight.trim();
    }

    public String getRailDistance() {
        return railDistance;
    }

    public void setRailDistance(String railDistance) {
        this.railDistance = railDistance == null ? null : railDistance.trim();
    }

    public String getReducehole() {
        return reducehole;
    }

    public void setReducehole(String reducehole) {
        this.reducehole = reducehole == null ? null : reducehole.trim();
    }

    public Integer getCehole() {
        return cehole;
    }

    public void setCehole(Integer cehole) {
        this.cehole = cehole ;
    }

    public String getDinghole() {
        return dinghole;
    }

    public void setDinghole(String dinghole) {
        this.dinghole = dinghole == null ? null : dinghole.trim();
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib == null ? null : rib.trim();
    }

    public String getHengxiang() {
        return hengxiang;
    }

    public void setHengxiang(String hengxiang) {
        this.hengxiang = hengxiang == null ? null : hengxiang.trim();
    }

    public String getShuxiang() {
        return shuxiang;
    }

    public void setShuxiang(String shuxiang) {
        this.shuxiang = shuxiang == null ? null : shuxiang.trim();
    }

    public Integer getRibthinck() {
        return ribthinck;
    }

    public void setRibthinck(Integer ribthinck) {
        this.ribthinck = ribthinck;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }
}