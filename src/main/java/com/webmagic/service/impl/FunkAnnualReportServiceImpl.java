package com.webmagic.service.impl;

import com.webmagic.dao.DealExcelPropertyDao;
import com.webmagic.model.DealExcelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunkAnnualReportServiceImpl implements FunkAnnualReportService{
    @Autowired
    private DealExcelPropertyDao dealExcelPropertyDao;

    @Override
    public void insertFundJYXW(DealExcelProperty dealExcelProperty) {
        dealExcelPropertyDao.insertFundJYXW(dealExcelProperty);

    }

    @Override
    public String qsNameIsNull(String QSname,String JJZDM,String JZRQ) {

        return dealExcelPropertyDao.qsNameIsNull(QSname,JJZDM,JZRQ);
    }

    @Override
    public void updateDealExcelPropertyByQSname(DealExcelProperty dealExcelProperty) {
        dealExcelPropertyDao.updateDealExcelPropertyByQSname(dealExcelProperty);
    }

    @Override
    public List<DealExcelProperty> selectAllByJZRQ(DealExcelProperty dealExcelProperty, String JZRQ) {
        return dealExcelPropertyDao.selectAllByJZRQ(dealExcelProperty,JZRQ);
    }
}
