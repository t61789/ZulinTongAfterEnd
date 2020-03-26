package com.ZuLinTong.frame;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;

public abstract class SqlSessionDaoSupportAbstract extends SqlSessionDaoSupport {

    @Resource
    public void setSession(SqlSessionFactory factory) {
        super.setSqlSessionFactory(factory);
    }
}


