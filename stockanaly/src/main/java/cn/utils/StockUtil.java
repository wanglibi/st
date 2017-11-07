package cn.utils;

import java.util.Date;

public class StockUtil {
  
  private StockUtil(){}
  
  /**
   * 今天是否已关闭股市，true已关
   */
  public static boolean isCloseMarket(){
    String closeMarketDate = DateUtil.currentDay("yyyy-MM-dd")+" 15:00:00";
    return new Date().after(DateUtil.toDate(closeMarketDate));
  }
  
  public static String beforeTradeDay(String date){
    Date d = DateUtil.toDate(date);
    int dayOfWeek = DateUtil.dayOfWeek(d);
    int intval = -1;
    if(dayOfWeek == 1){
      intval = -3;
    }else if(dayOfWeek == 7){
      intval = -2;
    }
    return DateUtil.addDate(d, intval, DateUtil.DEFAULT_DAY);
  }
  
  public static String afterTradeDay(String date){
    Date d = DateUtil.toDate(date);
    int dayOfWeek = DateUtil.dayOfWeek(d);
    int intval = +1;
    if(dayOfWeek == 5){
      intval = +3;
    }else if(dayOfWeek == 6){
      intval = +2;
    }
    return DateUtil.addDate(d, intval, DateUtil.DEFAULT_DAY);
  }
}
