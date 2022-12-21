package com.example.demo.utils;

import com.example.demo.contract.Sign2;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import static org.web3j.crypto.SignatureDataOperations.LOWER_REAL_V;
import static org.web3j.crypto.TransactionEncoder.encode;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Numeric;
import org.web3j.rlp.RlpEncoder;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@Slf4j
class test {


    private static final byte[] TEST_MESSAGE = "A test message".getBytes();

    private static final BigInteger gasPrice = new BigInteger("10000000000");
    private static final BigInteger gasLimit = new BigInteger("100000");

    private static Hash hash;

    public static byte[] StrToByte(String str) {
        byte[] byteValue = str.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        return byteValueLen32;
    }


    public static byte getRealV(BigInteger bv) {
        long v = bv.longValue();
        if (v == LOWER_REAL_V || v == (LOWER_REAL_V + 1)) {
            return (byte) v;
        }
        byte realV = LOWER_REAL_V;
        int inc = 0;
        if ((int) v % 2 == 0) {
            inc = 1;
        }
        return (byte) (realV + inc);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] getEncodedTransaction(Long chainId) {
        return new byte[0];
    }

    public static void main(String[] args) throws Exception {

//        org.web3j.crypto.ECKeyPair
//        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
//        Web3j web3j = Web3j.build(new HttpService("https://api.avax-test.network/ext/bc/C/rpc"));
//        Web3j web3j = Web3j.build(new HttpService("https://http-testnet.hecochain.com"));
//        String hashed = Hash.sha3("block".getBytes(StandardCharsets.UTF_8));
        String str = bytesToHex(Hash.sha3("block".getBytes(StandardCharsets.UTF_8)));
        Long l = Long.valueOf(80001);

        byte[] encodedTransaction = getEncodedTransaction(l);
//        System.out.printf(str);

        Web3j web3j = Web3j.build(new HttpService("https://matic-mumbai.chainstacklabs.com"));
        Credentials credentials = Credentials.create("bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47");
        System.out.println(credentials.getEcKeyPair());
        Sign.SignatureData signatureData = Sign.signMessage(Hash.sha3("block".getBytes(StandardCharsets.UTF_8)), credentials.getEcKeyPair());
        BigInteger v = Numeric.toBigInt(signatureData.getV());
        byte[] r = signatureData.getR();
        byte[] s = signatureData.getS();
        System.out.println( Numeric.toBigInt(signatureData.getV()));
        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 80001);
//        Sign2 s = Sign2.load("",web3j, transactionManager, new DefaultGasProvider());
//      s.validSignatureNow(TEST_MESSAGE,signatureData.getV(),signatureData.getR(),signatureData.getR());
//        String str2 = bytesToHex(signatureData);
//        encode()
        Sign.SignatureData signatureDataV = new Sign.SignatureData(getRealV(v), r, s);
        BigInteger key = Sign.signedMessageToKey(encodedTransaction, signatureDataV);
        System.out.println( Keys.getAddress(key));

        System.out.println(str);
        System.out.println("----r------"+ bytesToHex(signatureData.getR()));
        System.out.println("-----s-------"+bytesToHex(signatureData.getS()));

        System.out.println(5);
//
//        Sign.SignatureData expected = new Sign.SignatureData(
//                (byte) 28,
//                Numeric.hexStringToByteArray(
//                        "0x0464eee9e2fe1a10ffe48c78b80de1ed8dcf996f3f60955cb2e03cb21903d930"),
//                Numeric.hexStringToByteArray(
//                        "0x06624da478b3f862582e85b31c6a21c6cae2eee2bd50f55c93c4faad9d9c8d7f"));

////encodeABI
//
//        System.out.println("Welcome " + credentials.getAddress());
//        EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
//
//        System.out.println(blockNumber.getBlockNumber());
//        Request<?, Web3Sha3> x = web3j.web3Sha3("blockchain");
////        web3j.ethSign()
//        Request<?, EthSign> s = web3j.ethSign(credentials.getAddress(),"0x20b53acf0daefc8c6ad68c861fb3b543ca541abd101abc1edfcbf6606b838ef4");
//
////        System.out.printf();
////        Request<?, EthSign> s = web3j.ethSign(credentials.getAddress(),"blockchain");
////        System.out.printf(2);
//        ContractGasProvider contractGasProvider = new StaticGasProvider(gasPrice, gasLimit);
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
//        com.example.demo.contract.Sign erc721 = com.example.demo.contract.Sign.load("0x69F902143c4A04875942688455747280d177184e",web3j,transactionManager,contractGasProvider);
//        erc721.isValidSignatureNow(credentials.getAddress(),TEST_MESSAGE,signatureData);
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
//
    }
}
