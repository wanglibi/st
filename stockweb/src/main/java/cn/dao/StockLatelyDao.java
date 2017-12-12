package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockDataHis;
import cn.model.StockLately;
import cn.model.WebParam;

@Repository
public interface StockLatelyDao {
  
  List<StockLately> findLatelyList(WebParam webParam);
  
  int countLately(WebParam webParam);
  
  List<StockDataHis> findStockHis(WebParam webParam);
  
  List<StockDataHis> findBigStHis(WebParam webParam);
  
}
