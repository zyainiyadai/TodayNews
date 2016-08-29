package com.zwhkj.todaynews.todaynews.bean;

/**
 * 类描述:上传评论图片实体类
 * 作者: 岳志远
 * 时间: 2016/3/9 11:08
 * 版本:
 */
public class ReviewImgLists {
    /**批次号*/
    private String batchNo;
    /**创建时间*/
    private String createTime;
    /**禁用*/
    private String disabled;
    /**实体集*/
    private String entityClass;
    /**文件类型*/
    private String filetype;
    /**图片ID*/
    private String imageId;
    /**图片的名字*/
    private String imageName;
    /**图片路径*/
    private String imagePath;
    /**图像类型*/
    private String imageType;
    /**国防部的时间*/
    private String modTime;


    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getModTime() {
        return modTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }
}
