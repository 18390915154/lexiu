package com.xiuluo.model.aboutUs;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeWorker {
    private Integer id;

    private Integer workerid;

    private BigDecimal money;

    private String detail;

    private Short type;
    
    private Integer score;

    private Short delstate;

    private Date addtime;
    
    //******************
    private String formattime;
    
    private String workername;
    
    
    public String getWorkername() {
		return workername;
	}

	public void setWorkername(String workername) {
		this.workername = workername;
	}

	public String getFormattime() {
		return formattime;
	}

	public void setFormattime(String formattime) {
		this.formattime = formattime;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Integer workerid) {
        this.workerid = workerid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getDelstate() {
        return delstate;
    }

    public void setDelstate(Short delstate) {
        this.delstate = delstate;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}