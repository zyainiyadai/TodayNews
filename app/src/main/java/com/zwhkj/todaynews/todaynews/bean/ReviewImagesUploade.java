package com.zwhkj.todaynews.todaynews.bean;

import java.util.List;

/**
 * 类描述:上传评论图片实体类
 * 作者: 岳志远
 * 时间: 2016/3/9 11:05
 * 版本:
 */
public class ReviewImagesUploade {
    /***/
    private  String succ;
    /**批次号*/
    private  String batchNo;
    /**ReviewImgList*/
    private  List<ReviewImgList> datalist;
    /**信息*/
    private  String msg;


    public String getSucc() {
        return succ;
    }

    public void setSucc(String succ) {
        this.succ = succ;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public List<ReviewImgList> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<ReviewImgList> datalist) {
        this.datalist = datalist;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
