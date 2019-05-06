package com.webmagic.model;

import java.io.Serializable;

public class FundHk implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    //基金交易代码
    private String JJJYDM;
    //公告日期
    private String GGRQ;
    //截止日期
    private String JZRQ;
    //单位净值
    private String DWJZ;
    //单位净值币种
    private String DWJZBZ;
    //每新增单位资产净值
    private String MXZDWZCJZ;
    //每新增单位资产净值币种
    private String MXZDWZCJZBZ;
    //每新增单位实际现金值
    private String MXZDWJZSJXJ;
    //每新增单位实际现金值币种
    private String MXZDWJZSJXJBZ;
    //发行份额总数
    private String FXFEZS;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJJJYDM() {
        return JJJYDM;
    }

    public void setJJJYDM(String JJJYDM) {
        this.JJJYDM = JJJYDM;
    }

    public String getGGRQ() {
        return GGRQ;
    }

    public void setGGRQ(String GGRQ) {
        this.GGRQ = GGRQ;
    }

    public String getJZRQ() {
        return JZRQ;
    }

    public void setJZRQ(String JZRQ) {
        this.JZRQ = JZRQ;
    }

    public String getDWJZ() {
        return DWJZ;
    }

    public void setDWJZ(String DWJZ) {
        this.DWJZ = DWJZ;
    }

    public String getDWJZBZ() {
        return DWJZBZ;
    }

    public void setDWJZBZ(String DWJZBZ) {
        this.DWJZBZ = DWJZBZ;
    }

    public String getMXZDWZCJZ() {
        return MXZDWZCJZ;
    }

    public void setMXZDWZCJZ(String MXZDWZCJZ) {
        this.MXZDWZCJZ = MXZDWZCJZ;
    }

    public String getMXZDWZCJZBZ() {
        return MXZDWZCJZBZ;
    }

    public void setMXZDWZCJZBZ(String MXZDWZCJZBZ) {
        this.MXZDWZCJZBZ = MXZDWZCJZBZ;
    }

    public String getMXZDWJZSJXJ() {
        return MXZDWJZSJXJ;
    }

    public void setMXZDWJZSJXJ(String MXZDWJZSJXJ) {
        this.MXZDWJZSJXJ = MXZDWJZSJXJ;
    }

    public String getMXZDWJZSJXJBZ() {
        return MXZDWJZSJXJBZ;
    }

    public void setMXZDWJZSJXJBZ(String MXZDWJZSJXJBZ) {
        this.MXZDWJZSJXJBZ = MXZDWJZSJXJBZ;
    }

    public String getFXFEZS() {
        return FXFEZS;
    }

    public void setFXFEZS(String FXFEZS) {
        this.FXFEZS = FXFEZS;
    }
}
