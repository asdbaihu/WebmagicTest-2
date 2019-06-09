package com.webmagic.service.impl;


import com.webmagic.model.DealExcelProperty;

public interface FunkAnnualReportService {
    void insertFundJYXW(DealExcelProperty dealExcelProperty);

    String qsNameIsNull(String QSname,String JJZDM,String JZRQ);

    void updateDealExcelPropertyByQSname(DealExcelProperty dealExcelProperty);
}
