package com.zwhkj.todaynews.todaynews.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonTools {

	/**
	 * @description 把JSON数据转换成指定的java对象
	 * @param @param json
	 * @param @param clazz  泛型类 即T的类型
	 * @return T 
	 * @author jiaBF
	 */
	public static <T> T getBean(String json, Class<T> clazz){
		if(TextUtils.isEmpty(json)) return null;
		try
        {
			return JSON.parseObject(json, clazz);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
	        return null;
        }
	}

	/**
	 * 功能描述：把java对象转换成JSON字符串
	 * 
	 * @param object
	 *            java对象
	 * @return
	 */
	public static String getJsonString(Object object) {
		if(null == object) return "";
		
		try
        {
			return JSON.toJSONString(object,true);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
	        return null;
        }
	}

	/**
	 * @description 解析一个json数组
	 * @param @param json
	 * @param @param clazz 泛型类 即T的类型
	 * @return List<T> 
	 * @author jiaBF
	 */
	public static <T> List<T> getBeanList(String json, Class<T> clazz){
		if(TextUtils.isEmpty(json)) return null;
		try
        {
			return JSON.parseArray(json, clazz);
        }
        catch (Exception e)
        {
        	e.printStackTrace();
	        return null;
        }
	}

	/**
	 * @description 功能描述：List<Map<String, T>>解析
	 * @param @param json
	 * @param @param clazz 泛型类 即T的类型
	 * @return List<Map<String,T>> 
	 * @author jiaBF
	 */
	public static <T> List<Map<String, T>> getListMap(String json,Class<T> clazz){
		if(TextUtils.isEmpty(json)) return null;
		
		try
        {
			return JSON.parseObject(json, new TypeReference<List<Map<String, T>>>() {
			});
        }
        catch (Exception e)
        {
        	e.printStackTrace();
	        return null;
        }
	}
	
	/**
	 * @description 功能描述：Map对象解析 Map<String, T>
	 * @param @param json
	 * @param @param clazz  泛型类 即T的类型
	 * @return Map<String,T> 
	 * @author jiaBF
	 */
	public static  <T> Map<String, T> getMap(String json,Class<T> clazz){
		if(TextUtils.isEmpty(json)) return null;
		Map<String, T> map = null;
		try
        {
			@SuppressWarnings("unchecked")
			Map<String, T> temp = ((Map<String, T>) JSON.parse(json));
			if(null != temp)
			{
				map = new HashMap<String, T>(temp.size());
			}
			 for (String key : temp.keySet()) 
			 { 
			      map.put(key, getBean(temp.get(key)+"", clazz));
			 } 
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
		return map;
	}
	
	/**
	 * @description 功能描述：Map对象解析 Map<String, List<T>> 
	 * @param @param json
	 * @param @param clazz  泛型类 即T的类型
	 * @return Map<String, List<T>>
	 * @author jiaBF
	 */
	public static  <T> Map<String, List<T>> getMapList(String json,Class<T> clazz){
		if(TextUtils.isEmpty(json)) return null;
		Map<String, List<T>> map = null;
		try
        {
			@SuppressWarnings("unchecked")
			Map<String, T> temp = ((Map<String, T>) JSON.parse(json));
			if(null != temp)
			{
				map = new HashMap<String, List<T>>(temp.size());
			}
			 for (String key : temp.keySet()) 
			 { 
			      map.put(key, getBeanList(temp.get(key)+"", clazz));
			 } 
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
		return map;
	}

}
