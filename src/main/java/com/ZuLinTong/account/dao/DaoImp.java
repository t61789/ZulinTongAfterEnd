package com.ZuLinTong.account.dao;

import com.ZuLinTong.account.bean.UserInfo;
import com.ZuLinTong.frame.SqlSessionDaoSupportAbstract;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImp extends SqlSessionDaoSupportAbstract implements IDao {

    public String submit(UserInfo info) {

        try{
            this.getSqlSession().insert("com.ZuLinTong.account.submit",info);
        }catch(DuplicateKeyException e){
            return "0";
        }

        return "1";
    }

    public String login(UserInfo info) {
        return null;
    }
}
