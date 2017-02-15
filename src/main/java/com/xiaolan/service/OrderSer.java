package com.xiaolan.service;

import com.xiaolan.bean.Orders;
import com.xiaolan.po.OrderPre;

import java.util.ArrayList;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午2:33
 * Usage:
 */
public interface OrderSer {

    Orders getOrderByOrderId(int orderId);

    Integer orderCreate(OrderPre orderPre);

    int orderUpdate(Orders orders);

    ArrayList<String> getUserOrderIdList(int userId);
}
