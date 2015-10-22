package com.zaozao.test.base;


import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2015/2/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/applicationContext.xml",
        "classpath:spring/spring-mybatis.xml",
        "classpath:spring/springMVC-servlet.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public abstract class BaseJunit4Test {
        protected static Logger logger = LoggerFactory.getLogger(BaseJunit4Test.class);
}
