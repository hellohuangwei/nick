package com.example.demo.test;

import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;

import lombok.SneakyThrows;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import springfox.documentation.spring.web.json.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class testhttp {

    @SneakyThrows
    public static void main(String[] args) {
        String url = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=CET&tsyms=USD";
        HttpGet httpGet = new HttpGet(url);
        // 设置类型 "application/x-www-form-urlencoded" "application/json"
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
//        System.out.println("调用URL: " + httpGet.getURI());
        CloseableHttpClient httpClient = HttpClients.createDefault();


        HttpResponse response = httpClient.execute(httpGet);
        System.out.println("Response toString()" + response.toString());
        HttpEntity entity = response.getEntity();
        System.out.println("返回状态码：" + response.getStatusLine());
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = null;
        StringBuffer responseSB = new StringBuffer();
        while ((line = reader.readLine()) != null) {
                  responseSB.append(line);
        }
        JSONObject jsonObject= JSONObject.parseObject(responseSB.toString());
        System.out.println(JSONObject.parseObject(JSONObject.parseObject(JSONObject.parseObject(jsonObject.get("RAW").toString()).get("CET").toString()).get("USD").toString()).get("PRICE") );
        System.out.println("返回消息：" + responseSB );
        reader.close();
        httpClient.close();
    }
}
