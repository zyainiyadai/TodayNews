package com.zwhkj.todaynews.todaynews.presenter.base;

import android.content.Context;

import com.example.administrator.chinalife.utils.AppPreferenceImplUtil;
import com.example.administrator.chinalife.utils.ConstantValues;
import com.example.administrator.chinalife.utils.security.Md5Util;
import com.example.administrator.chinalife.utils.security.ParameterUtil;
import com.example.administrator.chinalife.utils.security.ZipAESUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;

import java.util.Map;

/**
 * 类描述：接口请求入口
 * 创建人：BfJia
 * 创建时间：2016/8/3 0003 14:35
 * 修改人：BfJia
 * 修改时间：2016/8/19 0003 14:35
 * 修改备注：
 */
public class RequestPresenter<T> extends BasePresenter<T> {

    public RequestPresenter() {
        super();
    }

    public RequestPresenter(Context context, T mvpView) {
        super(context, mvpView);
    }

    public RequestPresenter(T mvpView) {
        super(mvpView);
    }

    /**
     * 请求接口
     * @param params   请求参数
     * @param callback 请求接口回调
     */
    protected void request(Map<String, String> params, AbsCallback callback) {
        try {
            //参数配置,数据MD5，每次请求需要带有token
            String encryption = ParameterUtil.toString(params);
            String sign = Md5Util.encrypt(encryption + AppPreferenceImplUtil.getToken(), "UTF-8");
            encryption += "&sign=" + sign;
            //数据加密，压缩
            encryption = ZipAESUtils.aesGzip(encryption);
            //发起请求
            OkHttpUtils.post(ConstantValues.getUrl())//
                    .headers("Cookie", "JSESSIONID=" + AppPreferenceImplUtil.getSessionId()) //sessionID
                    .params("s", encryption)
                    .execute(callback);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
