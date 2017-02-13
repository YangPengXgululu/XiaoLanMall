package com.xiaolan.dao;

import com.xiaolan.bean.OrderReceipt;

public interface OrderReceiptMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderReceipt record);

    int insertSelective(OrderReceipt record);

    OrderReceipt selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderReceipt record);

    int updateByPrimaryKey(OrderReceipt record);
}