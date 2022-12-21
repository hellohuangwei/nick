package com.example.cyerbpopMarket.test;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import java.math.BigInteger;
import java.security.SignatureException;
import static com.example.cyerbpopMarket.utils.Web3j.getEthereumMessageHash;

public class Web3j {
    // 钱包私钥
    private static final String priKey = "bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47";
    // 钱包地址
    private static final String walletAddress = "bbb4115a12e5b62d4813cbb7f36afc70866522925ace1ca44a5afb8708c57c47";

    public static String bytes_String16(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<b.length;i++) {
            sb.append(String.format("%02x", b[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // 原文
        String content = "0xFD0cd49de3e8526fcE2854b40D9f9ef9c74dFb0f";
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        // 原文摘要字节数组
        byte[] contentHashBytes = Hash.sha3(new String(content.toLowerCase()+ "500" + Long.toString(timestamp)) .getBytes());

        // 原文摘要16进制字符串
        String contentHashHex = Hex.toHexString(contentHashBytes);
        System.out.println("-----------"+contentHashHex);
        Credentials credentials = Credentials.create(priKey);
        Sign.SignatureData signMessage = Sign.signPrefixedMessage(contentHashBytes, credentials.getEcKeyPair());
        System.out.println("0x" + Hex.toHexString(signMessage.getR()));
        System.out.println("0x" + Hex.toHexString(signMessage.getS()));
        System.out.println("0x" + Hex.toHexString(signMessage.getV()));
 
        // 签名后的字符串
        String signStr = "0x" + Hex.toHexString(signMessage.getR()) + Hex.toHexString(signMessage.getS()) + Hex.toHexString(signMessage.getV());
        System.out.println("signStr=" + signStr);

        try {
            // 原文摘要 添加 ETH签名头信息后再生成摘要
            byte[] messageHash = getEthereumMessageHash(Hex.decode(contentHashHex));
            //通过摘要和签名后的数据，还原公钥
            System.out.println("hash值       " + "0x" + bytes_String16(messageHash));
            Sign.SignatureData signatureData = new Sign.SignatureData(signMessage.getV(), signMessage.getR(), signMessage.getS());
            BigInteger publicKey = Sign.signedMessageHashToKey(messageHash, signatureData);
 
            //还原地址 0x5E32cc9cF83e6f346830fc11e207ac4b91213f30
            String parseAddress = "0x" + Keys.getAddress(publicKey);
            System.err.println("Address：：" + parseAddress);
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }
}