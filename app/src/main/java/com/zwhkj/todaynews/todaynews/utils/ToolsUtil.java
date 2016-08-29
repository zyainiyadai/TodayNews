package com.zwhkj.todaynews.todaynews.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.weipass.pos.sdk.IPrint;
import cn.weipass.pos.sdk.IPrint.Gravity;
import cn.weipass.pos.sdk.LatticePrinter;
import cn.weipass.pos.sdk.LatticePrinter.FontFamily;
import cn.weipass.pos.sdk.LatticePrinter.FontSize;
import cn.weipass.pos.sdk.LatticePrinter.FontStyle;

public class ToolsUtil {
    /**
     * font_size:字体大小枚举值 SMALL:16x16大小; MEDIUM:24x24大小; LARGE:32x32大小;
     * EXTRALARGE:48x48 一行的宽度为384
     * (当宽度大小为16时可打印384/16=24个字符;为24时可打印384/24=16个字符;为32时可
     * 打印384/32=12个字符;为48时可打印384/48=8个字符（一个汉字占1个字符，一个字母 、空格或者数字占半字符）
     * <p/>
     * 标准打印示例
     *
     * @param context
     * @param printer
     */
    public static final int rowSize = 384;
    // public static final int smallSize = (int) (384/16d);
    // public static final int mediumSize = (int) (384/24d);
    // public static final int largeSize = (int) (384/32d);
    // public static final int extralargeSize = (int) (384/48d);
    public static final int smallSize = 24 * 2;
    public static final int mediumSize = 16 * 2;
    public static final int largeSize = 12 * 2;
    public static final int extralargeSize = 8 * 2;

    public static String getPrintErrorInfo(int what, String info) {
        String message = "";
        switch (what) {
            case IPrint.EVENT_CONNECT_FAILD:
                message = "连接打印机失败";
                break;
            case IPrint.EVENT_CONNECTED:
                // Log.e("subscribe_msg", "连接打印机成功");
                break;
            case IPrint.EVENT_PAPER_JAM:
                message = "打印机卡纸";
                break;
            case IPrint.EVENT_UNKNOW:
                message = "打印机未知错误";
                break;
            case IPrint.EVENT_OK:
                // 回调函数中不能做UI操作，所以可以使用runOnUiThread函数来包装一下代码块
                // Log.e("subscribe_msg", "打印机正常");
                break;
            case IPrint.EVENT_NO_PAPER:
                message = "打印机缺纸";
                break;
            case IPrint.EVENT_HIGH_TEMP:
                message = "打印机高温";
                break;
        }

        return message;
    }

    /**
     * 获取md5加密信息
     *
     * @param s
     * @return
     */
    public static String getStringMD5(String s) {
        // char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
        // '9',
        // 'A', 'B', 'C', 'D', 'E', 'F' };
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * \n 代表换行 点阵打印示例
     *
     * @param context
     * @param latticePrinter
     */
    public static void printLattice(Context context,
                                    LatticePrinter latticePrinter, int id, int isBuda, String orderNo, String Name, String idCard, String insuranceIn, String totalfee, String ticketUser, String ticketPlace, String company) {
        String mediumSpline = "";
        for (int i = 0; i < smallSize; i++) {
            mediumSpline += "-";
        }
        // 打印logo图片,图片大小需要自己控制byte[]大小（总宽度一样是384），而且打印只能识别黑色图片
        Drawable logo = context.getResources().getDrawable(
                id);
        latticePrinter.printImage(bitmap2Bytes(drawableToBitmap(logo)),
                Gravity.CENTER);

        //备注，点阵打印FontSize.EXTRALARGE字体不支持
        String title = " 国寿乘客意外险电子保单查询凭证";
        int sizeTitle = largeSize - ToolsUtil.length(title);
        // 文字居中需要在前面补足相应空格，后面可以用换行符换行
        String titleStr = getBlankBySize((int) (sizeTitle / 2d)) + title;
        latticePrinter.printText(titleStr + "\n", FontFamily.SONG,
                FontSize.MEDIUM, FontStyle.BOLD);

        if (isBuda == 1) {
            String playweihang = " 补打";
            int sizePlay = largeSize - ToolsUtil.length(playweihang);
            String playSize = getBlankBySize(sizePlay - 7) + playweihang;
            latticePrinter.printText(playSize + "\n", FontFamily.SONG, FontSize.MEDIUM,
                    FontStyle.BOLD);
        }


//		String webUrl = "www.dianping.com";
//		int sizeWebUrl = mediumSize - ToolsUtil.length(webUrl);
//		String webUrlStr = getBlankBySize((int) (sizeWebUrl / 2d)) + webUrl;
//		latticePrinter.printText(webUrlStr + "\n", FontFamily.SONG,
//				FontSize.MEDIUM, FontStyle.BOLD);
        int feedCount = 1;
        //进纸数，1代表进一行的高度
        latticePrinter.feed(feedCount);
        // 打印分割线
//		latticePrinter.printText(mediumSpline + "\n", FontFamily.SONG,
//				FontSize.MEDIUM, FontStyle.BOLD);
//		latticePrinter.feed(feedCount);
//		String orderno = "订单号:2016071910211264646565";
//		int sizeResult = largeSize - ToolsUtil.length(orderno);
//		String resultStr = getBlankBySize((int) (sizeResult / 2d)) + orderno;
//		latticePrinter.printText(resultStr + "\n", FontFamily.SONG,
//				FontSize.MEDIUM, FontStyle.BOLD);
//		latticePrinter.feed(feedCount);

        String orderno = "订单号:" + orderNo;
        latticePrinter.printText(orderno + "\n", FontFamily.SONG, FontSize.SMALL,
                FontStyle.BOLD);

        String name = "投/被保险人姓名:" + Name;
        latticePrinter.printText(name + "\n", FontFamily.SONG, FontSize.SMALL,
                FontStyle.BOLD);

        String price = "投/被保险人证件号:" + idCard;
        latticePrinter.printText(price + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        String marchent = "保险期间:" + insuranceIn;
        latticePrinter.printText(marchent + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        String time = "保险费:" + totalfee + "元";
        latticePrinter.printText(time + "\n", FontFamily.SONG, FontSize.SMALL,
                FontStyle.BOLD);

        latticePrinter.feed(feedCount);
//		// 打印分割线
//		latticePrinter.printText(mediumSpline + "\n", FontFamily.SONG,
//				FontSize.MEDIUM, FontStyle.BOLD);
//		latticePrinter.feed(feedCount);

        String count = "保险责任:";
        latticePrinter.printText(count + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        String content = "搭乘火车意外伤害事故/伤残保险金额:50000元";
        latticePrinter.printText(content + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        String contenttaix = "搭乘汽车意外伤害事故/伤残保险金额:30000元";
        latticePrinter.printText(contenttaix + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        String contentmove = "搭乘火车意外医疗保险金额:20000元";
        latticePrinter.printText(contentmove + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        String contentCar = "搭乘汽车意外伤害医疗保险金额:5000元";
        latticePrinter.printText(contentCar + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        String special = "特别约定:";
        latticePrinter.printText(special + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);
        String specialone = "1、意外伤害医疗费用为符合社保规定的合理医疗费用,无免赔，100%赔付。";
        latticePrinter.printText(specialone + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        String specialtwo = "2、医疗机构：二级以上（含二级）公立医院或保险公司认可的其他医疗机构。";
        latticePrinter.printText(specialtwo + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);

        // 打印分割线
        latticePrinter.printText(mediumSpline + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);
        latticePrinter.feed(feedCount);

        String messageUrl = "如需保单，可至http://eshop.e-chinalife.com/ASLSS_QUERY/查询、下载并打印电子保单";
        latticePrinter.printText(messageUrl + "\n", FontFamily.SONG, FontSize.SMALL, FontStyle.BOLD);

        // 打印分割线
        latticePrinter.printText(mediumSpline + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);
        latticePrinter.feed(feedCount);

        String explain = "本产品适用条款为中国人寿保险股份有限公司《国寿通泰交通意外伤害保险(B款) （2013版）利益条款》、《国寿附加通泰交通意外费用补偿医疗保险利益条款》和《中国人寿保险股份有限公司短期保险基本条款》。";
        latticePrinter.printText(explain + "\n", FontFamily.SONG, FontSize.SMALL, FontStyle.BOLD);

        // 打印分割线
        latticePrinter.printText(mediumSpline + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);
        latticePrinter.feed(feedCount);

        String ticketView = "出票员:" + ticketUser;
        latticePrinter.printText(ticketView + "\n", FontFamily.SONG, FontSize.SMALL, FontStyle.BOLD);

        String ticketPlaces = "出票点:" + ticketPlace;
        latticePrinter.printText(ticketPlaces + "\n", FontFamily.SONG, FontSize.SMALL, FontStyle.BOLD);

        Date mDate = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(mDate);
        latticePrinter.printText("打印时间:" + createTime + "\n", FontFamily.SONG, FontSize.SMALL, FontStyle.BOLD);

        String place = company;
        latticePrinter.printText(place + "\n", FontFamily.SONG, FontSize.SMALL, FontStyle.BOLD);

        // 打印分割线
        latticePrinter.printText(mediumSpline + "\n", FontFamily.SONG,
                FontSize.SMALL, FontStyle.BOLD);
        latticePrinter.feed(feedCount);

        // 绘制序列号
//		String header = "序列号：";
//		String headerBlank = getBlankBySize(ToolsUtil.length(header));
//
//		ArrayList<String> dataList = new ArrayList<String>();
//		dataList.add("1234 4567 4565");
//		dataList.add("2345 3454 4546");
//		dataList.add("2344 2343 3545");
//		dataList.add("3445 2345 4576");
//		dataList.add("5353 2343 7552");
//		StringBuffer sb = new StringBuffer();
//		int size = dataList.size();
//		for (int i = 0; i < size; i++) {
//			String item = dataList.get(i);
//			if (i == 0) {
//				sb.append(header + item + "\n");
//			} else {
//				//分行，预留空白内容
//				sb.append(headerBlank + item + "\n");
//			}
//		}
//		latticePrinter.printText(sb.toString(), FontFamily.SONG,
//				FontSize.MEDIUM, FontStyle.BOLD);
        //最后进纸5行,方便撕纸
        latticePrinter.feed(5);
        // 真正提交打印事件
        latticePrinter.submitPrint();
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null || str.trim().equals("")
                || str.trim().equalsIgnoreCase("null")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     * <p/>
     * s 需要得到长度的字符串
     *
     * @return int 得到的字符串长度
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
     * <p/>
     * s 需要得到长度的字符串
     *
     * @return int 得到的字符串长度
     */
    public static double getLength(String s) {
        if (s == null) {
            return 0;
        }
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        // 进位取整
        return Math.ceil(valueLength);
    }

    public static String getBlankBySize(int size) {
        String resultStr = "";
        for (int i = 0; i < size; i++) {
            resultStr += " ";
        }
        return resultStr;
    }

    // 将Drawable转化为Bitmap
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    // Bitmap → byte[]
    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
