package com.example.boxpool.service;

import com.example.boxpool.dao.BoxDao;
import com.example.boxpool.pojo.MerkelPojo;
import com.example.boxpool.pojo.PurchasePojo;
import com.example.boxpool.pojo.RewardPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoxService {

    @Autowired
    private BoxDao boxDao;


    public void insertUserData(PurchasePojo purchasePojo){
        boxDao.insertUserData(purchasePojo.getAccount(),purchasePojo.getPrice(),purchasePojo.getRandomNum(),
                purchasePojo.getTimestamp(),purchasePojo.getAward(),purchasePojo.getPeroid(),purchasePojo.getTxHash());
    }

    //updateAward
    public void insertReward(RewardPojo rewardPojo){
    boxDao.insertRewardData(rewardPojo.getAccount(),rewardPojo.getRandomNum(),rewardPojo.getTimestamp(),rewardPojo.getPeroid(),rewardPojo.getTxHash());
    }

    public List<MerkelPojo> getUserProofData(int n){
        return boxDao.getMemkerProof(n);
    }

    public List<MerkelPojo> getHistoryPurchase(String account){
        return boxDao.getHistoryPurchase(account);
    }

    public int countPurchaseNumber(int n){
        return boxDao.numberCount(n);
    }

    public  List getMemkerProof(int peroid) {
        return boxDao.getMemkerProof(peroid);
    }

    public boolean insterRandomData(String root,int peroid,String randomStr){
          boxDao.insterRandomData(root,peroid,randomStr);
        return true;
    }

    public  List getRewardList(int peroid,int award) {
        return boxDao.getRewardList(peroid,award);
    }


    public  List getApplyList(int peroid,int award) {
        return boxDao.getApplyList(peroid,award);
    }

    public String getRewardNumberList(int peroid){
        return  boxDao.getRewardNumberList(peroid);
    }
}
