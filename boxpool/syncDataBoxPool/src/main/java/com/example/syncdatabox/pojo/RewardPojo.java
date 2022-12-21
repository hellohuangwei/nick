package com.example.syncdatabox.pojo;

public class RewardPojo {

    String txHash;
    String timestamp;
    String account;
    int peroid;
    int randomNum;

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getPeroid() {
        return peroid;
    }

    public void setPeroid(int peroid) {
        this.peroid = peroid;
    }

    public int getRandomNum() {
        return randomNum;
    }

    public void setRandomNum(int randomNum) {
        this.randomNum = randomNum;
    }

}
