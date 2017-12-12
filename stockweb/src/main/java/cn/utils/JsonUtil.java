package cn.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class JsonUtil {
  
  public static String toJson(Object obj) {
    return valToJson(obj);
  }
  
  private static String valToJson(Object o) {
    StringBuffer sb = new StringBuffer();
    if (o == null || o instanceof Byte ||  o instanceof Short || o instanceof Integer  || o instanceof Long || o instanceof Double || o instanceof Float || o instanceof Boolean) {
      sb.append(o);
    } else if(o instanceof String ||o instanceof Character){
      sb.append("\"").append(o).append("\"");
    } else if (o instanceof Collection) {
      sb.append("[").append(listToJson((Collection<?>) o)).append("]");
    } else if (o instanceof Object[]) {
      sb.append("[").append(listToJson(Arrays.asList((Object[]) o))).append("]");
    } else if (o instanceof Map) {
      sb.append("{").append(mapToJson((Map<?, ?>) o)).append("}");
    } else {
      sb.append(objToJson(o));
    }
    return sb.toString();
  }

  private static String listToJson(Collection<?> l) {
    StringBuffer sb = new StringBuffer();
    int i = 0;
    for (Object o : l) {
      sb.append(valToJson(o));
      if (i++ != l.size() - 1) sb.append(",");
    }
    return sb.toString();
  }

  private static String mapToJson(Map<?, ?> m) {
    StringBuffer sb = new StringBuffer();
    int i = 0;
    for (Object k : m.keySet()) {
      Object v = m.get(k);
      sb.append("\"").append(k).append("\":").append(valToJson(v));

      if (i++ != m.size() - 1) sb.append(",");
    }
    return sb.toString();
  }
  
  private static String objToJson(Object obj){
    Field[] fields = obj.getClass().getDeclaredFields();//获取属性名
    if(fields.length == 0) return obj.toString();
    
    StringBuffer sb = new StringBuffer("{");
    for (int i = 0; i < fields.length; i++) {
      try {
        String attrName = fields[i].getName();
        Object o = getValueByName(attrName, obj);
        sb.append("\"").append(attrName).append("\":").append(valToJson(o));
      } catch (NoSuchMethodException e) {
        continue;
      }
      
      if (i != fields.length - 1) sb.append(",");
    }
    return sb.append("}").toString();
  }

  private static Object getValueByName(String fieldName, Object o) throws NoSuchMethodException {
    try {
      String firstLetter = fieldName.substring(0, 1).toUpperCase();
      String getter = "get" + firstLetter + fieldName.substring(1);
      Method method = o.getClass().getMethod(getter, new Class[] {});
      Object value = method.invoke(o, new Object[] {});
      return value;
    }catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
      e.printStackTrace();
    }
    return null;
  }
}
