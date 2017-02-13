package com.xiaolan.bean;

import java.util.Date;

public class OrderReceipt {
    private Integer orderId;

    private String transactionId;

    private String openId;

    private String returnCode;

    private String returnMsg;

    private String resultCode;

    private String errCode;

    private String errMsg;

    private String isSubscribe;

    private Float orderFee;

    private String attach;

    private Date endTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode == null ? null : returnCode.trim();
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg == null ? null : returnMsg.trim();
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode == null ? null : resultCode.trim();
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode == null ? null : errCode.trim();
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }

    public String getIsSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe == null ? null : isSubscribe.trim();
    }

    public Float getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Float orderFee) {
        this.orderFee = orderFee;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach == null ? null : attach.trim();
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}