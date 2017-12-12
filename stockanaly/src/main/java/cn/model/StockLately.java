package cn.model;

import cn.utils.Arith;

public class StockLately {
  //唯一id
  private String oneId;
  //大盘编码
  private String bigId;
  // 编码
  private String stId;
  // 名称
  private String stName;
  // 统计日期
  private String stDate;
  // 累计总涨幅值
  private double stPriceFluc = 0d;
  // 累计总涨幅率
  private double stRateFluc = 0d;
  //最近一次连续涨跌，负数为连跌次数
  private int latelyTimes = 0;
  //股票运营天数
  private int days;
  //上一个交易日的资金总值差
  private double priceThanBefore;
  
  public String getOneId() {
    return oneId;
  }
  public void setOneId(String oneId) {
    this.oneId = oneId;
  }
  public String getBigId() {
    return bigId;
  }
  public void setBigId(String bigId) {
    this.bigId = bigId;
  }
  public void setStDate(String stDate) {
    this.stDate = stDate;
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
  public void setSetDate(String stDate) {
    this.stDate = stDate;
  }
  public double getStPriceFluc() {
    return stPriceFluc;
  }
  public void setStPriceFluc(double stPriceFluc) {
    this.stPriceFluc = stPriceFluc;
  }
  public double getStRateFluc() {
    return stRateFluc;
  }
  public void setStRateFluc(double stRateFluc) {
    this.stRateFluc = stRateFluc;
  }
  public int getLatelyTimes() {
    return latelyTimes;
  }
  public void setLatelyTimes(int latelyTimes) {
    this.latelyTimes = latelyTimes;
  }
  public int getDays() {
    return days;
  }
  public void setDays(int days) {
    this.days = days;
  }
  public double getPriceThanBefore() {
    return priceThanBefore;
  }
  public void setPriceThanBefore(double priceThanBefore) {
    this.priceThanBefore = priceThanBefore;
  }
  public void addPriceFluc(double flucPrice) {
    this.stPriceFluc = Arith.add(this.stPriceFluc, flucPrice);
  }
  
  public void addRateFluc(double flucRate) {
    this.stRateFluc = Arith.add(this.stRateFluc, flucRate);
  }
  
}
