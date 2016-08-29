package com.zwhkj.todaynews.todaynews.utils.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;


public class Md5Util {
    /**
     * Used building output as Hex
     */
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 对字符串进行MD5加密
     *
     * @param text    明文
     * @param charSet 字符编码
     * @return 密文
     */
    public static String encrypt(String text, String charSet) {
        MessageDigest msgDigest = null;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "System doesn't support MD5 algorithm.");
        }
        // charSet为null时，不重新编码
        if (charSet != null) {
            try {
                msgDigest.update(text.getBytes(charSet)); // 注意该接口是按照utf-8编码形式加密
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(
                        "System doesn't support your  EncodingException.");
            }
        }

        byte[] bytes = msgDigest.digest();
        return new String(encodeHex(bytes));
    }

    /**
     * 对字符串进行MD5加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String encrypt(String text) {
        return encrypt(text, null);
    }

    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }

    public static void main(String arg[]) {

        //
        String tmp = encrypt("hello", "UTF-8");
        System.out.println(tmp);

        //
        String s = new String("hello");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + md32(s));
        System.out.println("MD5后再加密：" + reversibleEncry(md32(s)));
        String string = reversibleEncry(md32(s));
        System.out.println("解密为MD5后的：" + reversibleEncry(string));

        for (int i = 1; i < 10; i++) {
            String str = reversibleEncry(String.valueOf(i));
            System.out.println(str);

            System.out.println(reversibleEncry(str));
        }
    }

    /**
     * 32 位标准 MD5 加密
     *
     * @param plainText
     * @return String
     * @throws
     * @Title: md32
     */
    public static String md32(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();

        } catch (NoSuchAlgorithmException e) {
            // CheckedExceptionHandler.handleException(e);
        }
        return null;
    }

    /**
     * 16 位标准 MD5 加密
     *
     * @param plainText
     * @return String
     * @throws
     * @Title: md16
     */
    public static String md16(String plainText) {
        String result = md32(plainText);
        if (result == null)
            return null;
        return result.toString().substring(8, 24);// 16位的加密
    }

    /**
     * 可逆的加密算法
     *
     * @param inStr
     * @return String
     * @throws
     * @Title: reversibleEncry
     */
    public static String reversibleEncry(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 'b');
        }
        String s = new String(a);
        return s;
    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }
}
