package com.example.demo.utils;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class HttpCetPrice {
    @SneakyThrows
    public String getCetUrl(){
        String url = "https://min-api.cryptocompare.com/data/pricemultifull?fsyms=CET&tsyms=USD";
        HttpGet httpGet = new HttpGet(url);

        // 设置类型 "application/x-www-form-urlencoded" "application/json"
        httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
//        System.out.println("调用URL: " + httpGet.getURI());
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpResponse response = httpClient.execute(httpGet);
        System.out.println("Response toString()" + response.toString());
        HttpEntity entity = response.getEntity();
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = null;
        StringBuffer responseSB = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            responseSB.append(line);
        }
        reader.close();
        httpClient.close();
        return  responseSB.toString();

    }
}
