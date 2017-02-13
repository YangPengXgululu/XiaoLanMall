package com.xiaolan.bean;

public class ItemDetailedMsg {
    private Integer itemId;

    private String itemInfo;

    private String thumbnails;

    private String hdimages;

    private Integer stock;

    private String commentTable;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo == null ? null : itemInfo.trim();
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails == null ? null : thumbnails.trim();
    }

    public String getHdimages() {
        return hdimages;
    }

    public void setHdimages(String hdimages) {
        this.hdimages = hdimages == null ? null : hdimages.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCommentTable() {
        return commentTable;
    }

    public void setCommentTable(String commentTable) {
        this.commentTable = commentTable == null ? null : commentTable.trim();
    }
}