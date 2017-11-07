package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockBasic;
import cn.model.StockDataHis;

@Repository
public interface StockDataHisDao {
  
  /**
   * 基础数据里面存的是： 1沪市；2深市
   * 
   * 获取个股数据是从其他网站获取， 0沪市；1深市
   * 获取的id为市场类型加股票代码。
   */
  List<StockBasic> findAllIdAndTime();
  void saveStockDataHis(List<StockDataHis> stockDatahisList);
}
