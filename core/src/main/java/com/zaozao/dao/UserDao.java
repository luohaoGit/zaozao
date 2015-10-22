package com.zaozao.dao;

import com.zaozao.model.po.User;
import org.springframework.stereotype.Repository;

/**
 * Created by luohao on 2015/1/27.
 */
@Repository
public interface UserDao extends BaseDao<User> {

    void bindWx(User user);

    int checkByTel(String telephone);

    User findByTel(String telephone);
}
