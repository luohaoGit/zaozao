package com.zaozao.model.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by luohao on 2015/10/20.
 */
public class UserVO extends BaseVO {

    @NotNull(message = "{user.telephone.NotEmpty.message}")
    @Pattern(regexp = "^((13[0-9])|(15[^4,\\\\D])|(18[0,5-9]))\\\\d{8}$", message = "{user.telephone.Pattern.message}")
    private String telephone;

    @NotNull(message = "{user.password.NotEmpty.message}")
    @Length(min = 6, message = "{user.password.Length.message}")
    private String password;

    @NotNull(message = "{user.identify.NotEmpty.message}")
    private String identifyId;

    private String username;

    @NotNull
    private String id;

    @NotNull
    private String openId;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentifyId() {
        return identifyId;
    }

    public void setIdentifyId(String identifyId) {
        this.identifyId = identifyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
