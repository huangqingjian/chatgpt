package com.chatgpt.util;

import com.chatgpt.constant.Constant;
import com.chatgpt.exception.ServiceException;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * http客户端
 */
public class HttpClient {

    /**
     * POST JSON请求
     *
     * @param url 请求地址
     * @param headers 请求头
     * @param socketTimeout	响应超时时间，根据业务而定，单位毫秒;
     * @param jsonStr json字符串
     * @return
     */
    public static String doPostJson(String url, Map<String, String> headers, Integer socketTimeout, String jsonStr) {
        return doPostJson(url, headers, 10000, 1000, socketTimeout, jsonStr);
    }

    /**
     * POST JSON请求
     *
     * @param url 请求地址
     * @param headers 请求头
     * @param connectTimeout 连接超时时间,单位毫秒
     * @param connectionRequestTimeout 从连接池获取到连接的超时,单位毫秒
     * @param socketTimeout	响应超时时间，根据业务而定，单位毫秒;
     * @param jsonStr json字符串
     * @return
     */
    public static String doPostJson(String url, Map<String, String> headers, Integer connectTimeout, Integer connectionRequestTimeout, Integer socketTimeout, String jsonStr) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSV1.2");
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            // 创建Httpclient对象
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求超时
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setSocketTimeout(socketTimeout).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
            if(!CollectionUtils.isEmpty(headers)) {
                for(Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
            }
            // 创建请求内容
            StringEntity entity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), Constant.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e);
        } finally {
            // 释放资源
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceException(e);
            }
        }
    }

}
