package cn.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dao.StockDataHisDao;
import cn.process.StockDataHisProcess;

public class StockTest {

  public ClassPathXmlApplicationContext ctx;

  @Before
  public void init() {
      ctx = new ClassPathXmlApplicationContext("spring.xml","spring-mvc.xml");
  }
  
  @Test
  public void test(){
    testgetStockData();
  }
  
  public void testStockId(){
    StockDataHisDao stockDataHisDao = ctx.getBean("stockDataHisDao", StockDataHisDao.class);
    stockDataHisDao.findAllIdAndTime();
    
  }
  
  public void testgetStockData(){
    StockDataHisProcess stockDataHisProcess =ctx.getBean("stockDataHisProcess", StockDataHisProcess.class);
    stockDataHisProcess.downloadStocksDataHis();
  }
  
}
