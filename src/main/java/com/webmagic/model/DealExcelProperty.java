package com.webmagic.model;


import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import java.util.Date;


public class DealExcelProperty extends BaseRowModel {

    //JJMC,JJJC,JJZDM
    private String JJMC;

    private String JJJC;

    private String JJZDM;

    //private Date XGSJ;

    @ExcelProperty(value = {"序号","序号"},index = 0)
    private Integer NUM;

    @ExcelProperty(value = {"券商名称","券商名称"},index = 0)
    private String QSname;
    @ExcelProperty(value = {"交易单元数量","交易单元数量"},index = 1)
    private Integer QSnum;
    @ExcelProperty(value = {"股票交易","股票成交金额"},index = 2)
    private Double GPJE;
    @ExcelProperty(value = {"股票交易","股票占比"},index = 3)
    private Double GPJEZB;
    @ExcelProperty(value = {"应支付该券商的佣金","佣金成交金额"},index = 4)
    private Double QSJE;
    @ExcelProperty(value = {"应支付该券商的佣金","佣金占比"},index = 5)
    private Double QSJEZB;
    @ExcelProperty(value = {"债券交易","债券成交金额"},index = 6)
    private Double ZQJE;
    @ExcelProperty(value = {"债券交易","债券占比"},index = 7)
    private Double ZQJEZB;
    @ExcelProperty(value = {"回购交易","回购成交金额"},index = 8)
    private Double HGJE;
    @ExcelProperty(value = {"回购交易","回购占比"},index = 9)
    private Double HGJEZB;
    @ExcelProperty(value = {"权证交易","权证成交金额"},index = 10)
    private Double QZJE;
    @ExcelProperty(value = {"权证交易","权证占比"},index = 11)
    private Double QZJEZB;
    @ExcelProperty(value = {"基金交易","基金成交金额"},index = 12)
    private Double JJJE;
    @ExcelProperty(value = {"基金交易","基金占比"},index = 13)
    private Double JJJEZB;
    @ExcelProperty(value = {"期货交易","期货成交金额"},index = 14)
    private Double QHJE;
    @ExcelProperty(value = {"期货交易","期货占比"},index = 15)
    private Double QHJEZB;

    public String getQSname() {
        return QSname;
    }

    public void setQSname(String QSname) {
        this.QSname = QSname;
    }

    public Integer getQSnum() {
        return QSnum;
    }

    public void setQSnum(Integer QSnum) {
        this.QSnum = QSnum;
    }

    public Double getGPJE() {
        return GPJE;
    }

    public void setGPJE(Double GPJE) {
        this.GPJE = GPJE;
    }

    public Double getGPJEZB() {
        return GPJEZB;
    }

    public void setGPJEZB(Double GPJEZB) {
        this.GPJEZB = GPJEZB;
    }

    public Double getQSJE() {
        return QSJE;
    }

    public void setQSJE(Double QSJE) {
        this.QSJE = QSJE;
    }

    public Double getQSJEZB() {
        return QSJEZB;
    }

    public void setQSJEZB(Double QSJEZB) {
        this.QSJEZB = QSJEZB;
    }

    public Double getZQJE() {
        return ZQJE;
    }

    public void setZQJE(Double ZQJE) {
        this.ZQJE = ZQJE;
    }

    public Double getZQJEZB() {
        return ZQJEZB;
    }

    public void setZQJEZB(Double ZQJEZB) {
        this.ZQJEZB = ZQJEZB;
    }

    public Double getHGJE() {
        return HGJE;
    }

    public void setHGJE(Double HGJE) {
        this.HGJE = HGJE;
    }

    public Double getHGJEZB() {
        return HGJEZB;
    }

    public void setHGJEZB(Double HGJEZB) {
        this.HGJEZB = HGJEZB;
    }

    public Double getQZJE() {
        return QZJE;
    }

    public void setQZJE(Double QZJE) {
        this.QZJE = QZJE;
    }

    public Double getQZJEZB() {
        return QZJEZB;
    }

    public void setQZJEZB(Double QZJEZB) {
        this.QZJEZB = QZJEZB;
    }

    public Double getJJJE() {
        return JJJE;
    }

    public void setJJJE(Double JJJE) {
        this.JJJE = JJJE;
    }

    public Double getJJJEZB() {
        return JJJEZB;
    }

    public void setJJJEZB(Double JJJEZB) {
        this.JJJEZB = JJJEZB;
    }

    public Double getQHJE() {
        return QHJE;
    }

    public void setQHJE(Double QHJE) {
        this.QHJE = QHJE;
    }

    public Double getQHJEZB() {
        return QHJEZB;
    }

    public void setQHJEZB(Double QHJEZB) {
        this.QHJEZB = QHJEZB;
    }

    public String getJJMC() {
        return JJMC;
    }

    public void setJJMC(String JJMC) {
        this.JJMC = JJMC;
    }

    public String getJJJC() {
        return JJJC;
    }

    public void setJJJC(String JJJC) {
        this.JJJC = JJJC;
    }

    public String getJJZDM() {
        return JJZDM;
    }

    public void setJJZDM(String JJZDM) {
        this.JJZDM = JJZDM;
    }



    public Integer getNUM() {
        return NUM;
    }

    public void setNUM(Integer NUM) {
        this.NUM = NUM;
    }
}
