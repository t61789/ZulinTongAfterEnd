package com.zulintong.user.service;

import com.zulintong.user.bean.UserInfo;

import javax.mail.MessagingException;

public interface IAccountService {

    String login(UserInfo info);

    String register(UserInfo info);

    String bindingSend(UserInfo userInfo) throws MessagingException;

    String bindingVerify(UserInfo userInfo);

    String retrieveSend(UserInfo userInfo) throws MessagingException;

    String retrieveVerify(UserInfo userInfo);
}
