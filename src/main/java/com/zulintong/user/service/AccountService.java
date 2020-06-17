package com.zulintong.user.service;

import com.zulintong.user.bean.UserInfo;
import com.zulintong.frame.MailSender;
import com.zulintong.frame.SecurityKeys;
import com.zulintong.user.dao.IAccountDao;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class AccountService implements IAccountService {

    @Resource
    private IAccountDao accountDao;

    @Resource
    private MailSender mailSender;

    @Resource
    private SecurityKeys securityKeys;

    @Override
    public String register(UserInfo info) {

        info.setPassword(DigestUtils.md5DigestAsHex(info.getPassword().getBytes()));

        try {
            accountDao.register(info);
        } catch (DuplicateKeyException e) {
            return "0";
        }
        return "1";
    }

    @Override
    public String login(UserInfo info) {
        info.setPassword(DigestUtils.md5DigestAsHex(info.getPassword().getBytes()));

        try {
            if (accountDao.login(info) == null) {
                return "0";
            } else
                return "1";
        } catch (DuplicateKeyException e) {
            return "0";
        }
    }


    @Override
    public String bindingSend(UserInfo userInfo) throws MessagingException {
        // TODO: 2020/3/28 合并查询，retrieve
        if (accountDao.getEmail(userInfo) != null) return "1";
        if (accountDao.getUsernameByEmail(userInfo) != null) return "0";

        String verifycode =securityKeys.getSecurityMD5(userInfo.getEmail()).substring(0,5);

        mailSender.SendMail(userInfo.getEmail(), "租赁通邮箱验证码", verifycode);
        userInfo.setVerifycode(verifycode);

        LocalDateTime now = LocalDateTime.now();
        userInfo.setTime(now);
        accountDao.sendVerifycode(userInfo);

        return "2";
    }

    @Override
    public String bindingVerify(UserInfo userInfo) {
        UserInfo tempUserInfo = accountDao.getVerifyCodeAndTime(userInfo);
        String temp=TimeAndExistDetect(userInfo,tempUserInfo);
        if(temp!="-1")return temp;


        if(!userInfo.getVerifycode().toLowerCase().equals(tempUserInfo.getVerifycode()))
            return "1";
        if(!securityKeys.getSecurityMD5(userInfo.getEmail()).substring(0,5).equals(userInfo.getVerifycode()))
            return "3";
        try{
            accountDao.updateEmail(userInfo);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        return "2";
    }

    @Override
    public String retrieveSend(UserInfo userInfo) throws MessagingException {
        if (!userInfo.getEmail().equals(accountDao.getEmail(userInfo))) return "0";

        LocalDateTime now = LocalDateTime.now();
        String verifycode =securityKeys.getSecurityMD5(now.toString()).substring(0,5);

        mailSender.SendMail(userInfo.getEmail(), "租赁通邮箱验证码", verifycode);
        userInfo.setVerifycode(verifycode);

        userInfo.setTime(now);
        accountDao.sendVerifycode(userInfo);

        return "1";
    }

    @Override
    public String retrieveVerify(UserInfo userInfo) {
        UserInfo tempUserInfo = accountDao.getVerifyCodeAndTime(userInfo);
        String temp=TimeAndExistDetect(userInfo,tempUserInfo);
        if(temp!="-1")return temp;

        if(!userInfo.getVerifycode().toLowerCase().equals(tempUserInfo.getVerifycode()))
            return "1";
        try{
            accountDao.setVerifyCodeAndTimeNull(userInfo);
            userInfo.setPassword(DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes()).toLowerCase());
            accountDao.updatePassword(userInfo);
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
            accountDao.setVerifyCodeAndTimeNull(userInfo);
            return "0";
        }
        return "-1";
    }
}
