package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockLately;

@Repository
public interface StockLatelyDao {
  
  void saveLatelyList(List<StockLately> stockDataHisAnalyList);
  
  void truncateTable();
}
