package com.zaozao.dao;

import com.zaozao.model.po.TempUser;
import org.springframework.stereotype.Repository;

/**
 * Created by luohao on 2016/1/19.
 */
@Repository
public interface TempUserDao extends BaseDao<TempUser> {

    TempUser searchByUserkey(String userkey);

}
