package cn.xiaomingxingwu.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 * @author xiaomingxing
 * @create 2020/12/14 0:48
 */
public class HttpUtils {

    private static final String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat";

    // 全局cookie
    private static CookieStore cookieStore = new BasicCookieStore();
    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5 * 1000)
            .setSocketTimeout(5 * 1000)
            .setConnectionRequestTimeout(3 * 1000)
            .build();


    /**
     * POST请求
     * @param url url地址
     * @param params 表单参数
     * @param host http头部中的host（假装更像人操作）
     * @return 返回响应的实体
     */
    public static String doPost(String url, Map<String, String> params, String host) {

        String res = "";
        HttpClientContext httpClientContext = HttpClientContext.create();
        httpClientContext.setCookieStore(cookieStore);
        CloseableHttpClient httpClient = HttpClients.custom().build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Connection", "keep-alive");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Host", host);
        httpPost.setHeader("User-Agent", userAgent);

        if (!params.isEmpty()) {

            ArrayList<NameValuePair> nvps = new ArrayList<NameValuePair>();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                // 默认值
                if (value == "") {
                    value = "none";
                }
                nvps.add(new BasicNameValuePair(key, value));
            }

            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        httpPost.setConfig(requestConfig);


        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost,httpClientContext);

            // 保存cookie
            List<Cookie> cookies = httpClientContext.getCookieStore().getCookies();
            if (!cookies.isEmpty()) {
                for (Cookie cookie : cookies) {
                    cookieStore.addCookie(cookie);
                }
            }
            if (null != httpResponse && httpResponse.getStatusLine().getStatusCode() == 200) {
                res = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeResources(httpClient, httpResponse);
        }

        return res;
    }


    /**
     * 关闭资源
     * @param httpClient
     * @param httpResponse
     */
    public static void closeResources(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) {
        if (null != httpClient) {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (null != httpResponse) {
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
