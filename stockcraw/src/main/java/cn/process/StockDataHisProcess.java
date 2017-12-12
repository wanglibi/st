package cn.process;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import cn.dao.StockBasicDao;
import cn.dao.StockDataHisDao;
import cn.exception.NotStockException;
import cn.model.StockBasic;
import cn.model.StockDataHis;
import cn.utils.Config;
import cn.utils.DateUtil;
import cn.utils.HttpUtil;
import cn.utils.Util;

/**
 * @author wang
 * @date  Sep 1, 2017 - 1:54:29 PM
 * @Description 获取个股历史数据定时器
 */
@Component
public class StockDataHisProcess implements BaseProcess {

  private static final Logger logger = LoggerFactory.getLogger(StockDataHisProcess.class);

  @Autowired
  private StockDataHisDao stockDataHisDao;

  @Autowired
  private StockBasicDao stockBasicDao;

  @Autowired
  private ThreadPoolTaskExecutor threadPool;

  /**
   * 从网上获取个股历史数据
   * */
  public void downloadStocksDataHis() {
    List<StockBasic> StockBasics = stockDataHisDao.findAllIdAndTime();

    // 截止日期
    String lastDay = DateUtil.currentDay("yyyyMMdd");

    String urlTemp = Config.getValue("stockDataHisUrl");

    for (StockBasic stock : StockBasics) {
      String startDay = stock.getDataUpdatetime();
      try {
        if (!DateUtil.isCurrentDay(startDay, "yyyyMMdd")) {
          // 起始日期+1
          startDay = DateUtil.addDate(stock.getDataUpdatetime(), "yyyyMMdd", 1);
        }
      } catch (ParseException e) {
        continue;
      }

      String url = String.format(urlTemp, stock.getStockId(), startDay, lastDay);
      // 发送请求
      String responseText = new String(HttpUtil.doGet(url, null, "GBK"));
      responseText = Util.coverCharset(responseText, "utf-8");
      // 开多线程处理
      //isWait();
      //threadPool.execute(new StockThread(this, responseText));
      //不开多线程
      job(responseText,stock.getStockId());
      logger.info("download stockDatahis： {} from {} to {}.", stock.getStockId(), startDay, lastDay);
    }
  }

  @Override
  public void job(Object responseText,String oneId) {
    if (Util.isEmpty(responseText)) return;
    List<StockDataHis> stockDataHis = parseStockDataHis(responseText.toString(),oneId);
    
    if (Util.isEmpty(stockDataHis)) return;
    stockDataHisDao.saveStockDataHis(stockDataHis);
    stockBasicDao.updateTimeById(stockDataHis.get(0).getStId(), getMaxTime(stockDataHis));
  }

  /**
   * 解析
   */
  public List<StockDataHis> parseStockDataHis(String stockText,String oneId) {
    List<StockDataHis> list = new ArrayList<>();
    String[] stock = stockText.replaceAll("\r|'", "").split("\n");

    for (int i = 0; i < stock.length; i++) {
      // 去掉空行、首行
      if (Util.isEmpty(stock[i]) || i == 0) continue;
      
      String[] s = stock[i].split(",");
      
      if(s.length == 13){
        StockDataHis stockDataHis = new StockDataHis();
        stockDataHis.setOneId(oneId);
        stockDataHis.setStDate(s[0]);
        stockDataHis.setStId(s[1]);
        stockDataHis.setStName(s[2]);
        stockDataHis.setStClosePrice(Util.parseDouble(s[3]));
        stockDataHis.setStMaxPrice(Util.parseDouble(s[4]));
        stockDataHis.setStMinPrice(Util.parseDouble(s[5]));
        stockDataHis.setStOpenPrice(Util.parseDouble(s[6]));
        stockDataHis.setStBeforePrice(Util.parseDouble(s[7]));
        stockDataHis.setStFlucPrice(Util.parseDouble(s[8]));
        stockDataHis.setStFlucRate(Util.parseDouble(s[9]));
        stockDataHis.setStChangeRate(Util.parseDouble(s[10]));
        stockDataHis.setStTradeVal(Util.parseDouble(s[11]));
        stockDataHis.setStTradePrice(Util.parseDouble(s[12]));
//        stockDataHis.setStTotalVal(Util.parseDouble(s[13]));
//        stockDataHis.setStCirculaVal(Util.parseDouble(s[14]));
        list.add(stockDataHis);
      }else if(s.length == 15){//个股
        StockDataHis stockDataHis = new StockDataHis();
        stockDataHis.setOneId(oneId);
        stockDataHis.setStDate(s[0]);
        stockDataHis.setStId(s[1]);
        stockDataHis.setStName(s[2]);
        stockDataHis.setStClosePrice(Util.parseDouble(s[3]));
        stockDataHis.setStMaxPrice(Util.parseDouble(s[4]));
        stockDataHis.setStMinPrice(Util.parseDouble(s[5]));
        stockDataHis.setStOpenPrice(Util.parseDouble(s[6]));
        stockDataHis.setStBeforePrice(Util.parseDouble(s[7]));
        stockDataHis.setStFlucPrice(Util.parseDouble(s[8]));
        stockDataHis.setStFlucRate(Util.parseDouble(s[9]));
        stockDataHis.setStChangeRate(Util.parseDouble(s[10]));
        stockDataHis.setStTradeVal(Util.parseDouble(s[11]));
        stockDataHis.setStTradePrice(Util.parseDouble(s[12]));
        stockDataHis.setStTotalVal(Util.parseDouble(s[13]));
        stockDataHis.setStCirculaVal(Util.parseDouble(s[14]));
        list.add(stockDataHis);
      }else{
        try{
          throw new NotStockException("stock length is not 13 or 15 :"+stock[i]);
        }catch(NotStockException e){
          e.printStackTrace();
        }
        continue;
      }
    }
    return list;
  }

  private String getMaxTime(List<StockDataHis> stockDataHisList) {
    String time = "1991-01-02";
    for (StockDataHis stockDataHis : stockDataHisList) {
      try {
        time = DateUtil.getMaxDate(time, stockDataHis.getStDate(), "yyyy-MM-dd");
      } catch (ParseException e) {
        continue;
      }
    }
    return time.replace("-", "");
  }

  @SuppressWarnings(value="unused")
  private void isWait() {
    // long totalMem = Runtime.getRuntime().totalMemory()/1024/1024;
    // long maxMem = Runtime.getRuntime().maxMemory()/1024/1024;
    long freeMem = Runtime.getRuntime().freeMemory() / 1024 / 1024;
    if (freeMem <= 100) {
      logger.info("memory is less:{},thread count:{},to wait!", freeMem, threadPool.getActiveCount());
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
