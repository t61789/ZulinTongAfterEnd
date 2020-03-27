package com.ZuLinTong.account.controller;

import com.ZuLinTong.account.dao.DaoTest;
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

    @Resource
    private DaoTest daoTest;

    // TODO: 2020/3/27 也许可以做个缓存
    @RequestMapping("/register")
    @ResponseBody
    public String submit(@RequestBody UserInfo userInfo) {

        String result="0";
        try{
            result = serviceImp.register(userInfo);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserInfo userInfo) {

        String result="0";
        try{
            result = serviceImp.login(userInfo);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestBody UserInfo userInfo) {

        daoTest.test();

        return "0";
    }
}
