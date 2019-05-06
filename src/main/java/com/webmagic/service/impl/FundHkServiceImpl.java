package com.webmagic.service.impl;

import com.webmagic.dao.FundHkDao;
import com.webmagic.model.FundHk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundHkServiceImpl implements FundHkService{

    @Autowired
    private FundHkDao fundHkDao;

    @Override
    public void insertFundHk(FundHk fundHk){
        fundHkDao.insertFundHk(fundHk);
    }


}
