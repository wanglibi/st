package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockParam;

@Repository
public interface StockParamDao {
  
  List<StockParam> findAllParam();
}
