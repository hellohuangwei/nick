//package com.example.demo.server;
//
//import com.example.demo.dao.GameItemDao;
//import com.example.demo.utils.CollectionConversionUtils;
//import com.example.demo.utils.Web3jUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.web3j.protocol.core.methods.response.TransactionReceipt;
//
//import java.math.BigInteger;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.zip.Adler32;
//
//@Service
//public class GameItemServer {
//
//    @Autowired
//    private GameItemDao gameItemDao;
//
//    @Autowired
//    private Web3jUtil contract;
//
//    public boolean loadingPrope(int chainId,String contractAddress,String tokenId) {
//        TransactionReceipt transactionReceipt = null;
//        try {
////           transactionReceipt  =  contract.getGamePoolContract(chainId,contractAddress).loadingNft(new BigInteger(tokenId)).send();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (transactionReceipt.getStatus().equals("0x1"))
//        return true;
//        else
//            return false;
//    }
//
//    public boolean  withdrawRole(int chainId,String contractAddress,String player, String tokenId){
//        TransactionReceipt transactionReceipt = null;
//        try {
//           transactionReceipt = contract.getGamePoolContract(chainId,contractAddress).withdrawRole(player,new BigInteger(tokenId)).send();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (transactionReceipt.getStatus().equals("0x1")){
//          return true;
//        }else {
//            return  false;
//        }
//    }
//
//    public  boolean loadingErc1155(int chainId,String contractAddress, String player, String id,String amount){
//        TransactionReceipt transactionReceipt = null;
//        try {
////            transactionReceipt = contract.getGamePoolContract(chainId,contractAddress).loadingErc1155(new BigInteger(id),new BigInteger(amount)).send();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (transactionReceipt.getStatus().equals("0x1")){
//            return true;
//        }else {
//            return  false;
//        }
//    }
//
//    public boolean withdrawGameProbe(int chainId,String contractAddress, String player,String[] ids,String[] amounts){
//        CollectionConversionUtils collectionConversionUtils = new CollectionConversionUtils();
//        TransactionReceipt transactionReceipt = null;
//        try {
//           transactionReceipt = contract.getGamePoolContract(chainId,contractAddress).withdrawGameProbe(player,collectionConversionUtils.StringToBigIntegerList(ids),collectionConversionUtils.StringToBigIntegerList(amounts)).send();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (transactionReceipt.getStatus().equals("0x1")){
//            return true;
//        }else {
//            return  false;
//        }
//    }
//
//    public boolean createNft(int chainId,String contractAddress, String player, String id) {
//        try {
//            contract.getGameItemContract(chainId,contractAddress).createNft(player, new BigInteger(id)).send();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
//    }
//
//
//    public boolean upgradePropeForErc1155(int chainId,String contractAddress, String player, String[] ids, String[] amounts, String newId, String newAmount) {
//        TransactionReceipt transactionReceipt = null;
//        CollectionConversionUtils collectionConversionUtils = new CollectionConversionUtils();
//        try {
////            transactionReceipt = contract.getGameItemContract(chainId,contractAddress).upgradePropeForErc1155(player, collectionConversionUtils.StringToBigIntegerList(ids),
////                    collectionConversionUtils.StringToBigIntegerList(amounts), new BigInteger(newId), new BigInteger(newAmount)).send();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (transactionReceipt.getStatus().equals("0x1")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public  boolean upgradePropeForNft(int chainId,String contractAddress,String player,String[] ids,String newId){
//        TransactionReceipt transactionReceipt = null;
//
//        CollectionConversionUtils collectionConversionUtils = new CollectionConversionUtils();
//        try {
////          transactionReceipt = contract.getGameItemContract(chainId,contractAddress).upgradePropeForNft(player,collectionConversionUtils.StringToBigIntegerList(ids),new BigInteger(newId)).send();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (transactionReceipt.getStatus().equals("0x1")) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }
//}
