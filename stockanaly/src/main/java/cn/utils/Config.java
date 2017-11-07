package cn.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @description:动态加载config.properties
 * @update_user:
 * @last_update_time:
 * @update_content:
 */
public class Config {
  private static Properties prop;


  public static Properties getProperties() {
    Properties prop = new Properties();
    String path = Config.class.getClassLoader().getResource("config.properties").getPath();
    InputStream in = null;
    try {
      in = new FileInputStream(new File(path));
      prop.load(in);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (in != null) {
          in.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return prop;
  }

  public static String getValue(String key) {
    if(prop == null){
      prop = getProperties();
    }
    return prop.getProperty(key);
  }

  public static <T> T getValue(String key,Class<T> c) {
    return Util.conver(getValue(key), c);
  }
  
  public static void main(String[] args){
    System.out.println(getValue("stock_time"));
  }
}
