package com.example.demo.controler;

import com.example.demo.utils.Proof;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Api(value = "/group", description = "生成相关证明")
@RestController
public class ProofControler2 {

    @ApiOperation(value = "提供用户取回erc20的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "coin数量", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tokenAddress", value = "coin数量", dataType = "String", paramType = "query")
    })
    @PostMapping(value = "erc20ProofWihtChianId")
    public Map<String, Object> erc20ProofWihtChianId(@RequestBody  Map<String,String> mapData){
        Map<String, Object> map = new HashMap<>();

        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopBox2022Pool")==true) {
            Proof coinProof = new Proof();
            map.put("code", "200");
            map.put("data",coinProof.provideProof(mapData.get("account"),mapData.get("amount"),mapData.get("tokenAddress"),mapData.get("chainId")));
            map.put("message", "得到证明");
        } else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }

        return map;
    }

    @ApiOperation(value = "提供用户取回erc721的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newId", value = "装备Id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tokenAddress", value = "资产合约地址", dataType = "String", paramType = "query")
    })
    @PostMapping(value = "erc721ProofWihtChianId")
    public  Map<String, Object> erc721ProofWihtChianId(@RequestBody  Map<String,String> mapData){
        Map<String, Object> map = new HashMap<>();
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopBox2022Pool")==true) {
            Proof roleProof = new Proof();
               map.put("code", "200");
                map.put("data",roleProof.provideProof(mapData.get("account"),mapData.get("newId"),mapData.get("tokenAddress"),mapData.get("chainId")));
                map.put("message", "得到证明");
        } else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }

        return map;
    }

    @ApiOperation(value = "提供用户取回role的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newId", value = "装备Id", dataType = "String", paramType = "query")
    })
    @PostMapping(value = "roleProofWihtChianIdGameLogic")
    public  Map<String, Object> roleProofWihtChianIdGameLogic(@RequestBody  Map<String,String> mapData){
        Map<String, Object> map = new HashMap<>();
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopBox2022Pool")==true) {
            Proof roleProof = new Proof();
            map.put("code", "200");
            map.put("data",roleProof.provideProofRole(mapData.get("account"),mapData.get("newId"),"ERC721_Role",mapData.get("chainId")));
            map.put("message", "得到证明");
        } else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }
        return map;
    }

    @ApiOperation(value = "提供用户取回1155的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "玩家地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "需要取回的数量", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tokenAddress", value = "coin数量", dataType = "String", paramType = "query")
    })
    @PostMapping(value = "erc1155ProofWihtChianId")

    public  Map<String, Object> erc1155ProofWihtChianId(@RequestBody  Map<String,String> mapData){

        Map<String, Object> map = new HashMap<>();
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopBox2022Pool")==true) {

            Proof gameProof = new Proof();
            map.put("code", "200");
            map.put("data", gameProof.provideErc1155Proof(mapData.get("account"),mapData.get("id") , mapData.get("amount"),mapData.get("tokenAddress"),mapData.get("chainId") ));
            map.put("message", "得到证明");
        }
         else {
                map.put("code", "400");
                map.put("message", "权限认证失败");
            }
            return map;
    }

    @ApiOperation(value = "用户在游戏池中升级资产的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "oldIds", value = "old id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "oldAmounts", value = "number of old id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newId", value = "新的Id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "新id的数量", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tokenAddress", value = "游戏合约的地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "chainId", value = "链Id", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "gameUpgradeProofWihtChianId")
    public  Map<String, Object> gameUpgradeProofWihtChianId(@RequestBody  Map<String,Object> mapData){
        Map<String, Object> map = new HashMap<>();
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopBox2022Pool")==true) {

             Proof gameProof = new Proof();
             map.put("code", "200");
             map.put("data",gameProof.gameUpgradeProofWihtChianId((String[]) mapData.get("oldIds"), (String[]) mapData.get("oldAmounts"), (String) mapData.get("ac count"), (String) mapData.get("newId"), (String) mapData.get("amount"), (String) mapData.get("tokenAddress"), (String) mapData.get("chainId")));
             map.put("message", "得到证明");
            }

            else {
                map.put("code", "400");
                map.put("message", "权限认证失败");
            }
        return map;
    }

    @ApiOperation(value = "用户在游戏池中升级角色资产的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "oldIds", value = "old id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newId", value = "新的Id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tokenAddress", value = "游戏合约的地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "chainId", value = "链Id", dataType = "String", paramType = "query"),
    })
    @PostMapping(value = "roleUpgradeProofWithChainId")
    public Map<String,Object> roleUpgradeProofWithChainId(@RequestBody  Map<String,Object> mapData){
        Map<String, Object> map = new HashMap<>();
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopBox2022Pool")==true) {
        Proof gameProof = new Proof();
        map.put("code", "200");
        map.put("data",gameProof.roleUpgradeProofWithChainId((String) mapData.get("account"), (String[]) mapData.get("oldIds"), (String) mapData.get("newId"), (String) mapData.get("chainId"), (String) mapData.get("tokenAddress")));


        map.put("message", "得到证明");
        }
        else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }
        return map;
    }
}
