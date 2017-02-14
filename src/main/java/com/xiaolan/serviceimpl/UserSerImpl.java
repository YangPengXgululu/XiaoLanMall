package com.xiaolan.serviceimpl;

import com.xiaolan.bean.User;
import com.xiaolan.dao.UserMapper;
import com.xiaolan.service.UserSer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 上午11:09
 * Usage:
 */
@Service
public class UserSerImpl implements UserSer {

    @Resource
    private UserMapper userMapper;

    public int userLogin(String openId) {
        User user = userMapper.selectByOpenid(openId);
        if (user == null) {
            user.setOpenid(openId);
            return userMapper.insert(user);
        }
        return 1;
    }

    public int userInfoUpdate(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User getUserByOpenid(String openId) {
        return userMapper.selectByOpenid(openId);
    }
}