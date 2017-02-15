package com.xiaolan.serviceimpl;

import com.google.gson.Gson;
import com.sun.tools.corba.se.idl.constExpr.Or;
import com.xiaolan.bean.ItemDetailedMsg;
import com.xiaolan.bean.Orders;
import com.xiaolan.bean.User;
import com.xiaolan.dao.ItemDetailedMsgMapper;
import com.xiaolan.dao.OrdersMapper;
import com.xiaolan.dao.UserMapper;
import com.xiaolan.po.OrderPre;
import com.xiaolan.service.OrderSer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

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

    @Resource
    private ItemDetailedMsgMapper itemDetailedMsgMapper;

    public Orders getOrderByOrderId(int orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }

    public Integer orderCreate(OrderPre orderPre) {
        Orders orders = new Orders();
        orders.setStatus("1");
        orders.setCreateTime(new Date());
        orders.setOrderList(new Gson().toJson(orderPre.getData().getItemList()));
        orders.setNote(orderPre.getData().getNote());
        orders.setUserName(orderPre.getData().getName());
        orders.setPhone(orderPre.getData().getPhone());
        orders.setAddress(orderPre.getData().getAddress());
        float price = 0;
        for (OrderPre.DataBean.ItemListBean item : orderPre.getData().getItemList()) {
            price += itemDetailedMsgMapper.selectPriceByPrimaryKey(item.getItemID()) * item.getNum();
        }
        orders.setOrderFee(price);
        int status = ordersMapper.insertSelective(orders);
        if (status > 0) {
            return orders.getOrderId();
        }
        return null;
    }

    public int orderUpdate(Orders orders) {
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    public ArrayList<String> getUserOrderIdList(int userId) {
        return ordersMapper.selectOrderIdList(userId);
    }


}
