package com.zack.zzweather.common.utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP encapsulation.
 * 
 */
@Slf4j
public class HttpSender {

    private static HttpSender instance;
    
    private HttpSender() {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(15000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    private static class HttpSenderHolder {
        private static HttpSender instance = new HttpSender();
    }

    public static synchronized  HttpSender getInstance() {
    	
    	if(instance == null){
    		instance = HttpSenderHolder.instance;
    	}
    	
    	return instance;
    }
    
    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    private String proxyIp;
    private Integer proxyPort;

    private final CloseableHttpClient httpClient;
    public final String CHARSET = "UTF-8";

    public String doGet(String url, Map<String, String> params) {
        return doGet(url, params, CHARSET);
    }

    public String doPost(String url, Map<String, String> params) {
        return doPost(url, params, CHARSET);
    }

    /**
     * HTTP Get request
     * 
     * @param url requested url
     * @param params request params
     * @param charset encoding format
     * @return response content
     */
    public String doGet(String url, Map<String, String> params, String charset) {
        if (isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            // 设置HTTP代理
            if (!isBlank(this.proxyIp)) {
                HttpHost proxy = new HttpHost(this.proxyIp, this.proxyPort, "http");
                RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                httpGet.setConfig(config);
            }
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                ContentType contentType = ContentType.getOrDefault(entity);
                if (contentType.getCharset() != null) {
                    charset = contentType.getCharset().toString();
                }
                result = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HTTP POST request
     *
     * @param url requested url
     * @param params request params
     * @param charset encoding format
     * @return response content
     */
    public String doPost(String url, Map<String, String> params, String charset) {
        if (isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            // 设置HTTP代理
            if (!isBlank(this.proxyIp)) {
                HttpHost proxy = new HttpHost(this.proxyIp, this.proxyPort, "http");
                RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                httpPost.setConfig(config);
            }
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                ContentType contentType = ContentType.getOrDefault(entity);
                if (contentType.getCharset() != null) {
                    charset = contentType.getCharset().toString();
                }
                result = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isBlank(String content) {
        if (content == null || content.trim().isEmpty()) {
            return true;
        }
        return false;
    }
}
