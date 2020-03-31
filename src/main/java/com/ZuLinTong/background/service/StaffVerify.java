package com.ZuLinTong.background.service;

import com.ZuLinTong.frame.BaseService;
import com.ZuLinTong.frame.SecurityKeys;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class StaffVerify extends BaseService {
    @Resource
    private SecurityKeys securityKeys;

    @Override
    public Object exec(Object... arg) {
        String arg1 = (String)arg[0];
        HttpServletRequest arg2 = (HttpServletRequest)arg[1];

        if(securityKeys.getKey().equals(arg1))
        {
            arg2.getSession().setAttribute("staffKey",arg1);
            return "1";
        }
        else
            return "0";
    }

    @Override
    public Object exec(Object arg) {
        return null;
    }
}
