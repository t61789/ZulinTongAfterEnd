package com.ZuLinTong.account.controller;

import com.ZuLinTong.account.bean.UserInfo;
import com.ZuLinTong.account.dao.DaoTest;
import com.ZuLinTong.account.service.IEmailService;
import com.ZuLinTong.account.service.IService;
import com.ZuLinTong.frame.MailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@org.springframework.stereotype.Controller
public class Controller {

    @Resource
    private IService serviceImp;

    @Resource
    private IEmailService emailServiceImp;

    @Resource
    private DaoTest daoTest;

    // TODO: 2020/3/27 也许可以做个缓存
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = serviceImp.register(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = serviceImp.login(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping("/bindingEmail/send")//{用户名_username:$String,邮箱地址_email:$String}
    @ResponseBody
    public String bindingEmailSend(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = emailServiceImp.bindingSend(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping("/bindingEmail/verify")//{用户名_username:$String,验证码_verifycode:$String,邮箱地址_email:$String}
    @ResponseBody
    public String bindingEmailVerify(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = emailServiceImp.bindingVerify(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping("/retrievePassword/send")//{用户名_username:$String,邮箱地址_email:$String})
    @ResponseBody
    public String retrieveEmailSend(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = emailServiceImp.retrieveSend(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping("/retrievePassword/verify")//{用户名_username:$String,验证码_verifycode:$String,新密码_password:$String})
    @ResponseBody
    public String retrieveEmailVerify(@RequestBody UserInfo userInfo) {

        String result = "-1";
        try {
            result = emailServiceImp.retrieveVerify(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {

        //mailSender.SendMail("t61789@163.com", "test", "<html>123456</html>");

        return "0";
    }
}
