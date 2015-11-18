package com.zaozao.service.impl;

import com.zaozao.dao.UserDao;
import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.po.User;
import com.zaozao.model.vo.PageVO;
import com.zaozao.model.vo.UserVO;
import com.zaozao.service.UserService;
import com.zaozao.service.WeixinService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by luohao on 2015/10/18.
 */
@Service
public class UserServiceImpl implements UserService {

    protected static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private WeixinService weixinService;

    public User findByUsername(String username) {
        return userDao.searchByUsername(username);
    }

    public User findByCarNumber(String carNumber) {
        return userDao.searchByCarNumber(carNumber);
    }

    public User findById(String id) {
        return userDao.searchById(id);
    }

    public User findByTel(String telephone) {
        User user = userDao.findByTel(telephone);
        if(user == null){
            throw new ZaozaoException("用户不存在");
        }
        return user;
    }

    public void bindTel(UserVO userVO) {
        User user = userDao.searchById(userVO.getId());
        if(user == null){
            throw new ZaozaoException("用户不存在");
        }
        user.setTelephone(userVO.getTelephone());
        userDao.bindTel(user);
    }

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

    public void autoRegister(UserVO userVO) {
        if(userVO == null || userVO.getOpenId() == null){
            throw new ZaozaoException("openid 或 id 不能为空");
        }
        int count = userDao.checkByWx(userVO.getOpenId());
        //没有关注的人，新增
        if(count == 0){
            User user = new User();
            generatorQrCode(user);
            user.setTelephone("未绑定");
            user.setUsername(userVO.getOpenId());
            user.setPassword("000000");
            user.setRegisterTime(new Date());
            user.setOpenId(userVO.getOpenId());
            user.setSubcribe(true);
            logger.info("保存用户：" + user.toString());
            userDao.insert(user);
        }else{
            User user = userDao.findByWx(userVO.getOpenId());
            if(StringUtils.isEmpty(user.getQrTicket())){
                generatorQrCode(user);
                userDao.updateQr(user);
            }
            userDao.subcribe(userVO.getOpenId());
        }
    }

    private User generatorQrCode(User user){
        try {
            WxMpQrCodeTicket ticket = weixinService.qrCodeCreateLastTicket("zzqr" + user.getId());
            user.setQrTicket(ticket.getTicket());
            user.setQrUrl(ticket.getUrl());
            File file = weixinService.qrCodePicture(ticket);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            String qrcode = Base64.encodeBase64String(buffer);
            user.setQrCode(qrcode);
        } catch (WxErrorException e) {
            logger.error(e.getMessage());
            //throw new ZaozaoException(e.getMessage());
        } catch (IOException e){
            logger.error(e.getMessage());
            //throw new ZaozaoException(e.getMessage());
        }
        return user;
    }

    public void login(UserVO userVO) {
        User user = findByTel(userVO.getTelephone());
        if(user == null){
            throw new ZaozaoException("用户不存在");
        }

        if(!userVO.getPassword().equals(user.getPassword())){
            throw new ZaozaoException("密码错误");
        }
    }

    public boolean checkByTel(String telephone) {
        int count = userDao.checkByTel(telephone);
        return count == 0 ? false : true;
    }

    public PageVO<User> getUserPage(PageVO<User> pageVO) {
        int pageSize = pageVO.getPageSize();
        long count = userDao.count();
        long start = pageVO.getPageNumber() * pageVO.getPageSize();
        List<User> userList = userDao.getPage(start, pageSize);
        pageVO.setRowCout(count);
        pageVO.setData(userList);
        return pageVO;
    }

    public void unsubcribe(String openid) {
        userDao.unsubcribe(openid);
    }

    public void subcribe(String openid) {
        userDao.subcribe(openid);
    }

    public String getQrCode(String id) {
        String qrcode = userDao.getQRById(id);
        return qrcode;
    }
}
