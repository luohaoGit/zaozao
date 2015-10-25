package com.zaozao.service.impl;

import com.zaozao.dao.UserDao;
import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.UserVO;
import com.zaozao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * Created by luohao on 2015/10/18.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findById(String id) {
        return userDao.searchById(id);
    }

    @Override
    public User findByTel(String telephone) {
        User user = userDao.findByTel(telephone);
        if(user == null){
            throw new ZaozaoException("用户不存在");
        }
        return user;
    }

    @Override
    public void bindWx(UserVO userVO) {
        User user = userDao.searchById(userVO.getId());
        if(user == null){
            throw new ZaozaoException("用户不存在");
        }
        user.setOpenId(userVO.getOpenId());
        userDao.bindWx(user);
    }

    @Override
    public void register(UserVO userVO) {
        boolean telExits = checkByTel(userVO.getTelephone());
        if(telExits){
            throw new ZaozaoException("手机号已经注册过");
        }

        User user = new User();
        user.setTelephone(userVO.getTelephone());
        user.setUsername(userVO.getTelephone());
        user.setPassword(userVO.getPassword());
        user.setRegisterTime(new Date());
        userDao.insert(user);
    }

    @Override
    public void login(UserVO userVO) {
        User user = findByTel(userVO.getTelephone());
        if(user == null){
            throw new ZaozaoException("用户不存在");
        }

        if(!userVO.getPassword().equals(user.getPassword())){
            throw new ZaozaoException("密码错误");
        }
    }

    @Override
    public boolean checkByTel(String telephone) {
        int count = userDao.checkByTel(telephone);
        return count == 0 ? false : true;
    }
}
