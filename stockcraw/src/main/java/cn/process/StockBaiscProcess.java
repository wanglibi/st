package cn.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dao.StockBasicDao;
import cn.model.StockBasic;
import cn.utils.Config;
import cn.utils.HttpUtil;
import cn.utils.Util;

/**
 * @author wang
 * @date  Sep 1, 2017 - 1:54:29 PM
 * @Description 获取股票名称定时器
 */
@Component
public class StockBaiscProcess {

  private static final Logger logger = LoggerFactory.getLogger(StockBaiscProcess.class);

  @Autowired
  private StockBasicDao stockBasicDao;

  /**
   * 从网上获取股票名称
   * */
  public String downloadStockBasic() {
    Map<String, String> headers = new HashMap<String, String>();
    // 设置参数
    headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
    headers.put("Accept-Encoding", "gzip, deflate");
    headers.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,zh-TW;q=0.4");
    headers.put("Connection", "Keep-Alive");
    headers.put("Host", "nufm.dfcfw.com");
    headers.put("Upgrade-Insecure-Requests", "1");
    headers
        .put("User-Agent",
            "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");

    String url =
        Config.getValue("stockBasicUrl").replace("{", "%7B").replace("}", "%7D").replace("%stockMax", Config.getValue("stockMax"));
    // 发送请求
    String responseText = HttpUtil.doGet(url, headers, "utf-8");
    logger.info("download stock baisc："+url);
    //处理数据
    List<StockBasic> stockBasicList = parseStock(responseText);
    if (!stockBasicList.isEmpty()) {
      saveOrUpdate(stockBasicList);
    }
    return responseText;
  }

  /**
   * 解析
   * var mozselQI={pages:2,data:["1,603106,恒银金融,24.93,100.00,1,10.02,68.79,8,61.05,72.34,5,-,电子信息,BK04471,2017-09-27 09:36:20",
   *                             "1,603533,掌阅科技,8.54,100.00,2,10.05,39.10,20,-,39.10,21,-,文化传媒,BK04861,2017-09-27 09:36:20"]}
   */
  public List<StockBasic> parseStock(String stockText) {
    List<StockBasic> list = new ArrayList<>();
    if (Util.isEmpty(stockText)) return list;
    // JSONObject jsStr = JSONObject.parseObject(stockText);
    int beginIndex = stockText.indexOf("[\"") + 2;
    int endIndex = stockText.indexOf("\"]");
    stockText = stockText.substring(beginIndex, endIndex);
    String[] stocks = stockText.split("\",\"");

    for (String stock : stocks) {
      StockBasic stockBasic = new StockBasic();
      String[] s = stock.split(",");
      stockBasic.setStockExchange(Util.parseInt(s[0]));
      stockBasic.setStockId(s[1]);
      stockBasic.setStockName(s[2]);
      stockBasic.setStockBlock(s[13]);
      stockBasic.setStockCreateTime(s[15]);
      list.add(stockBasic);
    }
    return list;
  }

  private void saveOrUpdate(List<StockBasic> newBasicList) {
    List<StockBasic> list = stockBasicDao.findAllBasic();

    // save all stock
    if (Util.isEmpty(list)) {
      stockBasicDao.saveStockBasics(newBasicList);
      return;
    }
    
    //list转换成map
    Map<String,String> map = new HashMap<>();
    for(StockBasic basic : list){
      map.put(basic.getStockId(),basic.getStockName());
    }

    List<StockBasic> saveList = new ArrayList<>();
    
    for (StockBasic newBasic : newBasicList) {
      String newId = newBasic.getStockId();
      String newName = newBasic.getStockName();
      
      //新增
      if(!map.containsKey(newId)){
        saveList.add(newBasic);
        continue;
      }
      //修改名称
      if(map.containsKey(newId) && !newName.equals(map.get(newId))){
        stockBasicDao.updateNameById(newId,newName);
      }
    }

    if (!Util.isEmpty(saveList)) {
      stockBasicDao.saveStockBasics(saveList);
    }
  }
}
