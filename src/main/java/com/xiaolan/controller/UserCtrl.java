package com.xiaolan.controller;

import com.xiaolan.util.OpenIdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: fallen
 * Date: 17-2-10
 * Time: 下午4:50
 * Usage:
 */
@Controller
@RequestMapping("/user")
public class UserCtrl {

    @RequestMapping("/login")
    public void userLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("----");
        String openId = OpenIdUtil.getOpenIdByCode(req.getParameter("code"));
        System.out.println(openId);
        resp.getWriter().write(openId);
    }
}