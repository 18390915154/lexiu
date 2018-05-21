package com.xiuluo.model.aboutUs;

import java.math.BigDecimal;
import java.util.Date;

public class Worker {
    private Integer workerid;
    
    private Integer companyid;

    private String phone;

    private String nickname;

    private String password;
    
    private String paypassword;

    private String avatar;

    private String sex;

    private Date birthday;
    
    private Integer age;

    private String contactTel;

    private String tel;

    private String companiesTel;

    private String identityFront;

    private String identityRear;

    private String credentialsFront;

    private String credentialsRear;

    private String skill;

    private Float grade;

    private BigDecimal money;
    
    private Integer score;

    private Date addtime;
    
    private String idcard;
    
    private Integer typeid;
    
    private Short isok;

    //*********************
    private String formatbirthday;
    
    private String companyname;
    
    
    
    public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Short getIsok() {
		return isok;
	}

	public void setIsok(Short isok) {
		this.isok = isok;
	}

	public String getFormatbirthday() {
		return formatbirthday;
	}

	public void setFormatbirthday(String formatbirthday) {
		this.formatbirthday = formatbirthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Integer workerid) {
        this.workerid = workerid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompaniesTel() {
        return companiesTel;
    }

    public void setCompaniesTel(String companiesTel) {
        this.companiesTel = companiesTel;
    }

    public String getIdentityFront() {
        return identityFront;
    }

    public void setIdentityFront(String identityFront) {
        this.identityFront = identityFront == null ? null : identityFront.trim();
    }

    public String getIdentityRear() {
        return identityRear;
    }

    public void setIdentityRear(String identityRear) {
        this.identityRear = identityRear == null ? null : identityRear.trim();
    }

    public String getCredentialsFront() {
        return credentialsFront;
    }

    public void setCredentialsFront(String credentialsFront) {
        this.credentialsFront = credentialsFront == null ? null : credentialsFront.trim();
    }

    public String getCredentialsRear() {
        return credentialsRear;
    }

    public void setCredentialsRear(String credentialsRear) {
        this.credentialsRear = credentialsRear == null ? null : credentialsRear.trim();
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}