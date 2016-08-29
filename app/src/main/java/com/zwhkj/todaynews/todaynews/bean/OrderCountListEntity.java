package com.zwhkj.todaynews.todaynews.bean;

import java.util.List;

/**
 * 类描述：
 * 创建人：BfJia
 * 创建时间：2016/8/5 0005 09:18
 * 修改人：BfJia
 * 修改时间：2016/8/5 0005 09:18
 * 修改备注：
 */
public class OrderCountListEntity extends BaseResponseEntity{
    private List<OrderCountEntity> item;

    public List<OrderCountEntity> getItem() {
        return item;
    }

    public void setItem(List<OrderCountEntity> item) {
        this.item = item;
    }

}
