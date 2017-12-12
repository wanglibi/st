package cn.model;

import java.util.List;

public class WebParam {
  
  private int pageNum = 1;// 查询的页码
  private int pageSize = 20;// 每页显示条数
  private int pageTotal;//总条数
  private String sDate;//查询开始时间
  private String eDate;//查询结束时间
  private int startNum;//起始查询行
  private List<?> datas;//数据集
  
  private String bigId;
  private String stId;
  private String oneId;
  private String stName;
  private int stType;
  
  public int getStType() {
    return stType;
  }
  public void setStType(int stType) {
    this.stType = stType;
  }
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
  public List<?> getDatas() {
    return datas;
  }
  public void setDatas(List<?> datas) {
    this.datas = datas;
  }
  public int getPageNum() {
    return pageNum;
  }
  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public int getPageTotal() {
    return pageTotal;
  }
  public void setPageTotal(int pageTotal) {
    this.pageTotal = pageTotal;
  }
  public String getsDate() {
    return sDate;
  }
  public void setsDate(String sDate) {
    this.sDate = sDate;
  }
  public String geteDate() {
    return eDate;
  }
  public void seteDate(String eDate) {
    this.eDate = eDate;
  }
  public int getStartNum() {
    return startNum;
  }
  public void setStartNum(int startNum) {
    this.startNum = startNum;
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
  
  public void setStartPage(){
    this.startNum = (pageNum-1)*pageSize;
  }
}
