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
import cn.model.StockDataHis;
import cn.model.StockDataHisAnaly;
import cn.model.StockParam;
import cn.service.StockParamService;
import cn.utils.DateUtil;
import cn.utils.Util;

/**
 * @author wang
 * @date  Sep 1, 2017 - 1:54:29 PM
 * @Description
 */
@Component
public class StockDataHisProcess implements BaseProcess {

  private static final Logger logger = LoggerFactory.getLogger(StockDataHisProcess.class);

  @Autowired
  private StockDataHisDao stockDataHisDao;
  @Autowired
  private StockDataHisAnalyDao stockDataHisAnalyDao;
  @Autowired
  private StockParamService params;

  @Override
  public void job() {
    Long sDate = new Date().getTime();
    List<String> ids = stockDataHisDao.findAllId();
    List<StockDataHisAnaly> saveList = new ArrayList<>();
    
    for (String id : ids) {
      List<StockDataHis> stockList = stockDataHisDao.findHisById(id);
      if (!Util.isEmpty(stockList)) {
        
        int stCount = stockList.size();
        String stId = stockList.get(stCount-1).getStId();
        String stName = stockList.get(stCount-1).getStName();
        String stDate = stockList.get(stCount-1).getStDate();

        StockDataHisAnaly analyData = new StockDataHisAnaly();
        analyData.setStId(stId);
        analyData.setStName(stName);
        analyData.setSetDate(stDate);
        analyData.setDays(stCount);
        
        //计算涨跌
        countfluc(stockList, analyData);
        saveList.add(analyData);
        logger.info("analysis {} ...{}/{}",stId,saveList.size(),ids.size());
      }else{
        logger.info("[{} data is empty.]",id);
      }
      // logger.info("memory used total:{}M.",
      // (Runtime.getRuntime().totalMemory() / 1024 / 1024 - Runtime.getRuntime().freeMemory() / 1024 / 1024));
    }
    
    if(!Util.isEmpty(saveList)){
      stockDataHisAnalyDao.truncateTable();
      stockDataHisAnalyDao.saveDataHisAnalyList(saveList);
    }
    logger.info("save analy data end..consume:{} second",(new Date().getTime()-sDate)/1000);
  }

  private void countfluc(List<StockDataHis> stockList, StockDataHisAnaly analyData) {
    int upTimes = 0,downTimes = 0,latelyTimes = 0;
    
    for (StockDataHis stockDataHis : stockList) {
      //总涨跌幅,总涨跌值
      analyData.addFlucPriceAll(stockDataHis.getStFlucPrice());
      analyData.addFlucRateAll(stockDataHis.getStFlucRate());

      //计算周一、周二、周三、周四、周五 这几个维度的涨跌幅、涨次数、跌次数
      countWeekFluc(stockDataHis,analyData);
      
      //计算连续涨跌次数
      if(stockDataHis.getStFlucRate() > 0){
        countContinueTimes(upTimes++,downTimes = 0,analyData);
        latelyTimes = latelyTimes>=0?++latelyTimes:1;//计算最后一次连续涨跌
        
      }else if(stockDataHis.getStFlucRate() < 0){
        countContinueTimes(upTimes = 0,downTimes++,analyData);
        latelyTimes = latelyTimes<=0?--latelyTimes:-1;//计算最后一次连续涨跌
      }
      
      //计算各个百分比阶段的次数
      countPointTimes(params.getValues("point"),stockDataHis,analyData);
    }
    analyData.setLatelyTimes(latelyTimes);
  }
  
  private void countWeekFluc(StockDataHis stockDataHis,StockDataHisAnaly analyData){
    if (Util.isEmpty(stockDataHis.getStDate())) return;
    
    double flucRate = stockDataHis.getStFlucRate();
    int dayOfWeek = DateUtil.dayOfWeek(stockDataHis.getStDate());
    
    if (dayOfWeek == 1) {
      analyData.addWeek1(flucRate);
      analyData.addTimes1(flucRate);
    } else if (dayOfWeek == 2) {
      analyData.addWeek2(flucRate);
      analyData.addTimes2(flucRate);
    } else if (dayOfWeek == 3) {
      analyData.addWeek3(flucRate);
      analyData.addTimes3(flucRate);
    } else if (dayOfWeek == 4) {
      analyData.addWeek4(flucRate);
      analyData.addTimes4(flucRate);
    } else if (dayOfWeek == 5) {
      analyData.addWeek5(flucRate);
      analyData.addTimes5(flucRate);
    }
  }
  
  private void countContinueTimes(int upTimes,int downTimes,StockDataHisAnaly analyData){
    if(upTimes == 3){
      analyData.addUpTimes3();
    }
    if(upTimes == 5){
      analyData.addUpTimes5();
    }
    if(downTimes == 3){
      analyData.addDownTimes3();
    }
    if(downTimes == 5){
      analyData.addDownTimes5();
    }
  }
  
  private void countPointTimes(List<StockParam> points,StockDataHis stockDataHis,StockDataHisAnaly analyData){
    double flucRate = stockDataHis.getStFlucRate();
    
    for(int i = 0;i<points.size();i++){
      String pv = points.get(i).getKvalue();
      String pname = points.get(i).getKname();
      
      if(Util.isEmpty(pv)) continue;
      
      String[] point = pv.split(",");
      
      if(point.length != 2) continue;
      
      double lessPoint = Util.conver(point[0], Double.class);
      double greatPoint = Util.conver(point[1], Double.class);
      
      if(lessPoint < flucRate && flucRate <= greatPoint){
        if("point1".equals(pname)){
          analyData.addPointTimes1();
        }else if("point2".equals(pname)){
          analyData.addPointTimes2();
        }else if("point3".equals(pname)){
          analyData.addPointTimes3();
        }else if("point4".equals(pname)){
          analyData.addPointTimes4();
        }
      }
    }
  }
}
