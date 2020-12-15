package com.nuaa.bean;

public class Slideway {
    
	private Integer daoguiId;

    private String xinghao;

    private String zhou;

    private String jdlevel;

    private Integer width;
    
    private Integer length;
    
    private String aL;
    
    private String aR;

    private String distance;

    private String hks;

    private String yuya;
    
    private Integer eddn;
    
    private Integer edjn;

    private Double dwjd;

    private Double agdwjd;
    
    private double similarity;

    private String course;
    
    private double xL;
    
    private double zL;
    
    private String showSimi;
    
    

    public void setxL(double xL) {
		this.xL = xL;
	}

	public void setzL(double zL) {
		this.zL = zL;
	}

	public String getShowSimi() {
		return showSimi;
	}

	public void setShowSimi(String showSimi) {
		this.showSimi = showSimi;
	}

	public Integer getDaoguiId() {
        return daoguiId;
    }

    public void setDaoguiId(Integer daoguiId) {
        this.daoguiId = daoguiId;
    }

    public Double getxL() {
		return xL;
	}

	public void setxL(Double xL) {
		this.xL = xL;
	}

	public Double getzL() {
		return zL;
	}

	public void setzL(Double zL) {
		this.zL = zL;
	}

	public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao == null ? null : xinghao.trim();
    }

    public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public String getZhou() {
        return zhou;
    }

    public void setZhou(String zhou) {
        this.zhou = zhou == null ? null : zhou.trim();
    }

    public String getJdlevel() {
        return jdlevel;
    }

    public void setJdlevel(String jdlevel) {
        this.jdlevel = jdlevel == null ? null : jdlevel.trim();
    }
    
    public String getaL() {
		return aL;
	}

	public void setaL(String aL) {
		this.aL = aL;
	}

	public String getaR() {
		return aR;
	}

	public void setaR(String aR) {
		this.aR = aR;
	}

	public Integer getEddn() {
		return eddn;
	}

	public void setEddn(Integer eddn) {
		this.eddn = eddn;
	}

	public Integer getEdjn() {
		return edjn;
	}

	public void setEdjn(Integer edjn) {
		this.edjn = edjn;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance == null ? null : distance.trim();
    }

    public String getHks() {
        return hks;
    }

    public void setHks(String hks) {
        this.hks = hks == null ? null : hks.trim();
    }

    public String getYuya() {
        return yuya;
    }

    public void setYuya(String yuya) {
        this.yuya = yuya == null ? null : yuya.trim();
    }

    public Double getDwjd() {
        return dwjd;
    }

    public void setDwjd(Double dwjd) {
        this.dwjd = dwjd;
    }

    public Double getAgdwjd() {
        return agdwjd;
    }

    public void setAgdwjd(Double agdwjd) {
        this.agdwjd = agdwjd;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course == null ? null : course.trim();
    }
}