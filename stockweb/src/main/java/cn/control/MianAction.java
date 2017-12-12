package cn.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.model.WebParam;
import cn.service.LatelyService;
import cn.utils.JsonUtil;

@RestController
public class MianAction {

  @Autowired
  private LatelyService latelyService;

  @RequestMapping(value = "/searchAllStock", produces = {"text/html;charset=UTF-8;"})
  public String searchAllStock(WebParam webParam) {
    webParam.setStartPage();
    webParam.setDatas(latelyService.findLatelyList(webParam));
    webParam.setPageTotal(latelyService.countLately(webParam));
    return JsonUtil.toJson(webParam);
  }

  @RequestMapping(value = "/searchBigStockInfo", produces = {"text/html;charset=UTF-8;"})
  public String searchBigStockInfo(WebParam webParam) {
    webParam.setDatas(latelyService.findBigHis(webParam));
    return JsonUtil.toJson(webParam);
  }

  @RequestMapping(value = "/searchStockInfo", produces = {"text/html;charset=UTF-8;"})
  public String searchStockInfo(WebParam webParam) {
    webParam.setDatas(latelyService.findHis(webParam));
    return JsonUtil.toJson(webParam);
  }
}
