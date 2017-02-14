package com.xiaolan.dao;

import com.xiaolan.bean.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByIdAndPass(@Param("adminId") String adminId, @Param("password") String password);

    Integer selectById(String id);
}