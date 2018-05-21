package com.xiuluo.dao.aboutUs;

import com.xiuluo.model.aboutUs.Checkout;
import java.util.List;

public abstract interface CheckoutMapper
{
  public abstract int deleteByPrimaryKey(Integer paramInteger);

  public abstract int insert(Checkout paramCheckout);

  public abstract int insertSelective(Checkout paramCheckout);

  public abstract Checkout selectByPrimaryKey(Integer paramInteger);

  public abstract Checkout selectByType(Integer paramInteger);

  public abstract List<Checkout> selectAll();

  public abstract int updateByPrimaryKeySelective(Checkout paramCheckout);

  public abstract int updateByPrimaryKey(Checkout paramCheckout);
}