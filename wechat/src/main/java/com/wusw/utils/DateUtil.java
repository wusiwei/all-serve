package com.wusw.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
	
	/**
	 * 获取当前时间到秒
	 * @return
	 */
	public static String getDBNowDateStr() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	/**
	 * 获取当前时间到天
	 * @return
	 */
	public static String getDBNowDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static String format(Date date, String formate) {
		return new SimpleDateFormat(formate).format(date);
	}
	
	/**
	 * 
	 * @param timeZoneOffset
	 * @return
	 */
    public static TimeZone getFormatedDateString(float timeZoneOffset){
        if (timeZoneOffset > 13 || timeZoneOffset < -12) {
            timeZoneOffset = 0;
        }
        
        int newTime=(int)(timeZoneOffset * 60 * 60 * 1000);
        TimeZone timeZone;
        String[] ids = TimeZone.getAvailableIDs(newTime);
        if (ids.length == 0) {
            timeZone = TimeZone.getDefault();
        } else {
        	timeZone = new SimpleTimeZone(newTime, ids[0]);
        }
    
        return timeZone;
    }
	
	
	/**
	 * 根据传入日期获取上个月第一天
	 * @param date 传入的格式"yyyyMMdd" 如："20171011"
	 * @return 返回格式"yyyyMMdd" 如："20170901"
	 */
	public static String getLastMonthFirstDay(String date){
		if(StringUtils.isBlank(date)){
			return date;
		}
		
		return DateUtil.getLastMonth(date)+"01";
	}
	
	
	/**
	 * 根据传入日期获取上个月最后一天
	 * @param date 传入的格式"yyyyMMdd" 如："20171011"
	 * @return 返回格式"yyyyMMdd" 如："20170930"
	 */
	public static String getLastMonthLastDay(String date){
		if(StringUtils.isBlank(date)){
			return date;
		}
		String lastMonth=DateUtil.getLastMonth(date);
		return lastMonth+DateUtil.getDaysByMonth(lastMonth);
	}
	
	/**
	 * 根据传入日期获取上个月
	 * @param date 传入的格式"yyyyMMdd" 如："20171011"
	 * @return  返回格式"yyyyMM" 如："201709"
	 */
	public static String getLastMonth(String date){
		if(StringUtils.isBlank(date)){
			return date;
		}
		int Year=Integer.parseInt(date.substring(0, 4));
		int month=Integer.parseInt(date.substring(4, 6));
		String lastMonth="";
		if(month==1){
			Year--;
			month=12;
		}else{
			month--;
		}
		
		if(month>9){
			lastMonth=lastMonth+Year+month;
		}else{
			lastMonth=Year+"0"+month;
		}
		return lastMonth;
	}
	
	/**
	 * 获取传入月份的天数
	 * @param yearMonth  传入的格式"yyyyMM" 如："201710"
	 * @return
	 */
	public static String getDaysByMonth(String yearMonth){
		int year=Integer.parseInt(yearMonth.substring(0, 4));
		int month=Integer.parseInt(yearMonth.substring(4, 6));
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:	
		case 8:
		case 10:
		case 12:
			return "31";
		case 2:
			if((year%10==0&&year%100==0)||(year%10!=0&&year%4==0)){
				return "28";
			}else{
				return "29";
			}
		default:
			return "30";
		}
	}
	
	/**
	 * 获取传入月份的工作天数
	 * @param yearMonth  传入的格式"yyyyMM" 如："201710"
	 * @return 工作天数  
	 */
	public static int countWorkdayByMonth(String yearMonth){
		int year=Integer.parseInt(yearMonth.substring(0, 4));
		int month=Integer.parseInt(yearMonth.substring(4, 6));
		int count=0;
		Calendar calendar=Calendar.getInstance();
		calendar.set(year, month-1, 1);
		while(calendar.get(Calendar.MONTH)<month && calendar.get(Calendar.YEAR)<=year){
			if(!checkHoliday(calendar,null)){
				count++;
			}
			calendar.add(Calendar.DATE, 1);
		};
		return count;
	}
	
	/**
	 * 判断传入的日期是否是节假日
	 * @param calendar  需判断的日期
	 * @param holidayList 节假日数组，可为空（为空时，只判断是否是周末）
	 * @return
	 * 
	 */
	public static boolean checkHoliday(Calendar calendar,List<Calendar> holidayList) {
		if((calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)||(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)){
			return true;
		}
		if(holidayList!=null){
			for(Calendar ca:holidayList){
				if(calendar.get(Calendar.YEAR)==ca.get(Calendar.YEAR) && calendar.get(Calendar.MONTH)==ca.get(Calendar.MONTH)
						&& calendar.get(Calendar.DAY_OF_MONTH)==ca.get(Calendar.DAY_OF_MONTH) ){
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.getLastMonthFirstDay("20170115"));
		System.out.println(DateUtil.getLastMonthLastDay("20170115"));
		System.out.println(DateUtil.getDaysByMonth("200802"));
		System.out.println(DateUtil.countWorkdayByMonth("201712"));
	}
}
