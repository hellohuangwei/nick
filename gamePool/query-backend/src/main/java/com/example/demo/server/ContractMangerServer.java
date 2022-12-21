package com.example.demo.server;

import com.example.demo.dao.ContractDao;
import com.example.demo.pojo.ContractPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ContractMangerServer {

    @Autowired
    private ContractPojo contractPojo;

    @Autowired
    private ContractDao contractDao;

    public boolean setContractInfo(int chainId, String credentials, String contractAddress, String chainRpc, BigInteger gasPrice, BigInteger gasLimit) {
        contractDao.insertContractdata(chainId, credentials, contractAddress, chainRpc, gasPrice, gasLimit);
        return true;
    }

    public ContractPojo getContractInfo(int chainId) {
        return contractDao.showContractInfo(chainId);
    }

    public boolean updateContractInformation(long chainId, String credentials, String contractAddress, String chainRpc, BigInteger gasPrice, BigInteger gasLimit) {
        return contractDao.updateContractData(chainId, credentials, contractAddress, chainRpc, gasPrice, gasLimit);
    }
}
