package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockDataHis;

@Repository
public interface StockDataHisDao {
  
  List<String> findAlloneId();
  List<StockDataHis> findHisByoneId(String id);
  String findBigStId(String id);
  StockDataHis findHisByDate(String id,String date);
  /**
   * 查询大于afterDate日期数据
   */
  List<StockDataHis> findHisGreaterDate(String id,String afterDate);
  /**
   * 查询最近一条各股的数据
   */
  List<StockDataHis> findLatelyHis();
}
