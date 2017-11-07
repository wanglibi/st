package cn.model;

import cn.utils.Arith;

public class StockDataHisWeek {
  // 编码
  private String stId;
  // 名称
  private String stName;
  // 本周起始时间
  private String stDate;
  // 本周所属年月
  private String stMonth;
  // 总记录的第几周
  private int stNo;
  // 本月的第几周
  private int wkNo;
  // 本周累计涨幅率
  private double flucRateWk = 0;
  // 本周一涨幅率
  private double flucRate1 = 0;
  // 本周二涨幅率
  private double flucRate2 = 0;
  // 本周三涨幅率
  private double flucRate3 = 0;
  // 本周四涨幅率
  private double flucRate4 = 0;
  // 本周五涨幅率
  private double flucRate5 = 0;
  
  public StockDataHisWeek(){}
  
  public StockDataHisWeek(String stId,String stName){
    this.stId = stId;
    this.stName = stName;
  }
  
  public StockDataHisWeek(String stId,String stName,String stDate,String stMonth,int stNo,int wkNo){
    this.stId = stId;
    this.stName = stName;
    this.stDate = stDate;
    this.stMonth = stMonth;
    this.stNo = stNo;
    this.wkNo = wkNo;
  }
  
  public String getStId() {
    return stId;
  }
  public void setStId(String stId) {
    this.stId = stId;
  }
  public String getStName() {
    return stName;
  }
  public void setStName(String stName) {
    this.stName = stName;
  }
  public String getStDate() {
    return stDate;
  }
  public void setStDate(String stDate) {
    this.stDate = stDate;
  }
  public String getStMonth() {
    return stMonth;
  }
  public void setStMonth(String stMonth) {
    this.stMonth = stMonth;
  }
  public int getStNo() {
    return stNo;
  }
  public void setStNo(int stNo) {
    this.stNo = stNo;
  }
  public int getWkNo() {
    return wkNo;
  }
  public void setWkNo(int wkNo) {
    this.wkNo = wkNo;
  }
  public double getFlucRateWk() {
    return flucRateWk;
  }
  public void setFlucRateWk(double flucRateWk) {
    this.flucRateWk = flucRateWk;
  }
  public double getFlucRate1() {
    return flucRate1;
  }
  public void setFlucRate1(double flucRate1) {
    this.flucRate1 = flucRate1;
  }
  public double getFlucRate2() {
    return flucRate2;
  }
  public void setFlucRate2(double flucRate2) {
    this.flucRate2 = flucRate2;
  }
  public double getFlucRate3() {
    return flucRate3;
  }
  public void setFlucRate3(double flucRate3) {
    this.flucRate3 = flucRate3;
  }
  public double getFlucRate4() {
    return flucRate4;
  }
  public void setFlucRate4(double flucRate4) {
    this.flucRate4 = flucRate4;
  }
  public double getFlucRate5() {
    return flucRate5;
  }
  public void setFlucRate5(double flucRate5) {
    this.flucRate5 = flucRate5;
  }
  
  /**
   * 一周的总涨幅
   */
  public void addFlucRateWk(double flucRateWk){
    this.flucRateWk = Arith.add(this.flucRateWk, flucRateWk);
  }
  
  @Override
  public String toString() {
    return new StringBuffer()
          .append("stId=").append(stId).append(",")
          .append("stName=").append(stName).append(",")
          .append("stDate=").append(stDate).append(",")
          .append("stMonth=").append(stMonth).append(",")
          .append("stNo=").append(stNo).append(",")
          .append("wkNo=").append(wkNo).append(",")
          .append("flucRateWk=").append(flucRateWk).append(",")
          .append("flucRate1=").append(flucRate1).append(",")
          .append("flucRate2=").append(flucRate2).append(",")
          .append("flucRate3=").append(flucRate3).append(",")
          .append("flucRate4=").append(flucRate4).append(",")
          .append("flucRate5=").append(flucRate5).toString();
  }
}
