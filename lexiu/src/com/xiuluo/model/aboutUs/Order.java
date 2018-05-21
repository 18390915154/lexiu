package com.xiuluo.model.aboutUs;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer orderid;

    private String ordernum;

    private Date starttime;

    private Date overtime;

    private String address;

    private BigDecimal price;

    private Integer userid;

    private Integer workerid;

    private String counttime;

    private Short type;

    private String content;

    private String fault;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Short delflg;

    private String reason;
    
    private String voice;

    private Date addtime;
    
    private Integer typeid;
   
    private String detail;
    
    private String picurl;
    
    private Integer companyid;
    
    //*********************
    private String username;

	private String workname;
    
    private String typename;
    
    private String worktypename;
    
    private String formatadd;
   
    private String formatstart;
    
    private String formatover;
    
    private String avatar;
    
    private String userphone;
    
    private int gomap;
    
    
	public int getGomap() {
		return gomap;
	}

	public void setGomap(int gomap) {
		this.gomap = gomap;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	public String getWorktypename() {
		return worktypename;
	}

	public void setWorktypename(String worktypename) {
		this.worktypename = worktypename;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getFormatadd() {
		return formatadd;
	}

	public void setFormatadd(String formatadd) {
		this.formatadd = formatadd;
	}

	public String getFormatstart() {
		return formatstart;
	}

	public void setFormatstart(String formatstart) {
		this.formatstart = formatstart;
	}

	public String getFormatover() {
		return formatover;
	}

	public void setFormatover(String formatover) {
		this.formatover = formatover;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getWorkname() {
		return workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Integer workerid) {
        this.workerid = workerid;
    }

    public String getCounttime() {
        return counttime;
    }

    public void setCounttime(String counttime) {
        this.counttime = counttime == null ? null : counttime.trim();
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault == null ? null : fault.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Short getDelflg() {
        return delflg;
    }

    public void setDelflg(Short delflg) {
        this.delflg = delflg;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}