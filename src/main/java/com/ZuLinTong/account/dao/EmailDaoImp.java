package com.ZuLinTong.account.dao;

import com.ZuLinTong.account.bean.UserInfo;
import com.ZuLinTong.frame.SqlSessionDaoSupportAbstract;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDaoImp extends SqlSessionDaoSupportAbstract implements IEmailDao {

    @Override
    public String getEmail(UserInfo userInfo) {

        return this.getSqlSession().selectOne("com.ZuLinTong.account.getEmail", userInfo);
    }

    @Override
    public String getUsernameByEmail(UserInfo userInfo) {
        return this.getSqlSession().selectOne("com.ZuLinTong.account.getUserByEmail", userInfo);
    }

    @Override
    public void sendVerifycode(UserInfo userInfo) {
        this.getSqlSession().update("com.ZuLinTong.account.sendVerifycode", userInfo);
    }

    @Override
    public UserInfo getVerifyCodeAndTime(UserInfo userInfo) {
        return this.getSqlSession().selectOne("com.ZuLinTong.account.getVerifyCodeAndTime", userInfo);
    }

    @Override
    public void setVerifyCodeAndTimeNull(UserInfo userInfo) {
        this.getSqlSession().update("com.ZuLinTong.account.setVerifyCodeAndTimeNull", userInfo);
    }

    @Override
    public void updateEmail(UserInfo userInfo) {
        this.getSqlSession().update("com.ZuLinTong.account.updateEmail", userInfo);
    }

    @Override
    public void updatePassword(UserInfo userInfo) {
        this.getSqlSession().update("com.ZuLinTong.account.updatePassword", userInfo);
    }
}
