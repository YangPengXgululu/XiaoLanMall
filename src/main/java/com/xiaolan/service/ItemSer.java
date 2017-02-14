package com.xiaolan.service;


import com.xiaolan.bean.ItemDetailedMsg;
import com.xiaolan.bean.ItemSimpleMsg;
import com.xiaolan.po.Pager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午12:47
 * Usage:
 */
public interface ItemSer {

    List<ItemSimpleMsg> getItemListInPages(Pager pager);

    ItemDetailedMsg getItemDetailedMsg(int itemId);

    Float getItemPrice(int itemId);

    int addNewItem(ArrayList<File> files, ItemDetailedMsg detailedMsg);

    int reduceItem(int itemId);

    int updateItem(ArrayList<File> files, ItemDetailedMsg detailedMsg);

}