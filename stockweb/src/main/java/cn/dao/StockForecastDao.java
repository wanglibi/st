package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.Common;
import cn.model.StockForecast;

@Repository
public interface StockForecastDao {
  
  List<StockForecast> findForecasts(Common common);
}
