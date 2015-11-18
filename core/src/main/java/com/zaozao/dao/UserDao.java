package com.zaozao.dao;

import com.zaozao.model.po.User;
import org.springframework.stereotype.Repository;

/**
 * Created by luohao on 2015/1/27.
 */
@Repository
public interface UserDao extends BaseDao<User> {

    void bindTel(User user);

    void updateQr(User user);

    int checkByTel(String telephone);

    int checkByWx(String openid);

    User findByWx(String openid);

    User searchByCarNumber(String carNumber);

    User searchByUsername(String username);

    User findByTel(String telephone);

    void unsubcribe(String openid);

    void subcribe(String openid);

    String getQRById(String id);
}
