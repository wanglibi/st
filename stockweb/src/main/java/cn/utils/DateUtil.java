package cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class DateUtil {

  private DateUtil() {}

  private static Calendar calendar1 = Calendar.getInstance();
  private static Calendar calendar2 = Calendar.getInstance();

  public static final String DEFAULT_DAY = "yyyy-MM-dd";

  public static final String DEFAULT_TIME = "yyyy-MM-dd HH:mm:ss";

  public static String currentDay() {
    return parseDate(new Date(), DEFAULT_DAY);
  }
  
  public static String currentDay(String format) {
    return parseDate(new Date(), format);
  }
  
  public static boolean isCurrentDay(String day) {
    day = day.replaceAll("[^\\d]", "").substring(0, 8);
    if (currentDay("yyyyMMdd").equals(day)) return true;
    return false;
  }
  
  public static String parseDate(Long time) {
    return parseDate(new Date(time), DEFAULT_TIME);
  }

  public static String parseDate(Date date, String format) {
    if (format == null) format = DEFAULT_TIME;
    return new SimpleDateFormat(format).format(date);
  }

  public static Date toDate(String date){
    int l = date.length();
    String num = "\\d+";
    String mark = "[^\\d]+";//连接符
    try {
      if(Pattern.matches(num, date)){
        if(l < 6)
          return new SimpleDateFormat("yyyy").parse(date);
        if(l == 6)
          return new SimpleDateFormat("yyyyMM").parse(date);
        if(l == 8)
          return new SimpleDateFormat("yyyyMMdd").parse(date);
        if(l == 10)
          return new SimpleDateFormat("yyyyMMddHH").parse(date);
        if(l == 12)
          return new SimpleDateFormat("yyyyMMddHHmm").parse(date);
        if(l == 14)
          return new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
        if(l > 14)
          return new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(date);
        throw new ParseException(date, l);
      }
      if(Pattern.matches(num+mark+num, date)){
        return new SimpleDateFormat("yyyyMM").parse(date.replaceAll(mark, ""));
      }
      if(Pattern.matches(num+mark+num+mark+num, date)){
        return new SimpleDateFormat("yyyyMMdd").parse(date.replaceAll(mark, ""));
      }
      if(Pattern.matches(num+mark+num+mark+num+mark+num, date)){
        return new SimpleDateFormat("yyyyMMddHH").parse(date.replaceAll(mark, ""));
      }
      if(Pattern.matches(num+mark+num+mark+num+mark+num+mark+num, date)){
        return new SimpleDateFormat("yyyyMMddHHmm").parse(date.replaceAll(mark, ""));
      }
      if(Pattern.matches(num+mark+num+mark+num+mark+num+mark+num+mark+num, date)){
        return new SimpleDateFormat("yyyyMMddHHmmss").parse(date.replaceAll(mark, ""));
      }
      if(Pattern.matches(num+mark+num+mark+num+mark+num+mark+num+mark+num+mark+num, date)){
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(date.replaceAll(mark, ""));
      }
      if(date.contains("GST")){//中部标准时间
        return new SimpleDateFormat("EEE MMM dd HH:mm:ss 'CST' yyyy",Locale.US).parse(date);
      }
      if(date.contains("GMT")){//格林威治标准时间
        return new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT' yyyy",Locale.US).parse(date);
      }
    }catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static Date toDate(String date, String format) {
    if (format == null) format = DEFAULT_DAY;
    try {
      return new SimpleDateFormat(format).parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Long toTime(String date) {
    return toDate(date).getTime();
  }

  public static String addCurrentDate(String format, int interval) {
    Date d = addDate(new Date(), interval);
    return parseDate(d, format);
  }

  public static String addDate(Date date,int interval,String format) {
    Date d = addDate(date, interval);
    return parseDate(d, format);
  }
  
  public static String addDate(String date,int interval,String format) {
    Date d = addDate(toDate(date), interval);
    return parseDate(d, format);
  }

  /**
   * 加减天
   */
  public static synchronized Date addDate(Date date, int interval) {
    calendar1.setTime(date);
    calendar1.add(Calendar.DATE, interval);
    return calendar1.getTime();
  }

  /**
   * 时间比大小，d1>d2:ture
   */
  public static boolean compareDate(String d1, String d2) {
    Long day1 = Long.parseLong(d1.replaceAll("[^(0-9)]", ""));
    Long day2 = Long.parseLong(d2.replaceAll("[^(0-9)]", ""));
    return day1 > day2;
  }

  /**
   * 返回最大的时间
   */
  public static String getMaxDate(String d1, String d2) {
    if (compareDate(d1, d2)) return d1;
    return d2;
  }

  /**
   * 判断周几： 周一返回1，以此类推，周日返回7
   */
  public static synchronized int dayOfWeek(Date d) {
    calendar1.setTime(d);
    if (calendar1.get(Calendar.DAY_OF_WEEK) == 1) {
      return 7;
    } else {
      return calendar1.get(Calendar.DAY_OF_WEEK) - 1;
    }
  }


  /**
   * 判断周几：周一返回1，以此类推，周日返回7
   */
  public static int dayOfWeek(String date) {
    return dayOfWeek(toDate(date));
  }

  /**
   * 判断同年同周
   */
  public static synchronized boolean isSameWeek(Date d1, Date d2) {
    // 西方周日为第一天，这里设周一为第一天
    calendar1.setFirstDayOfWeek(Calendar.MONDAY);
    calendar2.setFirstDayOfWeek(Calendar.MONDAY);
    calendar1.setTime(d1);
    calendar2.setTime(d2);
    // 同周
    if (calendar1.get(Calendar.WEEK_OF_YEAR) == calendar2.get(Calendar.WEEK_OF_YEAR)) {
      Long diffDay = (calendar1.getTime().getTime() - calendar2.getTime().getTime()) / (1000 * 3600 * 24);
      // 日期相差在7天内，表示未跨年.(跨年情况：20150101，20170101)
      if (-7 < diffDay && diffDay < 7) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断同年同周
   */
  public static boolean isSameWeek(String d1, String d2) {
    return isSameWeek(toDate(d1), toDate(d2));
  }

  /**
   * 在月份里属于第几周
   */
  public static synchronized int getWeekOfMonth(String d1) {
    calendar1.setFirstDayOfWeek(Calendar.MONDAY);
    calendar1.setTime(toDate(d1));
    return calendar1.get(Calendar.WEEK_OF_MONTH);
  }
}
