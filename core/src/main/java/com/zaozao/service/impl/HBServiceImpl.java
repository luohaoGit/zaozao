package com.zaozao.service.impl;

import com.zaozao.exception.ZaozaoException;
import com.zaozao.service.HBService;
import com.zaozao.utils.WebServiceUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 * Created by luohao on 2015/11/5.
 */
@Service
public class HBServiceImpl implements HBService {

    protected static Logger logger = LoggerFactory.getLogger(HBServiceImpl.class);

    @Value("${hb_userid}")
    private String hbUserId;

    @Value("${hb_url}")
    private String hbUrl;

    public String getMobile(String carNumber) {
        String mobile;
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getSJHM xmlns=\"http://tempuri.org/\">\n" +
                "      <userID>" + hbUserId + "</userID>\n" +
                "      <hphm>" + carNumber + "</hphm>\n" +
                "      <hpzl>02</hpzl>\n" +
                "    </getSJHM>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        try {
            mobile = getPhoneFromXML(WebServiceUtil.soap(hbUrl, xml, "1.1"));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        }
        return mobile;
    }

    private String getPhoneFromXML(String xml){
        String phone;
        try {
            Document doc = new SAXReader().read(new StringReader(xml));
            Element rootElt = doc.getRootElement();
            Element el = rootElt.element("Body").element("getSJHMResponse")
                    .element("getSJHMResult").element("root").element("body")
                    .element("sjhm");
            phone = el.getText();
        } catch (DocumentException e) {
            logger.error(e.getMessage());
            throw new ZaozaoException(e.getMessage());
        }
        return phone;
    }

}
