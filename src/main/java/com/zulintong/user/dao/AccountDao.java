package com.zulintong.user.dao;

import com.zulintong.user.bean.UserInfo;
import com.zulintong.frame.SqlSessionDaoSupportAbstract;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao  extends SqlSessionDaoSupportAbstract implements IAccountDao {

    @Override
    public String getEmail(UserInfo userInfo) {
        return this.getSqlSession().selectOne("com.ZuLinTong.user.getEmail", userInfo);
    }

    @Override
    public String getUsernameByEmail(UserInfo userInfo) {
        return this.getSqlSession().selectOne("com.ZuLinTong.user.getUserByEmail", userInfo);
    }

    @Override
    public void sendVerifycode(UserInfo userInfo) {
        this.getSqlSession().update("com.ZuLinTong.user.sendVerifycode", userInfo);
    }

    @Override
    public UserInfo getVerifyCodeAndTime(UserInfo userInfo) {
        return this.getSqlSession().selectOne("com.ZuLinTong.user.getVerifyCodeAndTime", userInfo);
    }

    @Override
    public void setVerifyCodeAndTimeNull(UserInfo userInfo) {
        this.getSqlSession().update("com.ZuLinTong.user.setVerifyCodeAndTimeNull", userInfo);
    }

    @Override
    public void updateEmail(UserInfo userInfo) {
        this.getSqlSession().update("com.ZuLinTong.user.updateEmail", userInfo);
    }

    @Override
    public void updatePassword(UserInfo userInfo) {
        this.getSqlSession().update("com.ZuLinTong.user.updatePassword", userInfo);
    }

    // TODO: 2020/3/28 多表更新
    @Override
    public void register(UserInfo info) {
        this.getSqlSession().insert("com.ZuLinTong.account.registerAccount", info);
        this.getSqlSession().insert("com.ZuLinTong.account.registerUseremail", info);
    }

    @Override
    public String login(UserInfo info) {
        return this.getSqlSession().selectOne("com.ZuLinTong.account.login", info);
    }

//    public void test() {
//        System.out.println(this.getSqlSession() == null);
//
//        UserInfo ui = new UserInfo();
//        ui.setPassword("123456");
//        ui.setUsername("insertTest");
//        try {
//            this.getSqlSession().insert("com.ZuLinTong.account.insertTest", ui);
//            this.getSqlSession().insert("com.ZuLinTong.account.insertTest", ui);
//        } catch (RuntimeException e) {
//            System.out.println("insert");
//            e.printStackTrace();
//        }
//
//        ui.setUsername("deleteTest");
//        try {
//            this.getSqlSession().delete("com.ZuLinTong.account.deleteTest", ui);
//            this.getSqlSession().delete("com.ZuLinTong.account.deleteTest", ui);
//        } catch (Exception e) {
//            System.out.println("delete");
//            e.printStackTrace();
//        }
//
//        ui.setPassword("654321");
//        ui.setUsername("updateTest");
//        try {
//            this.getSqlSession().update("com.ZuLinTong.account.updateTest", ui);
//            ui.setPassword("123456");
//            ui.setUsername("updateTest1");
//            this.getSqlSession().update("com.ZuLinTong.account.updateTest", ui);
//        } catch (Exception e) {
//            System.out.println("update");
//            e.printStackTrace();
//        }
//
//        ui.setPassword("654321");
//        ui.setUsername("updateTest");
//        try {
//            System.out.println((String) this.getSqlSession().selectOne("com.ZuLinTong.account.selectTest", ui));
//            ui.setPassword("123456");
//            System.out.println(this.getSqlSession().selectOne("com.ZuLinTong.account.selectTest", ui) == null);
//        } catch (Exception e) {
//            System.out.println("select");
//            e.printStackTrace();
//        }
//
//        System.out.println("------------------------");
//        try {
//
//            ui.setPassword("123456");
//            ui.setUsername("s");
//            for (Object i : this.getSqlSession().selectList("com.ZuLinTong.account.selectTest1", ui)) {
//                UserInfo u = (UserInfo) i;
//                System.out.println(u.getUsername() + " " + u.getPassword());
//            }
////            ui.setPassword("12345");
////            ui.setUsername("s");
////            System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
////            this.getSqlSession().select("com.ZuLinTong.account.selectTest1", ui, (resultContext -> {
////                Map<String, List<String>> result = (Map<String, List<String>>) resultContext.getResultObject();
////                for (String i : result.keySet()) {
////                    System.out.println(i);
////                }
////                System.out.println("!!!!!!");
////                for (List<String> i : result.values()) {
////                    for (String j : i) {
////                        System.out.println(j);
////                    }
////                }
////            }));
//        } catch (Exception e) {
//            System.out.println("select1");
//            e.printStackTrace();
//        }
//    }
}
