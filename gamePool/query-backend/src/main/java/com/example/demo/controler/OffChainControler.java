//package com.example.demo.controler;
//
//
//import io.swagger.annotations.*;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.math.BigInteger;
//import java.util.HashMap;
//import java.util.Map;
//
//@Api(value = "/group", description = "用来上传装备的基本信息")
//@CrossOrigin
//@RestController
//public class OffChainControler {
//
//    @ApiOperation(value = "根据tokenId生成链下数据信息")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "代币Id", dataType = "String", paramType = "query"),
//    })
//    @GetMapping(value = "createPropeInformation")
//    public Map<String, Object> createPropeInformation(BigInteger tokenId, String name, @RequestParam(value = "taskId") int taskId, @ApiParam(value = "上传图片文件", required = true) @RequestParam(value = "myFile", required = true) MultipartFile[] files) {
//        Map<String, Object> map = new HashMap<>();
//        //根据tokenId,查询数据库相关信息，比如tokenId的名称等,然后拼凑成tokenurl返回出来
//        String tokenUrl = "cyber pop";
//        map.put("code", "200");
//        map.put("message", "nice");
//        map.put("data", tokenUrl);
//        return map;
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "代币Id", dataType = "String", paramType = "query"),
//    })
//    @ApiOperation(value = "根据tokenI得到json元数据")
//    @GetMapping(value = "getTokenUrl")
//    public Map<String, Object> getTokenUrl(BigInteger tokenId) {
//        Map<String, Object> map = new HashMap<>();
//        //根据tokenId,查询数据库相关信息，比如tokenId的名称等,然后拼凑成tokenurl返回出来
//        String tokenUrl = "cyber pop";
//        map.put("code", "200");
//        map.put("message", "nice");
//        map.put("data", tokenUrl);
//        return map;
//    }
//
//}
