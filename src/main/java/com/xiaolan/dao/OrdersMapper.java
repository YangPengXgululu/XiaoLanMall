package com.xiaolan.dao;

import com.xiaolan.bean.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer orderId);

    Orders selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);


}