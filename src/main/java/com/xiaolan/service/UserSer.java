package com.xiaolan.service;

import com.xiaolan.bean.User;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 上午11:08
 * Usage:
 */
public interface UserSer {

    int userLogin(String openId);

    int userInfoUpdate(User user);

    User getUserByOpenid(String openId);
}