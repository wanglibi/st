package cn.thread;

import cn.process.BaseProcess;

public class StockThread implements Runnable{
  
//  private static final Logger logger = LoggerFactory.getLogger(StockThread.class);
  
  private BaseProcess process;
  
  public StockThread(BaseProcess process){
    this.process = process;
  }
  
  @Override
  public void run() {
    process.job();
  }
  
  public static void start(BaseProcess process){
    StockThread stockThread = new StockThread(process);
    new Thread(stockThread,"Thread-"+process.getClass().getName()).start();
  }
}
