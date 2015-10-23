package com.zaozao.service;

import com.zaozao.model.vo.MessageVO;

/**
 * Created by luohao on 2015/10/22.
 */
public interface WXService {

    void receive(String xml);

    void pushTemplateMessage(MessageVO messageVO);

}
