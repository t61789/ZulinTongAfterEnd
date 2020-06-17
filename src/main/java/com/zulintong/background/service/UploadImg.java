package com.zulintong.background.service;

import com.zulintong.background.bean.Bike;
import com.zulintong.background.dao.BikeDao;
import com.zulintong.frame.BaseService;
import com.zulintong.frame.CommonProperties;
import com.zulintong.frame.SecurityKeys;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UploadImg extends BaseService {

    @Resource
    private SecurityKeys securityKeys;

    @Resource
    private CommonProperties commonProperties;

    @Resource
    private BikeDao bikeDao;

    @Override
    public Object exec(Object... arg) {
        return null;
    }

    @Override
    public Object exec(Object arg) {
        MultipartFile file = (MultipartFile)arg;
        if(!file.isEmpty()){
            Bike bike = newBike(file);
            try {
                file.transferTo(new File(bike.getImgUrl()));
                bikeDao.AddBike(bike);

                Bike returnResult = new Bike();
                returnResult.setAlias(bike.getAlias());
                returnResult.setBikeId(bike.getBikeId());
                returnResult.setTypeList(new ObjectMapper().readValue(commonProperties.getTypeList(),String[].class));

                return returnResult;
            } catch (IOException e) {
                e.printStackTrace();
                return "0";
            }
        }
        return "-1";
    }

    private Bike newBike(MultipartFile file){
        Bike bike = new Bike();
        String originFileName = file.getOriginalFilename();
        String expanded = originFileName.substring(originFileName.lastIndexOf("."));

        bike.setBikeId(UUID.randomUUID().toString().toLowerCase().replaceAll("-",""));
        bike.setAlias(originFileName.substring(0,originFileName.lastIndexOf(".")));
        bike.setAddedTime(LocalDateTime.now());
        bike.setType("型号暂无");
        bike.setImgUrl(commonProperties.getImgStoragePath()+File.separator+bike.getBikeId()+expanded);

        return bike;
    }
}
