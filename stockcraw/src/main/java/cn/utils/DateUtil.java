package cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
  
  public static String currentDay(){
    return parseDate(new Date(),"yyyy-MM-dd");
  }
  
  public static String currentDay(String format){
    return parseDate(new Date(),format);
  }
  
  public static String parseDate(Long time){
    return parseDate(new Date(time));
  }
  
  public static String parseDate(Date date){
    return parseDate(date,"yyyy-MM-dd HH:mm:ss");
  } 
  
  public static String parseDate(Date date,String format){
    SimpleDateFormat s = new SimpleDateFormat(format);
    return  s.format(date);
  }
  
  public static Date toDate(String date) throws ParseException{
    return toDate(date,"yyyy-MM-dd HH:mm:ss");
  }
  
  public static Date toDate(String date,String format) throws ParseException{
    SimpleDateFormat s = new SimpleDateFormat(format);
    return s.parse(date);
  }
  
  public static Long toTime(String date) throws ParseException{
    return toDate(date,"yyyy-MM-dd HH:mm:ss.SSS").getTime();
  }
  
  public static String addCurrentDate(String format,int interval){
    Date d = addDate(new Date(), interval);
    return parseDate(d, format);
  }
  
  public static String addDate(String date,String format,int interval) throws ParseException{
    Date d = addDate(toDate(date,format),interval);
    return parseDate(d, format);
  }
  /**
   * 加减天
   */
  public static Date addDate(Date date,int interval){
    Calendar cal=Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, interval);
    return cal.getTime();
  }
  
  /**
   * 时间比大小,比lastDate大为ture,比lastDate小或相当为false
   */
  public static boolean compareDate(Date firstDate,Date secondDate){
    if(firstDate.getTime()>secondDate.getTime()){
      return true;
    }
    return false;
  }
  
  /**
   * 返回最大的时间
   * @throws ParseException 
   */
  public static String getMaxDate(String firstDate,String secondDate,String format) throws ParseException{
    if(toDate(firstDate, format).getTime()>toDate(secondDate, format).getTime()){
     return firstDate;
    }
    return secondDate;
  }
  
  public static boolean isCurrentDay(String day,String format){
    String currentDay = DateUtil.currentDay(format);
    if(day.trim().equals(currentDay)){
      return true;
    }
    return false;
  }
}
