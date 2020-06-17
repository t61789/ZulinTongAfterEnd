package com.zulintong.background.service;

import com.zulintong.background.bean.Bike;
import com.zulintong.background.dao.BikeDao;
import com.zulintong.frame.BaseService;
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
