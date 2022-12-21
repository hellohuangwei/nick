package com.example.demo.test;

import com.example.demo.contract.GameItem;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

@Slf4j
class asset {

    private static final BigInteger gasPrice = new BigInteger("10000000000");
    private static final BigInteger gasLimit = new BigInteger("100000");

    private static Hash hash;

    public static byte[] StrToByte(String str) {
        byte[] byteValue = str.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return byteValueLen32;
    }

    public static void main(String[] args) throws Exception {
        Web3j web3j = Web3j.build(new HttpService("https://rpc.coinex.net"));

//        Web3j web3j = Web3j.build(new HttpService("https://api.avax-test.network/ext/bc/C/rpc"));
//        Web3j web3j = Web3j.build(new HttpService("https://http-testnet.hecochain.com"));
//        Web3j web3j = Web3j.build(new HttpService("https://matic-mumbai.chainstacklabs.com"));
        Credentials credentials = Credentials.create("0xfd0cd49de3e8526fce2854b40d9f9");
//encodeABI
        System.out.println("Welcome " + credentials.getAddress());
        EthBlockNumber blockNumber = web3j.ethBlockNumber().send();

        System.out.println(blockNumber.getBlockNumber());

//        ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 5777);
//        web3j.ethSign("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47",hash.sha3("blockchain"));
//        Storage awToken = Storage.deploy(web3j, credentials, web3j.ethGasPrice().send().getGasPrice(), Contract.GAS_LIMIT).send();
//        System.out.println(awToken.getContractAddress());
//        Erc1155Asset contract = Erc1155Asset.load("0x4159Fa4e017061C27073308a166A071c97Ed9C68",web3j,transactionManager,contractGasProvider);
//        byte[] data = new byte[]{(byte)0x0};
//        System.out.println(contract.grantRole(role,"0x8d0004bfdb832b5a70957969c9fd2decb620acc571548f215ca933f039fc2d7e").send());
//        System.out.println(contract.mint("0xFD0cd49de3e8526fcE2854b40D9f9ef9c74dFb0f",new BigInteger("1"),new BigInteger("10"),data).send());
        ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);

        byte[] data = new byte[]{(byte) 0x0};
//        Owner erc721 = Owner.load("0x69F902143c4A04875942688455747280d177184e",web3j,transactionManager,contractGasProvider);
        GameItem erc721 = GameItem.load("0x69F902143c4A04875942688455747280d177184e", web3j, transactionManager, contractGasProvider);
        System.out.println(23);
        TransactionReceipt s = erc721.createGamePrope("0x5A0D243a4E1E27daE95EFc521237F9B41d8caCd5", new BigInteger("2500"), new BigInteger("2323"), data).send();
        System.out.println(s);
//        erc721.getId().decodeFunctionResponse(erc721.getId().send().getLogs().get());
//        System.out.println(s.getLogsBloom());
//        System.out.println( erc721.getId().decodeFunctionResponse(s.getBlockHash()));
        System.out.println();

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
//
    }
}
