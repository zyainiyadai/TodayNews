package com.zwhkj.todaynews.todaynews.bean;

/**
 * 类描述：分页参数对象
 * 创建人：BfJia
 * 创建时间：2016/8/4 0004 11:44
 * 修改人：BfJia
 * 修改时间：2016/8/4 0004 11:44
 * 修改备注：
 */
public class PageSizeEntity {
    public static final int PAGE_SIZE = 20;
    //分页每页请求的条数
    private int pageSize = PAGE_SIZE;
    //当前页
    private int currPage = 1;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void nextPage()
    {
        currPage+=1;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void resetNextPage()
    {
        currPage =1;
    }

    /**
     * 加载更多加载成功以后，pageSize保持不变，否则，恢复上一页的页码
     * @param size
     */
    public void loadMore(int size)
    {
        if(currPage>1)
        {
            if(size==0)currPage-=1;
        }
    }


    public boolean hasLoadMore(int size)
    {
        return size>=pageSize;
    }
}
