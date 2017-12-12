package cn.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dao.StockDataHisDao;
import cn.dao.StockLatelyDao;
import cn.model.StockDataHis;
import cn.model.StockLately;
import cn.utils.Arith;
import cn.utils.Util;

/**
 * @author wang
 * @date  Sep 1, 2017 - 1:54:29 PM
 * @Description 替换StockAnalyProcess
 */
@Component
public class StockLatelyProcess implements BaseProcess {

  private static final Logger logger = LoggerFactory.getLogger(StockLatelyProcess.class);

  @Autowired
  private StockDataHisDao stockDataHisDao;
  @Autowired
  private StockLatelyDao stockLatelyDao;

  @Override
  public void job() {
    Long sDate = new Date().getTime();
    List<String> ids = stockDataHisDao.findAlloneId();
    List<StockLately> saveList = new ArrayList<>();

    for (String oneId : ids) {
      List<StockDataHis> stockList = stockDataHisDao.findHisByoneId(oneId);
      
      if (Util.isEmpty(stockList)) continue;
      StockDataHis his = stockList.get(stockList.size()-1);
      
      String bigStId = stockDataHisDao.findBigStId(his.getStId());
      
      StockLately lately = new StockLately();
      lately.setBigId(bigStId);
      lately.setOneId(his.getOneId());
      lately.setStId(his.getStId());
      lately.setStName(his.getStName());
      lately.setSetDate(his.getStDate());
      lately.setDays(stockList.size());
      lately.setPriceThanBefore(countFlucTotolPrice(his));

      // 计算涨跌
      countfluc(stockList, lately);

      saveList.add(lately);
      logger.info("lately {} ...{}/{}", his.getStId(), ids.indexOf(oneId) + 1, ids.size());
    }

    if (!Util.isEmpty(saveList)) {
      stockLatelyDao.truncateTable();
      stockLatelyDao.saveLatelyList(saveList);
    }
    logger.info("save lately data end..consume:{} min", (new Date().getTime() - sDate) / 1000 / 60);
  }

  private double countFlucTotolPrice(StockDataHis his) {
    if (Util.isEmpty(his.getStCirculaVal()) || Util.isEmpty(his.getStClosePrice())
        || Util.isEmpty(his.getStFlucPrice())) return 0d;
    return Arith.mul(Arith.div(his.getStCirculaVal(), his.getStClosePrice()), his.getStFlucPrice());
  }

  private void countfluc(List<StockDataHis> stockList, StockLately lately) {
    int latelyTimes = 0;

    for (StockDataHis stockDataHis : stockList) {
      // 总涨跌幅,总涨跌值
      lately.addPriceFluc(stockDataHis.getStFlucPrice());
      lately.addRateFluc(stockDataHis.getStFlucRate());


      // 计算连续涨跌次数
      if (stockDataHis.getStFlucRate() > 0) {
        latelyTimes = latelyTimes >= 0 ? ++latelyTimes : 1;// 计算最后一次连续涨跌

      } else if (stockDataHis.getStFlucRate() < 0) {
        latelyTimes = latelyTimes <= 0 ? --latelyTimes : -1;// 计算最后一次连续涨跌
      }

    }
    lately.setLatelyTimes(latelyTimes);
  }
}
