package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockDataHisWeek;

@Repository
public interface StockDataHisWeekDao {
  
  void saveWeekList(List<StockDataHisWeek> weekList);
  
  StockDataHisWeek findLastObj(String stid);
  
  void updateWeekObj(StockDataHisWeek weekObj);

  List<StockDataHisWeek> finWeekById(String id);
  
}
