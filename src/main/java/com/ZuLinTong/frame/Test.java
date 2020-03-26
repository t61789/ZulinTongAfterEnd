package com.ZuLinTong.frame;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

@Controller
public class Test {

    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader br = null;
        OutputStream o = null;
        try {
            String acceptjson = "";
            br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            acceptjson = sb.toString();
            System.out.print(acceptjson);
            o = response.getOutputStream();
            o.write(acceptjson.getBytes());
        } catch (Exception e) {
        } finally {
            if (br != null) br.close();
            if (o != null) o.close();
        }
    }
}
