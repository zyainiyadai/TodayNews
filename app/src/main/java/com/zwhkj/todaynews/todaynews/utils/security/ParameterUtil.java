package com.zwhkj.todaynews.todaynews.utils.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author QinJian
 * @version 1.0
 * @Description :参数处理
 * @created 2011-9-1 下午04:37:53
 * @fileName com.doorto.common.util.ParameterUtil.java
 */
public class ParameterUtil {

/*	public static  Object conver(Map<String, String[]> map, Object value, Class toClazz){
        Object result = null;

		if(value != null) {
			if(toClazz == Date.class){
				try{
					Long dateTime = new Long((String)value);
					result = new Date(dateTime);
				} catch (Exception e) {
					try{
						result = DateTimeUtil.getFormatDate((String)value);
					}catch (Exception ex) {
						result = null;
						
					}
				}
			}else{
				DefaultTypeConverter converter = new DefaultTypeConverter();
				result = converter.convertValue(map,value, toClazz);
			}
       }

		return result;
	}*/


    /**
     * @param paramString
     * @return
     * @Description 把name=value&name=value&name=value&name=value的字符串转换成map
     */
    public static Map<String, String> toMap(String paramString) {
        if (paramString == null)
            return null;

        String[] nameValueArr = paramString.split("\\&");
        if (nameValueArr == null || nameValueArr.length == 0)
            return null;

        Map<String, String> map = new HashMap<String, String>();
        for (String nameValue : nameValueArr) {
            if (nameValue == null)
                continue;
            String[] param = nameValue.split("=");
            if (param == null || param.length == 0)
                continue;

            map.put(param[0], param.length > 1 ? (param[1] == null ? "" : param[1]) : "");
        }

        return map;
    }

    /**
     * @param paramString
     * @return
     * @Description 把name=value&name=value&name=value&name=value的字符串转换成map, 支持String[]
     */
    public static Map<String, String[]> toMapArray(String paramString) {
        if (paramString == null)
            return null;

        String[] nameValueArr = paramString.split("\\&");
        if (nameValueArr == null || nameValueArr.length == 0)
            return null;

        Map<String, String[]> map = new HashMap<String, String[]>();
        String[] newValues;

        for (String nameValue : nameValueArr) {
            if (nameValue == null)
                continue;
            String[] param = nameValue.split("=");
            if (param == null || param.length == 0)
                continue;

            String[] values = map.get(param[0]);
            if (null != values) {
                newValues = new String[values.length + 1];
                for (int i = 0; i < values.length; i++) {
                    newValues[i] = values[i];
                }
                newValues[values.length] = param[1];
            } else {
                newValues = new String[1];

                if (param.length <= 1) {
                    newValues[0] = "";
                } else {
                    newValues[0] = param[1];
                }

            }


            map.put(param[0], newValues);
        }


        return map;
    }

    public static String toString(Integer i) {
        return String.valueOf(i);
    }

    /**
     * @param map 把map转换成name=value&name=value&name=value&name=value各式的字符串
     * @return
     * @Description Map返回无序的字符串
     */
    public static String toString(Map<String, String> map) {
        if (map == null)
            return null;
        Set<Entry<String, String>> set = map.entrySet();
        if (set == null || set.size() == 0)
            return null;
        StringBuilder bu = new StringBuilder();

        int i = 0;
        for (Entry<String, String> entry : set) {
            bu.append(entry.getKey()).append("=").append(entry.getValue() == null ? "" : entry.getValue());
            if (i + 1 < set.size()) {
                bu.append("&");
            }
            i++;
        }

        return bu.toString();
    }

    /**
     * @param map
     * @return
     * @Description Map返回有序的字符串
     */
    public static String toStringSort(Map<String, String> map) {
        map = changeMap(map);

        return toString(map);
    }


    /**
     * @param map 把map转换成name=value&name=value&name=value&name=value各式的字符串
     * @return
     * @Description Map返回无序的字符串
     */
    public static String toStringArray(Map<String, String[]> map) {
        if (map == null)
            return null;

        Set<Entry<String, String[]>> set = map.entrySet();
        if (set == null || set.size() == 0)
            return null;

        StringBuilder bu = new StringBuilder();

        int i = 0;
        for (Entry<String, String[]> entry : set) {
            //bu.append(entry.getKey()).append("=").append(entry.getValue() == null ? "" : entry.getValue().toString());

            if (null != entry.getValue()) {
                int jj = 0;
                for (String s : entry.getValue()) {
                    bu.append(entry.getKey()).append("=");
                    bu.append(s == null ? "" : s);

                    if (jj + 1 < entry.getValue().length) {
                        bu.append("&");
                    }
                    jj++;
                }
            }

            if (i + 1 < set.size()) {
                bu.append("&");
            }
            i++;
        }

        return bu.toString();
    }

    /**
     * @param map
     * @return
     * @Description Map返回有序的字符串
     */
    public static String toStringArraySort(Map<String, String[]> map) {

        map = changeMaps(map);

        return toStringArray(map);
    }

    /**
     * @param map
     * @return
     * @Description 返回有序的TreeMap
     */
    public static Map<String, String[]> changeMaps(Map<String, String[]> map) {
        Map<String, String[]> mapRet = new TreeMap<String, String[]>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String[] value = map.get(key);
            mapRet.put(key, value);
        }
        return mapRet;
    }

    /**
     * @param map
     * @return
     * @Description 返回有序的TreeMap
     */
    public static Map<String, String> changeMap(Map<String, String> map) {
        Map<String, String> mapRet = new TreeMap<String, String>();
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String value = map.get(key);
            mapRet.put(key, value);
        }
        return mapRet;
    }

    /**
     * @param target
     * @return
     * @Description 把对象的属性，转换成name=value&name=value&name=value&name=value各式的字符串
     */
    public static String toString(Object target) {
        if (target == null)
            return null;
        StringBuilder bu = new StringBuilder();
        // 取定义的全部属性
        Field[] fields = target.getClass().getDeclaredFields();
        if (fields == null) {
            return null;
        }

        int i = 0;
        for (Field field : fields) {
            if (field.getAnnotation(IgnoreToString.class) != null) {
                continue;
            }
            // 属性值
            Object value = null;
            try {
                boolean original = field.isAccessible();
                field.setAccessible(true);
                value = field.get(target);
                field.setAccessible(original);
                bu.append(field.getName()).append("=").append(value);
                if (i + 1 < fields.length)
                    bu.append("&");
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bu.toString();
    }

	/*public static <T> T toObject(Map<String,String[]> map,Class<T> clazz) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		T obj = clazz.newInstance();
		PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				String[] values = map.get(propertyName);
				if(values != null) {
					Class targetClass = descriptor.getPropertyType();
					Object value = null;
					if(!targetClass.isArray())
						value = values[0];

					value = ParameterUtil.conver(map, value, targetClass);

					if(null != value){
						try{
							descriptor.getWriteMethod().invoke(obj, value);
						} catch(Exception e) {
							//e.printStackTrace();
							//System.out.println("property: " + propertyName + " type cast exception.");
							log.error("property: " + propertyName + " type cast exception.", e);
						}
					}
               }
           }
       }
		return obj;
	}*/


    /**
     * 是否忽略转换成字符串
     *
     * @author QinJian
     * @version 1.0
     * @Description :
     * @created Oct 1, 2011 11:28:36 PM
     * @fileName com.doorto.common.util.ParameterUtil.java
     */
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @Inherited
    public static @interface IgnoreToString {
    }

    public static void main(String[] args) {

        test_toMap();

    }


    private static void test_toMap() {
        Map<String, String> map = new HashMap<String, String>();

        Map<String, String[]> map2 = new HashMap<String, String[]>();

        String s = "pwd=14e1b600b1fd579f47433b88e8d85291&mFlag=000000003e916b47013e91700b960003&mid=&loginNo=13867165621&mOldFlag=000000003e916b47013e916e76b50002&itype=202";

        try {
            map = ParameterUtil.toMap(s);
            System.out.println("原：" + s);

            String dd = ParameterUtil.toString(map);
            System.out.println("dd:" + dd);

            map2 = ParameterUtil.toMapArray(dd);
            String dd2 = ParameterUtil.toStringArraySort(map2);
            System.out.println("dd2:" + dd2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/*	private static void test_toObject(){
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("id", new String[]{"aaaaaaaaaaaa"});
		map.put("user_name", new String[]{"10000"});
		map.put("last_login_time", new String[]{"10000"});
		try {
			User user = ParameterUtil.toObject(map,User.class);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

}
