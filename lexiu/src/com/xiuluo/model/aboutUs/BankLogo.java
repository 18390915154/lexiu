package com.xiuluo.model.aboutUs;

import java.util.Date;

public class BankLogo {
    private Integer logoid;

    private String picurl;

    private String bankname;

    private Date addtime;

    public Integer getLogoid() {
        return logoid;
    }

    public void setLogoid(Integer logoid) {
        this.logoid = logoid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}