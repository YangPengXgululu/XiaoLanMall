package com.xiaolan.controller;

import com.xiaolan.bean.User;
import com.xiaolan.dao.UserMapper;
import com.xiaolan.po.Token;
import com.xiaolan.service.AdminSer;
import com.xiaolan.service.UserSer;
import com.xiaolan.staticconfig.StaticConfig;
import com.xiaolan.util.JedisUtil;
import com.xiaolan.util.OpenIdUtil;
import com.xiaolan.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Author: fallen
 * Date: 17-2-10
 * Time: 下午4:50
 * Usage:
 */
@Controller
@RequestMapping("/user")
public class UserCtrl {
    @Resource
    private UserSer userSerImpl;

    @RequestMapping(value = "/login")
    @ResponseBody
    public Result userLogin(String code) {
        Result result = new Result();
        String openId = OpenIdUtil.getOpenIdByCode(code);
        if (openId == null) {
            result.setRes(0);
            result.setMsg("登陆失败");
            return result;
        }
        int status = userSerImpl.userLogin(openId);
        if (status == 0) {
            result.setRes(0);
            result.setMsg("登陆失败");
            return result;
        }
        User user = userSerImpl.getUserByOpenid(openId);
        String tokenStr = UUID.randomUUID().toString();
        JedisUtil.setValue("user_" + tokenStr, String.valueOf(user.getUserId()), StaticConfig.user_alive_time);
        Token token = new Token();
        token.setTokenStr(tokenStr);
        result.setRes(1);
        result.setMsg("登陆成功");
        result.setData(token);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result userUpdate(String token, User user) {
        Result result = new Result();
        String _userId = JedisUtil.getValue(token);
        if (_userId == null) {
            result.setRes(0);
            result.setMsg("token已经失效，请重新登录");
            return result;
        }
        JedisUtil.reborn(token, StaticConfig.user_alive_time);
        int userId = Integer.parseInt(_userId);
        user.setUserId(userId);
        int status = userSerImpl.userInfoUpdate(user);
        if (status > 0) {
            result.setRes(1);
            result.setMsg("信息更改成功");
        } else {
            result.setRes(0);
            result.setMsg("信息更改失败");
        }
        return result;
    }
}