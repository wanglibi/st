package cn.model;

import cn.utils.JsonUtil;

public abstract class Basic {

  @Override
  public String toString() {
    return toJson();
  }

  public String toJson() {
    return JsonUtil.toJson(this);
    // return JSONObject.toJSONString(this);//为null的属性不显示
  }

}
