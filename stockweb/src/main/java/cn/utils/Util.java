package cn.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.model.WebParam;

public class Util {
  
  private Util(){}

  public static boolean isEmpty(Object obj) {
    if (null == obj) {
      return true;
    }
    if (obj instanceof String) {
      return stringIsEmpty((String) obj);
    } else if (obj instanceof List) {
      return listIsEmpty((List<?>) obj);
    } else if (obj instanceof StringBuffer) {
      return stringBufferIsEmpty((StringBuffer) obj);
    } else if (obj instanceof String[]) {
      return arrayStrIsEmpty((String[]) obj);
    }else if (obj instanceof Map) {
      return mapIsEmpty((Map<?, ?>) obj);
    }
    return false;
  }

  private static boolean arrayStrIsEmpty(String[] str) {
    if (str.length == 0) {
      return true;
    }
    return false;
  }

  private static boolean stringIsEmpty(String str) {
    if ("".equals(str.trim())) {
      System.err.println(str);
      return true;
    }
    return false;
  }

  private static boolean listIsEmpty(List<?> list) {
    if (list.isEmpty() || list.size() == 0) {
      return true;
    }
    return false;
  }

  private static boolean stringBufferIsEmpty(StringBuffer sb) {
    if (sb.toString().trim().equals("")) {
      return true;
    }
    return false;
  }
  
  private static boolean mapIsEmpty(Map<?,?> map) {
    if (map.isEmpty()) {
      return true;
    }
    return false;
  }

  @SuppressWarnings(value = {"unchecked"})
  public static <T> T conver(Object object, Class<T> c) {
    if (object == null) {
      return null;
    }
    if (c.equals(String.class)) {
      return (T) object.toString();
    }
    if (object instanceof String) {
      String s = object.toString();
      if (c.equals(Integer.class)) {
        return (T) Integer.valueOf(s);
      } else if (c.equals(Double.class)) {
        return (T) Double.valueOf(s);
      } else if (c.equals(Long.class)) {
        return (T) Long.valueOf(s);
      } else if (c.equals(Short.class)) {
        return (T) Short.valueOf(s);
      } else if (c.equals(Byte.class)) {
        return (T) Byte.valueOf(s);
      } else if (c.equals(BigDecimal.class)) {
        return (T) new BigDecimal(s);
      } else if (c.equals(Boolean.class)) {
        Boolean is = ("true".equals(s) || "1".equals(s)) ? true : false;
        return (T) is;
      }
    }
    return (T) object;
  }
  
  @SuppressWarnings(value = {"unchecked"})
  public static <T> T conver(Object o) {
    if (o == null) {
      return null;
    }
    String s = o.toString();
    if (o instanceof String){
      return (T) s;
    }else if(o instanceof Integer){
      return (T) Integer.valueOf(s);
    }else if(o instanceof Double){
      return (T) Double.valueOf(s);
    } else if (o instanceof Long) {
      return (T) Long.valueOf(s);
    } else if (o instanceof Short) {
      return (T) Short.valueOf(s);
    } else if (o instanceof Byte) {
      return (T) Byte.valueOf(s);
    } else if (o instanceof BigDecimal) {
      return (T) new BigDecimal(s);
    }else if (o instanceof Boolean) {
      Boolean b = (Boolean) o;
      return (T) b;
    }
    return (T)o;
  }

  public static Double parseDouble(String s) {
    try {
      return Double.parseDouble(s);
    } catch (NumberFormatException e) {
      return 0.0;
    }
  }

  public static Integer parseInt(String s) {
    if(s.contains("\\.")){
      s = s.split("\\.")[0];
    } 
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return 0;
    }
  }
  
  //首字母大写
  public static String upFirstChar(String name) {
    return name.substring(0, 1).toUpperCase() + name.substring(1);
  }
  
  public static void main(String[] args) {
    List<String> s1 = new ArrayList<>();
    List<String> s2 = new ArrayList<>();
    s1.add("1");s1.add("2");
    s2.add("3");s2.add("4");
    WebParam p = new WebParam();
    
    List s3 = new ArrayList();
    s3.add(s1);s3.add(s2);
    p.setDatas(s3);
    System.err.println(JsonUtil.toJson(s3));
  }
}
