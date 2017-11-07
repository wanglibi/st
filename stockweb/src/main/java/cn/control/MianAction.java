package cn.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dao.StockForecastDao;
import cn.model.Common;
import cn.model.StockForecast;

import com.alibaba.fastjson.JSON;

@RestController
public class MianAction {
  
  @Autowired
  private StockForecastDao stockForecastDao;
  
  @RequestMapping(value="searchForecast")
  public String searchForecast(Common common){
    System.err.println(123);
    
    return  JSON.toJSONString(stockForecastDao.findForecasts(common));
  }
}
