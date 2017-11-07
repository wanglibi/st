package cn.utils;

import java.text.ParseException;
import java.util.Date;

public class StockUtil {
  /**
   * 今天是否已关闭股市，true已关
   */
  public static boolean isCloseMarket(){
    String closeMarketDate = DateUtil.currentDay("yyyy-MM-dd")+" 15:00:00";
    try {
      return DateUtil.compareDate(new Date(),DateUtil.toDate(closeMarketDate));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return false;
  }
}
