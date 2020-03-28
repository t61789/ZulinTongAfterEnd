package com.ZuLinTong.account.service;

import com.ZuLinTong.account.bean.UserInfo;

import javax.mail.MessagingException;

public interface IEmailService {

    String bindingSend(UserInfo userInfo) throws MessagingException;

    String bindingVerify(UserInfo userInfo);

    String retrieveSend(UserInfo userInfo) throws MessagingException;

    String retrieveVerify(UserInfo userInfo);
}
