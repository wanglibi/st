package cn.schedule;

import org.springframework.beans.factory.annotation.Autowired;

import cn.process.StockAnalyProcess;
import cn.process.StockForecastProcess;
import cn.process.StockLatelyProcess;
import cn.process.StockWeekProcess;

public class StockAnalyTask {
  
  @Autowired
  private StockAnalyProcess stockAnaylProcess;
  @Autowired
  private StockWeekProcess stockWeekProcess;
  @Autowired
  private StockForecastProcess stockForecastProcess;
  @Autowired
  private StockLatelyProcess stockLatelyProcess;

  public void execute() {
    //多线程
//    StockThread.start(stockDataHisProcess);
    
    //单线程
    stockLatelyProcess.job();
  }
}
