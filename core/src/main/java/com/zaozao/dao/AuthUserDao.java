package com.zaozao.dao;

import com.zaozao.model.po.AuthUser;
import com.zaozao.model.po.User;
import org.springframework.stereotype.Repository;

/**
 * Created by luohao on 2016/1/19.
 */
@Repository
public interface AuthUserDao extends BaseDao<AuthUser> {

    AuthUser searchByAppId(String appid);

}
