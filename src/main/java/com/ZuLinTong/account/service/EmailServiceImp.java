package com.ZuLinTong.account.service;

import com.ZuLinTong.account.bean.UserInfo;
import com.ZuLinTong.account.dao.IEmailDao;
import com.ZuLinTong.frame.MailSender;
import com.ZuLinTong.frame.SecurityKeys;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EmailServiceImp implements IEmailService {

    @Resource
    private IEmailDao emailDaoImp;

    @Resource
    private MailSender mailSender;

    @Resource
    private SecurityKeys securityKeys;

    @Override
    public String bindingSend(UserInfo userInfo) throws MessagingException {
        // TODO: 2020/3/28 合并查询，retrieve
        if (emailDaoImp.getEmail(userInfo) != null) return "1";
        if (emailDaoImp.getUsernameByEmail(userInfo) != null) return "0";

        String verifycode =securityKeys.getSecurityMD5(userInfo.getEmail()).substring(0,5);

        mailSender.SendMail(userInfo.getEmail(), "租赁通邮箱验证码", verifycode);
        userInfo.setVerifycode(verifycode);

        LocalDateTime now = LocalDateTime.now();
        userInfo.setTime(now);
        emailDaoImp.sendVerifycode(userInfo);

        return "2";
    }

    @Override
    public String bindingVerify(UserInfo userInfo) {
        UserInfo tempUserInfo = emailDaoImp.getVerifyCodeAndTime(userInfo);
        String temp=TimeAndExistDetect(userInfo,tempUserInfo);
        if(temp!="-1")return temp;


        if(!userInfo.getVerifycode().toLowerCase().equals(tempUserInfo.getVerifycode()))
            return "1";
        if(!securityKeys.getSecurityMD5(userInfo.getEmail()).substring(0,5).equals(userInfo.getVerifycode()))
            return "3";
        try{
            emailDaoImp.updateEmail(userInfo);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        return "2";
    }

    @Override
    public String retrieveSend(UserInfo userInfo) throws MessagingException {
        if (!userInfo.getEmail().equals(emailDaoImp.getEmail(userInfo))) return "0";

        LocalDateTime now = LocalDateTime.now();
        String verifycode =securityKeys.getSecurityMD5(now.toString()).substring(0,5);

        mailSender.SendMail(userInfo.getEmail(), "租赁通邮箱验证码", verifycode);
        userInfo.setVerifycode(verifycode);

        userInfo.setTime(now);
        emailDaoImp.sendVerifycode(userInfo);

        return "1";
    }

    @Override
    public String retrieveVerify(UserInfo userInfo) {
        UserInfo tempUserInfo = emailDaoImp.getVerifyCodeAndTime(userInfo);
        String temp=TimeAndExistDetect(userInfo,tempUserInfo);
        if(temp!="-1")return temp;

        if(!userInfo.getVerifycode().toLowerCase().equals(tempUserInfo.getVerifycode()))
            return "1";
        try{
            emailDaoImp.setVerifyCodeAndTimeNull(userInfo);
            userInfo.setPassword(DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes()).toLowerCase());
            emailDaoImp.updatePassword(userInfo);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        return "2";
    }

    private String TimeAndExistDetect(UserInfo userInfo,UserInfo tempUserInfo){
        if(tempUserInfo==null)return "1";

        LocalDateTime date = tempUserInfo.getTime();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(date, now);
        if (duration.toMinutes() > 30) {
            emailDaoImp.setVerifyCodeAndTimeNull(userInfo);
            return "0";
        }
        return "-1";
    }
}
