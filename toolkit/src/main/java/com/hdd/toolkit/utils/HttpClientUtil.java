package com.hdd.toolkit.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager poolManger;
    private static RequestConfig config;

    public static CloseableHttpClient getHttpClient() {
        if (poolManger == null) {
            //设置连接池的参数
            poolManger = new PoolingHttpClientConnectionManager();
            poolManger.setMaxTotal(100);//最大连接数
            poolManger.setDefaultMaxPerRoute(20);//路由最大连接数
        }

        if (config == null) {
            //配置连接池中连接的参数
            config = RequestConfig.custom()
                    .setConnectTimeout(5000)//发送请求的超时时间
                    .setSocketTimeout(2000)//响应超时时间
                    .setConnectionRequestTimeout(500)//从连接池中获取的延时时间
                    .build();
        }

        //拿到httpClient
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolManger)
                .setDefaultRequestConfig(config)
                .build();
        return httpClient;
    }

    /**
     * 执行get请求返回结果
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        String result = "";
        CloseableHttpClient httpClient = getHttpClient();
        //创建httpGet请求
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36");//设置头消息
        //执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            //状态码获取
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 执行post请求返回结果
     *
     * @param url
     * @return
     */
    public static String doPoset(String url) {
        String result = "";
        CloseableHttpClient httpClient = getHttpClient();
        //创建httpPost请求
        HttpPost httpPoset = new HttpPost(url);
        //执行请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPoset);
            //状态码获取
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
