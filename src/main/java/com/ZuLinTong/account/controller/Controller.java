package com.ZuLinTong.account.controller;

import com.ZuLinTong.account.service.IService;
import com.ZuLinTong.account.bean.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@org.springframework.stereotype.Controller
public class Controller {

    @Resource
    private IService serviceImp;

    @RequestMapping("/register")
    @ResponseBody
    public String submit(@RequestBody UserInfo userInfo) {

        String result="0";
        try{
            result = serviceImp.submit(userInfo);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    // TODO: 2020/3/27  登陆相关
}
