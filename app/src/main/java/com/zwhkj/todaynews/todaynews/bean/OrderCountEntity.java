package com.zwhkj.todaynews.todaynews.bean;

/**
 * 类描述：
 * 创建人：BfJia
 * 创建时间：2016/8/5 0005 09:title_rens
 * 修改人：BfJia
 * 修改时间：2016/8/5 0005 09:title_rens
 * 修改备注：
 */
public class OrderCountEntity {

    private String salerCount,returnCount,orderPrice;
    public String getSalerCount() {
        return salerCount;
    }

    public void setSalerCount(String salerCount) {
        this.salerCount = salerCount;
    }

    public String getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(String returnCount) {
        this.returnCount = returnCount;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }


}
