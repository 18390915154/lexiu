package com.xiuluo.model.aboutUs;

import java.util.Date;

public class Type {
    private Integer typeid;

    private String name;
   
    private String typenum;

    private Date addtime;

    
    
    public String getTypenum() {
		return typenum;
	}

	public void setTypenum(String typenum) {
		this.typenum = typenum;
	}

	public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
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