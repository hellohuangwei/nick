package com.example.demo.server;

import com.example.demo.utils.Web3jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class AssetServer {

    @Autowired
    Web3jUtil contract;

    public boolean grantRole(int chianId,String contractAddress, byte[] role, String account) {
        try {
            contract.getErc1155AssetContract(chianId,contractAddress).grantRole(role, account).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean mint(int chainId,String contractAddress, String to, BigInteger id, BigInteger amount, byte[] data) {
        try {
            contract.getGameItemContract(chainId,contractAddress).createGamePrope(to, id, amount, data).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public BigInteger balanceOf(int chainId,String contractAddress, String account, BigInteger id) {
        BigInteger balance = null;
        try {
            balance = contract.getErc1155AssetContract(chainId,contractAddress).balanceOf(account, id).sendAsync().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public List balanceOfBatch(int chainId,String contractAddress, String accounts_, List<BigInteger> ids) {
        List list = null;
        List<String> accounts = new ArrayList<>(Collections.nCopies(ids.size(), accounts_));

        try {
              list =  contract.getErc1155AssetContract(chainId,contractAddress).balanceOfBatch(accounts, ids).sendAsync().get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List tokensOfOwner(String accounts, int chainId,String contractAddress) {
        List list = null;
        try {
            list = contract.getErc721AssetContract(chainId,contractAddress).tokensOfOwner(accounts).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        for (int i=0;i<list.size();i++){
            list.set(i, list.get(i).toString());
        }
        return list;
    }

    public  BigInteger allowanceOfErc20(String accounts,String contractAddress,int chainId,String spender){
        BigInteger value = null;
        try {
           value = contract.getErc20AssetContract(chainId,contractAddress).allowance(accounts,spender).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return value;
    }



    public BigInteger balanceOfErc20(String accounts,int chainId ,String contractAddress){
        BigInteger result = null;
        try {
           result =  contract.getErc20AssetContract(chainId,contractAddress).balanceOf(accounts).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean isApprovedForAllForErc721(int chainId ,String contractAddress,String owner, String operator){
        boolean result = false;
        try {
           result = contract.getErc721AssetContract(chainId,contractAddress).isApprovedForAll(owner,operator).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean isApprovedForAllForErc1155(int chainId ,String contractAddress,String owner, String operator){
        boolean result = false;
        try {
            result = contract.getErc1155AssetContract(chainId,contractAddress).isApprovedForAll(owner,operator).sendAsync().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean approve(int chainId,String contractAddress, String to, String tokenId){
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = contract.getErc721AssetContract(chainId, contractAddress).approve(to,new BigInteger(tokenId)).send();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (transactionReceipt.getStatus().equals("0x1"))
            return true;
        else
            return false;
    }

    public boolean transferFromNft(int chainId,String contractAddress,String from, String to, String tokenId){

        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = contract.getErc721AssetContract(chainId,contractAddress).transferFrom(from,to,new BigInteger(tokenId)).send();

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (transactionReceipt.getStatus().equals("0x1"))
            return true;
        else
            return false;
//        System.out.println(transactionReceipt.getStatus());
    }


    public boolean setApprovalForAll(int chainId,String contractAddress,String account,boolean operator){
        TransactionReceipt transactionReceipt = null;
        try {
            transactionReceipt = contract.getErc1155AssetContract(chainId,contractAddress).setApprovalForAll(account,operator).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (transactionReceipt.getStatus().equals("0x1"))
            return true;
        else
            return false;
    }

}
