package com.ZuLinTong.frame;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@Component
public class CommonProperties {

    private Properties properties;

    public void init() throws IOException {
        properties = new Properties();
        properties.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("common.properties"),"UTF-8"));
    }

    public String getImgStoragePath(){
        return properties.getProperty("imgStoragePath");
    }

    public String getTypeList(){
        return properties.getProperty("typeList");
    }
}
