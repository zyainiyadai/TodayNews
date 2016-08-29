package com.zwhkj.todaynews.todaynews.bean;

import com.rtkj.junhetai.chinalife.model.ProductOrder;

import java.util.List;

/**
 * 类描述：
 * 创建人：BfJia
 * 创建时间：2016/8/3 0003 12:48
 * 修改人：BfJia
 * 修改时间：2016/8/3 0003 12:48
 * 修改备注：
 */
public class OrderListEntity extends BaseResponseEntity{
    public OrderItemEntity getItem() {
        return item;
    }

    public void setItem(OrderItemEntity item) {
        this.item = item;
    }

    private OrderItemEntity item;

    public class OrderItemEntity
    {
        public List<ProductOrder> getRows() {
            return Rows;
        }

        public void setRows(List<ProductOrder> rows) {
            Rows = rows;
        }

        private List<ProductOrder> Rows;

    }


}
