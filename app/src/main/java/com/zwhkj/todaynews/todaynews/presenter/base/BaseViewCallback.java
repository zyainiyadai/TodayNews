package com.zwhkj.todaynews.todaynews.presenter.base;

import java.lang.ref.WeakReference;

/**
 * 类描述：防止内存泄漏GC处理类
 * 创建人：BfJia
 * 创建时间：2016/8/4 0004 09:03
 * 修改人：BfJia
 * 修改时间：2016/8/4 0004 09:03
 * 修改备注：
 */
public  class BaseViewCallback<T> {
    WeakReference<T> paramsWeakReference = null;

    public BaseViewCallback() {
    }

    public BaseViewCallback(T t) {
        if(null == t)return;
        //防止内存泄漏
        paramsWeakReference = new WeakReference<T>(t);
    }

    /**
     * 检查是否有对象被回收
     * @return
     */
    protected boolean hasGC()
    {
        boolean isGC = false;
        if(null == paramsWeakReference)return isGC;
        return null == paramsWeakReference.get();
    }

    /**
     * 获取构造传入的参数
     * @return
     */
    protected  <T> T getParam()
    {
        if(null == paramsWeakReference)return null;
        return (T)paramsWeakReference.get();
    }

}
