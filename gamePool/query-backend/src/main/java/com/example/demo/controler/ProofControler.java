package com.example.demo.controler;

import com.example.demo.utils.Proof;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Api(value = "/group", description = "生成相关证明")
@RestController
public class ProofControler {

    @ApiOperation(value = "提供用户取回coin的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "coin数量", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "coinProof")
    public Map<String, Object> coinProof(String account, String amount){
        Map<String, Object> map = new HashMap<>();
        Proof bdProof = new Proof();
        map.put("code", "200");
        map.put("data",bdProof.provideProof(account,amount,"ERC20_COIN"));
        map.put("message", "得到证明");
        return map;
    }

    @ApiOperation(value = "提供用户取回cyt的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "coin数量", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "cytProof")
    public  Map<String, Object> cytProof(String account,String amount){
        Map<String, Object> map = new HashMap<>();
        Proof bdProof = new Proof();
        map.put("code", "200");
        map.put("data",bdProof.provideProof(account,amount,"ERC20_CYT"));
        map.put("message", "得到证明");
        return map;
    }

    @ApiOperation(value = "提供用户取回role的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newId", value = "装备Id", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "roleProof")
    public  Map<String, Object> roleProof(String account,String newId){
        Map<String, Object> map = new HashMap<>();
        Proof bdProof = new Proof();
        map.put("code", "200");
        map.put("data",bdProof.provideProof(account,newId,"ERC721_Role"));
        map.put("message", "得到证明");
        return map;
    }

    @ApiOperation(value = "提供用户取回game的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "需要取回的数量", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "gameProof")
    public  Map<String, Object> gameProof(String account,String id,String amount){
        Map<String, Object> map = new HashMap<>();
        Proof gameProof = new Proof();
        map.put("code", "200");
        map.put("data",gameProof.provideProof(account,id,"ERC1155_game"));
        map.put("message", "得到证明");
        return map;
    }


    @ApiOperation(value = "提供用户取回coin的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "coin数量", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "coinProofWihtChianId")
    public Map<String, Object> coinProofWihtChianId(String account, String amount,String chainId){
        Map<String, Object> map = new HashMap<>();
        Proof coinProof = new Proof();
        map.put("code", "200");
        map.put("data",coinProof.provideProof(account,amount,"ERC20_COIN",chainId));
        map.put("message", "得到证明");
        return map;
    }

    @ApiOperation(value = "提供用户取回cyt的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "coin数量", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "cytProofWihtChianId")
    public  Map<String, Object> cytProofWihtChianId(String chainId,String account,String amount){
        Map<String, Object> map = new HashMap<>();
        Proof cytProof = new Proof();
        map.put("code", "200");
        map.put("data",cytProof.provideProof(account,amount,"ERC20_CYT",chainId));
        map.put("message", "得到证明");
        return map;
    }

    @ApiOperation(value = "提供用户取回role的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newId", value = "装备Id", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "roleProofWihtChianId")
    public  Map<String, Object> roleProofWihtChianId(String account,String newId,String chainId){
        Map<String, Object> map = new HashMap<>();
        Proof roleProof = new Proof();
        map.put("code", "200");
        map.put("data",roleProof.provideProofRole(account,newId,"ERC721_Role",chainId));
        map.put("message", "得到证明");
        return map;
    }

    @ApiOperation(value = "提供用户取回game的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "需要取回的数量", dataType = "String", paramType = "query")
    })
    @GetMapping(value = "gameProofWihtChianId")
    public  Map<String, Object> gameProofWihtChianId(String chainId,String account,String id,String amount){
        Map<String, Object> map = new HashMap<>();
        Proof gameProof = new Proof();
        map.put("code", "200");
        map.put("data",gameProof.provideErc1155Proof(account,id,amount,"ERC1155_game",chainId));
        map.put("message", "得到证明");
        return map;
    }




//    @ApiOperation(value = "提供用户取回game的证明")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "ids", value = "id", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "amounts", value = "需要取回的数量", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "tokenAddress", value = "资产合约地址", dataType = "String", paramType = "query"),
//    })
//    @GetMapping(value = "BatchGameProofWihtChianId")
//    public  Map<String, Object> BatchGameProofWihtChianId(String chainId,String account,String[] ids,String[] amounts,String tokenAddress){
//        Map<String, Object> map = new HashMap<>();
//        Proof gameProof = new Proof();
//        map.put("code", "200");
//        map.put("data",gameProof.batchProvideErc1155Proof(account,ids,amounts,tokenAddress,chainId));
//        map.put("message", "得到证明");
//        return map;
//    }




}
