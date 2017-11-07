package cn.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

  public static HttpResponse doGet(String url, Map<String, String> headers) {
    HttpResponse response = null;
    HttpGet getMethod = new HttpGet(url);
    CloseableHttpClient client = HttpClients.createDefault();
    try {
      if (headers != null && headers.keySet().size() > 0) {
        for (String key : headers.keySet()) {
          getMethod.addHeader(key, headers.get(key));
        }
      }
      response = client.execute(getMethod);
    } catch (IOException e) {
      e.printStackTrace();
      // } finally {
      // try {
      // client.close();// 释放资源
      // } catch (IOException e) {
      // e.printStackTrace();
      // }
    }
    return response;
  }

  public static String doGet(String url, Map<String, String> headers, String charSet) {
    HttpResponse response = doGet(url, headers);
    
    if(response == null)return "";

    Header[] hs = response.getHeaders("Content-Encoding");
    boolean isGzip = false;
    for (Header h : hs) {
      if (h.getValue().equals("gzip")) {
        isGzip = true;
        break;
      }
    }

    String responseText = null;
    try {
      if (isGzip) {
        // 需要进行gzip解压处理
        responseText = EntityUtils.toString(new GzipDecompressingEntity(response.getEntity()), charSet);
      } else {
        responseText = EntityUtils.toString(response.getEntity(), charSet);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return responseText;
  }
}
