package com.xiaolan.service;

import com.xiaolan.bean.Orders;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午2:33
 * Usage:
 */
public interface OrderSer {

    Orders getOrderByUserID(int userId);

    int orderCreate(Orders orders);

    int orderUpdate(Orders orders);

}
