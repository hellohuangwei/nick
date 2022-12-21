package com.example.cyerbpopMarket.algorithm;

import com.example.cyerbpopMarket.pojo.AssetPojo;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
public class MinimumNum {

    public AssetPojo getFloorPrice(List<AssetPojo> list){
        Float min = Float.valueOf(list.get(0).getNum());
        System.out.println(min);
        AssetPojo assetPojo = new AssetPojo();
        for (int i =0 ;i < list.size() ;i++){
            if(Float.valueOf(list.get(i).getNum())<= min){
                assetPojo = list.get(i);
            }
        }
        return  assetPojo;
    }
}
