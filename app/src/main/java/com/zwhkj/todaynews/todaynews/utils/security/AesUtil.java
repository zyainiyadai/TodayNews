package com.zwhkj.todaynews.todaynews.utils.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Administrator on 2016/8/19.
 */
public class AesUtil {
    private static String ivParameter = "0392039203920300";//偏移量,可自行修改

    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static byte[] encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = password.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] result = cipher.doFinal(content.getBytes("utf-8"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            byte[] raw = password.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {

//            String content = "我的我的我221233333333333333333333333333333333333333333333333333333333333";
//            String password = UUIDGenerator.generate();
////加密
//            System.out.println("加密前：" + content);
//            byte[] encryptResult = encrypt(content, password);
//            String encryptResultStr = parseByte2HexStr(encryptResult);
//
//            System.out.println("加密后：" + encryptResultStr);
////解密
//            byte[] decryptFrom = parseHexStr2Byte(encryptResultStr);
//            byte[] decryptResult = decrypt(decryptFrom,password);
//            System.out.println("解密后：" + new String(decryptResult));

//                String key = "123456781";
//        String data = "我的我的我";
////
////        System.out.println("加密解密 测试 ");
//        String str1 = aesEncrypt(data, key);// 加密
//        String str2 = aesDecrypt(data, key);// 解密
//        System.out.println(str1);
//        System.out.println(str2);

        }
}
