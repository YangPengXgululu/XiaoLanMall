package com.xiaolan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: fallen
 * Date: 17-1-11
 * Time: 下午3:05
 * Usage:
 */
@Controller
@RequestMapping("/admin")
public class AdminCtrl {

    @RequestMapping("/login")
    public void test(HttpServletRequest req,HttpServletResponse resp) throws Exception{

    }
}
