package com.zaozao.weixin.mp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zaozao.weixin.WXMessageService;
import com.zaozao.weixin.bean.WXContext;
import com.zaozao.weixin.bean.send.WXServiceMessage;
import com.zaozao.weixin.bean.send.WXTemplateMessage;
import com.zaozao.weixin.exception.WXException;
import com.zaozao.weixin.kit.MapUtil;
import com.zaozao.weixin.kit.http.WXRequestErrorHandler;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * author: Tkk
 * date: 2015/8/4
 */
@Service
public class WXMessageServiceImpl extends WXServiceAdapter implements WXMessageService {

    public WXMessageServiceImpl() {
    }

    public WXMessageServiceImpl(WXContext context, WXRequestErrorHandler errorHandler) {
        super(context, errorHandler);
    }

    @Override
    public void sendServiceMessage(WXServiceMessage serviceMessage) throws WXException {
    }

    @Override
    public String sendTemplateMessage(WXTemplateMessage templateMessage) throws WXException {
        String url = String.format("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s", context.getAccessToken().getAccessToken());
        Map<String, Object> o = MapUtil.toMap(
                "touser", templateMessage.getToUser(),
                "template_id", templateMessage.getTemplateId(),
                "data", templateMessage.getTemplateDataMap(),
                "url", templateMessage.getUrl(),
                "topcolor", templateMessage.getTopColor());
        String jsonParam = JSON.toJSONString(o);
        JSONObject result = request.jsonPost(url, jsonParam, null);
        return result.getString("msgid");
    }
}
