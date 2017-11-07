package cn.model;

import java.io.Serializable;

public class StockBasic implements Serializable {
  /**
   * @fieldName: serialVersionUID
   * @fieldType: long
   * @Description: TODO 
   */
  private static final long serialVersionUID = 1L;
  // 编码
  private String stockId;
  // 名称
  private String stockName;
  // 时间
  private String stockCreateTime;
  // 所属板块
  private String stockBlock;
  // 1沪市；2深市
  private int stockExchange;
  //股票数据更新到的时间
  private String dataUpdateTime;

  public int getStockExchange() {
    return stockExchange;
  }

  public void setStockExchange(int stockExchange) {
    this.stockExchange = stockExchange;
  }

  public String getStockBlock() {
    return stockBlock;
  }

  public void setStockBlock(String stockBlock) {
    this.stockBlock = stockBlock;
  }

  public String getStockId() {
    return stockId;
  }

  public void setStockId(String stockId) {
    this.stockId = stockId;
  }

  public String getStockName() {
    return stockName;
  }

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public String getStockCreateTime() {
    return stockCreateTime;
  }

  public void setStockCreateTime(String stockCreateTime) {
    this.stockCreateTime = stockCreateTime;
  }

  public String getDataUpdatetime() {
    return dataUpdateTime;
  }

  public void setDataUpdatetime(String dataUpdateTime) {
    this.dataUpdateTime = dataUpdateTime;
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("stockId:").append(stockId).append(",")
    .append("stockName:").append(stockName).append(",")
    .append("stockBlock:").append(stockBlock).append(",")
    .append("stockExchange:").append(stockExchange).append(",")
    .append("stockCreateTime:").append(stockCreateTime)
    .append("dataUpdateTime:").append(dataUpdateTime).append(",");
    return sb.toString();
  }
}
