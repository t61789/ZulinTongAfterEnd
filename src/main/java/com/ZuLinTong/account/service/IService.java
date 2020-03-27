package com.ZuLinTong.account.service;

import com.ZuLinTong.account.bean.UserInfo;

public interface IService {

    String login(UserInfo info);

    String register(UserInfo info);
}
