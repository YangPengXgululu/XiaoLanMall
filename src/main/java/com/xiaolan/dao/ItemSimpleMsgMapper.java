package com.xiaolan.dao;

import com.xiaolan.bean.ItemSimpleMsg;

public interface ItemSimpleMsgMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(ItemSimpleMsg record);

    int insertSelective(ItemSimpleMsg record);

    ItemSimpleMsg selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(ItemSimpleMsg record);

    int updateByPrimaryKey(ItemSimpleMsg record);
}