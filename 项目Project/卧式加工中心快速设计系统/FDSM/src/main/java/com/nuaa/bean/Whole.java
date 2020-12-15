package com.nuaa.bean;

public class Whole {
    private Integer mtId;

    private String mtType;

    private String mtWp;

    private String mtSpindle;

    private String mtAtc;

    private String mtScrew;

    private String mtSlideway;

    private String mtUpright;

    private String mtWorkbed;

    public Integer getMtId() {
        return mtId;
    }

    public void setMtId(Integer mtId) {
        this.mtId = mtId;
    }

    public String getMtType() {
        return mtType;
    }

    public void setMtType(String mtType) {
        this.mtType = mtType == null ? null : mtType.trim();
    }

    public String getMtWp() {
        return mtWp;
    }

    public void setMtWp(String mtWp) {
        this.mtWp = mtWp == null ? null : mtWp.trim();
    }

    public String getMtSpindle() {
        return mtSpindle;
    }

    public void setMtSpindle(String mtSpindle) {
        this.mtSpindle = mtSpindle == null ? null : mtSpindle.trim();
    }

    public String getMtAtc() {
        return mtAtc;
    }

    public void setMtAtc(String mtAtc) {
        this.mtAtc = mtAtc == null ? null : mtAtc.trim();
    }

    public String getMtScrew() {
        return mtScrew;
    }

    public void setMtScrew(String mtScrew) {
        this.mtScrew = mtScrew == null ? null : mtScrew.trim();
    }

    public String getMtSlideway() {
        return mtSlideway;
    }

    public void setMtSlideway(String mtSlideway) {
        this.mtSlideway = mtSlideway == null ? null : mtSlideway.trim();
    }

    public String getMtUpright() {
        return mtUpright;
    }

    public void setMtUpright(String mtUpright) {
        this.mtUpright = mtUpright == null ? null : mtUpright.trim();
    }

    public String getMtWorkbed() {
        return mtWorkbed;
    }

    public void setMtWorkbed(String mtWorkbed) {
        this.mtWorkbed = mtWorkbed == null ? null : mtWorkbed.trim();
    }
}