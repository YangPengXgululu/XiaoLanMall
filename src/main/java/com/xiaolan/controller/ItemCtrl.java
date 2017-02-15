package com.xiaolan.controller;

import com.xiaolan.bean.ItemDetailedMsg;
import com.xiaolan.bean.ItemSimpleMsg;
import com.xiaolan.po.Pager;
import com.xiaolan.service.AdminSer;
import com.xiaolan.service.ItemSer;
import com.xiaolan.staticconfig.StaticConfig;
import com.xiaolan.util.JedisUtil;
import com.xiaolan.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午12:46
 * Usage:
 */
@Controller
@RequestMapping("/item")
public class ItemCtrl {

    @Resource
    private ItemSer itemSerImpl;
    @Resource
    private AdminSer adminSerImpl;

    @RequestMapping("/itemList")
    @ResponseBody
    public Result getItemList(Pager pager, String token) {
        Result result = new Result();
        if (token == null || JedisUtil.getValue(token) == null) {
            result.setRes(0);
            result.setMsg("token无效，请重新登录");
            return result;
        }
        JedisUtil.reborn(token, StaticConfig.user_alive_time);
        if (pager.getCurPage() == null) {
            pager.setCurPage(1);
        }
        List<ItemSimpleMsg> itemList = itemSerImpl.getItemListInPages(pager);
        if (itemList.size() > 0) {
            result.setRes(1);
            result.setMsg("查询成功");
            result.setData(itemList);
        } else {
            result.setRes(0);
            result.setMsg("查询失败");
        }
        return result;
    }

    @RequestMapping("/detail")
    @ResponseBody
    public Result getItemDetail(Integer itemId, String token) {
        JedisUtil.reborn(token, StaticConfig.user_alive_time);
        Result result = new Result();
        if (itemId == null) {
            result.setRes(0);
            result.setMsg("查询失败");
            return result;
        }
        ItemDetailedMsg itemDetailedMsg = itemSerImpl.getItemDetailedMsg(itemId);
        result.setRes(1);
        result.setMsg("查询成功");
        result.setData(itemDetailedMsg);
        return result;
    }

    @RequestMapping("/price")
    @ResponseBody
    public Result getItemsPrice(int itemId, String token) {
        JedisUtil.reborn(token, StaticConfig.user_alive_time);
        Result result = new Result();
        Float price = itemSerImpl.getItemPrice(itemId);
        if (price == null || price <= 0) {
            result.setRes(0);
            result.setMsg("查询异常");
            return result;
        }
        result.setRes(1);
        result.setMsg("查询成功");
        result.setData(price);
        return result;
    }

    @RequestMapping("/new")
    @ResponseBody
    public Result createNewItem(@RequestParam(value = "file") CommonsMultipartFile[] upFiles, String token, ItemDetailedMsg detailedMsg) {
        Result result = new Result();
        String _id = JedisUtil.getValue(token);
        if (!adminSerImpl.isAdmin(_id)) {
            result.setRes(0);
            result.setMsg("操作失败");
            return result;
        }
        ArrayList<File> list = new ArrayList<File>(upFiles.length);
        if (upFiles.length > 0) {
            try {
                for (CommonsMultipartFile file : upFiles) {
                    File f = new File(StaticConfig.upload_dir + file.getOriginalFilename());
                    file.transferTo(f);
                    list.add(f);
                }
            } catch (Exception e) {
                result.setRes(0);
                result.setMsg("操作失败");
                return result;
            }
        }
        int status = itemSerImpl.addNewItem(list, detailedMsg);
        if (status == 2) {
            result.setRes(1);
            result.setMsg("操作成功");
        } else {
            result.setRes(0);
            result.setMsg("操作失败");
        }
        return result;
    }

    @RequestMapping("/reduce")
    @ResponseBody
    public Result removeItem(String token, int itemId) {
        Result result = new Result();
        String _id = JedisUtil.getValue(token);
        if (!adminSerImpl.isAdmin(_id)) {
            result.setRes(0);
            result.setMsg("操作失败");
            return result;
        }
        int status = itemSerImpl.reduceItem(itemId);
        if (status == 2) {
            result.setRes(1);
            result.setMsg("操作成功");
        } else {
            result.setRes(0);
            result.setMsg("操作失败");
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result updateItem(@RequestParam(value = "file") CommonsMultipartFile[] upFiles, String token, ItemDetailedMsg detailedMsg) {
        Result result = new Result();
        String _id = JedisUtil.getValue(token);
        if (!adminSerImpl.isAdmin(_id)) {
            result.setRes(0);
            result.setMsg("操作失败");
            return result;
        }
        ArrayList<File> list = new ArrayList<File>(upFiles.length);
        if (upFiles.length > 0) {
            try {
                for (CommonsMultipartFile file : upFiles) {
                    File f = new File(StaticConfig.upload_dir + file.getOriginalFilename());
                    file.transferTo(f);
                    list.add(f);
                }
            } catch (Exception e) {
                result.setRes(0);
                result.setMsg("操作失败");
                return result;
            }
        }
        int status = itemSerImpl.updateItem(list, detailedMsg);
        if (status == 2) {
            result.setRes(1);
            result.setMsg("操作成功");
        } else {
            result.setRes(0);
            result.setMsg("操作失败");
        }
        return result;
    }
}