package cn.model;

public class StockForecast {
  
  private String stId;
  private String stName;
  private String fcDate;
  private double stFlucRate;
  
  private double weekFlucRate;//周几总涨幅/总涨幅
  private int weekFlucFCST;//预测：0跌 1涨 
  
  private double weekUpRate;//周几的上涨次数/总天数
  private int weekUpFCST;//预测：0跌 1涨 
  
  private int latelyTimes;//最近连涨跌次数
  private double latelyTimesRate;//最近连涨率
  private int latelyTimesFCST;//预测：0跌 1涨
  
  private int fcst;//总预测：0跌 1涨
  
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
  public double getWeekFlucRate() {
    return weekFlucRate;
  }
  public void setWeekFlucRate(double weekFlucRate) {
    this.weekFlucRate = weekFlucRate;
  }
  public int getWeekFlucFCST() {
    return weekFlucFCST;
  }
  public void setWeekFlucFCST(int weekFlucFCST) {
    this.weekFlucFCST = weekFlucFCST;
  }
  public double getWeekUpRate() {
    return weekUpRate;
  }
  public void setWeekUpRate(double weekUpRate) {
    this.weekUpRate = weekUpRate;
  }
  public int getWeekUpFCST() {
    return weekUpFCST;
  }
  public void setWeekUpFCST(int weekUpFCST) {
    this.weekUpFCST = weekUpFCST;
  }
  public int getLatelyTimes() {
    return latelyTimes;
  }
  public void setLatelyTimes(int latelyTimes) {
    this.latelyTimes = latelyTimes;
  }
  public int getLatelyTimesFCST() {
    return latelyTimesFCST;
  }
  public void setLatelyTimesFCST(int latelyTimesFCST) {
    this.latelyTimesFCST = latelyTimesFCST;
  }
  public int getFcst() {
    return fcst;
  }
  public void setFcst(int fcst) {
    this.fcst = fcst;
  }
  public String getFcDate() {
    return fcDate;
  }
  public void setFcDate(String fcDate) {
    this.fcDate = fcDate;
  }
  public double getStFlucRate() {
    return stFlucRate;
  }
  public void setStFlucRate(double stFlucRate) {
    this.stFlucRate = stFlucRate;
  }
  public double getLatelyTimesRate() {
    return latelyTimesRate;
  }
  public void setLatelyTimesRate(double latelyTimesRate) {
    this.latelyTimesRate = latelyTimesRate;
  }
}
