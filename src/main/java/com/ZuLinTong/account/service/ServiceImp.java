package com.ZuLinTong.account.service;

import com.ZuLinTong.account.bean.UserInfo;
import com.ZuLinTong.account.dao.IDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServiceImp implements IService {

    @Resource
    private IDao daoImp;

    public String submit(UserInfo info) {
        return daoImp.submit(info);
    }

    public String login(UserInfo info) {
        return null;
    }
}
