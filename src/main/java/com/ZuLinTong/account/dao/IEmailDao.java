package com.ZuLinTong.account.dao;

import com.ZuLinTong.account.bean.UserInfo;

public interface IEmailDao {

    String getEmail(UserInfo userInfo);

    String getUsernameByEmail(UserInfo userInfo);

    void sendVerifycode(UserInfo userInfo);

    UserInfo getVerifyCodeAndTime(UserInfo userInfo);

    void setVerifyCodeAndTimeNull(UserInfo userInfo);

    void updateEmail(UserInfo userInfo);

    void updatePassword(UserInfo userInfo);
}
