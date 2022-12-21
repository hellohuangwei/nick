package com.example.syncdatabox.service;


import com.example.syncdatabox.dao.BlockNumberDao;
import com.example.syncdatabox.dao.BoxDao;
import com.example.syncdatabox.pojo.PurchasePojo;
import com.example.syncdatabox.pojo.RewardPojo;
import com.example.syncdatabox.utils.Web3Intence;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.BytesType;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.tx.Contract;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.web3j.abi.datatypes.Type.MAX_BYTE_LENGTH;

@Service
public class ApplyService {
    private  static  Web3j web3j;
    private  static BigInteger start;
    private static BigInteger end;

    @Autowired
    private BlockNumberDao blockNumberDao;

    @Autowired
    private  BoxService boxService;

    @Autowired
    private BoxDao boxDao;

    private static final List outputParameters = new ArrayList<TypeReference<Type>>();

    public static final Event purchase_EVENT = new Event("applyForEvent",
            Arrays.asList(new TypeReference<Address>(false) {
            },new TypeReference<Uint256>(false) {
            },new TypeReference<Uint256>(false) {
            },new TypeReference<Uint256>(false) {
            }));

    public static final String purchase_Topic = EventEncoder.encode(purchase_EVENT);

    static String encodeBytes(BytesType bytesType) {
        byte[] value = bytesType.getValue();
        int length = value.length;
        int mod = length % MAX_BYTE_LENGTH;

        byte[] dest;
        if (mod != 0) {
            int padding = MAX_BYTE_LENGTH - mod;
            dest = new byte[length + padding];
            System.arraycopy(value, 0, dest, 0, length);
        } else {
            dest = value;
        }
        return Numeric.toHexStringNoPrefix(dest);
    }

    @SneakyThrows
    @Scheduled(fixedDelay = 4 * 1000)
    public  void  get(){
//        web3j = Web3Intence.getInstance("https://bsc-daaseed1.defibit.io");
        web3j = Web3Intence.getInstance("https://api.avax-test.network/ext/bc/C/rpc");
        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");

        String number_start = blockNumberDao.showContractInfo(2).getStart();
        String number_end = blockNumberDao.showContractInfo(2).getEnd();

        start  = new BigInteger(number_start);
        end  = new BigInteger(number_end);
//0xaf3F9274A435BE60f5F3392749D81eCBFaD51010
//        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(start),DefaultBlockParameter.valueOf(end),"0xB7fFD9476d8Ac96a275F0FA095c4bd7916Ee1Cc8");
        EthFilter filter = new EthFilter( DefaultBlockParameter.valueOf(start),DefaultBlockParameter.valueOf(end),"0xeBC0d1e62f929Ec47ec9E71AC03FDd78E274E3D9");
        filter.addSingleTopic(purchase_Topic);
        EthLog logs = null;
        try {
            logs = web3j.ethGetLogs(filter).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<EthLog.LogResult> logsList = logs.getLogs();

        EthBlockNumber blockNumber = null;
        try {
            blockNumber = web3j.ethBlockNumber().send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BigInteger num = blockNumber.getBlockNumber();

        if (logsList != null){
            try {
                for (int i =0;i<logsList.size();i++){
                    System.out.println("开始解析"+i);
                    EventValues eventValues = Contract.staticExtractEventParameters(purchase_EVENT, (Log) logsList.get(i));
                    List<Type> delogResults =   eventValues.getNonIndexedValues();
                    RewardPojo rewardPojo = new RewardPojo();
                    rewardPojo.setAccount(String.valueOf(delogResults.get(0).getValue()));
                    rewardPojo.setPeroid(Integer.valueOf(delogResults.get(1).getValue().toString()));
                    rewardPojo.setRandomNum(Integer.valueOf(delogResults.get(2).getValue().toString()));
                    rewardPojo.setTimestamp(String.valueOf(delogResults.get(3).getValue()));
                    rewardPojo.setTxHash(((Log) logsList.get(i)).getTransactionHash());
                    boxDao.updateApply(rewardPojo.getRandomNum(),1);
                    boxService.insertApply(rewardPojo);
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("4秒没有产生区块");
            }
        }
        BigInteger distance = num.subtract(end);

        if(distance.compareTo(new BigInteger("2047")) == 1) {
            start = end.add(new BigInteger("1"));
            end = start.add(new BigInteger("2047"));
            blockNumberDao.setBlockNumberPojo(start.toString(), end.toString(), 2);
        }
        if(distance.compareTo(new BigInteger("0")) == 1 && distance.compareTo(new BigInteger("2048")) == -1 ) {            start = end.add(new BigInteger("1"));
            end = end.add(distance);
            blockNumberDao.setBlockNumberPojo(start.toString(), end.toString(), 2);
        }
//        System.out.println("提交订单服务start"+start);
//        System.out.println("提交订单服务end"+end);
//        System.out.println("提交订单服务blockNumber"+ num);
    }
}

