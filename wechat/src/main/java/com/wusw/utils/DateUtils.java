package com.wusw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateUtils {

	public static String getDBNowDateStr() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	public static String getDBNowDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	public static String getDBTodyFirstTimeStr() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "000000";
	}

	public static String getDBTodyLatestTimeStr() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "235959";
	}
	
	public static String format(Date date, String formate) {
		return new SimpleDateFormat(formate).format(date);
	}
	
	//获取上个月
	public static String getLastMonth () {
		int year = Integer.parseInt(DateUtils.format(new Date(), "yyyy"));
		int month = Integer.parseInt(DateUtils.format(new Date(), "MM"));
		if(month == 1){
			year--;
			month = 12;
		}else{
			month--;
		}
		String lastMonth = "";
		while (month<10) {
			lastMonth = "0"+month;
			break;
		}
		return year + lastMonth;
	}
	
	/**
	 * 去除日期字符串的分割符
	 * @param date
	 * @return
	 */
	public static String removeDecollatorForDate(String date) {
		if (StringUtils.isBlank(date)) {
			return date;
		}
		String[] strs = date.split("[/-]");
		for(int i =0;i<strs.length;i++){
		}
		String result = "";
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			if ((i == 1 || i == 2) && str.length() == 1) {
				result += "0" + str;
			} else {
				result += str;
			}
		}
		return result;
	}

	/**
	 * 去除时间的分割符
	 * @param time
	 * @return
	 */
	public static String removeDecollatorForTime(String time) {
		if (StringUtils.isBlank(time)) {
			return time;
		}
		return time.replaceAll("[\\s-:]", "");
	}
	
	public static String transTime(String str){
		   StringBuffer sb = new StringBuffer(str);
		   sb.insert(10, ":");
		   sb.insert(8, " ");
		   sb.insert(6, "-");
		   sb.insert(4, "-");
		   sb.delete(16,18);
		   String result = sb.toString();
		   return result;
	   }
	public static String transDate(String str){
		if (StringUtils.isEmpty(str)) {
			return "";
		}
		   StringBuffer sb = new StringBuffer(str);
		   sb.insert(6, "-");
		   sb.insert(4, "-");
		   String result = sb.toString();
		   return result;
	   }
	public static String getWeek(Date date){
		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if(week_index<0){
			week_index = 0;
		} 
		return weeks[week_index];
	}
	
	public static String getNextMonth (String month) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String result = "";
		try {
			Date date = sdf.parse(month);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH,+1);  
			Date next = c.getTime();
			result = sdf.format(next);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static String getLastDay (String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String result = "";
		try {
			Date date = sdf.parse(dateStr);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE,-1);  
			Date next = c.getTime();
			result = sdf.format(next);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static Date formatDateFromString (String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date result = null;
		try {
			Date date = sdf.parse(dateStr);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			result = c.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtils.getLastDay("20161201"));
	}
}
