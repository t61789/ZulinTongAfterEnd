package com.zulintong.background.dao;

import com.zulintong.background.bean.Bike;
import com.zulintong.frame.SqlSessionDaoSupportAbstract;
import org.springframework.stereotype.Repository;

@Repository
public class BikeDao extends SqlSessionDaoSupportAbstract {

    public void AddBike(Bike bike){
        this.getSqlSession().insert("com.ZuLinTong.background.addBike",bike);
    }

    public void SetDetail(Bike bike){
        try{
            this.getSqlSession().update("com.ZuLinTong.background.setDetail",bike);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
