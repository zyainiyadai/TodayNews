package com.zwhkj.todaynews.todaynews.application;

import android.app.Application;
import android.content.Context;

import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.zwhkj.todaynews.todaynews.utils.ConstantValues;

/**
 * 项目名称：TodayNews
 * 类描述：
 * 创建人：周跃
 * 创建时间：2016/8/29 13:43
 * 修改人：周跃
 * 修改时间：2016/8/29 13:43
 * 修改备注：
 */
public class TodayNewsApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()
                .debug("LogUtil"); //https证书
        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("MyTodayNews")
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
                .configShowBorders(false)
                .configLevel(LogLevel.TYPE_VERBOSE);
    }
}
