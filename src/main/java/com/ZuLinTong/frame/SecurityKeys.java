package com.ZuLinTong.frame;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class SecurityKeys {

    private Properties keyFile;

    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private void init() throws IOException {
        try{
            keyFile = new Properties();
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("keys.properties");
            keyFile.load(in);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String GetKey(){
        return keyFile.getProperty("key");
    }

    public String getSecurityMD5(String source){
        return DigestUtils.md5DigestAsHex((GetKey()+source).getBytes()).toLowerCase();
    }
}
