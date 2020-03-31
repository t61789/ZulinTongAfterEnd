package com.ZuLinTong.background.dao;

import com.ZuLinTong.background.bean.Bike;
import com.ZuLinTong.frame.SqlSessionDaoSupportAbstract;
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
