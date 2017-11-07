package cn.schedule;

import org.springframework.beans.factory.annotation.Autowired;

import cn.process.StockDataHisProcess;
import cn.process.StockForecastProcess;
import cn.process.StockWeekProcess;

public class StockAnalyTask {
  
  @Autowired
  private StockDataHisProcess stockDataHisProcess;
  @Autowired
  private StockWeekProcess stockWeekProcess;
  @Autowired
  private StockForecastProcess stockForecastProcess;

  public void execute() {
    //多线程
//    StockThread.start(stockDataHisProcess);
//    StockThread.start(StockWeekProcess);
    //单线程
    stockDataHisProcess.job();
//    stockWeekProcess.job();
    stockForecastProcess.job();
  }
}
