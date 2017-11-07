package cn.model;

public class Common {
  
  private int pageNum;// 查询的页码
  private int pageSize;// 每页显示条数
  private int pageTotal;//总条数
  private String sDate;//查询开始时间
  private String eDate;//查询结束时间
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
  
}
