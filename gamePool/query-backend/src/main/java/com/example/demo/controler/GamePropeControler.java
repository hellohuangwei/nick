//package com.example.demo.controler;
//
//import com.example.demo.server.GameItemServer;
//import com.example.demo.utils.Proof;
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
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//
//@Api(value = "/logic", description = "游戏逻辑")
//@CrossOrigin
//@RestController
//public class GamePropeControler {
//
//    @Autowired
//    private GameItemServer gameItemServer;
//
//    @ApiOperation(value = "Erc155升级")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id",dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "player", value = "玩家地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "ids", value = "待升级的Id装备集合", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "amounts", value = "消耗", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "newId", value = "铸造出来的新Id", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "newAmount", value = "需要铸造出来新的Id的数量", dataType = "String", paramType = "query"),
//
//    })
//    @PostMapping(value = "upgradePropeForErc1155")
//    public Map<String, Object> upgradePropeForErc1155(int chainId,String contractAddress,String player, String[] ids, String[] amounts, String newId,String newAmount) {
//        byte[] midbytes = new String("").getBytes(StandardCharsets.UTF_8);
//        Map<String, Object> map = new HashMap<>();
//        boolean flog = gameItemServer.upgradePropeForErc1155(chainId,contractAddress,player,ids,amounts,newId,newAmount);
//        map.put("code", "200");
//        map.put("message", "授权成功");
//        map.put("data", flog);
//        return map;
//    }
//
//
//    @ApiOperation(value = "Erc721升级")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id", dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "player", value = "玩家地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "ids", value = "待升级的Id装备集合", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "newId", value = "铸造出来的新Id", dataType = "String", paramType = "query"),
//    })
//    @PostMapping(value = "upgradePropeForNft")
//    public Map<String, Object> upgradePropeForNft(int chainId,String contractAddress,String player,String[]  ids,String newId){
//        byte[] midbytes = new String("").getBytes(StandardCharsets.UTF_8);
//        Map<String, Object> map = new HashMap<>();
//        boolean flog = gameItemServer.upgradePropeForNft(chainId,contractAddress,player,ids,newId);
//        map.put("code", "200");
//        map.put("message", "授权成功");
//        map.put("data", flog);
//        return map;
//    }
//
//
//    @ApiOperation(value = "加载装备到游戏合约里面")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "链Id", dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "id", value = "装备Id", dataType = "String", paramType = "query"),
//    })
//    @PostMapping(value = "loadingNft")
//    public Map<String, Object> loadingNft(int chainId,String contractAddress, String id) {
//        Map<String, Object> map = new HashMap<>();
//        boolean res = gameItemServer.loadingPrope(chainId,contractAddress,id);
//        map.put("code", "200");
//        map.put("data",res);
//        map.put("message", "加载成功");
//        return map;
//    }
//
//    @ApiOperation(value = "玩家从游戏池里面取回装备")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id", dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "contractAddress", value = "合约地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "player", value = "玩家地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "id", value = "装备Id", dataType = "String", paramType = "query")
//    })
//    @PostMapping(value = "withdrawNft")
//    public Map<String, Object> withdrawNft(int chainId,String contractAddress,String player, String id) {
//        Map<String, Object> map = new HashMap<>();
//        boolean res =   gameItemServer.withdrawRole(chainId,contractAddress,player,id);
//        map.put("code", "200");
//        map.put("data",res);
//        map.put("message", "取回成功");
//        return map;
//    }
//
//
//    @ApiOperation(value = "加载erc1155装备")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chainId", value = "链Id", dataType = "int", paramType = "query"),
//            @ApiImplicitParam(name = "player", value = "玩家地址", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "id", value = "装备Id", dataType = "String", paramType = "query"),
//            @ApiImplicitParam(name = "amount", value = "要加载装备的数量", dataType = "String", paramType = "query"),
//    })
//
//    @PostMapping(value = "loadingErc1155")
//    public Map<String, Object> loadingErc1155(int chainId,String contractAddress, String player, String id,String amount) {
//        Map<String, Object> map = new HashMap<>();
//        boolean res = gameItemServer.loadingErc1155(chainId,contractAddress,player,id,amount);
//        map.put("code", "200");
//        map.put("data",res);
//        map.put("message", "加载erc1155成功");
//        return map;
//    }
//
//    @ApiOperation(value = "取回游戏装备")
//    @PostMapping(value = "withdrawErc1155")
//    public  Map<String, Object> withdrawErc1155(int chainId,String contractAddress, String player,String[] ids,String[] amounts){
//        Map<String, Object> map = new HashMap<>();
//        boolean res = gameItemServer.withdrawGameProbe(chainId,contractAddress,player,ids,amounts);
//        map.put("code", "200");
//        map.put("data",res);
//        map.put("message", "取回erc1155成功");
//        return map;
//    }
//
//}
