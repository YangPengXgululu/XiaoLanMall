package com.xiaolan.controller;

import com.xiaolan.service.OrderSer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
}