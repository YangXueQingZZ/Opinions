package com.taowei.opinions.data;

public class WechatBean {



    String nickName = null;
    String sex = null;
    String city = null;
    String province = null;
    String country = null;
    String headimgurl = null;

    public WechatBean(String nickName, String sex, String city, String province, String country, String headimgurl) {
        this.nickName = nickName;
        this.sex = sex;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
