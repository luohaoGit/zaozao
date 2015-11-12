package com.zaozao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by luohao on 2015/11/12.
 */
public class WebServiceUtil {

    public static String soap(String url, String soap, String version) throws IOException{
        URL wsUrl = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        if("1.2".equals(version)){
            conn.setRequestProperty("Content-Type", "application/soap+xml;charset=UTF-8");
        }else{
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        }

        OutputStream os = conn.getOutputStream();

        os.write(soap.getBytes("UTF-8"));

        InputStream is = conn.getInputStream();

        byte[] b = new byte[1024];
        int len = 0;
        String s = "";
        while((len = is.read(b)) != -1){
            String ss = new String(b,0,len,"UTF-8");
            s += ss;
        }

        is.close();
        os.close();
        conn.disconnect();
        return s;
    }

}
