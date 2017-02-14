package com.xiaolan.serviceimpl;

import com.xiaolan.bean.Orders;
import com.xiaolan.dao.OrdersMapper;
import com.xiaolan.service.OrderSer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午2:38
 * Usage:
 */
@Service
public class OrderSerImpl implements OrderSer {
    @Resource
    private OrdersMapper ordersMapper;

    public Orders getOrderByUserID(int userId) {
        return ordersMapper.selectByUserId(userId);
    }

    public int orderCreate(Orders orders) {
        return ordersMapper.insertSelective(orders);
    }

    public int orderUpdate(Orders orders) {
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }
}
