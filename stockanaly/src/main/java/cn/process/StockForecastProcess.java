package cn.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dao.StockDataHisAnalyDao;
import cn.dao.StockDataHisDao;
import cn.dao.StockForecastDao;
import cn.model.StockDataHisAnaly;
import cn.model.StockForecast;
import cn.service.StockParamService;
import cn.utils.Arith;
import cn.utils.DateUtil;
import cn.utils.StockUtil;
import cn.utils.Util;

/**
 * @author wang
 * @date  Oct 18, 2017 - 4:42:19 PM
 * @Description
 */
@Component
public class StockForecastProcess implements BaseProcess {

  private static final Logger logger = LoggerFactory.getLogger(StockForecastProcess.class);

  @Autowired
  private StockDataHisDao stockDataHisDao;
  @Autowired
  private StockDataHisAnalyDao stockDataHisAnalyDao;
//  @Autowired
//  private StockDataHisWeekDao stockDataHisWeekDao;
  @Autowired
  private StockForecastDao stockForecastDao;
  @Autowired
  private StockParamService params;
  

  @Override
  public void job() {
    Long sDate = new Date().getTime();
    List<String> ids = stockDataHisDao.findAlloneId();
    List<StockForecast> sfList = new ArrayList<>();
    int i =0;
    for (String id : ids) {
      StockDataHisAnaly analyObj = stockDataHisAnalyDao.findAnalyById(id);
//      List<StockDataHisWeek> weekList = stockDataHisWeekDao.finWeekById(id);
      if (Util.isEmpty(analyObj))continue;
//      StockDataHis currentHis = stockDataHisDao.findHisByDate(id,analyObj.getSetDate());//当天数据
//      StockDataHis beforeHis = stockDataHisDao.findHisByDate(id,StockUtil.beforeTradeDay(analyObj.getSetDate()));//前一天数据
      
      int currentWeek = DateUtil.dayOfWeek(analyObj.getSetDate());//判断是周几
      
      StockForecast sf = new StockForecast();
      sf.setStId(analyObj.getStId());
      sf.setStName(analyObj.getStName());
      sf.setFcDate(StockUtil.afterTradeDay(analyObj.getSetDate()));
      
      //周几的累计涨幅率/总涨幅率所占百分比
      double wfRate = Arith.div(analyObj.getWeekfluc(currentWeek), analyObj.getFlucRateAll());
      int wfst = wfRate>params.getValue("weekFlucRate",Double.class)?1:0;
      sf.setWeekFlucRate(wfRate);
      sf.setWeekFlucFCST(wfst);
      
      //周几的上涨次数占总天数的百分比
      double wuRate = Arith.div(analyObj.getWeekUpTimes(currentWeek), analyObj.getDays());
      int wust = wuRate>params.getValue("weekUpRate",Double.class)?1:0;
      sf.setWeekUpRate(wuRate);
      sf.setWeekUpFCST(wust);
      
      //最近连续涨跌计算 (连涨次数所在连涨跌范围内总数/连涨跌总次数)
      double ltRate = arithLatelyTimesRate(analyObj);
      int ltst = ltRate>params.getValue("latelyTimesRate",Double.class)?1:0;
      sf.setLatelyTimesRate(ltRate);
      sf.setLatelyTimesFCST(ltst);
      sf.setLatelyTimes(analyObj.getLatelyTimes());
      
      //计算总涨跌率:几次结果相加/(几次/2)
      sf.setFcst(wfst+wust+ltst>1.5?1:0);
      
      sfList.add(sf);
      logger.info("forecast {} ...{}/{}",sf.getStId(),i++,ids.size());
    }
    stockForecastDao.saveList(sfList);
    logger.info("forecast data end..consume:{} second",(new Date().getTime()-sDate)/1000);
  }
  
  
  private double arithLatelyTimesRate(StockDataHisAnaly analyObj){
    int latelyTimes = analyObj.getLatelyTimes();
    int times = 0;
    int totalTimes = analyObj.getMoreDownTimes3()+analyObj.getMoreDownTimes5()+analyObj.getMoreUpTimes3()+analyObj.getMoreUpTimes5();
    
    if(latelyTimes<=-5){
      times = totalTimes-analyObj.getMoreDownTimes5();
      
    }else if(latelyTimes>-5 && latelyTimes <= -3){
      times = totalTimes-analyObj.getMoreDownTimes3();
      
    }else if(latelyTimes>-3 && latelyTimes<=0){
      times = analyObj.getDownTimesWeek1()+analyObj.getDownTimesWeek2()+analyObj.getDownTimesWeek3()+analyObj.getDownTimesWeek4()+analyObj.getDownTimesWeek5();
      totalTimes = analyObj.getDays();
      return Arith.round(Arith.div(times,totalTimes)-Arith.div(latelyTimes,10),2);
      
    }else if(latelyTimes>0 && latelyTimes<3){
      times = analyObj.getUpTimesWeek1()+analyObj.getUpTimesWeek2()+analyObj.getUpTimesWeek3()+analyObj.getUpTimesWeek4()+analyObj.getUpTimesWeek5();
      totalTimes = analyObj.getDays();
      return Arith.round(Arith.div(times,totalTimes)-Arith.div(latelyTimes,10),2);
      
    }else if(latelyTimes>=3 && latelyTimes<5){
      times = analyObj.getMoreUpTimes3();
      
    }else if(latelyTimes>=5){
      times = analyObj.getMoreUpTimes5();
    }
    return Arith.div(times,totalTimes);
  }
}
