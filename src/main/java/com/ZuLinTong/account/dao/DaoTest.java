package com.ZuLinTong.account.dao;

import com.ZuLinTong.account.bean.UserInfo;
import com.ZuLinTong.frame.SqlSessionDaoSupportAbstract;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DaoTest extends SqlSessionDaoSupportAbstract {

    public void test(){
        System.out.println(this.getSqlSession()==null);

        UserInfo ui = new UserInfo();
        ui.setPassword("123456");
        ui.setUsername("insertTest");
        try {
            this.getSqlSession().insert("com.ZuLinTong.account.insertTest", ui);
            this.getSqlSession().insert("com.ZuLinTong.account.insertTest", ui);
        } catch (RuntimeException e) {
            System.out.println("insert");
            e.printStackTrace();
        }

        ui.setUsername("deleteTest");
        try {
            this.getSqlSession().delete("com.ZuLinTong.account.deleteTest", ui);
            this.getSqlSession().delete("com.ZuLinTong.account.deleteTest", ui);
        } catch (Exception e) {
            System.out.println("delete");
            e.printStackTrace();
        }

        ui.setPassword("654321");
        ui.setUsername("updateTest");
        try {
            this.getSqlSession().update("com.ZuLinTong.account.updateTest", ui);
            ui.setPassword("123456");
            ui.setUsername("updateTest1");
            this.getSqlSession().update("com.ZuLinTong.account.updateTest", ui);
        } catch (Exception e) {
            System.out.println("update");
            e.printStackTrace();
        }

        ui.setPassword("654321");
        ui.setUsername("updateTest");
        try {
            System.out.println((String) this.getSqlSession().selectOne("com.ZuLinTong.account.selectTest", ui));
            ui.setPassword("123456");
            System.out.println(this.getSqlSession().selectOne("com.ZuLinTong.account.selectTest", ui) == null);
        } catch (Exception e) {
            System.out.println("select");
            e.printStackTrace();
        }

        System.out.println("------------------------");
        try {

            ui.setPassword("123456");
            ui.setUsername("s");
            for(Object i : this.getSqlSession().selectList("com.ZuLinTong.account.selectTest1", ui)){
                UserInfo u = (UserInfo)i ;
                System.out.println(u.getUsername()+" "+u.getPassword());
            }
//            ui.setPassword("12345");
//            ui.setUsername("s");
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//            this.getSqlSession().select("com.ZuLinTong.account.selectTest1", ui, (resultContext -> {
//                Map<String, List<String>> result = (Map<String, List<String>>) resultContext.getResultObject();
//                for (String i : result.keySet()) {
//                    System.out.println(i);
//                }
//                System.out.println("!!!!!!");
//                for (List<String> i : result.values()) {
//                    for (String j : i) {
//                        System.out.println(j);
//                    }
//                }
//            }));
        } catch (Exception e) {
            System.out.println("select1");
            e.printStackTrace();
        }
    }
}
