package com.example.cyerbpopMarket.utils;

import com.example.cyerbpopMarket.pojo.AssetPojo;
import com.example.cyerbpopMarket.pojo.OrderDataPojo;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;

import java.util.Arrays;
import java.util.Collections;

public class Test {

    public static void main(String[] args) {
        Decode decode = new Decode();
        OrderDataPojo assetPojo = new OrderDataPojo();
        assetPojo= decode.decodeOrderData("0x00000000000000000000000078f66e37e9fe077d2f0126e3a26e6fb0d14f2bb0000000000000000000000000000000000000000000000000000000089df954910000000000000000000000000000000000000000000000000000000000000000");
        System.out.println( );
    }

}
