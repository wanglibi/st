package cn.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.model.StockBasic;

@Repository
public interface StockBasicDao {
  
  /**
   * mysql需要修改配置文件在[mysqld]下面添加：group_concat_max_len=102400
   * 
   * linux 配置文件在/etc/my.cnf
   * window 查找软件 MySQL 5.6 Command Line Client 并右键查看属性找到my.ini文件所在位置
   * 
   * 重启mysql通过命令查看：
   * show variables like 'group_concat_max_len' 
   */
  String findAllStockBasicId();
  
  List<StockBasic> findAllBasic();
  
  void saveStockBasics(List<StockBasic> stockBasicList);
  
  void updateTimeById(String stockId,String day);
  
  void updateNameById(String stId,String stName);
}
