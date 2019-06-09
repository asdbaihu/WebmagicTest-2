package com.webmagic.service.impl;


import com.webmagic.model.DealExcelProperty;

import java.util.List;

public interface FunkAnnualReportService {
    void insertFundJYXW(DealExcelProperty dealExcelProperty);

    String qsNameIsNull(String QSname,String JJZDM,String JZRQ);

    void updateDealExcelPropertyByQSname(DealExcelProperty dealExcelProperty);

    List<DealExcelProperty> selectAllByJZRQ(DealExcelProperty dealExcelProperty, String JZRQ);
}
