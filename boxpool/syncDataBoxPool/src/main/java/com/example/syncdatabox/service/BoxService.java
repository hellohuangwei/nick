package com.example.syncdatabox.service;

import com.example.syncdatabox.dao.BoxDao;
import com.example.syncdatabox.mapper.MerkelMapper;
import com.example.syncdatabox.pojo.MerkelPojo;
import com.example.syncdatabox.pojo.PurchasePojo;
import com.example.syncdatabox.pojo.RewardPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoxService {

    @Autowired
    private BoxDao boxDao;


    public void insertUserData(PurchasePojo purchasePojo){
        boxDao.insertUserData(purchasePojo.getAccount(),purchasePojo.getPrice(),purchasePojo.getRandomNum(),
                purchasePojo.getTimestamp(),purchasePojo.getAward(),purchasePojo.getPeroid(),purchasePojo.getApply(),purchasePojo.getTxHash());
    }


    //updateAward
    public void insertReward(RewardPojo rewardPojo){
    boxDao.insertRewardData(rewardPojo.getAccount(),rewardPojo.getRandomNum(),rewardPojo.getTimestamp(),rewardPojo.getPeroid(),rewardPojo.getTxHash());
    }

    //updateAward
    public void insertApply(RewardPojo rewardPojo){
        boxDao.insertApplydata(rewardPojo.getAccount(),rewardPojo.getRandomNum(),rewardPojo.getTimestamp(),rewardPojo.getPeroid(),rewardPojo.getTxHash());
    }


    public List<MerkelPojo> getUserProofData(int n){
        return boxDao.getMemkerProof(n);
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

}
