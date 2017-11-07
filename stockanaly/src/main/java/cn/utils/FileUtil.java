package cn.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @author wang
 * @date  Aug 28, 2017 - 2:16:14 PM
 * @Description 读写文件
 */
public class FileUtil {

  private static BufferedReader br;
  private static List<List<String>> files = new ArrayList<>();

  public static List<String> readFile(String filePath,String charset){
    return readFile(new File(filePath),charset);
  }
  
  public static List<List<String>> readFiles(String filePath,String charset){
    setFiles(filePath,charset);
    return files;
  }
  
  private static void setFiles(String path, String charset){
    File file = new File(path);
    if (!file.exists()) return;
    
    File[] fs = file.listFiles();
    if (fs == null) return;
    
    for (File f : fs) {
      if (f.isDirectory()) {
        setFiles(f.getAbsolutePath(), charset);
      } else {
        files.add(readFile(f, charset));
      }
    }
  }
  
  private static List<String> readFile(File file, String charset) {
    List<String> list = null;
    charset = charset == null ? charset : "UTF-8";
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
      list = new ArrayList<>();
      String rec;
      while ((rec = br.readLine()) != null) {
        list.add(rec);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  public static void writeFile(String filePath,String content){
    FileOutputStream out = null;
    try{
      File file = new File(filePath);
      if(!file.exists())file.createNewFile();
      
      out = new FileOutputStream(new File(filePath));
      out.write(content.getBytes());
    }catch (IOException e) {
      e.printStackTrace();
    }
    try {
      if(out != null)
        out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
