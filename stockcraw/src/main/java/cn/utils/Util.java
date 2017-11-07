package cn.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Util {

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
}
