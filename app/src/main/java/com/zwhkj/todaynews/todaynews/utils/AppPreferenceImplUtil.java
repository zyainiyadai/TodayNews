package com.zwhkj.todaynews.todaynews.utils;

import com.example.administrator.chinalife.application.App;
import com.example.administrator.chinalife.utils.security.Base64Util;

import java.io.UnsupportedEncodingException;

/**
 * 类描述：保存配置信息
 * 创建人：BfJia
 * 创建时间：2016/8/11 0011 16:57
 * 修改人：BfJia
 * 修改时间：2016/8/11 0011 16:57
 * 修改备注：
 */
public class  AppPreferenceImplUtil extends AppPreferenceUtil{

    private static final String KEY_TOKEN = "token";

    private static final String KEY_SESSSIONID = "sesssionid";

    private static final String KEY_SALERID = "salerId";

    private static final String KEY_START_TIME = "prodect_start_time";



    public static void saveToken(String token)
    {
        setString(App.context,KEY_TOKEN, Base64Util.encode(token.getBytes()));
    }

    public static String getToken()
    {
        try {
            return new String(Base64Util.decode(getString(App.context,KEY_TOKEN,"")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static void saveSessionId(String sessionId)
    {
        setString(App.context,KEY_SESSSIONID,Base64Util.encode(sessionId.getBytes()));
    }

    public static String getSessionId()
    {
        try {
            return new String(Base64Util.decode(getString(App.context,KEY_SESSSIONID,"")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void saveSalerId(String salerId)
    {
        setString(App.context,KEY_SALERID,salerId);
    }

    public static String getSalerId()
    {
        return getString(App.context,KEY_SALERID,"");
    }

    public static void saveValidateTime(String date)
    {
        setString(App.context,KEY_START_TIME,date);
    }

    public static String getValidateTime()
    {
        return getString(App.context,KEY_START_TIME,"");
    }

}
