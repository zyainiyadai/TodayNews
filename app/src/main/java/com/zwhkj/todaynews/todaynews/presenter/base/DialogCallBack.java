package com.zwhkj.todaynews.todaynews.presenter.base;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.administrator.chinalife.widget.ProgressDialog;
import com.lzy.okhttputils.request.BaseRequest;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 类描述：请求添加Dialog提示
 * 创建人：BfJia
 * 创建时间：2016/8/5 0005 12:57
 * 修改人：BfJia
 * 修改时间：2016/8/5 0005 12:57
 * 修改备注：
 */
public abstract class DialogCallBack extends  BaseCallback{
    private ProgressDialog progressDialog;
    private boolean isShowDialog = true;

    public DialogCallBack(Context context) {
      super(context);
        initDialog(context);
    }
    public DialogCallBack(Context context,Object params) {
        super(context,params);
        initDialog(context);
    }


    private void initDialog(Context context)
    {
        progressDialog = new ProgressDialog(context);
    }


    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        if(null != progressDialog && !progressDialog.isShowing() && isShowDialog)
        {
            try {
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onResponse(boolean b, String s, Request request, @Nullable Response response) {
        closeDialog();
    }

    @Override
    public void onAfter(boolean isFromCache, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
        super.onAfter(isFromCache, s, call, response, e);
        closeDialog();
    }

    private void closeDialog()
    {
        if(null != progressDialog && progressDialog.isShowing())
        {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setShowDialog(boolean showDialog) {
        isShowDialog = showDialog;
    }

}
