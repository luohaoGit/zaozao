package com.zaozao.service;

import com.zaozao.model.po.User;
import com.zaozao.model.vo.PageVO;
import com.zaozao.model.vo.UserVO;

/**
 * Created by luohao on 2015/10/18.
 */
public interface UserService {

    User findByUsername(String username);

    User findByCarNumber(String carNumber);

    User findById(String id);

    User findByTel(String telephone);

    void bindTel(UserVO userVO);

    void register(UserVO userVO);

    void autoRegister(UserVO userVO);

    void login(UserVO userVO);

    boolean checkByTel(String telephone);

    PageVO<User> getUserPage(PageVO<User> pageVO);

    void unsubcribe(String openid);

    void subcribe(String openid);

    String getQrCode(String id);
}