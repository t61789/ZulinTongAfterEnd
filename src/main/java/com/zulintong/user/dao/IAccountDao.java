package com.zulintong.user.dao;

import com.zulintong.user.bean.UserInfo;

public interface IAccountDao {

    String getEmail(UserInfo userInfo);

    String getUsernameByEmail(UserInfo userInfo);

    void sendVerifycode(UserInfo userInfo);

    UserInfo getVerifyCodeAndTime(UserInfo userInfo);

    void setVerifyCodeAndTimeNull(UserInfo userInfo);

    void updateEmail(UserInfo userInfo);

    void updatePassword(UserInfo userInfo);

    String login(UserInfo info);

    void register(UserInfo info);
}
