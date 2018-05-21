package com.xiuluo.model.aboutUs;

import java.util.Date;

public class ShopBanner {
    private Integer shopBannerid;

    private Integer shopid;

    private String picurl;

    private Integer sort;

    private Date addtime;

    public Integer getShopBannerid() {
        return shopBannerid;
    }

    public void setShopBannerid(Integer shopBannerid) {
        this.shopBannerid = shopBannerid;
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}