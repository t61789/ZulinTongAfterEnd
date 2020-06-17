package com.zulintong.user.controller;

import com.zulintong.user.bean.UserInfo;
import com.zulintong.user.service.IAccountService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@org.springframework.stereotype.Controller
public class AccountController {

    @Resource
    private IAccountService accountService;

    // TODO: 2020/3/27 也许可以做个缓存
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = accountService.register(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = accountService.login(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/bindingEmail/send")//{用户名_username:$String,邮箱地址_email:$String}
    @ResponseBody
    public String bindingEmailSend(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = accountService.bindingSend(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/bindingEmail/verify")//{用户名_username:$String,验证码_verifycode:$String,邮箱地址_email:$String}
    @ResponseBody
    public String bindingEmailVerify(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = accountService.bindingVerify(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/retrievePassword/send")//{用户名_username:$String,邮箱地址_email:$String})
    @ResponseBody
    public String retrieveEmailSend(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = accountService.retrieveSend(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/retrievePassword/verify")//{用户名_username:$String,验证码_verifycode:$String,新密码_password:$String})
    @ResponseBody
    public String retrieveEmailVerify(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = accountService.retrieveVerify(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestBody String input) {

        return input;
    }
}
