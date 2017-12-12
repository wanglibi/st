package cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.StockLatelyDao;
import cn.model.StockDataHis;
import cn.model.StockLately;
import cn.model.WebParam;
import cn.service.LatelyService;

@Service
public class LatelyServiceImpl implements LatelyService{

  @Autowired
  private StockLatelyDao stockLatelyDao;
  
  @Override
  public List<StockLately> findLatelyList(WebParam webParam) {
    return stockLatelyDao.findLatelyList(webParam);
  }
  
  @Override
  public int countLately(WebParam webParam) {
    return stockLatelyDao.countLately(webParam);
  }
  
  
  @Override
  public List<StockDataHis> findHis(WebParam webParam) {
    return stockLatelyDao.findStockHis(webParam);
  }
  
  @Override
  public List<StockDataHis> findBigHis(WebParam webParam) {
    return stockLatelyDao.findBigStHis(webParam);
  }

}
