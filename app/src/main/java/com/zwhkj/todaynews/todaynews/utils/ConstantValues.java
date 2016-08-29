package com.zwhkj.todaynews.todaynews.utils;

/**
 * @author 李全鑫
 * @time 2016/1/21 15:25
 */
public class ConstantValues {
    public static boolean HAS_RELEASE = false;  //true发布的版本，false 测试版本
    public static String URL_DEBUG = "https://test.rtkj.com.cn:443/zito-gyrs-web-interface_war/gateWay";
    public static String URL_RELEASE = "https://test.rtkj.com.cn:443/zito-gyrs-web-interface_war/gateWay"; //外网

    public static String PRODUCT_ID = "P120000000705";

    /**
     * rsa加密公共key名称
     */
    public static final String PUBLIC_KEY_HTTPS = "user_test_rtkj_com_cn.crt";

    /**
     * 请求地址
     *
     * @return
     */
    public static String getUrl() {
        return HAS_RELEASE ? URL_RELEASE : URL_DEBUG;
    }

}
