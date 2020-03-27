package com.ZuLinTong.account.service;

import com.ZuLinTong.account.bean.UserInfo;
import com.ZuLinTong.account.dao.IDao;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Service
public class ServiceImp implements IService {

    @Resource
    private IDao daoImp;

    public String register(UserInfo info) {

        info.setPassword(DigestUtils.md5DigestAsHex(info.getPassword().getBytes()));

        try{
            daoImp.register(info);
        }catch(DuplicateKeyException e){
            return "0";
        }
        return "1";
    }

    public String login(UserInfo info) {
        info.setPassword(DigestUtils.md5DigestAsHex(info.getPassword().getBytes()));

        try{
            if(daoImp.login(info)==null){
                return "0";
            }else
                return "1";
        }catch(DuplicateKeyException e){
            return "0";
        }
    }
}
