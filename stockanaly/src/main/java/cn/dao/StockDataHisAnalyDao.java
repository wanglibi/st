package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockDataHisAnaly;

@Repository
public interface StockDataHisAnalyDao {
  
  StockDataHisAnaly findAnalyById(String stId);
  
  void saveDataHisAnalyList(List<StockDataHisAnaly> stockDataHisAnalyList);
  
  void truncateTable();
}
