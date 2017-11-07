package cn.schedule;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.process.StockBaiscProcess;
import cn.process.StockDataHisProcess;

/**
 * @author wang
 * @date  Sep 1, 2017 - 1:54:29 PM
 * @Description 定时器：获取股票信息
 */
public class StockBasicTask {

  private static final Logger logger = LoggerFactory.getLogger(StockBasicTask.class);

  @Autowired
  private StockBaiscProcess stockBaiscProcess;
  @Autowired
  private StockDataHisProcess stockDataHisProcess;

  /**
   * @author: wang
   * @time:Sep 1, 2017 1:54:51 PM
   * @title execute
   * @Description: 每天定时执行
   */
  public void execute() {
    logger.info("download stock info start===");
    Long sDate = new Date().getTime();
    
    //获取基本信息
    stockBaiscProcess.downloadStockBasic();
    //获取股票历史数据
    stockDataHisProcess.downloadStocksDataHis();
    
    logger.info("download stock info end===.consume:{} min",(new Date().getTime()-sDate)/1000/60);
  }

}
