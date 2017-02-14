package com.xiaolan.controller;

import com.xiaolan.bean.Admin;
import com.xiaolan.po.Token;
import com.xiaolan.service.AdminSer;
import com.xiaolan.staticconfig.StaticConfig;
import com.xiaolan.util.JedisUtil;
import com.xiaolan.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Author: fallen
 * Date: 17-1-11
 * Time: 下午3:05
 * Usage:
 */
@Controller
@RequestMapping("/admin")
public class AdminCtrl {

    @Resource
    private AdminSer adminSerImpl;

    @RequestMapping("/login")
    @ResponseBody
    public Result adminLogin(String adminId, String password) throws Exception {
        Result result = new Result();
        if (adminSerImpl.getAllOnLineAdmin().containsKey(adminId)) {
            JedisUtil.reborn(adminSerImpl.getAllOnLineAdmin().get(adminId), StaticConfig.admin_alive_time);
            result.setRes(1);
            result.setMsg("登陆成功");
            Token token = new Token();
            token.setTokenStr(adminSerImpl.getAllOnLineAdmin().get(adminId).replace("admin_", ""));
            result.setData(token);
            return result;
        }
        Admin admin = adminSerImpl.adminLogin(adminId, password);
        if (admin == null) {
            result.setRes(0);
            result.setMsg("账号或者密码错误");
            return result;
        }
        String tokenStr = UUID.randomUUID().toString();
        JedisUtil.setValue("admin_" + tokenStr, String.valueOf(admin.getAdminId()), StaticConfig.admin_alive_time);
        result.setRes(1);
        result.setMsg("登陆成功");
        Token token = new Token();
        token.setTokenStr(tokenStr);
        result.setData(token);
        return result;
    }
}