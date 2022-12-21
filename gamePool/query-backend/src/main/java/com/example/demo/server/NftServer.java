package com.example.demo.server;

import com.example.demo.utils.Web3jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class NftServer {


    @Autowired
    private Web3jUtil contract;

    public BigInteger withdrawPrope(String player, String tokenId) {
//        contract.ge

        return new BigInteger("3");
    }

}
