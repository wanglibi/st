package cn.tool;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.utils.FileUtil;
import cn.utils.Util;

public class AddJavaFile {

  private static String path = System.getProperty("user.dir");
  private static String filepath = null;
  private static Class<?> thisJava = AddJavaFile.class;//创建对象会放到此java一起
  
  public static void main(String[] args) {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://192.168.0.96:3306/stock?useUnicode=true&characterEncoding=UTF8";
    String name = "root";
    String password = "root";
    String tableName = "stock_forecast";
    createModel(driver,url,name,password,tableName);
  }
  
  /**
   * mysql
   */
  public static void createModel(String driver, String url,String username,String password,String tableName){
    Connection c = connectDB(driver, url, username, password);
    Map<String, String> cols = selectCols(c, tableName);
    createJava(tableName,cols);
    createXml(tableName,cols);
  }

  public static Connection connectDB(String driver, String url, String username, String password) {
    Connection conn = null;
    try {
      Class.forName(driver);
      conn = DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public static Map<String, String> selectCols(Connection conn, String tableName) {
    Map<String, String> cols = new HashMap<>();
    if (conn == null || tableName == null) return cols;

    Statement st = null;
    ResultSet rs = null;
    try {
      st = conn.createStatement();
      rs = st.executeQuery("show columns from " + tableName);
      while (rs.next()) {
        String colName = rs.getString(1);
        String colType = rs.getString(2);
        if (colType.contains("int")) {
          colType = "int";
        } else if (colType.contains("double") || colType.contains("float")) {
          colType = "double";
        } else {
          colType = "String";
        }
        cols.put(colName,colType);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (conn != null) conn.close();
      if (st != null) st.close();
      if (rs != null) rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cols;
  }

  public static void createJava(String tableName, Map<String, String> cols) {
    String javaName = Util.upFirstChar(tableName).replace("_", "");
    //包和类
    StringBuffer sb = new StringBuffer(thisJava.getPackage().toString()).append(";\n\n")
    .append("public class ").append(javaName).append(" {\n");

    //属性
    for (Entry<String, String> entry : cols.entrySet()) {
      sb.append("  private ").append(entry.getValue()).append(" ").append(toJavaAttr(entry.getKey())).append(";\n");
    }
    //方法
    sb.append("\n");
    for (Entry<String, String> entry : cols.entrySet()) {
      String javaAttr = toJavaAttr(entry.getKey());
      String type = entry.getValue();
      sb.append("  public void set").append(Util.upFirstChar(javaAttr)).append("(").append(type).append(" ").append(javaAttr).append(") {\n")
        .append("    this.").append(javaAttr).append(" = ").append(javaAttr).append(";\n")
        .append("  }\n");
      
      sb.append("  public ").append(type).append(" get").append(Util.upFirstChar(javaAttr)).append("() {\n")
        .append("    return ").append(javaAttr).append(";\n")
        .append("  }\n");
    }
    sb.append("}").toString();
    FileUtil.writeFile(getThisPath() + javaName + ".java", sb.toString());
  }

  public static void createXml(String tableName,Map<String, String> cols) {
    String className = Util.upFirstChar(tableName).replace("_", "");
    String model = thisJava.getPackage().getName()+"."+className;
    //xml格式
    StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
        .append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \n")
        .append("  \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
    //根
    sb.append("<mapper namespace=\"").append(model).append("\">\n");
    //查询
    sb.append("\t<select id=\"findAll\" parameterType=\"").append(model).append("\">\n");
    sb.append("\t\tselect \n");
    int i = 0;
    for (Entry<String,String> e:cols.entrySet()) {
      String colName = e.getKey();
      String javaAttr = toJavaAttr(colName);
      sb.append("\t\t\t").append(colName).append(" as ").append(javaAttr);
      if(++i != cols.entrySet().size()){
        sb.append(",\n");
      }else{
        sb.append(" \n");
      }
    }
    sb.append("\t\tfrom ").append(tableName).append("\n");
    sb.append("\t</select>\n");
    sb.append("</mapper>");
    FileUtil.writeFile(getThisPath() + tableName + ".xml", sb.toString());
  }

  private static String toJavaAttr(String colName) {
    String[] ss = colName.split("_");
    String attrName = "";
    for (int i = 0; i < ss.length; i++) {
      if (i == 0) {
        attrName = ss[i];
        continue;
      }
      attrName += Util.upFirstChar(ss[i]);
    }
    return attrName;
  }

  private static String getThisPath() {
    File file = new File(path);
    File[] fs = file.listFiles();
    for (File f : fs) {
      if (f.isDirectory()) {
        path = f.getAbsolutePath();
        getThisPath();
      } else {
        if (f.getName().trim().equals(thisJava.getSimpleName()+".java")) {
          filepath = f.getAbsolutePath().split(thisJava.getSimpleName()+".java")[0];
        }
      }
    }
    return filepath;
  }
}
