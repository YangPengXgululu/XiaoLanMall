package com.xiaolan.serviceimpl;

import com.google.gson.Gson;
import com.xiaolan.bean.ItemDetailedMsg;
import com.xiaolan.bean.ItemSimpleMsg;
import com.xiaolan.dao.ItemDetailedMsgMapper;
import com.xiaolan.dao.ItemSimpleMsgMapper;
import com.xiaolan.po.Pager;
import com.xiaolan.service.ItemSer;
import com.xiaolan.staticconfig.StaticConfig;
import com.xiaolan.util.QiniuUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author: fallen
 * Date: 17-2-13
 * Time: 下午12:47
 * Usage:
 */
@Service
public class ItemSerImpl implements ItemSer {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private ItemDetailedMsgMapper itemDetailedMsgMapper;

    @Resource
    private ItemSimpleMsgMapper itemSimpleMsgMapper;

    public List<ItemSimpleMsg> getItemListInPages(Pager pager) {
        return itemSimpleMsgMapper.selectItemsInPages(pager);
    }

    public ItemDetailedMsg getItemDetailedMsg(int itemId) {
        return itemDetailedMsgMapper.selectByPrimaryKey(itemId);
    }

    public Float getItemPrice(int itemId) {
        return itemDetailedMsgMapper.selectPriceByPrimaryKey(itemId);
    }

    public int addNewItem(ArrayList<File> files, ItemDetailedMsg itemDetailedMsg) {
        Gson gson = new Gson();
        ItemSimpleMsg itemSimpleMsg = new ItemSimpleMsg();
        itemSimpleMsg.setThumbnail(null);
        itemSimpleMsg.setPrice(String.valueOf(itemDetailedMsg.getPrice()));
        itemSimpleMsg.setTitle(itemDetailedMsg.getTitle());
        int status1 = itemSimpleMsgMapper.insertSelective(itemSimpleMsg);
        ArrayList<String> HDlist = new ArrayList<String>(files.size());
        ArrayList<String> Thumblist = new ArrayList<String>(files.size());
        for (int i = 0; HDlist.size() <= files.size(); i++) {
            HDlist.add("item_" + i + ".jpg");
            Thumblist.add("item_thumb_" + i + ".jpg");
        }
        itemDetailedMsg.setItemId(itemSimpleMsg.getItemId());
        itemDetailedMsg.setCommentTable(StaticConfig.comment_table);
        itemDetailedMsg.setHdimages(gson.toJson(HDlist));
        itemDetailedMsg.setThumbnails(gson.toJson(Thumblist));
        int status2 = itemDetailedMsgMapper.insertSelective(itemDetailedMsg);

        try {
            for (int i = 0; i <= files.size(); i++) {
                File _f = new File(StaticConfig.upload_dir + HDlist.get(i));
                FileUtils.moveFile(files.get(i), _f);
                QiniuUtil.upload(_f);
                File fThumb = new File(StaticConfig.upload_dir + Thumblist.get(i));
                Thumbnails.of(_f).size(500, 500).toFile(fThumb);
                QiniuUtil.upload(fThumb);
            }
        } catch (Exception e) {
            log.info("文件上传中出现错误，原因是{}", e.getMessage());
            return 0;
        }
        return status1 + status2;
    }

    public int reduceItem(int itemId) {
        return itemDetailedMsgMapper.deleteByPrimaryKey(itemId) + itemSimpleMsgMapper.deleteByPrimaryKey(itemId);
    }

    public int updateItem(ArrayList<File> files, ItemDetailedMsg detailedMsg) {
        ItemSimpleMsg simpleMsg = new ItemSimpleMsg();
        simpleMsg.setItemId(detailedMsg.getItemId());
        simpleMsg.setTitle(detailedMsg.getTitle());
        simpleMsg.setPrice(String.valueOf(detailedMsg.getPrice()));
        try {
            for (int i = 0; i <= files.size(); i++) {
                File _f = new File(StaticConfig.upload_dir + "item_" + detailedMsg.getItemId() + ".jpg");
                FileUtils.moveFile(files.get(i), _f);
                QiniuUtil.upload(_f);
                File fThumb = new File(StaticConfig.upload_dir + "item_thumb_" + detailedMsg.getItemId() + ".jpg");
                Thumbnails.of(_f).size(500, 500).toFile(fThumb);
                QiniuUtil.upload(fThumb);
            }
        } catch (Exception e) {
            log.info("文件上传中出现错误，原因是{}", e.getMessage());
            return 0;
        }
        return itemSimpleMsgMapper.insertSelective(simpleMsg) + itemDetailedMsgMapper.insertSelective(detailedMsg);
    }
}