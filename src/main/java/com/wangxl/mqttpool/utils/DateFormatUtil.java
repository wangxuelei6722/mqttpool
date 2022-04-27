package com.wangxl.mqttpool.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

/** @author: Wangxl
 * @description: 时间处理类
 * @date :2019年3月23日 下午5:35:56
 * @modifier:
 * @modificationTime:
 * @description: 
 */
@Component("dateFormatUtil")
public class DateFormatUtil
{
	/**
	 * 将日期变成字符串
	 * 
	 * @param date
	 * @return
	 */
	public String dateStr(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(CommonUtils.DATE_FORMAT);
		return sdf.format(date);
	}
	
	/**
	 * 将日期时间变成字符串
	 * 
	 * @param date
	 * @return
	 */
	public String dateTimeStr(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(CommonUtils.DATETIME_FORMAT);
		return sdf.format(date);
	}
	
	/**
	 * 将日期变成字符串
	 * 
	 * @param date
	 * @param pattern 日期时间的格式
	 * @return
	 */
	public String dateStr(Date date,String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 将日期变成字符串
	 * 
	 * @param date
	 * @return
	 */
	public Date strDate(String date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(CommonUtils.DATE_FORMAT);
		try
		{
			return sdf.parse(date);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null ; 
	}
	
	/**
	 * 将日期时间变成字符串
	 * 
	 * @param date
	 * @return
	 */
	public Date strDateTime(String date)
	{
		//如果参数为空，返回当前时间。
		if (null==date && "".equals(date)){
			return new Date();
		}
		SimpleDateFormat sdf = new SimpleDateFormat(CommonUtils.DATETIME_FORMAT, Locale.CHINA);
		ParsePosition pos = new ParsePosition(0);
		try
		{
 			return sdf.parse(date,pos);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null ;
	}

	/**
	 * 将日期变成字符串
	 * 
	 * @param date
	 * @param pattern 日期时间的格式
	 * @return
	 */
	public Date strDate(String date,String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try
		{
			return sdf.parse(date);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return null ; 
	}
	/**
	 * 
	 * @Title: strDates   
	 * @Description: 将时间字符串保留日期  
	 * @param: @param date
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String strDates(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CommonUtils.DATE_FORMAT);
		try
		{
			return sdf.format(sdf.parse(date));
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * @Title: strDates   
	 * @Description: 保留时间段   
	 * @param: @param date
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String strDateForTime(String time) {
		//截取字符串
		String[] strings = time.split(" ");
		return strings[1];
	}
}
