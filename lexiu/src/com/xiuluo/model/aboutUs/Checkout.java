package com.xiuluo.model.aboutUs;

import java.util.Date;

public class Checkout
{
  private Integer checkoutid;
  private String name;
  private Short type;
  private Double number;
  private Date addtime;

  public Integer getCheckoutid()
  {
    return this.checkoutid;
  }

  public void setCheckoutid(Integer checkoutid) {
    this.checkoutid = checkoutid;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = ((name == null) ? null : name.trim());
  }

  public Short getType() {
    return this.type;
  }

  public void setType(Short type) {
    this.type = type;
  }

  public Double getNumber() {
    return this.number;
  }

  public void setNumber(Double number) {
    this.number = number;
  }

  public Date getAddtime() {
    return this.addtime;
  }

  public void setAddtime(Date addtime) {
    this.addtime = addtime;
  }
}