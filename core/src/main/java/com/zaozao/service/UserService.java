package com.zaozao.service;

import com.zaozao.model.po.User;
import com.zaozao.model.vo.UserVO;

/**
 * Created by luohao on 2015/10/18.
 */
public interface UserService {

    User findByUsername(String username);

    User findById(String id);

    User findByTel(String telephone);

    void bindWx(UserVO userVO);

    void register(UserVO userVO);

    void login(UserVO userVO);

    boolean checkByTel(String telephone);

}