package com.example.demo.pojo;

import org.springframework.stereotype.Component;

@Component
public class LoadGamePropePojo {

    private String accountAddress;
    private String gamePoolAddress;
    private String loadTime;
    private String tokenId;
    private String amount;

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public String getGamePoolAddress() {
        return gamePoolAddress;
    }

    public void setGamePoolAddress(String gamePoolAddress) {
        this.gamePoolAddress = gamePoolAddress;
    }

    public String getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(String loadTime) {
        this.loadTime = loadTime;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
