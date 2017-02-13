package com.xiaolan.dao;

import com.xiaolan.bean.ItemComment01;

public interface ItemComment01Mapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(ItemComment01 record);

    int insertSelective(ItemComment01 record);

    ItemComment01 selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(ItemComment01 record);

    int updateByPrimaryKey(ItemComment01 record);
}