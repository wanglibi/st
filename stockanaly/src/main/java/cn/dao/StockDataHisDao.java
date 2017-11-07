package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockDataHis;

@Repository
public interface StockDataHisDao {
  
  List<String> findAllId();
  List<StockDataHis> findHisById(String id);
  StockDataHis findHisByDate(String id,String date);
  /**
   * 不包含afterDate当日
   */
  List<StockDataHis> findHisGreaterDate(String id,String afterDate);
}
