package cn.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dao.StockDataHisDao;
import cn.dao.StockDataHisWeekDao;
import cn.model.StockDataHis;
import cn.model.StockDataHisWeek;
import cn.utils.DateUtil;
import cn.utils.Util;

/**
 * @author wang
 * @date  Oct 18, 2017 - 4:42:19 PM
 * @Description
 */
@Component
public class StockWeekProcess implements BaseProcess {

  private static final Logger logger = LoggerFactory.getLogger(StockWeekProcess.class);

  @Autowired
  private StockDataHisDao stockDataHisDao;
  @Autowired
  private StockDataHisWeekDao stockDataHisWeekDao;

  @Override
  public void job() {
    Long sDate = new Date().getTime();
    List<String> ids = stockDataHisDao.findAlloneId();
    List<StockDataHisWeek> saveList = new ArrayList<>();
    int num = 0;

    for (String id : ids) {
      StockDataHisWeek lastWeek = stockDataHisWeekDao.findLastObj(id);
      List<StockDataHis> stockList = null;
      int stNo = 1;

      if (Util.isEmpty(lastWeek)) {
        // 全量查询
        stockList = stockDataHisDao.findHisByoneId(id);
      } else {
        // 增量量查询
        stockList = stockDataHisDao.findHisGreaterDate(id, lastWeek.getStDate());
        // 更新lastWeek
        updateWeek(stockList, lastWeek);
        stNo = lastWeek.getStNo() + 1;
      }

      if (Util.isEmpty(stockList)) continue;
      // 计算每周涨幅
      contWeek(stockList, saveList, stNo);

      // 保存全量或增量数据
      if (!Util.isEmpty(saveList)) {
        stockDataHisWeekDao.saveWeekList(saveList);
        saveList = new ArrayList<>();
      }
      logger.info("week data {}...{}/{}", id, ++num, ids.size());
    }
    logger.info("week data end..consume:{} minute", (new Date().getTime() - sDate) / 1000/60);
  }

  /**
   * 修改lastWeek:同一周数据
   */
  private void updateWeek(List<StockDataHis> stockList, StockDataHisWeek lastWeek) {

    if (Util.isEmpty(stockList)) return;

    for (int i = 0; i < stockList.size(); i++) {

      StockDataHis hObj = stockList.get(i);

      if (DateUtil.isSameWeek(lastWeek.getStDate(), hObj.getStDate())) {
        lastWeek.addFlucRateWk(hObj.getStFlucRate());
        setflucRate(lastWeek, hObj);
        // 删除hObj，当前下标要减一
        stockList.remove(i--);
      }
    }
    stockDataHisWeekDao.updateWeekObj(lastWeek);
  }

  /**
   * 计算每周涨幅
   */
  private void contWeek(List<StockDataHis> stockList, List<StockDataHisWeek> saveList, int stNo) {

    StockDataHisWeek weekObj = null;
    String stDate = null;

    for (StockDataHis hObj : stockList) {

      // 不在同一周
      if (weekObj == null || !DateUtil.isSameWeek(stDate, hObj.getStDate())) {
        stDate = hObj.getStDate();
        weekObj = new StockDataHisWeek(hObj.getStId(), hObj.getStName());
        weekObj.setStMonth(stDate.substring(0, 7));
        weekObj.setStNo(stNo++);
        weekObj.setWkNo(DateUtil.getWeekOfMonth(stDate));
        // 放入list
        saveList.add(weekObj);
      }
      setflucRate(weekObj, hObj);
      weekObj.addFlucRateWk(hObj.getStFlucRate());
    }
  }

  /**
   * 设置周一到周五涨幅率
   */
  private void setflucRate(StockDataHisWeek weekObj, StockDataHis hObj) {
    double flucRate = hObj.getStFlucRate();
    int weekNum = DateUtil.dayOfWeek(hObj.getStDate());
    if (weekNum == 1) {
      weekObj.setFlucRate1(flucRate);
    } else if (weekNum == 2) {
      weekObj.setFlucRate2(flucRate);
    } else if (weekNum == 3) {
      weekObj.setFlucRate3(flucRate);
    } else if (weekNum == 4) {
      weekObj.setFlucRate4(flucRate);
    } else if (weekNum == 5) {
      weekObj.setFlucRate5(flucRate);
    }
    weekObj.setStDate(hObj.getStDate());
  }
}
