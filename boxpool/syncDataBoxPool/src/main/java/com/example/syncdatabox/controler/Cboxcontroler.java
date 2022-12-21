//package com.example.syncdatabox.controler;
//
//import com.example.syncdatabox.service.BoxService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Api(value = "/group", description = "cbox")
//@CrossOrigin
//@RestController
//public class Cboxcontroler {
//
//    @Autowired
//    private BoxService boxService;
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "peroid", value = "多少期", dataType = "int",paramType = "query")
//    })
//    @ApiOperation(value = "得到证明")
//    @RequestMapping(value = "getMemkerProof", method = RequestMethod.GET)
//    public ResponseEntity<Map<String,Object>> getMemkerProof(int peroid) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "200");
//        map.put("message","nice");
//        map.put("data", boxService.getMemkerProof(peroid));
//        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "peroid", value = "多少期", dataType = "int",paramType = "query"),
//            @ApiImplicitParam(name = "award", value = "多少期", dataType = "int",paramType = "query")
//    })
//    @ApiOperation(value = "查看哪些人领到了奖励")
//    @RequestMapping(value = "getRewardList", method = RequestMethod.GET)
//    public ResponseEntity<Map<String,Object>> getRewardList(int peroid,int award) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "200");
//        map.put("message","nice");
//        map.put("data", boxService.getRewardList(peroid,award));
//        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string",paramType = "query"),
//            @ApiImplicitParam(name = "passward", value = "密码", dataType = "string",paramType = "query"),
//            @ApiImplicitParam(name = "root", value = "根节点", dataType = "string",paramType = "query"),
//            @ApiImplicitParam(name = "peroid", value = "多少期", dataType = "int",paramType = "query"),
//            @ApiImplicitParam(name = "String", value = "字符串号码", dataType = "int",paramType = "query")
//    })
//    @ApiOperation(value = "插入每期随机数中奖号码")
//    @RequestMapping(value = "insterRandomData", method = RequestMethod.POST)
//    public ResponseEntity<Map<String,Object>> insterRandomData(String username,String passward,String root,int peroid,String randomStr) {
//        Map<String, Object> map = new HashMap<>();
//        if(username.equals("cyberpop") == true&& passward.equals("cyberpopBox2022Pool")==true)  {
//            map.put("code", "200");
//            map.put("message", "nice");
//            map.put("data", boxService.insterRandomData(root,peroid, randomStr));
//        }
//        else {
//            map.put("code", "400");
//            map.put("message", "权限认证失败");
//        }
//        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
//    }
//
//}
