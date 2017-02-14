package com.xiaolan.service;

import com.xiaolan.bean.Admin;

import java.util.HashMap;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午12:19
 * Usage:
 */
public interface AdminSer {
    Admin adminLogin(String adminId, String password);

    boolean isAdmin(String userId);

    HashMap<String, String> getAllOnLineUsers();

    HashMap<String, String> getAllOnLineAdmin();
}