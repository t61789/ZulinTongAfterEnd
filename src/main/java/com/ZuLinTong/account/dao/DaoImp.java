package com.ZuLinTong.account.dao;

import com.ZuLinTong.account.bean.UserInfo;
import com.ZuLinTong.frame.SqlSessionDaoSupportAbstract;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImp extends SqlSessionDaoSupportAbstract implements IDao {

    public void register(UserInfo info) {
        this.getSqlSession().insert("com.ZuLinTong.account.submit", info);
    }

    public String login(UserInfo info) {
        return this.getSqlSession().selectOne("com.ZuLinTong.account.login",info);
    }
}
