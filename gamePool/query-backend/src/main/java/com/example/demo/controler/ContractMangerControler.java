//package com.example.demo.controler;
//
//import com.example.demo.server.ContractMangerServer;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.math.BigInteger;
//import java.util.HashMap;
//import java.util.Map;
//
//@Api(value = "/group", description = "配置链跟合约的基本信息")
//@CrossOrigin
//@RestController
//public class ContractMangerControler {
//
//    @Autowired
//    private ContractMangerServer contractMangerServer;
//
//    @ApiOperation(value = "配置合约基本信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
//            @ApiImplicitParam(name = "chainRpc", value = "链的rpc", paramType = "query"),
//            @ApiImplicitParam(name = "credentials", value = "账户私钥", paramType = "query"),
//            @ApiImplicitParam(name = "chainId", value = "链Id", paramType = "query"),
//            @ApiImplicitParam(name = "gasPrice", value = "燃料价格", paramType = "query"),
//            @ApiImplicitParam(name = "gasLimit", value = "燃料限制", paramType = "query"),
//
//    })
//    @PostMapping(value = "setBasicContractInformation")
//    public Map<String, Object> setBasicContractInformation(int chainId, String credentials, String contractAddress, String chainRpc, BigInteger gasPrice, BigInteger gasLimit) {
//        Map<String, Object> map = new HashMap<>();
//        contractMangerServer.setContractInfo(chainId, credentials, contractAddress, chainRpc, gasPrice, gasLimit);
//        map.put("code", "200");
//        map.put("message", "增加合约信息成功");
//        map.put("data", "");
//        return map;
//    }
//
//    @ApiOperation(value = "得到合约基本信息")
//    @GetMapping(value = "getBasicContractInformation")
//    public Map<String, Object> getBasicContractInformation(int chainId) {
//        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> map2 = new HashMap<>();
//        map2.put("data", contractMangerServer.getContractInfo(chainId));
//        map.put("code", "200");
//        map.put("message", "得到合约信息");
//        map.put("data", map2);
//        return map;
//    }
//
//    @ApiOperation(value = "根据chainId更新合约基本信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
//            @ApiImplicitParam(name = "chainRpc", value = "链的rpc", paramType = "query"),
//            @ApiImplicitParam(name = "credentials", value = "账户私钥", paramType = "query"),
//            @ApiImplicitParam(name = "chainId", value = "链Id", paramType = "query"),
//            @ApiImplicitParam(name = "gasPrice", value = "燃料价格", paramType = "query"),
//            @ApiImplicitParam(name = "gasLimit", value = "燃料限制", paramType = "query"),
//
//    })
//    @PostMapping(value = "updateContractInformation")
//    public Map<String, Object> updateContractInformation(int chainId, String credentials, String contractAddress, String chainRpc, BigInteger gasPrice, BigInteger gasLimit) {
//        Map<String, Object> map = new HashMap<>();
//        Map<String, Object> map2 = new HashMap<>();
//        map2.put("data", contractMangerServer.updateContractInformation(chainId, credentials, contractAddress, chainRpc, gasPrice, gasLimit));
//        map.put("code", "200");
//        map.put("message", "更新合约信息");
//        map.put("data", map2);
//        return map;
//    }
//
//}
