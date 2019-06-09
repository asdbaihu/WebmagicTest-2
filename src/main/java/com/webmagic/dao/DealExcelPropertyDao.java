package com.webmagic.dao;

import com.webmagic.model.DealExcelProperty;
import org.apache.ibatis.annotations.Param;
public interface DealExcelPropertyDao {
    //新增
    void insertFundJYXW(DealExcelProperty dealExcelProperty);
    //查询是否有券商名称

    String qsNameIsNull(@Param("QSname") String QSname,@Param("JJZDM")String JJZDM,@Param("JZRQ")String JZRQ);
    //通过券商名称修改
    void updateDealExcelPropertyByQSname(DealExcelProperty dealExcelProperty);
}
