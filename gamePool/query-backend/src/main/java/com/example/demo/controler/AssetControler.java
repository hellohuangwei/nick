package com.example.demo.controler;


import com.example.demo.server.AssetServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "/group", description = "游戏资产")
@CrossOrigin
@RestController
public class AssetControler {

    @Autowired
    private AssetServer AssetServer;
//
//    @ApiOperation(value = "授权")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
//            @ApiImplicitParam(name = "minterRole", value = "角色", paramType = "query"),
//            @ApiImplicitParam(name = "account", value = "要授权的地址", paramType = "query"),
//    })
//    @PostMapping(value = "grantMint")
//    public Map<String, Object> grantMint(int chainId,String contractAddress, String minterRole, String account) {
//        Map<String, Object> map = new HashMap<>();
//        byte[] role = minterRole.getBytes(StandardCharsets.UTF_8);
//        AssetServer.grantRole(chainId,contractAddress, role, account);
//        map.put("code", "200");
//        map.put("message", "授权成功");
//        map.put("data", "");
//        return map;
//    }
//
//    @ApiOperation(value = "铸造代币")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
//            @ApiImplicitParam(name = "to", value = "得币地址", paramType = "query"),
//            @ApiImplicitParam(name = "id", value = "代币Id", paramType = "query"),
//            @ApiImplicitParam(name = "amount", value = "铸造数量", paramType = "query"),
//    })
//    @PostMapping(value = "Mint")
//    public Map<String, Object> Mint(int chainId,String contractAddress, String to, String id, String amount) {
//        Map<String, Object> map = new HashMap<>();
//        byte[] data = new byte[]{(byte) 0x0};
//        //String to, BigInteger id, BigInteger amount
//        AssetServer.mint(chainId, contractAddress,to, new BigInteger(id), new BigInteger(amount), data);
//        map.put("code", "200");
//        map.put("message", "铸造代币成功");
//        map.put("data", "");
//        return map;
//    }

    @ApiOperation(value = "查询Erc1155单个代币的余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
            @ApiImplicitParam(name = "Id", value = "代币Id", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户地址", paramType = "query"),
    })
    @GetMapping(value = "balanceOf")
    public Map<String, Object> balanceOf(int chainId,String contractAddress, String account, BigInteger Id) {
        Map<String, Object> map = new HashMap<>();
        BigInteger balance = AssetServer.balanceOf(chainId,contractAddress, account, Id);
        map.put("code", "200");
        map.put("message", "查询余额成功");
        map.put("data", balance);
        return map;
    }

    @ApiOperation(value = "查询Erc20代币的余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户地址", paramType = "query"),
    })
    @GetMapping(value = "balanceOfErc20")
    public Map<String, Object> balanceOfErc20(int chainId,String contractAddress, String account) {
        Map<String, Object> map = new HashMap<>();
        BigInteger balance = AssetServer.balanceOfErc20(account,chainId,contractAddress);
        map.put("code", "200");
        map.put("message", "查询余额成功");
        map.put("data", balance.toString());
        return map;
    }

    @ApiOperation(value = "批量查询账户余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
            @ApiImplicitParam(name = "accounts", value = "玩家地址", paramType = "query"),
            @ApiImplicitParam(name = "ids", value = "要求游戏服务器传一个所有的Id集合，并且数值不能传重复的，因为合约里面的Id是由游戏数据库一对一映射的，这边不清楚共有哪些Id", paramType = "query"),
    })
    @GetMapping(value = "balanceOfBatch")
    public Map<String, Object> balanceOfBatch(int chainId,String contractAddress, String accounts, String[] ids) {
        String[] ids2 = ids;
        BigInteger[] ids_ = new BigInteger[ids.length];
        for (int i = 0; i < ids.length; i++) {
            ids_[i] = new BigInteger(ids[i]);
        }
        List<BigInteger> idsList = Arrays.asList(ids_);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        List list = AssetServer.balanceOfBatch(chainId,contractAddress ,accounts, idsList);
        for(int i = 0;i<list.size();i++){
            if(!list.get(i).toString().equals("0")){
                map2.put(ids[i],list.get(i));
            }
        }
        map.put("code", "200");
        map.put("message","查询余额成功");
        map.put("data", map2);
        return map;
    }

    @ApiOperation(value = "查询erc721余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
            @ApiImplicitParam(name = "accounts", value = "玩家地址", paramType = "query"),
    })
    @GetMapping(value = "tokensOfOwner")
    public Map<String, Object> tokensOfOwner(@RequestParam(value = "accounts") String accounts,@RequestParam(value = "contractAddress")  String contractAddress,@RequestParam(value = "chainId")  int chainId) {
        Map<String, Object> map = new HashMap<>();

        List list = AssetServer.tokensOfOwner(accounts, chainId,contractAddress);
        map.put("code", "200");
        map.put("message", "查询erc721余额成功");
        map.put("data",list);
        return map;

    }

    @ApiOperation(value = "查询erc20的权限剩余值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
            @ApiImplicitParam(name = "owner", value = "账户地址", paramType = "query"),
            @ApiImplicitParam(name = "spender", value = "目标地址", paramType = "query")
    })
    @GetMapping(value = "allowanceOfErc20")
    public Map<String, Object> allowanceOfErc20(@RequestParam(value = "owner") String owner,@RequestParam(value = "contractAddress")  String contractAddress,@RequestParam(value = "chainId")  int chainId,@RequestParam(value = "spender")  String spender) {
        Map<String, Object> map = new HashMap<>();
        BigInteger value = AssetServer.allowanceOfErc20(owner,contractAddress,chainId,spender);
        map.put("code", "200");
        map.put("message", "查询erc20授权余额成功");
        map.put("data",value);
        return map;
    }

    @ApiOperation(value = "查询erc721是否授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
            @ApiImplicitParam(name = "owner", value = "账户地址", paramType = "query"),
            @ApiImplicitParam(name = "operator", value = "目标地址", paramType = "query")
    })
    @GetMapping(value = "isApprovedForAllForErc721")
    public Map<String, Object> isApprovedForAllForErc721(@RequestParam(value = "owner") String owner,@RequestParam(value = "contractAddress")  String contractAddress,@RequestParam(value = "chainId")  int chainId,@RequestParam(value = "operator")  String operator) {
        Map<String, Object> map = new HashMap<>();
        boolean value = AssetServer.isApprovedForAllForErc721(chainId,contractAddress,owner,operator);
        map.put("code", "200");
        map.put("message", "查询erc20授权余额成功");
        map.put("data",value);
        return map;
    }



    @ApiOperation(value = "查询erc1155是否授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
            @ApiImplicitParam(name = "owner", value = "账户地址", paramType = "query"),
            @ApiImplicitParam(name = "operator", value = "目标地址", paramType = "query")
    })
    @GetMapping(value = "isApprovedForAllForErc1155")
    public Map<String, Object> isApprovedForAllForErc1155(@RequestParam(value = "owner") String owner,@RequestParam(value = "contractAddress")  String contractAddress,@RequestParam(value = "chainId")  int chainId,@RequestParam(value = "operator")  String operator) {
        Map<String, Object> map = new HashMap<>();
        boolean value = AssetServer.isApprovedForAllForErc1155(chainId,contractAddress,owner,operator);
        map.put("code", "200");
        map.put("message", "查询erc20授权余额成功");
        map.put("data",value);
        return map;
    }

//    @ApiOperation(value = "授权")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
//            @ApiImplicitParam(name = "to", value = "玩家地址", paramType = "query"),
//            @ApiImplicitParam(name = "tokenId", value = "要授权g给目标地址的Id", paramType = "query")
//    })
//    @PostMapping(value = "approve")
//    public Map<String, Object> approve(@RequestParam(value = "chainId")int chainId,@RequestParam(value = "contractAddress")String contractAddress,@RequestParam(value = "to") String to,@RequestParam(value = "tokenId")  String tokenId) {
//        Map<String, Object> map = new HashMap<>();
//        boolean res = AssetServer.approve(chainId,contractAddress,to,tokenId);
//        map.put("code", "200");
//        map.put("message", "授权成功");
//        map.put("data", res);
//        return map;
//    }

//    @ApiOperation(value = "转账erc721代币")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
//            @ApiImplicitParam(name = "from", value = "from", paramType = "query"),
//            @ApiImplicitParam(name = "to", value = "to", paramType = "query"),
//            @ApiImplicitParam(name = "tokenId", value = "tokenId", paramType = "query"),
//    })
//    @PostMapping(value = "transferNft")
//    public Map<String, Object> transferFromNft(int chainId,String contractAddress,String from, String to, String tokenId) {
//        Map<String, Object> map = new HashMap<>();
//        boolean flog = AssetServer.transferFromNft(chainId,contractAddress,from,to,tokenId);
//        map.put("code", "200");
//        map.put("message", "转账成功");
//        map.put("data", flog);
//        return map;
//    }

//    @PostMapping(value = "createNft2")
//    public Map<String, Object> createNft2(int chainId,String contractAddress,String player, String tokenId) {
//        Map<String, Object> map = new HashMap<>();
//        boolean flog = gameItemServer.createNft(chainId,contractAddress,player,tokenId);
//        map.put("code", "200");
//        map.put("message", "创建nft成功");
//        map.put("data", flog);
//        return map;
//    }

//    @ApiOperation(value = "授权erc1155到目标地址")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", paramType = "query"),
//            @ApiImplicitParam(name = "account", value = "from", paramType = "query"),
//            @ApiImplicitParam(name = "operator", value = "true or false",dataType = "boolean", paramType = "query")
//    })
//    @PostMapping(value = "setApprovalForAll")
//    public Map<String, Object>setApprovalForAll(int chainId,String contractAddress,String account,boolean operator){
//        Map<String, Object> map = new HashMap<>();
//        boolean flog = AssetServer.setApprovalForAll(chainId,contractAddress,account,operator);
//        map.put("code", "200");
//        map.put("message", "创建nft成功");
//        map.put("data", flog);
//        return map;
//    }
}