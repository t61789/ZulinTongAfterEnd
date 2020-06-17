package com.zulintong.background.controller;

import com.zulintong.background.bean.Bike;
import com.zulintong.frame.BaseController;
import com.zulintong.frame.SecurityKeys;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Controller
public class Controller extends BaseController {

    @Resource
    private SecurityKeys securityKeys;

    @RequestMapping("/staffVerify")
    @ResponseBody
    public String staffVerify(@RequestBody String key, HttpServletRequest request) {
        String result = "-1";
        try {
            result = (String) service("staffVerify", key,request);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @RequestMapping("/uploadImg")
    @ResponseBody
    public Object uploadImg(@RequestParam MultipartFile bikeImg, HttpServletRequest request) {
        if(!securityCheck(request))
            return "-1";
        try {
            return service("uploadImg", bikeImg);
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    @RequestMapping("/setDetail")
    @ResponseBody
    public String setDetail(@RequestBody Bike bike, HttpServletRequest request) {
        String result = "-1";
        if(!securityCheck(request))
            return result;

        try {
            result = (String) service("setDetail", bike);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public boolean securityCheck(HttpServletRequest request){
        return securityKeys.getKey().equals(request.getSession().getAttribute("staffKey"));
    }
}
