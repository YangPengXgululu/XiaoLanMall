package com.xiaolan.serviceimpl;

import com.xiaolan.bean.Admin;
import com.xiaolan.dao.AdminMapper;
import com.xiaolan.service.AdminSer;
import com.xiaolan.util.JedisUtil;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午12:21
 * Usage:
 */
@Service
public class AdminSerImpl implements AdminSer {
    @Resource
    private AdminMapper adminMapper;

    public Admin adminLogin(String adminId, String password) {
        return adminMapper.selectByIdAndPass(adminId, password);
    }

    public boolean isAdmin(String id) {
        if (id == null)
            return false;
        return adminMapper.selectById(id) > 0;
    }

    public HashMap<String, String> getAllOnLineUsers() {
        Set<String> tokens = JedisUtil.FuzzySeach("user_*");
        HashMap<String, String> users = new HashMap<String, String>(tokens.size());
        for (String token : tokens) {
            users.put(JedisUtil.getValue(token), token);
        }
        return users;
    }

    public HashMap<String, String> getAllOnLineAdmin() {
        Set<String> tokens = JedisUtil.FuzzySeach("admin_*");
        HashMap<String, String> admins = new HashMap<String, String>(tokens.size());
        for (String token : tokens) {
            admins.put(JedisUtil.getValue(token), token);
        }
        return admins;
    }
}