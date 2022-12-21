package com.example.cyerbpopMarket.utils;


import com.example.cyerbpopMarket.dao.BlockNumberDao;
import com.example.cyerbpopMarket.pojo.CoinPojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSyc {

    @Autowired
    private static BlockNumberDao blockNumberDao;

    private static Web3j web3j;

    public static final Event WITHDRAWCOIN_EVENT = new Event("withdrawCoinEvent",
            Arrays.asList(new TypeReference<Address>(false) {
            }, new TypeReference<Uint256>(false) {
            }, new TypeReference<Uint256>(false) {
            }));

    public static final String WITHDRAWCOIN_TOPIC = EventEncoder.encode(WITHDRAWCOIN_EVENT);


    public static void main(String[] args) throws IOException {
        Web3j web3j = Web3Intence.getInstance("https://api.avax-test.network/ext/bc/C/rpc");
        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");
        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(BigInteger.valueOf(9704723)),DefaultBlockParameter.valueOf(BigInteger.valueOf(9706723)),"0xAD3Da4A693a7469105a93675A00A789A74461202");
        filter.addSingleTopic(WITHDRAWCOIN_TOPIC);
        EthLog logs = web3j.ethGetLogs(filter).send();
        List<EthLog.LogResult> logsList = logs.getLogs();
        List<CoinPojo> logsResultList = new ArrayList<>();

        for (int i =0;i<logsList.size();i++){
            EventValues  eventValues = Contract.staticExtractEventParameters(WITHDRAWCOIN_EVENT, (Log) logsList.get(i));
            System.out.println( ((Log) logsList.get(i)).getTransactionHash());
            List<Type> delogResults =   eventValues.getNonIndexedValues();

            CoinPojo coinPojo =new CoinPojo();
            coinPojo.setCall((String) delogResults.get(0).getValue());
            coinPojo.setAmount((BigInteger) delogResults.get(1).getValue());
            coinPojo.setTimestamp((BigInteger) delogResults.get(2).getValue());
            logsResultList.add(coinPojo);
            System.out.println(coinPojo.getCall());
        }

//        blockNumberDao = new BlockNumberDao();
//        SyncBlockNumberPojo syncBlockNumberPojo = blockNumberDao.showContractInfo();
//        System.out.println();
//        EventValues eventValues = Contract.staticExtractEventParameters(WITHDRAWCOIN_EVENT,log);
//        List<Type> list3 = eventValues.getNonIndexedValues();
//        System.out.println(list3.get(2).getValue());
    }
}
