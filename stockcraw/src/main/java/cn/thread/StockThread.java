package cn.thread;

import cn.process.BaseProcess;

public class StockThread implements Runnable{
  
//  private static final Logger logger = LoggerFactory.getLogger(StockThread.class);
  
  private BaseProcess process;
  
  private Object o;
  
  
  public StockThread(BaseProcess process,Object o){
    this.process = process;
    this.o = o;
  }
  
  @Override
  public void run() {
//    logger.info("{} start...",Thread.currentThread().getName());
    process.job(o);
  }
  
}
