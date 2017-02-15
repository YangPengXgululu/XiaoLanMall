package com.xiaolan.controller;

import com.xiaolan.bean.Orders;
import com.xiaolan.po.OrderPre;
import com.xiaolan.service.OrderSer;
import com.xiaolan.util.JedisUtil;
import com.xiaolan.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午2:32
 * Usage:
 */
@Controller
@RequestMapping("/order")
public class OrderCtrl {

    @Resource
    private OrderSer orderSerImpl;

    @RequestMapping("/new")
    @ResponseBody
    public Result newOrder(OrderPre orderPre, String token) {
        Result result = new Result();
        result.setRes(0);
        result.setMsg("操作失败");
        if (token == null || token.length() < 32) {
            return result;
        }
        Integer userId;
        try {
            userId = Integer.parseInt(JedisUtil.getValue(token));
        } catch (Exception e) {
            return result;
        }
        orderPre.getData().setUserId(userId);
        Integer orderId = orderSerImpl.orderCreate(orderPre);
        if (orderId != null) {
            result.setRes(1);
            result.setMsg("下单成功");
            result.setData("{'orderId':'" + orderId + "'}");
        }
        return result;
    }

    @RequestMapping("/inquire")
    @ResponseBody
    public Result orderQuery(String token, int orderId) {
        Result result = new Result();
        result.setRes(0);
        result.setMsg("操作失败");
        if (token == null || token.length() < 32) {
            return result;
        }
        Integer userId;
        try {
            userId = Integer.parseInt(JedisUtil.getValue(token));
            if (orderSerImpl.getUserOrderIdList(userId).contains(String.valueOf(orderId))) {
                Orders orders = orderSerImpl.getOrderByOrderId(orderId);
                result.setRes(1);
                result.setMsg("操作成功");
                result.setData(orders);
            }
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    @RequestMapping("/orderIds")
    @ResponseBody
    public Result getOrderIds(String token) {
        Result result = new Result();
        result.setRes(0);
        result.setMsg("操作失败");
        if (token == null || token.length() < 32) {
            return result;
        }
        Integer userId;
        try {
            userId = Integer.parseInt(JedisUtil.getValue(token));
            ArrayList<String> orderIds = orderSerImpl.getUserOrderIdList(userId);
            result.setRes(1);
            result.setMsg("操作成功");
            result.setData(orderIds);
        } catch (Exception e) {
            return result;
        }
        return result;
    }
}