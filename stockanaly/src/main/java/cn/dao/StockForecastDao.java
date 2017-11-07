package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockForecast;

@Repository
public interface StockForecastDao {
  
  void saveForecast(StockForecast stockForecast);
  
  void saveList(List<StockForecast> sfList);
}
