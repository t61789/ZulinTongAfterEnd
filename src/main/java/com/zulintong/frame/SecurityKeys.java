package com.zulintong.frame;

import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SecurityKeys {

    private Properties keyFile;

    private void init() throws IOException {
        try{
            keyFile = new Properties();
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("keys.properties");
            keyFile.load(in);
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getKey(){
        return keyFile.getProperty("key");
    }

    public String getSecurityMD5(String source){
        return DigestUtils.md5DigestAsHex((getKey()+source).getBytes()).toLowerCase();
    }
}
