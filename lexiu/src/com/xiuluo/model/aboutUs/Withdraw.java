package com.xiuluo.model.aboutUs;

import java.math.BigDecimal;
import java.util.Date;

public class Withdraw {
    private Integer withdrawid;

    private Integer persionid;

    private BigDecimal monery;

    private String account;
    
    private Short userorwork;
    
    private Short isok;

    private Date addtime;

    //********************
    
    private String name; 
    
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getUserorwork() {
		return userorwork;
	}

	public void setUserorwork(Short userorwork) {
		this.userorwork = userorwork;
	}

	public Short getIsok() {
		return isok;
	}

	public void setIsok(Short isok) {
		this.isok = isok;
	}

	public Integer getWithdrawid() {
        return withdrawid;
    }

    public void setWithdrawid(Integer withdrawid) {
        this.withdrawid = withdrawid;
    }

    public Integer getPersionid() {
        return persionid;
    }

    public void setPersionid(Integer persionid) {
        this.persionid = persionid;
    }

    public BigDecimal getMonery() {
        return monery;
    }

    public void setMonery(BigDecimal monery) {
        this.monery = monery;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}