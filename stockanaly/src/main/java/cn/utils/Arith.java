package cn.utils;

import java.math.BigDecimal;

/**
 * @author wang
 * @date  Oct 12, 2017 - 3:49:18 PM
 * @Description 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 */
public class Arith {
  private Arith() {}

  // 默认运算精度
  private static final int DEF_DIV_SCALE = 2;

  /**
   * @Description: 加法
   */
  public static double add(Object v1, Object v2) {
    BigDecimal b1 = new BigDecimal(v1.toString());
    BigDecimal b2 = new BigDecimal(v2.toString());
    return b1.add(b2).doubleValue();
  }

  /**
   * @Description: 减法
   */
  public static double sub(Object v1, Object v2) {
    BigDecimal b1 = new BigDecimal(v1.toString());
    BigDecimal b2 = new BigDecimal(v2.toString());
    return b1.subtract(b2).doubleValue();
  }

  /**
   * @Description: 乘法
   */
  public static double mul(Object v1, Object v2) {
    BigDecimal b1 = new BigDecimal(v1.toString());
    BigDecimal b2 = new BigDecimal(v2.toString());
    return b1.multiply(b2).doubleValue();
  }

  /**
   * @Description: 除法 v1/v2
   * @param scale  表示表示需要精确到小数点以后几位。
   */
  public static double div(Object v1, Object v2, int scale) {
    BigDecimal b1 = new BigDecimal(v1.toString());
    BigDecimal b2 = new BigDecimal(v2.toString());
    return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  /**
   * @Description: 除法 v1/v2，保留两位小数
   */
  public static double div(Object v1, Object v2) {
    if(Double.parseDouble(v2.toString()) == 0) return 0;
    BigDecimal b1 = new BigDecimal(v1.toString());
    BigDecimal b2 = new BigDecimal(v2.toString());
    return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
  }
  
  /**
   * 
   * @Description: 四舍五入
   * @param scale 保留几位小数
   */
  public static double round(Object v, int scale) {
    BigDecimal b = new BigDecimal(v.toString());
    BigDecimal one = new BigDecimal("1");
    return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
  }

}
