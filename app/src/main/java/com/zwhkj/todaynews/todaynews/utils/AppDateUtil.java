/*
 * Copyright (C) 2013 www.418log.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zwhkj.todaynews.todaynews.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 描述：日期处理类.
 * @version v1.0
 */
public class AppDateUtil {
	
	/** 时间日期格式化到年月日时分秒. */
	public static String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";
	
	/** 时间日期格式化到月日时分秒. */
	public static String dateFormatMDHMS = "MM-dd HH:mm:ss";
	
	/** 时间日期格式化到年月日. */
	public static String DATEFORMATYMD = "yyyy-MM-dd";
	
	/** 时间日期格式化到年月. */
	public static String dateFormatYM = "yyyy-MM";
	
	/** 时间日期格式化到年月日时分. */
	public static String dateFormatYMDHM = "yyyy-MM-dd HH:mm";
	public static String DATEFORMATYMDHM_CHAR = "yyyy年MM月dd日 HH:mm";
	public static String DATEFORMATYMD_CHAR = "yyyy年MM月dd日";
	/** 时间日期格式化到月日. */
	public static String dateFormatMD = "MM/dd";
	public static String dateFormatM_D = "MM-dd";
	
	/** 时分秒. */
	public static String dateFormatHMS = "HH:mm:ss";
	
	/** 时分. */
	public static String dateFormatHM = "HH:mm";

	/**
	 * 描述：String类型的日期时间转化为Date类型.
	 * @param strDate String形式的日期时间
	 * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @return Date Date类型日期时间
	 */
	public static Date getDateByFormat(String strDate, String format) {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = mSimpleDateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 描述：获取偏移之后的Date.
	 * @param date 日期时间
	 * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
	 * @param offset 偏移(值大于0,表示+,值小于0,表示－)
	 * @return Date 偏移之后的日期时间
	 */
	public Date getDateByOffset(Date date,int calendarField,int offset) {
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(date);
			c.add(calendarField, offset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.getTime();
	}
	
	/**
	 * 描述：获取指定日期时间的字符串(可偏移).
	 *
	 * @param strDate String形式的日期时间
	 * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
	 * @param offset 偏移(值大于0,表示+,值小于0,表示－)
	 * @return String String类型的日期时间
	 */
	public static String getStringByOffset(String strDate, String format,int calendarField,int offset) {
		String mDateTime = null;
		try {
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			c.setTime(mSimpleDateFormat.parse(strDate));
			c.add(calendarField, offset);
			mDateTime = mSimpleDateFormat.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return mDateTime;
	}
	
	/**
	 * 描述：Date类型转化为String类型(可偏移).
	 *
	 * @param date the date
	 * @param format the format
	 * @param calendarField the calendar field
	 * @param offset the offset
	 * @return String String类型日期时间
	 */
	public static String getStringByOffset(Date date, String format,int calendarField,int offset) {
		String strDate = null;
		try {
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			c.setTime(date);
			c.add(calendarField, offset);
			strDate = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}
	

	/**
	 * 描述：Date类型转化为String类型.
	 * @param date the date
	 * @param format the format
	 * @return String String类型日期时间
	 */
	public static String getStringByFormat(Date date, String format) {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
		String strDate = null;
		try {
			strDate = mSimpleDateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}
	
	/**
	 * 描述：获取指定日期时间的字符串,用于导出想要的格式.
	 * param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
	 * param format 时间字符串的格式
	 * param outFormat 输出的日期格式字符串，如："yyyy-MM-dd HH:mm:ss"
	 * return String 转换后的String类型的日期时间
	 */
	public static String getStringByFormat(String strDate, String format,String outFormat) {
		String mDateTime = null;
		try {
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			c.setTime(mSimpleDateFormat.parse(strDate));
			SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(outFormat);
			mDateTime = mSimpleDateFormat2.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mDateTime;
	}
	
	/**
	 * 描述：获取milliseconds表示的日期时间的字符串.
	 * @param milliseconds the milliseconds
	 * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @return String 日期时间字符串
	 */
	public static String getStringByFormat(long milliseconds,String format) {
		String thisDateTime = null;
		try {
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			thisDateTime = mSimpleDateFormat.format(milliseconds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return thisDateTime;
	}
	
	/**
	 * 描述：获取表示当前日期时间的字符串.
	 *
	 * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @return String String类型的当前日期时间
	 */
	public static String getCurrentDate(String format) {
		String curDateTime = null;
		try {
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			Calendar c = new GregorianCalendar();
			curDateTime = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curDateTime;

	}

	/**
	 * 描述：获取表示当前日期时间的字符串(可偏移).
	 *
	 * @param format 格式化字符串，如："yyyy-MM-dd HH:mm:ss"
	 * @param calendarField Calendar属性，对应offset的值， 如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
	 * @param offset 偏移(值大于0,表示+,值小于0,表示－)
	 * @return String String类型的日期时间
	 */
	public static String getCurrentDateByOffset(String format,int calendarField,int offset) {
		String mDateTime = null;
		try {
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			Calendar c = new GregorianCalendar();
			c.add(calendarField, offset);
			mDateTime = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mDateTime;

	}

	/**
	 * 描述：计算两个日期所差的天数.
	 *
	 * @param date1 第一个时间的毫秒表示
	 * @param date2 第二个时间的毫秒表示
	 * @return int 所差的天数
	 */
	public static int getOffectDay(long date1, long date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date2);
		//先判断是否同年
		int y1 = calendar1.get(Calendar.YEAR);
		int y2 = calendar2.get(Calendar.YEAR);
		int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
		int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
		int maxDays = 0;
		int day = 0;
		if (y1 - y2 > 0) {
			maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
			day = d1 - d2 + maxDays;
		} else if (y1 - y2 < 0) {
			maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
			day = d1 - d2 - maxDays;
		} else {
			day = d1 - d2;
		}
		return day;
	}
	
	/**
	 * 描述：计算两个日期所差的小时数.
	 *
	 * @param date1 第一个时间的毫秒表示
	 * @param date2 第二个时间的毫秒表示
	 * @return int 所差的小时数
	 */
	public static int getOffectHour(long date1, long date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date2);
		int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
		int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
		int h = 0;
		int day = getOffectDay(date1, date2);
		h = h1-h2+day*24;
		return h;
	}
	
	/**
	 * 描述：计算两个日期所差的分钟数.
	 *
	 * @param date1 第一个时间的毫秒表示
	 * @param date2 第二个时间的毫秒表示
	 * @return int 所差的分钟数
	 */
	public static int getOffectMinutes(long date1, long date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTimeInMillis(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTimeInMillis(date2);
		int m1 = calendar1.get(Calendar.MINUTE);
		int m2 = calendar2.get(Calendar.MINUTE);
		int h = getOffectHour(date1, date2);
		int m = 0;
		m = m1-m2+h*60;
		return m;
	}

	/**
	 * 描述：获取本周一.
	 *
	 * @param format the format
	 * @return String String类型日期时间
	 */
	public static String getFirstDayOfWeek(String format) {
		return getDayOfWeek(format,Calendar.MONDAY);
	}

	/**
	 * 描述：获取本周日.
	 *
	 * @param format the format
	 * @return String String类型日期时间
	 */
	public static String getLastDayOfWeek(String format) {
		return getDayOfWeek(format,Calendar.SUNDAY);
	}

	/**
	 * 描述：获取本周的某一天.
	 *
	 * @param format the format
	 * @param calendarField the calendar field
	 * @return String String类型日期时间
	 */
	private static String getDayOfWeek(String format,int calendarField) {
		String strDate = null;
		try {
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			int week = c.get(Calendar.DAY_OF_WEEK);
			if (week == calendarField){
				strDate = mSimpleDateFormat.format(c.getTime());
			}else{
				int offectDay = calendarField - week;
				if (calendarField == Calendar.SUNDAY) {
					offectDay = 7-Math.abs(offectDay);
				} 
				c.add(Calendar.DATE, offectDay);
				strDate = mSimpleDateFormat.format(c.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}
	
	/**
	 * 描述：获取本月第一天.
	 *
	 * @param format the format
	 * @return String String类型日期时间
	 */
	public static String getFirstDayOfMonth(String format) {
		String strDate = null;
		try {
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			//当前月的第一天
			c.set(GregorianCalendar.DAY_OF_MONTH, 1);
			strDate = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;

	}

	/**
	 * 描述：获取本月最后一天.
	 *
	 * @param format the format
	 * @return String String类型日期时间
	 */
	public static String getLastDayOfMonth(String format) {
		String strDate = null;
		try {
			Calendar c = new GregorianCalendar();
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
			// 当前月的最后一天
			c.set(Calendar.DATE, 1);
			c.roll(Calendar.DATE, -1);
			strDate = mSimpleDateFormat.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}

	

	/**
	 * 描述：获取表示当前日期的0点时间毫秒数.
	 *
	 * @return the first time of day
	 */
	public static long getFirstTimeOfDay() {
			Date date = null;
			try {
				String currentDate = getCurrentDate(DATEFORMATYMD);
				date = getDateByFormat(currentDate+" 00:00:00",dateFormatYMDHMS);
				return date.getTime();
			} catch (Exception e) {
			}
			return -1;
	}
	
	/**
	 * 描述：获取表示当前日期24点时间毫秒数.
	 *
	 * @return the last time of day
	 */
	public static long getLastTimeOfDay() {
			Date date = null;
			try {
				String currentDate = getCurrentDate(DATEFORMATYMD);
				date = getDateByFormat(currentDate+" 24:00:00",dateFormatYMDHMS);
				return date.getTime();
			} catch (Exception e) {
			}
			return -1;
	}
	
	/**
	 * 描述：判断是否是闰年()
	 * <p>(year能被4整除 并且 不能被100整除) 或者 year能被400整除,则该年为闰年.
	 *
	 * @param year 年代（如2012）
	 * @return boolean 是否为闰年
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 描述：根据时间返回格式化后的时间的描述.
	 * 小于1小时显示多少分钟前  大于1小时显示今天＋实际日期，大于今天全部显示实际时间
	 *
	 * @param strDate the str date
	 * @param outFormat the out format
	 * @return the string
	 */
	public static String formatDateStr2Desc(String strDate,String fromat,String outFormat) {

		DateFormat df = new SimpleDateFormat(fromat);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c2.setTime(df.parse(strDate));
			c1.setTime(new Date());
			int d = getOffectDay(c1.getTimeInMillis(), c2.getTimeInMillis());
			if(d==0){
				int h = getOffectHour(c1.getTimeInMillis(), c2.getTimeInMillis());
				if(h>0){
					return "今天"+getStringByFormat(strDate,fromat,outFormat);
					//return h + "小时前";
				}else if(h<0){
					//return Math.abs(h) + "小时后";
				}else if(h==0){
					int m = getOffectMinutes(c1.getTimeInMillis(), c2.getTimeInMillis());
					if(m>0){
						return m + "分钟前";
					}else if(m<0){
						//return Math.abs(m) + "分钟后";
					}else{
						return "刚刚";
					}
				}

			}else if(d>0){
				if(d == 1){
					//return "昨天"+getStringByFormat(strDate,outFormat);
				}else if(d==2){
					//return "前天"+getStringByFormat(strDate,outFormat);
				}
			}else if(d<0){
				if(d == -1){
					//return "明天"+getStringByFormat(strDate,outFormat);
				}else if(d== -2){
					//return "后天"+getStringByFormat(strDate,outFormat);
				}else{
				    //return Math.abs(d) + "天后"+getStringByFormat(strDate,outFormat);
				}
			}

			String out = getStringByFormat(strDate,fromat,outFormat);
			if(!TextUtils.isEmpty(out)){
				return out;
			}
		} catch (Exception e) {
		}

		return strDate;
	}


	/**
	 * 取指定日期为星期几.
	 *
	 * @param strDate 指定日期
	 * @param inFormat 指定日期格式
	 * @return String   星期几
	 */
    public static String getWeekNumberStr(String strDate,String inFormat) {
      String week = "星期日";
      Calendar calendar = new GregorianCalendar();
      DateFormat df = new SimpleDateFormat(inFormat);
      try {
		   calendar.setTime(df.parse(strDate));
	  } catch (Exception e) {
		  return "错误";
	  }
      int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
      switch (intTemp){
        case 0:
          week = "星期日";
          break;
        case 1:
          week = "星期一";
          break;
        case 2:
          week = "星期二";
          break;
        case 3:
          week = "星期三";
          break;
        case 4:
          week = "星期四";
          break;
        case 5:
          week = "星期五";
          break;
        case 6:
          week = "星期六";
          break;
      }
      return week;
    }
    
    /**
     * @description 获得当前时间的是几号字符串
     * @param @param strDate
     * @param @param format
     * @param @return 
     * @return String 
     * @author jiaBF
     */
    public static String getMonthDayStr(String strDate,String format) 
    {
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat(format);
        try {
  		   calendar.setTime(df.parse(strDate));
  	  } catch (Exception e) {
  		  return "错误";
  	  }
       return calendar.get(Calendar.DAY_OF_MONTH)+"";
    }
    
    
    /**
	 * 取指定日期为星期几.
	 * @param strDate 指定日期
	 * @param inFormat 指定日期格式
	 * @return String   星期几
	 */
    public static int getWeekNumber(String strDate,String inFormat) {
      Calendar calendar = new GregorianCalendar();
      DateFormat df = new SimpleDateFormat(inFormat);
      try {
		   calendar.setTime(df.parse(strDate));
	  } catch (Exception e) {
		  return -1;
	  }
      int intTemp = calendar.get(Calendar.DAY_OF_WEEK) - 1;
      return intTemp;
    }
	
    /**
     * description 当前时间是否在两个时间之间
     * auther jiaBF
     * param String startTime,String endTime
     * return boolean currentTime>=startTimeLong && currentTime<endTimeLong
     */
    public static boolean isCurrentTimeInBetween(String startTime,String endTime,String format)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			Date startDate = simpleDateFormat.parse(startTime);
			long startTimeLong = startDate.getTime();
			
			Date endDate = simpleDateFormat.parse(endTime);
			long endTimeLong = endDate.getTime();
			
			Date currDate = new Date(System.currentTimeMillis());
			String currTime = simpleDateFormat.format(currDate);
			currDate= simpleDateFormat.parse(currTime);
			long currentTime = currDate.getTime();
			return currentTime>=startTimeLong && currentTime<endTimeLong;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
    /**
     * description 和当前时间时间进行比较
     * auther jiaBF
     * param String endTime,String format
     * return boolean   currentTime>endTime
     */
	public static boolean isOutCurrentTime(String endTime,String format)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			Date endDate = simpleDateFormat.parse(endTime);
			long endTimeLong = endDate.getTime();
			
			Date currDate = new Date(System.currentTimeMillis());
			String currTime = simpleDateFormat.format(currDate);
			currDate= simpleDateFormat.parse(currTime);
			long currentTime = currDate.getTime();
			
			return currentTime>endTimeLong;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * description 比较两个时间的大小
	 * auther jiaBF
	 * param String endTime,String format
	 * return boolean  beforeTime>endTime
	 */
	public static boolean timeCompare(String beforeTime,String endTime,String format)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			Date beforeDate = simpleDateFormat.parse(beforeTime);
			long beforeTimeLong = beforeDate.getTime();
			
			Date endDate = simpleDateFormat.parse(endTime);
			long endTimeLong = endDate.getTime();
			
			return beforeTimeLong>endTimeLong;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 获取周
	 * @return int  1-7
	 */
	public static int getWeek(){
		Calendar c = Calendar.getInstance();
		//获取当前的小时数
		int weekIndex = c.get(Calendar.DAY_OF_WEEK);
		int week = 1;
		switch(weekIndex)
		{  
		case 1:
			week = 7;
			break;
		case 2:
			week = 1;
			break;
		case 3:
			week = 2;
			break;
		case 4:
			week = 3;
			break;
		case 5:
			week = 4;
			break;
		case 6:
			week = 5;
			break;
		case 7:
			week = 6;
			break;
		}
		return week;
	}
	
    /**
     * 获取两个时间串时间的差值，单位为秒
     *
     * @param startTime
     *            开始时间 yyyy-MM-dd HH:mm:ss
     * @param endTime
     *            结束时间 yyyy-MM-dd HH:mm:ss
     * @return 两个时间的差值(秒)
     */
    public static long getBetweenDiff(String startTime, String endTime,String format) {
        long diff = 0;
        SimpleDateFormat ft = new SimpleDateFormat(format);
        try {
            Date startDate = ft.parse(startTime);
            Date endDate = ft.parse(endTime);
            diff = startDate.getTime() - endDate.getTime();
            diff = diff / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }
    
    /**
     * 获取当前日期，月份，之后或者之前的日期  比如：后一个月的日期，前一个月的日期
     * monthDiff>0之后monthDiff个月，monthDiff<0之前monthDiff个月
     * @description 
     * @return String 
     * @author jiaBF
     */
    public static String getCurrMonthAfter(int monthDiff,String format) {
        SimpleDateFormat ft = new SimpleDateFormat(format);
        Calendar c = new GregorianCalendar();
        c.add(Calendar.MONTH, monthDiff);
        return ft.format(c.getTime());
    }
    

}
