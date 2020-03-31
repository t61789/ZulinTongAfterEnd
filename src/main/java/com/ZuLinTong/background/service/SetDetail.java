package com.ZuLinTong.background.service;

import com.ZuLinTong.background.bean.Bike;
import com.ZuLinTong.background.dao.BikeDao;
import com.ZuLinTong.frame.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SetDetail  extends BaseService {

    @Resource
    private BikeDao bikeDao;

    @Override
    public Object exec(Object... arg) {
        return null;
    }

    @Override
    public Object exec(Object arg) {
        Bike bike = (Bike)arg;
        bikeDao.SetDetail(bike);

        return "0";
    }
}
