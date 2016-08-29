package com.zwhkj.todaynews.todaynews.presenter.base;

import com.lzy.okhttputils.callback.StringCallback;

import java.lang.ref.WeakReference;

/**
 * 类描述：防止内存泄漏GC处理类
 * 创建人：BfJia
 * 创建时间：2016/8/4 0004 09:03
 * 修改人：BfJia
 * 修改时间：2016/8/4 0004 09:03
 * 修改备注：
 */
public  abstract class BaseCallback extends StringCallback {
    WeakReference[] paramsWeakReference = null;

    public BaseCallback() {
        super();
    }

    public BaseCallback(Object ...params) {
        if(null == params)return;
        //防止内存泄漏
        int len = params.length;
        paramsWeakReference = new WeakReference[len];
        for(int i = 0; i < len; i++){
            if(null != params[i]) paramsWeakReference[i] = new WeakReference(params[i]);
        }
    }

    /**
     * 检查是否有对象被回收
     * @return
     */
    protected boolean hasGC()
    {
        boolean isGC = false;
        if(null == paramsWeakReference)return isGC;
        int len = paramsWeakReference.length;
        for(int i = 0; i < len; i++){
            WeakReference weakReferences = paramsWeakReference[i];
            if(null == weakReferences || null == weakReferences.get())
            {
                isGC = true;
                break;
            }
        }
        return isGC;
    }

    /**
     * 获取构造传入的参数
     * @param index 传入参数的顺序
     * @return
     */
    public <T> T getParams(int index)
    {
        if(null == paramsWeakReference)return null;
        if(paramsWeakReference.length<=index)return null;
        WeakReference weakReferences = paramsWeakReference[index];
        if(null == weakReferences)return null;
        Object object = weakReferences.get();
        return null != object?(T)object:null;
    }

}
