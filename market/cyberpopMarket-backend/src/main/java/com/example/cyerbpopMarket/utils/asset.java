package com.example.cyerbpopMarket.utils;

import lombok.extern.slf4j.Slf4j;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.Log;

import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.methods.response.EthLog.LogResult;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.tx.Contract;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
class asset {

    private static final BigInteger gasPrice = new BigInteger("10000000000");
    private static final BigInteger gasLimit = new BigInteger("100000");
    private static Hash hash;

    public static final Event TRANSFER_EVENT = new Event("withdrawCoinEvent",
            Arrays.asList(new TypeReference<Address>(false) {
            }, new TypeReference<Uint256>(false) {
            }, new TypeReference<Uint256>(false) {
            }));

    public static final String TRANSFER_TOPIC = EventEncoder.encode(TRANSFER_EVENT);
    public static final HashMap<String, Event> eventMap = new HashMap<String, Event>() {
        {
            put(TRANSFER_TOPIC, TRANSFER_EVENT);
        }
    };

    public static byte[] StrToByte(String str) {
        byte[] byteValue = str.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return byteValueLen32;
    }
    @SuppressWarnings("rawtypes")
    public static List<LogResult> getEthLogs(BigInteger start, BigInteger end, List<String> address) throws InterruptedException, ExecutionException, IOException {
        List<LogResult> list = new ArrayList<>();
        List<LogResult> temp = null;
        for (String addr : address) {
            temp = getEthLogs(start, end, addr);
            if (null != temp && !temp.isEmpty()) {
                list.addAll(temp);
            }
        }
        return list;
    }

    @SuppressWarnings("rawtypes")
    public static List<LogResult> getEthLogs(BigInteger start, BigInteger end, String address) throws InterruptedException, ExecutionException, IOException {
        EthFilter filter = new EthFilter(new DefaultBlockParameterNumber(start), new DefaultBlockParameterNumber(end),
                address);
        Web3j web3j = Web3j.build(new HttpService("https://api.avax-test.network/ext/bc/C/rpc"));
        EthLog log = web3j.ethGetLogs(filter).send();
        if (log.hasError()) {
            System.out.println(log.getError().getMessage());
            throw new RuntimeException(log.getError().getMessage());
        } else {
            return log.getLogs();
        }
    }

    private static EventValuesExt decodeLog(LogResult<Log> logResult, Event event, Map<String, BigInteger> timeMap) {
        EventValues eventValues = Contract.staticExtractEventParameters(event, logResult.get());
        Web3j web3j = Web3j.build(new HttpService("https://api.avax-test.network/ext/bc/C/rpc"));
        //????
        BigInteger timestamp = timeMap.get(logResult.get().getAddress());
        //null
        if (null == timestamp) {
            try {
                timestamp = web3j.ethGetBlockByHash(logResult.get().getBlockHash(), false).send().getBlock().getTimestamp();
                //时间戳
            } catch (IOException e) {
                throw new RuntimeException("get block timestamp error");
            }
        }
        EventValuesExt val = new EventValuesExt(eventValues, logResult.get().getTransactionHash(), logResult.get().getAddress(),
                logResult.get().getBlockNumber(), timestamp);
        System.out.println(val.getAddress());
        return val;
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map<String, List<EventValuesExt>> decodeLog(List<LogResult> logList, Map<String, Event> eventMap) {
        Map<String, List<EventValuesExt>> map = new HashMap<>();
        if (null != logList && !logList.isEmpty()) {
            Map<String, BigInteger> timeMap = new HashMap<>();
            logList.forEach(log -> {
                eventMap.keySet().forEach(topic -> {
                    if (((Log) log.get()).getTopics().contains(topic)) {
                        if (null == map.get(topic)) {
                            map.put(topic, new ArrayList<>());
                        }
                        map.get(topic).add(decodeLog(log, eventMap.get(topic), timeMap));
                    }
                });
            });
        }
        return map;
    }


    public static void main(String[] args) throws Exception {
//        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));

        Web3j web3j = Web3Intence.getInstance("https://api.avax-test.network/ext/bc/C/rpc");
//        Web3j web3j = Web3j.build(new HttpService("https://http-testnet.hecochain.com"));
//        Web3j web3j = Web3j.build(new HttpService("https://matic-mumbai.chainstacklabs.com"));
        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");
////encodeABI
        System.out.println("Welcome " + credentials.getAddress());
        EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
        BigInteger num = blockNumber.getBlockNumber();
        System.out.println(blockNumber.getBlockNumber());
        BigInteger n1 = new BigInteger("2");
        System.out.println(n1.add(new BigInteger("2")));

//        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(BigInteger.valueOf(9344612)),DefaultBlockParameter.valueOf(BigInteger.valueOf(9705142)),"0xAD3Da4A693a7469105a93675A00A789A74461202");
//        EthLog logs = web3j.ethGetLogs(filter).send();
//        Log log = (Log) logs.getLogs().get(0);
//        System.out.println(logs.getLogs().get(0));
//        List results = FunctionReturnDecoder.decode(log.getData(),TRANSFER_EVENT.getNonIndexedParameters());
//        System.out.println(log.getTopics().contains(TRANSFER_TOPIC));
//
//        EventValues eventValues = Contract.staticExtractEventParameters(TRANSFER_EVENT,log);
//        List<Type> list2 = eventValues.getIndexedValues();
//        List<Type> list3 = eventValues.getNonIndexedValues();
//        System.out.println(list3.get(2).getValue());

        //         EventValuesExt e = (EventValuesExt) decodeLog(logs,TRANSFER_TOPIC,TRANSFER_EVENT);
//        System.out.println(e.getAddress());
//        ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
//        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 5777);
//        web3j.ethSign("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47",hash.sha3("blockchain"));
//        Storage awToken = Storage.deploy(web3j, credentials, web3j.ethGasPrice().send().getGasPrice(), Contract.GAS_LIMIT).send();
//        System.out.println(awToken.getContractAddress());
//        Erc1155Asset contract = Erc1155Asset.load("0x4159Fa4e017061C27073308a166A071c97Ed9C68",web3j,transactionManager,contractGasProvider);
//        byte[] data = new byte[]{(byte)0x0};
//        System.out.println(contract.grantRole(role,"0x8d0004bfdb832b5a70957969c9fd2decb620acc571548f215ca933f039fc2d7e").send());
//        System.out.println(contract.mint("0xFD0cd49de3e8526fcE2854b40D9f9ef9c74dFb0f",new BigInteger("1"),new BigInteger("10"),data).send());
//        ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);

//        byte[] data = new byte[]{(byte) 0x0};
//        Owner erc721 = Owner.load("0x69F902143c4A04875942688455747280d177184e",web3j,transactionManager,contractGasProvider);
//        GameItem erc721 = GameItem.load("0x69F902143c4A04875942688455747280d177184e", web3j, transactionManager, contractGasProvider);
//        System.out.println(23);
//        TransactionReceipt s = erc721.createGamePrope("0x5A0D243a4E1E27daE95EFc521237F9B41d8caCd5", new BigInteger("2500"), new BigInteger("2323"), data).send();
//        System.out.println(s);
//        erc721.getId().decodeFunctionResponse(erc721.getId().send().getLogs().get());
//        System.out.println(s.getLogsBloom());
//        System.out.println( erc721.getId().decodeFunctionResponse(s.getBlockHash()));
//        System.out.println();

//        String l = erc721.ownerOf(new BigInteger("2")).encodeFunctionCall();  //List<byte[]> list = new List();
//        System.out.println(l);
//        System.out.println(l.getBytes());
//        byte[] data = new byte[]{(byte)};
//        List<byte[]> data2 = new ArrayList<>();
//        data2.add(l.getBytes());
//        System.out.println(erc721.multicall(data2).sendAsync().get());
//        byte[] data = new  byte[]{(String)l};
//         erc721.multicall(l);

//        multicall.multicall(erc721.ownerOf(new BigInteger("1")).encodeFunctionCall());

//        System.out.println(transactionReceipt);
//        BigInteger id = new BigInteger("2");
//        System.out.println(contract.balanceOf("0xFD0cd49de3e8526fcE2854b40D9f9ef9c74dFb0f",new BigInteger("10")).sendAsync().get());

//        BigInteger b = contract.balanceOf("0xFD0cd49de3e8526fcE2854b40D9f9ef9c74dFb0f",new BigInteger("1")).sendAsync().get();
//        System.out.println(b);
//        System.out.println(contract.mint("0xFD0cd49de3e8526fcE2854b40D9f9ef9c74dFb0f",new BigInteger("1"),new BigInteger("20"),data));
//        ERC20 erc20 = ERC20.load("0xA76755Ff847AF6eD36f80672C711789e3A2B8E46",web3j,transactionManager,contractGasProvider);
//        System.out.println( erc20.decimals().sendAsync().get());
}
}