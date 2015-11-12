package com.zaozao.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;

/**
 * Created by luohao on 2015/11/12.
 */
public class XMLUtil {

    public static <T> T toObject(String xml, Class<T> beanClass){
        XStream xstream = XStreamInitializer.getInstance();
        xstream.alias("xml", beanClass);
        xstream.ignoreUnknownElements();
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        return (T)xstream.fromXML(xml);
    }

}
