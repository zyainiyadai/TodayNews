package com.zwhkj.todaynews.todaynews.utils.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 *
 * @Description :  字符串压缩工具类
 * @author pengchengyang
 * @version 1.0
 * @created Apr 19, 2013 4:27:18 PM
 * @fileName com.doorto.common.util.ZipUtils.java
 *
 */
public class ZipAESUtils {

	/**
	 * 使用gzip进行压缩
	 */
	public static String aesGzip(String primStr) {
		if (primStr == null || primStr.length() == 0 || primStr.length() < 25) {
			return primStr;
		}
		//生成key
		String key = StringUtil.genRandomKeyNum();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip = null;
		try {
			//先加密
			byte[] encryptParam = AesUtil.encrypt(primStr,key);

			primStr = AesUtil.parseByte2HexStr(encryptParam);
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//获取加密后的数据
		String reStringParam = Base64Util.encode(out.toByteArray());

		//将key值放进去
		reStringParam = StringUtil.setKeyParam(reStringParam,key,15);

		return reStringParam;
	}

	/**
	 *
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 *
	 * @param compressedStr
	 * @return
	 */
	public static String aesGunzip(String compressedStr) {
		if (compressedStr == null || compressedStr.length() < 25) {
			return null;
		}

		//获取key
		String key = StringUtil.getKeyParam(compressedStr,15,16);

		//获取真实有效的参数
		compressedStr = StringUtil.getValueParam(compressedStr,15,16);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		byte[] decryptResult = null;
		try {
			compressed = Base64Util.decode(compressedStr);
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}

			//解密
			decompressed = out.toString("utf-8");

			byte[] decryptFrom = AesUtil.parseHexStr2Byte(decompressed);
			decryptResult = AesUtil.decrypt(decryptFrom, key);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		String s = null;
		try {
			s = new String(decryptResult, "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}

		return s;
	}

}
