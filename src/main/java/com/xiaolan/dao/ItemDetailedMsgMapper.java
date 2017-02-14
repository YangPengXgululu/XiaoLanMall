package com.xiaolan.dao;

import com.xiaolan.bean.ItemDetailedMsg;

public interface ItemDetailedMsgMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(ItemDetailedMsg record);

    int insertSelective(ItemDetailedMsg record);

    ItemDetailedMsg selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(ItemDetailedMsg record);

    int updateByPrimaryKey(ItemDetailedMsg record);

    Float selectPriceByPrimaryKey(Integer itemId);
}