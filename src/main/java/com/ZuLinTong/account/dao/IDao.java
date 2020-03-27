package com.ZuLinTong.account.dao;

import com.ZuLinTong.account.bean.UserInfo;

public interface IDao {

    String login(UserInfo info);

    void register(UserInfo info);
}
