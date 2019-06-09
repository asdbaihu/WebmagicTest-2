package com.webmagic.dao;

import com.webmagic.model.DealExcelProperty;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DealExcelPropertyDao {
    //新增
    void insertFundJYXW(DealExcelProperty dealExcelProperty);
    //查询是否有券商名称

    String qsNameIsNull(@Param("QSname") String QSname,@Param("JJZDM")String JJZDM,@Param("JZRQ")String JZRQ);
    //通过券商名称修改
    void updateDealExcelPropertyByQSname(DealExcelProperty dealExcelProperty);

    //根据截止日期查询
    List<DealExcelProperty> selectAllByJZRQ(@Param("DealExcelProperty")DealExcelProperty dealExcelProperty,@Param("JZRQ")String JZRQ);
}
