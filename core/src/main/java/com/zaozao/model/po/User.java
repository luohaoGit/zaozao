package com.zaozao.model.po;

import com.zaozao.utils.EmojiFilterUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by luohao on 2015/10/15.
 */
public class User extends BasePO {

    private String zzid;
    private String username; //用户名
    private String password; //密码
    private String name; //姓名
    private String nickName; //昵称
    private String gender; //性别
    private String telephone; //电话
    private Date birthday; //生日
    private String wxNumber; //微信号
    private Date registerTime; //注册时间
    private String address; //地址
    private String idNumber; //身份证号码
    private String portrait; //头像
    private String qrCode; //二维码
    private String qrTicket;
    private String qrUrl;
    private boolean locked; //锁定
    private String salt; //盐值
    private String openId; //关注者id
    private boolean bundling; //是否绑定账号
    private boolean subcribe;

    protected String wxnickname;
    protected String sex;
    protected String language;
    protected String city;
    protected String province;
    protected String country;
    protected String headImgUrl;
    protected Long subscribeTime;
    protected String unionId;
    protected String remark;
    protected Integer groupId;

    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getZzid() {
        return zzid;
    }

    public void setZzid(String zzid) {
        this.zzid = zzid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getWxNumber() {
        return wxNumber;
    }

    public void setWxNumber(String wxNumber) {
        this.wxNumber = wxNumber;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public boolean isBundling() {
        return bundling;
    }

    public void setBundling(boolean bundling) {
        this.bundling = bundling;
    }

    public String getQrTicket() {
        return qrTicket;
    }

    public void setQrTicket(String qrTicket) {
        this.qrTicket = qrTicket;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public boolean isSubcribe() {
        return subcribe;
    }

    public void setSubcribe(boolean subcribe) {
        this.subcribe = subcribe;
    }

    public String getWxnickname() {
        return EmojiFilterUtils.filterEmoji(wxnickname);
    }

    public void setWxnickname(String wxnickname) {
        this.wxnickname = wxnickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
