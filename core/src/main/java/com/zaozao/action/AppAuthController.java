package com.zaozao.action;

import com.zaozao.dao.AuthUserDao;
import com.zaozao.dao.TempUserDao;
import com.zaozao.exception.ZaozaoException;
import com.zaozao.model.po.AuthUser;
import com.zaozao.model.po.TempUser;
import com.zaozao.model.vo.AuthVO;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/app")
public class AppAuthController {

	public static final Logger logger = LoggerFactory.getLogger(AppAuthController.class);
	public static final Logger error = LoggerFactory.getLogger("ERROR");

	@Autowired
	private AuthUserDao authUserDao;

	@Autowired
	private TempUserDao tempUserDao;

	@RequestMapping(value="/auth/m", method = RequestMethod.POST)
	public String authMoveCar(ModelMap model, @RequestBody AuthVO authVO) {
		AuthUser user = authUserDao.searchByAppId(authVO.getAppid());
		Assert.notNull(user);

		TempUser tuser = tempUserDao.searchByUserkey(authVO.getUserkey());
		if(tuser == null){
			TempUser suser = new TempUser();
			try {
				PropertyUtils.copyProperties(suser, authVO);
				tempUserDao.insert(suser);
			} catch (Exception e) {
				error.error(e.getMessage(), e);
			}
		}

		if(user.getAppsecret().equals(authVO.getAppsecret())){
			return "h5/informationPlate";
		}else{
			throw new ZaozaoException("认证失败");
		}
	}

}