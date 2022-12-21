package com.example.demo.utils;

import com.example.demo.contract.*;
import com.example.demo.dao.ContractDao;
import com.example.demo.pojo.ContractPojo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

@Component
public class Web3jUtil {

    @Autowired
    private ContractPojo contractPojo;

    @Autowired
    private ContractDao contractDao;

    public Erc1155Asset getErc1155AssetContract(int chainId,String contractAddress) {
        contractPojo = contractDao.showContractInfo(chainId);
        Web3j web3j = null;
        if(chainId == 43113){
            web3j = FujiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 80001){
            web3j = MumbaiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 56){
            web3j = BscWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 53){
            web3j = CscTestWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 52){
            web3j = CscWeb3Intence.getInstance(contractPojo.getChainRpc());
        }

        Credentials credentials = Credentials.create(contractPojo.getCredentials());
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, chainId);
        return Erc1155Asset.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());
    }

    @SneakyThrows
    public Cyborg getErc721AssetContract(int chainId, String contractAddress) {
        contractPojo = contractDao.showContractInfo(chainId);
        Web3j web3j = null;
        if(chainId == 43113){
            web3j = FujiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 80001){
            web3j = MumbaiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 56){
            web3j = BscWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 52){
            web3j = CscWeb3Intence.getInstance(contractPojo.getChainRpc());
            EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
            System.out.println("区块高度");
            System.out.println(blockNumber.getBlockNumber());

        }
        if(chainId == 53){
            web3j = CscTestWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        Credentials credentials = Credentials.create(contractPojo.getCredentials());
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, chainId);
        return Cyborg.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());
    }

    public GameLogic getGameItemContract(int chainId,String contractAddress) {
        contractPojo = contractDao.showContractInfo(chainId);
        Web3j web3j = null;
        if(chainId == 43113){
            web3j = FujiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 80001){
            web3j = MumbaiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 56){
            web3j = BscWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        Credentials credentials = Credentials.create(contractPojo.getCredentials());
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, chainId);
        return GameLogic.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());
    }

    public GamePool getGamePoolContract(int chainId,String contractAddress) {
        contractPojo = contractDao.showContractInfo(chainId);
        Web3j web3j = null;
        if(chainId == 43113){
            web3j = FujiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 80001){
            web3j = MumbaiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 56){
            web3j = BscWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        Credentials credentials = Credentials.create(contractPojo.getCredentials());
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, chainId);
        return GamePool.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());
    }

    public ERC20 getErc20AssetContract(int chainId,String contractAddress){
        contractPojo = contractDao.showContractInfo(chainId);
        Web3j web3j = null;
        if(chainId == 43113){
            web3j = FujiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 80001){
            web3j = MumbaiWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 56){
            web3j = BscWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 52){
            web3j = CscWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        if(chainId == 53){
            web3j = CscTestWeb3Intence.getInstance(contractPojo.getChainRpc());
        }
        Credentials credentials = Credentials.create(contractPojo.getCredentials());
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, chainId);
        return ERC20.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());
    }
}