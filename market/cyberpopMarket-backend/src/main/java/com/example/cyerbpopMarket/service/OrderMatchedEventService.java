//package com.example.cyerbpopMarket.service;
//
//import com.example.cyerbpopMarket.dao.BlockNumberDao;
//import com.example.cyerbpopMarket.pojo.OrderInfoPojo;
//import com.example.cyerbpopMarket.pojo.OrderMatchPojo;
//import com.example.cyerbpopMarket.utils.Web3Intence;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.web3j.abi.EventEncoder;
//import org.web3j.abi.EventValues;
//import org.web3j.abi.FunctionReturnDecoder;
//import org.web3j.abi.TypeReference;
//import org.web3j.abi.datatypes.*;
//import org.web3j.abi.datatypes.generated.Bytes32;
//import org.web3j.abi.datatypes.generated.Uint256;
//import org.web3j.crypto.Credentials;
//import org.web3j.crypto.Hash;
//import org.web3j.protocol.Web3j;
//import org.web3j.protocol.core.DefaultBlockParameter;
//import org.web3j.protocol.core.methods.request.EthFilter;
//import org.web3j.protocol.core.methods.response.EthBlockNumber;
//import org.web3j.protocol.core.methods.response.EthLog;
//import org.web3j.protocol.core.methods.response.Log;
//import org.web3j.tx.Contract;
//import org.web3j.utils.Numeric;
//
//import java.io.IOException;
//import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.web3j.abi.datatypes.Type.MAX_BYTE_LENGTH;
//
//@Service
//public class OrderMatchedEventService {
//
//    @Autowired
//    private  OrderInfoService orderInfoService;
//
//    @Autowired
//    private BlockNumberDao blockNumberDao;
//
//    private  static Web3j web3j;
//    private  static BigInteger start;
//    private static BigInteger end;
//
//    private static final List outputParameters = new ArrayList<TypeReference<Type>>();
//
//    public static final Event orderMatch_Event = new Event("orderMatchEvent",
//            Arrays.asList(new TypeReference<Address>(false) {
//            }, new TypeReference<Address>(false) {
//            }, new TypeReference<Bytes32>(false) {
//            }, new TypeReference<Bytes32>(false) {
//            }, new TypeReference<Uint256>(false) {
//            }));
//
//    public static final String orderMatch_Topic = EventEncoder.encode(orderMatch_Event);
//
//    static String encodeBytes(BytesType bytesType) {
//        byte[] value = bytesType.getValue();
//        int length = value.length;
//        int mod = length % MAX_BYTE_LENGTH;
//
//        byte[] dest;
//        if (mod != 0) {
//            int padding = MAX_BYTE_LENGTH - mod;
//            dest = new byte[length + padding];
//            System.arraycopy(value, 0, dest, 0, length);
//        } else {
//            dest = value;
//        }
//        return Numeric.toHexStringNoPrefix(dest);
//    }
//
//    @SneakyThrows
//    @Scheduled(fixedDelay = 3 * 1000)
//    public  void  get(){
//        web3j = Web3Intence.getInstance("https://api.avax-test.network/ext/bc/C/rpc");
//        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");
//
//        String number_start = blockNumberDao.showContractInfo(2).getStart();
//        String number_end = blockNumberDao.showContractInfo(2).getEnd();
//
//        start  = new BigInteger(number_start);
//        end  = new BigInteger(number_end);
//        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(start),DefaultBlockParameter.valueOf(end),"0x3e7ef740027a963d215Bd24B70BEC62d4a39fded");
//        filter.addSingleTopic(orderMatch_Topic);
//        EthLog logs = null;
//        try {
//            logs = web3j.ethGetLogs(filter).send();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<EthLog.LogResult> logsList = logs.getLogs();
//
//        EthBlockNumber blockNumber = null;
//        try {
//            blockNumber = web3j.ethBlockNumber().send();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BigInteger num = blockNumber.getBlockNumber();
//
//        if (logsList != null){
//            try {
//                for (int i =0;i<logsList.size();i++){
//                    System.out.println("开始解析"+i);
//                    EventValues eventValues = Contract.staticExtractEventParameters(orderMatch_Event, (Log) logsList.get(i));
//
//                    List<Type> delogResults =  eventValues.getNonIndexedValues();
//
//                    OrderMatchPojo orderMatchPojo = new OrderMatchPojo();
//                    orderMatchPojo.setFirstCaller(delogResults.get(0).getValue().toString());
//                    orderMatchPojo.setSeccondCaller(delogResults.get(1).getValue().toString());
//                    orderMatchPojo.setFirstHashOrder("0x" + encodeBytes( (BytesType) delogResults.get(2)));
//                    orderMatchPojo.setSeccondHashOrder("0x" + encodeBytes( (BytesType) delogResults.get(3)));
//                    orderMatchPojo.setTimestamp(delogResults.get(4).getValue().toString());
//
//                    orderInfoService.insertOrderMatchedData(orderMatchPojo);
//                    orderInfoService.updateOrderState(orderMatchPojo.getFirstHashOrder(),2);
//                    orderInfoService.updateOrderState(orderMatchPojo.getSeccondHashOrder(),2);
//                    System.out.println("订单匹配成功交易hash为"+((Log) logsList.get(i)).getTransactionHash());
//
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//                System.out.println("4秒没有产生区块");
//            }
//
//        }
//        BigInteger distance = num.subtract(end);
//        if(distance.compareTo(new BigInteger("0")) == 1) {
//            start = end.add(new BigInteger("1"));
//            end = end.add(distance);
//            blockNumberDao.setBlockNumberPojo(start.toString(), end.toString(), 2);
//        }
//    }
//}
