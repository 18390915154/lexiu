package com.xiuluo.model.aboutUs;

import java.util.Date;

public class CompanyType {
    private Integer companytypeid;

    private String name;

    private Date addtime;

    public Integer getCompanytypeid() {
        return companytypeid;
    }

    public void setCompanytypeid(Integer companytypeid) {
        this.companytypeid = companytypeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}