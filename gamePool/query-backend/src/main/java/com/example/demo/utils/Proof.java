package com.example.demo.utils;


import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.fastjson.JSONObject;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Proof {


    private static final String priKey = "";
    public Map<String, Object> provideProof(String account, String newId,String tokenName){
        Map<String, Object> map = new HashMap<>();
        long timestamp = System.currentTimeMillis()/1000;
        map.put("account",account);
        map.put("newId/amount",newId);
        map.put("timestamp", Long.toString(timestamp));
        String  s1 = account.toLowerCase()+ newId +  Long.toString(timestamp)+tokenName.toLowerCase();
        byte[] contentHashBytes = Hash.sha3(s1.getBytes());
        String contentHashHex = Hex.toHexString(contentHashBytes);
        Credentials credentials = Credentials.create(priKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        map.put("signResultData",signResultData);
        return  map;
    }

    public Map<String, Object> provideProof(String account, String newId,String tokenName,String chainId){
        Map<String, Object> map = new HashMap<>();
        long timestamp = System.currentTimeMillis()/1000;
        map.put("account",account);
        map.put("newId",newId);
        map.put("timestamp", Long.toString(timestamp));
        map.put("tokenAddress",tokenName);
        map.put("chainId",chainId);
        String  s1 = account.toLowerCase()+ newId +  Long.toString(timestamp)+tokenName.toLowerCase()+chainId;
        byte[] contentHashBytes = Hash.sha3(s1.getBytes());
        String contentHashHex = Hex.toHexString(contentHashBytes);
        Credentials credentials = Credentials.create(priKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        map.put("signResultData",signResultData);
        return  map;
    }


    public Map<String, Object> provideProofRole(String account, String newId,String tokenName,String chainId){
        Map<String, Object> map = new HashMap<>();
        long timestamp = System.currentTimeMillis()/1000;
        map.put("account",account);
        map.put("newId",newId);
        map.put("timestamp", Long.toString(timestamp));
        map.put("tokenAddress",tokenName);
        map.put("chainId",chainId);
        String  s1 = account.toLowerCase()+ newId +  Long.toString(timestamp)+tokenName+chainId;
        byte[] contentHashBytes = Hash.sha3(s1.getBytes());
        String contentHashHex = Hex.toHexString(contentHashBytes);
        Credentials credentials = Credentials.create(priKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        map.put("signResultData",signResultData);
        return  map;
    }

    public Map<String, Object> provideErc1155Proof(String account, String id,String amount,String tokenName,String chainId){
        Map<String, Object> map = new HashMap<>();
        long timestamp = System.currentTimeMillis()/1000;
        map.put("account",account);
        map.put("id",id);
        map.put("amount",amount);
        map.put("chainId",chainId);
        map.put("tokenName",tokenName);
        map.put("timestamp", Long.toString(timestamp));
        String  s1 = account.toLowerCase()+ id + amount  +  Long.toString(timestamp)+tokenName.toLowerCase() + chainId;
        byte[] contentHashBytes = Hash.sha3(s1.getBytes());
        String contentHashHex = Hex.toHexString(contentHashBytes);
        Credentials credentials = Credentials.create(priKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        map.put("signResultData",signResultData);
        return  map;
    }


    public Map<String, Object> provideCetProof(String account,String amount,String chainId){
        Map<String, Object> map = new HashMap<>();
        long timestamp = System.currentTimeMillis()/1000;
        HttpCetPrice httpCetPrice = new HttpCetPrice();
        String responseSB = httpCetPrice.getCetUrl();
        JSONObject jsonObject= JSONObject.parseObject(responseSB.toString());
        String  price_ = JSONObject.parseObject(JSONObject.parseObject(JSONObject.parseObject(jsonObject.get("RAW").toString()).get("CET").toString()).get("USD").toString()).get("PRICE").toString();
        BigDecimal price = new BigDecimal(price_);
        BigDecimal dec  = new BigDecimal("1000000000000000000");
        String price_cet = dec.multiply(price).setScale(0, BigDecimal.ROUND_DOWN).toString();

        map.put("amount",amount);
        map.put("price_cet",price_cet);
        map.put("account",account);
        map.put("timestamp", Long.toString(timestamp));
        map.put("chainId",chainId);
        String  s1 = amount + price_cet + account.toLowerCase()+ timestamp +chainId;
        byte[] contentHashBytes = Hash.sha3(s1.getBytes());
        String contentHashHex = Hex.toHexString(contentHashBytes);
        Credentials credentials = Credentials.create(priKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        map.put("signResultData",signResultData);
        return  map;
    }

//    public Map<String, Object> batchProvideErc1155Proof(String account, String[] ids,String[] amounts,String tokenAddress,String chainId){
//        Map<String, Object> map = new HashMap<>();
//
//        List idsList = new ArrayList();
//        List amountsList = new ArrayList();
//
//        for (int i =0 ;i< ids.length;i++){
//            idsList.add(ids[i]);
//        }
//        for (int i =0 ;i< amounts.length;i++){
//            amountsList.add(amounts[i]);
//        }
//        List timeList = new ArrayList<>();
//        List signList = new ArrayList();
//
//        for (int i = 0;i< ids.length;i++) {
//            long timestamp = System.currentTimeMillis();
//            String  s1 = account.toLowerCase()+ ids[i] + amounts[i]  +  Long.toString(timestamp)+tokenAddress + chainId;
//            byte[] contentHashBytes = Hash.sha3(s1.getBytes());
//            String contentHashHex = Hex.toHexString(contentHashBytes);
//            Credentials credentials = Credentials.create(priKey);
//            Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
//            String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
//            timeList.add(timestamp);
//            signList.add(signResultData);
//        }
//        map.put("account",account);
//        map.put("id",idsList);
//        map.put("amount",amountsList);
//        map.put("tokenAddress",tokenAddress);
//        map.put("timestamp",timeList);
//        map.put("chainId",chainId);
//        map.put("signResultData",signList);
//        return  map;
//    }



    public Map<String, Object> gameUpgradeProofWihtChianId(String[] oldIds , String[] oldAmounts, String account, String newId, String amount, String  tokenAddress, String chainId){
        Map<String, Object> map = new HashMap<>();
        List oldIdList = new ArrayList();
        List oldAmountsList = new ArrayList();
        for (int i =0 ;i< oldIds.length;i++){
            oldIdList.add(oldIds[i]);
        }

        for (int i =0 ;i< oldAmounts.length;i++){
            oldAmountsList.add(oldAmounts[i]);
        }

        List timeList = new ArrayList<>();
        List signList = new ArrayList();
        for (int i = 0;i< oldIds.length;i++){
            long timestamp = System.currentTimeMillis();
            String  s1 = oldIds[i].toString()+oldAmounts[i].toString()+account.toLowerCase() + newId + amount + tokenAddress.toLowerCase() + Long.toString(timestamp)+ chainId;
            byte[] contentHashBytes = Hash.sha3(s1.getBytes());
            String contentHashHex = Hex.toHexString(contentHashBytes);
            Credentials credentials = Credentials.create(priKey);
            Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
            String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
            timeList.add(timestamp);
            signList.add(signResultData);
        }
        map.put("oldIds", oldIdList);
        map.put("oldAmounts",oldAmountsList);
        map.put("account",account);
        map.put("newId",newId);
        map.put("amount",amount);
        map.put("tokenAddress",tokenAddress);
        map.put("timestamp",timeList);
        map.put("chainId",chainId);
        map.put("signResultData",signList);
        return  map;
    }


    public Map<String,Object> roleUpgradeProofWithChainId(String account,String[] oldIds,String newId,String chainId,String tokenAddress){
        Map<String, Object> map = new HashMap<>();
        List oldIdList = new ArrayList();
        for (int i = 0; i < oldIds.length; i++) {
            oldIdList.add(oldIds[i]);
        }
        System.out.println("account"+account.toLowerCase() );
        List timeList = new ArrayList<>();
        List signList = new ArrayList();
        for (int i = 0; i < oldIds.length; i++) {
            long timestamp = System.currentTimeMillis();
            String s1 = account.toLowerCase() + oldIds[i] + newId + Long.toString(timestamp) + tokenAddress.toLowerCase() + chainId;

            byte[] contentHashBytes = Hash.sha3(s1.getBytes());
            String contentHashHex = Hex.toHexString(contentHashBytes);
            Credentials credentials = Credentials.create(priKey);
            Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
            String signResultData = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
            timeList.add(timestamp);
            signList.add(signResultData);
        }
        map.put("oldIds", oldIdList);
        map.put("account", account);
        map.put("newId", newId);
        map.put("tokenAddress", tokenAddress);
        map.put("timestamp", timeList);
        map.put("chainId", chainId);
        map.put("signResultData", signList);
        return map;
    }
}
