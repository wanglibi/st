package cn.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.process.BaseProcess;

public class StockThread implements Runnable{
  
  private static final Logger logger = LoggerFactory.getLogger(StockThread.class);
  
  private BaseProcess process;
  
  private Object o;
  private String s;
  
  
  public StockThread(BaseProcess process,Object o,String s){
    this.process = process;
    this.o = o;
    this.s = s;
  }
  
  @Override
  public void run() {
    logger.info("{} start...",Thread.currentThread().getName());
    process.job(o,s);
  }
  
}
