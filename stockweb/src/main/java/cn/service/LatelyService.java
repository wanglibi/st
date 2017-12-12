package cn.service;

import java.util.List;

import cn.model.StockDataHis;
import cn.model.StockLately;
import cn.model.WebParam;

public interface LatelyService {
  List<StockLately> findLatelyList(WebParam webParam);
  int countLately(WebParam webParam);
  
  List<StockDataHis> findHis(WebParam webParam);
  List<StockDataHis> findBigHis(WebParam webParam);
}
