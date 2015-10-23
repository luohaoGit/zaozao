package com.zaozao.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by luohao on 2015/10/20.
 */
public class PropertiesUtil {

    protected static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static String filePath = "config/private.properties";
    private static Map<String, String> configMap = new HashMap<String, String>();
    private static String accessToken;

    static{
        Properties pps = new Properties();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
        try {
            pps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration en = pps.propertyNames(); //得到配置文件的名字

        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            logger.info(strKey + "=" + strValue);
            configMap.put(strKey, strValue);
        }
    }

    public static String getAccessToken(){
        return configMap.get("access_token");
    }

    public static void setAccessToken(String value){
        configMap.put("access_token", value);
    }

    //根据Key读取Value
    public static String get(String key) {
        return configMap.get(key);
    }

    public static void set(String key, String value){
        configMap.put(key, value);
    }

    //写入Properties信息
    public static void writeProperties (String pKey, String pValue) throws IOException {
        Properties pps = new Properties();

        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
        //从输入流中读取属性列表（键和元素对）
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，
        //将此 Properties 表中的属性列表（键和元素对）写入输出流
        pps.store(out, "Update " + pKey + " name");
    }
}
