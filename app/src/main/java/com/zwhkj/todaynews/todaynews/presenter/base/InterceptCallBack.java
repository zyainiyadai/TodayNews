package com.zwhkj.todaynews.todaynews.presenter.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.administrator.chinalife.activity.LoginActivity;
import com.example.administrator.chinalife.model.BaseResponseEntity;
import com.example.administrator.chinalife.utils.AppPreferenceImplUtil;
import com.example.administrator.chinalife.utils.JsonTools;
import com.example.administrator.chinalife.utils.LogUtil;
import com.example.administrator.chinalife.utils.security.ZipAESUtils;
import com.example.administrator.chinalife.widget.ToastView;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类描述：对相应信息进行拦截
 *         1、获取token信息
 *         2、数据解密
 * 创建人：BfJia
 * 创建时间：2016/8/11 0011 16:16
 * 修改人：BfJia
 * 修改时间：2016/8/11 0011 16:16
 * 修改备注：
 */
public  abstract class InterceptCallBack extends DialogCallBack{


    public InterceptCallBack(Context context) {
        super(context);
    }
    public InterceptCallBack(Context context,BasePresenter params) {
        super(context, params);
    }


    @Override
    public String parseNetworkResponse(Response response) throws Exception {
        String message = super.parseNetworkResponse(response);
        if(!TextUtils.isEmpty(message))
        {
            message = ZipAESUtils.aesGunzip(message);
        }
        return message;
    }

    /**
     * 获取构造传入的参数
     * @return
     */
    protected  Context getContext()
    {
        return getParams(0);
    }

    protected   <T extends BasePresenter> T getPresenter()
    {
        return getParams(1);
    }

    private boolean gc()
    {
        BasePresenter basePresenter = getPresenter();
        return null != basePresenter && !basePresenter.checkViewAttached() || hasGC();
    }

    @Override
    public void onResponse(boolean b, String data, Request request, @Nullable Response response) {
        super.onResponse(b,data, request, response);
        if(gc())return;
        LogUtil.i("===============================data: "+data);
        //token解析,并保存
        if(!TextUtils.isEmpty(data))
        {
            BaseResponseEntity baseResponseEntity = JsonTools.getBean(data,BaseResponseEntity.class);
            if(null != baseResponseEntity )
            {
                Context context = getContext();
                //出现错误，重新登录，主要是防止session失效，现在没有返回session失效的错误code
                if(baseResponseEntity.hasRLogin() && null != context)
                {
                    //清空token
                    AppPreferenceImplUtil.saveToken("");
                    AppPreferenceImplUtil.saveSessionId("");
                    AppPreferenceImplUtil.saveSalerId("");
                    ToastView.show(context,baseResponseEntity.getMsg());
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    return;
                }
                //保存token
                if(!TextUtils.isEmpty(baseResponseEntity.getToken()))
                {
                    AppPreferenceImplUtil.saveToken(baseResponseEntity.getToken());
                }
            }
        }
        response(b,data,request,response);
    }

    @Override
    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
        super.onError(isFromCache, call, response, e);
        if(gc())return;
        error(isFromCache,call,response,e);
    }

    /**
     * 请求响应
     * @param data 数据对象
     * @param request
     * @param response
     */
    public abstract void response(boolean b, String data, Request request, @Nullable Response response);

    /**
     * 请求出现错误
     * @param isFromCache
     */
    public abstract void error(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e);

}
