package com.webmagic.dao;

import com.webmagic.model.DealExcelProperty;

public interface DealExcelPropertyDao {
    //新增
    void insertFundJYXW(DealExcelProperty dealExcelProperty);
    //查询是否有券商名称
    String qsNameIsNull(String QSname);
    //通过券商名称修改
    void updateDealExcelPropertyByQSname(DealExcelProperty dealExcelProperty);
}
