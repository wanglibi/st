package cn.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.StockParamDao;
import cn.model.StockParam;
import cn.utils.Util;

@Service
public class StockParamService {
  @Autowired
  private StockParamDao stockParamDao;
  
  private Map<String,String> paramMap = new HashMap<>();
  
  private List<StockParam> paramList = new ArrayList<>();
  
  private void loadParam(){
    paramList = stockParamDao.findAllParam();
    for(StockParam param:paramList){
      paramMap.put(param.getKname(), param.getKvalue());
    }
  }
  
  public String getValue(String keyName){
    if(Util.isEmpty(paramMap)){
      loadParam();
    }
    return paramMap.get(keyName);
  }
  
  public <T> T getValue(String keyName,Class<T> classType){
    String val = getValue(keyName);
    return Util.conver(val, classType);
  }
  
  /**
   * @author: wang
   * @time:Oct 17, 2017 10:16:45 AM
   * @Description: 按组取值，已排序k_order
   */
  public List<StockParam> getValues(String groupName){
    List<StockParam> group = new ArrayList<>();
    if(Util.isEmpty(paramList)){
      loadParam();
    }
    for(StockParam stockParam:paramList){
      if(groupName.equals(stockParam.getKgroup())){
        group.add(stockParam);
      }
    }
    return group;
  }
  
  @Deprecated
  public void modParam(String key,String val){
  }
  
  @Deprecated
  public void addParam(String key,String val){
  }
  
  public void clear(){
    this.paramList.clear();
    this.paramMap.clear();
  }
}
