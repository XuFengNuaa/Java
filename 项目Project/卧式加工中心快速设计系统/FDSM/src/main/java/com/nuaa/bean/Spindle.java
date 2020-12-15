package com.nuaa.bean;

public class Spindle {
	private Integer sindleId;

    private String sdType;

    private String kinds;

    private double powerMin;

    private double powerMax;

    private String power;

    private double maxSpeed;

    private String torsion;
    
    private String zhongxin; //分离使min,max

	private String zhuikong;

    private String xLength;

    private String yLength;

    private String duanmianZhuantai;

    private String zhongxinMin;

    private String zhongxinMax;

    private String course;

    public Integer getSindleId() {
        return sindleId;
    }

    public void setSindleId(Integer sindleId) {
        this.sindleId = sindleId;
    }
    
    public String getZhongxin() {
		return zhongxin;
	}

	public void setZhongxin(String zhongxin) {
		this.zhongxin = zhongxin;
	}
    public String getSdType() {
        return sdType;
    }

    public void setSdType(String sdType) {
        this.sdType = sdType == null ? null : sdType.trim();
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds == null ? null : kinds.trim();
    }

    public double getPowerMin() {
        return powerMin;
    }

    public void setPowerMin(double powerMin) {
        this.powerMin = powerMin;
    }

    public double getPowerMax() {
        return powerMax;
    }

    public void setPowerMax(double powerMax) {
        this.powerMax = powerMax;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed ;
    }

    public String getTorsion() {
        return torsion;
    }

    public void setTorsion(String torsion) {
        this.torsion = torsion == null ? null : torsion.trim();
    }

    public String getZhuikong() {
        return zhuikong;
    }

    public void setZhuikong(String zhuikong) {
        this.zhuikong = zhuikong == null ? null : zhuikong.trim();
    }

    public String getxLength() {
        return xLength;
    }

    public void setxLength(String xLength) {
        this.xLength = xLength == null ? null : xLength.trim();
    }

    public String getyLength() {
        return yLength;
    }

    public void setyLength(String yLength) {
        this.yLength = yLength == null ? null : yLength.trim();
    }

    public String getDuanmianZhuantai() {
        return duanmianZhuantai;
    }

    public void setDuanmianZhuantai(String duanmianZhuantai) {
        this.duanmianZhuantai = duanmianZhuantai == null ? null : duanmianZhuantai.trim();
    }

    public String getZhongxinMin() {
        return zhongxinMin;
    }

    public void setZhongxinMin(String zhongxinMin) {
        this.zhongxinMin = zhongxinMin == null ? null : zhongxinMin.trim();
    }

    public String getZhongxinMax() {
        return zhongxinMax;
    }

    public void setZhongxinMax(String zhongxinMax) {
        this.zhongxinMax = zhongxinMax == null ? null : zhongxinMax.trim();
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }
}