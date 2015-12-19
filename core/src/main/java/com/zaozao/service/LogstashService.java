package com.zaozao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by luohao on 15/12/15.
 */
public interface LogstashService {

    Logger logstash = LoggerFactory.getLogger("LOGSTASH");
    Logger error = LoggerFactory.getLogger("ERROR");

}
