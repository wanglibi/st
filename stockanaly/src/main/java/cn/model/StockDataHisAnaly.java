package cn.model;

import cn.exception.NoWeekException;
import cn.utils.Arith;

public class StockDataHisAnaly {
  // 编码
  private String stId;
  // 名称
  private String stName;
  // 统计日期
  private String setDate;
  // 累计总涨幅值
  private double flucPriceAll = 0d;
  // 累计总涨幅率
  private double flucRateAll = 0d;
  
  //周一累计 涨幅率
  private double flucRateWeek1 = 0d;
  //周二累计 涨幅率
  private double flucRateWeek2 = 0d;
  private double flucRateWeek3 = 0d;
  private double flucRateWeek4 = 0d;
  private double flucRateWeek5 = 0d;
  
  //上涨次数
  private int upTimesWeek1 = 0;
  private int upTimesWeek2 = 0;
  private int upTimesWeek3 = 0;
  private int upTimesWeek4 = 0;
  private int upTimesWeek5 = 0;
  
  //下跌次数
  private int downTimesWeek1 = 0;
  private int downTimesWeek2 = 0;
  private int downTimesWeek3 = 0;
  private int downTimesWeek4 = 0;
  private int downTimesWeek5 = 0;

  //连涨3次以上
  private int moreUpTimes3 = 0;
  //连涨5次以上
  private int moreUpTimes5 = 0;
  //连跌3次以上
  private int moreDownTimes3 = 0;
  //连跌5次以上
  private int moreDownTimes5 = 0;
  //最近一次连续涨跌，负数为连跌次数
  private int latelyTimes = 0;
  
  //统计各个涨跌百分比次数
  private int pointTimes1 = 0;
  private int pointTimes2 = 0;
  private int pointTimes3 = 0;
  private int pointTimes4 = 0;
  //股票运营天数
  private int days;


  public int getDays() {
    return days;
  }

  public void setDays(int days) {
    this.days = days;
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

  public String getSetDate() {
    return setDate;
  }

  public void setSetDate(String setDate) {
    this.setDate = setDate;
  }

  public double getFlucPriceAll() {
    return flucPriceAll;
  }

  public void setFlucPriceAll(double flucPriceAll) {
    this.flucPriceAll = flucPriceAll;
  }

  public double getFlucRateAll() {
    return flucRateAll;
  }

  public void setFlucRateAll(double flucRateAll) {
    this.flucRateAll = flucRateAll;
  }

  public double getFlucRateWeek1() {
    return flucRateWeek1;
  }

  public void setFlucRateWeek1(double flucRateWeek1) {
    this.flucRateWeek1 = flucRateWeek1;
  }

  public double getFlucRateWeek2() {
    return flucRateWeek2;
  }

  public void setFlucRateWeek2(double flucRateWeek2) {
    this.flucRateWeek2 = flucRateWeek2;
  }

  public double getFlucRateWeek3() {
    return flucRateWeek3;
  }

  public void setFlucRateWeek3(double flucRateWeek3) {
    this.flucRateWeek3 = flucRateWeek3;
  }

  public double getFlucRateWeek4() {
    return flucRateWeek4;
  }

  public void setFlucRateWeek4(double flucRateWeek4) {
    this.flucRateWeek4 = flucRateWeek4;
  }

  public double getFlucRateWeek5() {
    return flucRateWeek5;
  }

  public void setFlucRateWeek5(double flucRateWeek5) {
    this.flucRateWeek5 = flucRateWeek5;
  }

  public int getUpTimesWeek1() {
    return upTimesWeek1;
  }

  public void setUpTimesWeek1(int upTimesWeek1) {
    this.upTimesWeek1 = upTimesWeek1;
  }

  public int getUpTimesWeek2() {
    return upTimesWeek2;
  }

  public void setUpTimesWeek2(int upTimesWeek2) {
    this.upTimesWeek2 = upTimesWeek2;
  }

  public int getUpTimesWeek3() {
    return upTimesWeek3;
  }

  public void setUpTimesWeek3(int upTimesWeek3) {
    this.upTimesWeek3 = upTimesWeek3;
  }

  public int getUpTimesWeek4() {
    return upTimesWeek4;
  }

  public void setUpTimesWeek4(int upTimesWeek4) {
    this.upTimesWeek4 = upTimesWeek4;
  }

  public int getUpTimesWeek5() {
    return upTimesWeek5;
  }

  public void setUpTimesWeek5(int upTimesWeek5) {
    this.upTimesWeek5 = upTimesWeek5;
  }

  public int getDownTimesWeek1() {
    return downTimesWeek1;
  }

  public void setDownTimesWeek1(int downTimesWeek1) {
    this.downTimesWeek1 = downTimesWeek1;
  }

  public int getDownTimesWeek2() {
    return downTimesWeek2;
  }

  public void setDownTimesWeek2(int downTimesWeek2) {
    this.downTimesWeek2 = downTimesWeek2;
  }

  public int getDownTimesWeek3() {
    return downTimesWeek3;
  }

  public void setDownTimesWeek3(int downTimesWeek3) {
    this.downTimesWeek3 = downTimesWeek3;
  }

  public int getDownTimesWeek4() {
    return downTimesWeek4;
  }

  public void setDownTimesWeek4(int downTimesWeek4) {
    this.downTimesWeek4 = downTimesWeek4;
  }

  public int getDownTimesWeek5() {
    return downTimesWeek5;
  }

  public void setDownTimesWeek5(int downTimesWeek5) {
    this.downTimesWeek5 = downTimesWeek5;
  }

  public int getMoreUpTimes3() {
    return moreUpTimes3;
  }

  public void setMoreUpTimes3(int moreUpTimes3) {
    this.moreUpTimes3 = moreUpTimes3;
  }

  public int getMoreUpTimes5() {
    return moreUpTimes5;
  }

  public void setMoreUpTimes5(int moreUpTimes5) {
    this.moreUpTimes5 = moreUpTimes5;
  }

  public int getMoreDownTimes3() {
    return moreDownTimes3;
  }

  public void setMoreDownTimes3(int moreDownTimes3) {
    this.moreDownTimes3 = moreDownTimes3;
  }

  public int getMoreDownTimes5() {
    return moreDownTimes5;
  }

  public void setMoreDownTimes5(int moreDownTimes5) {
    this.moreDownTimes5 = moreDownTimes5;
  }
  public int getPointTimes1() {
    return pointTimes1;
  }

  public void setPointTimes1(int pointTimes1) {
    this.pointTimes1 = pointTimes1;
  }

  public int getPointTimes2() {
    return pointTimes2;
  }

  public void setPointTimes2(int pointTimes2) {
    this.pointTimes2 = pointTimes2;
  }

  public int getPointTimes3() {
    return pointTimes3;
  }

  public void setPointTimes3(int pointTimes3) {
    this.pointTimes3 = pointTimes3;
  }

  public int getPointTimes4() {
    return pointTimes4;
  }

  public void setPointTimes4(int pointTimes4) {
    this.pointTimes4 = pointTimes4;
  }
  public int getLatelyTimes() {
    return latelyTimes;
  }

  public void setLatelyTimes(int latelyTimes) {
    this.latelyTimes = latelyTimes;
  }

  public void addFlucPriceAll(double flucPrice) {
    this.flucPriceAll = Arith.add(this.flucPriceAll, flucPrice);
  }
  
  public void addFlucRateAll(double flucRate) {
    this.flucRateAll = Arith.add(this.flucRateAll, flucRate);
  }
  
  public void addWeek1(double flucRate) {
    this.flucRateWeek1 = Arith.add(this.flucRateWeek1, flucRate);
  }
  
  public void addWeek2(double flucRate) {
    this.flucRateWeek2 = Arith.add(this.flucRateWeek2, flucRate);
  }
  
  public void addWeek3(double flucRate) {
    this.flucRateWeek3 = Arith.add(this.flucRateWeek3, flucRate);
  }
  
  public void addWeek4(double flucRate) {
    this.flucRateWeek4 = Arith.add(this.flucRateWeek4, flucRate);
  }
  
  public void addWeek5(double flucRate) {
    this.flucRateWeek5 = Arith.add(this.flucRateWeek5, flucRate);
  }
  
  public void addTimes1(double flucRate){
    if(flucRate > 0)
      this.upTimesWeek1++;
    if(flucRate < 0)
      this.downTimesWeek1++;
  }
  
  public void addTimes2(double flucRate){
    if(flucRate > 0)
      this.upTimesWeek2++;
    if(flucRate < 0)
      this.downTimesWeek2++;
  }
  
  public void addTimes3(double flucRate){
    if(flucRate > 0)
      this.upTimesWeek3++;
    if(flucRate < 0)
      this.downTimesWeek3++;
  }
  
  public void addTimes4(double flucRate){
    if(flucRate > 0)
      this.upTimesWeek4++;
    if(flucRate < 0)
      this.downTimesWeek4++;
  }
  
  public void addTimes5(double flucRate){
    if(flucRate > 0)
      this.upTimesWeek5++;
    if(flucRate < 0)
      this.downTimesWeek5++;
  }
  
  public void addUpTimes3(){
    this.moreUpTimes3++;
  }
  
  public void addUpTimes5(){
    this.moreUpTimes5++;
  }
  
  public void addDownTimes3(){
    this.moreDownTimes3++;
  }
  
  public void addDownTimes5(){
    this.moreDownTimes5++;
  }
  
  public void addPointTimes1(){
    this.pointTimes1++;
  }
  public void addPointTimes2(){
    this.pointTimes2++;
  }
  public void addPointTimes3(){
    this.pointTimes3++;
  }
  public void addPointTimes4(){
    this.pointTimes4++;
  }
  
  public double getWeekfluc(int week){
    if(week == 1){
      return flucRateWeek1;
    }else if(week == 2){
      return flucRateWeek2;
    }else if(week == 3){
      return flucRateWeek3;
    }else if(week == 4){
      return flucRateWeek4;
    }else if(week == 5){
      return flucRateWeek5;
    }
    throw new NoWeekException("week(1-5) error:"+week);
  }
  
  public int getWeekUpTimes(int week){
    if(week == 1){
      return upTimesWeek1;
    }else if(week == 2){
      return upTimesWeek2;
    }else if(week == 3){
      return upTimesWeek3;
    }else if(week == 4){
      return upTimesWeek4;
    }else if(week == 5){
      return upTimesWeek5;
    }
    throw new NoWeekException("week(1-5) error:"+week);
  }
  
}
