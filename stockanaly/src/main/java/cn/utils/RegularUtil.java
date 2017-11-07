package cn.utils;

import java.util.regex.Pattern;

public class RegularUtil {
  
  private RegularUtil(){}
  
  public static boolean regex(String regex, String val) {
    if(val == null)return false;
    return Pattern.matches(regex, val);
  }

  /**
   *2016-01-01 00:00:09
   *2016/01/01 00:00:09
   */
  public static boolean isDate(String str) {
    return regex("[0-9-:/ ]+", str);
  }

  /** 
   * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
   * 此方法中前三位格式有： 
   * 13+任意数 
   * 15+除4的任意数 
   * 18+除1和4的任意数 
   * 17+除9的任意数 
   * 147 
   */
  public static boolean isChinaPhone(String str) {
    String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
    return regex(regExp, str);
  }
  
  /** 
   * 香港手机号码8位数，5|6|8|9开头+7位任意数 
   */  
  public static boolean isHKPhone(String str){  
      String regExp = "^(5|6|8|9)\\d{7}$";
      return regex(regExp, str);
  }
  
  /** 
   * 默认前缀、后缀不以'_'、'-'、'.'结尾
   */  
  public static boolean isMail(String str){  
      String regExp = "^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$";
      return regex(regExp, str);
  }
  
  /**
   * 整数或小数
   */
  public static boolean isNumber(String str){  
    String regExp = "^[-|+]?\\d+(.\\d+)?((E|e|E\\+|e\\+)\\d+)?$";
    return regex(regExp, str);
  }
  
  public static boolean isDouble(String str){  
    String regExp = "^[-|+]?\\d+[.]\\d+((E|e|E\\+|e\\+)\\d+)?$";
    return regex(regExp, str);
  }
  
  public static boolean isInteger(String str){  
    String regExp = "^[-|+]?\\d+((E|e|E\\+|e\\+)\\d+)?$";
    return regex(regExp, str);
  }
}
