package com.example.air.controler;

import com.example.air.dao.AirDao;
import com.example.air.service.AirService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "/group", description = "订单相关信息")
@CrossOrigin
@RestController
public class OrderControler {

    @Autowired
    private AirDao airDao;

    @Autowired
    private AirService airService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户地址", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "数量", dataType = "int", paramType = "query")
    })
    @ApiOperation(value = "显示有效订单信息")
    @RequestMapping(value = "insertAirData", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> insertAirData(int peroid,String account,String num) {
        Map<String, Object> map = new HashMap<>();
        boolean result =  airService.insertAirdata(peroid,account,num);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data",result );
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "account", value = "账户地址", dataType = "string",paramType = "query")
    })
    @ApiOperation(value = "得到空投名单信息")
    @RequestMapping(value = "getAirData", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAirData(int peroid,String account) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message","nice");
        map.put("data",airService.getAirData(peroid,account) );
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "int",paramType = "query")
    })
    @ApiOperation(value = "得到空投名单信息")
    @RequestMapping(value = "getAirDataForPeroid", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAirDataForPeroid(int peroid) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message","nice");
        map.put("data",airService.getAirData(peroid) );
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }


    @ApiOperation(value = "得到空投名单信息")
    @RequestMapping(value = "getLastPeroid", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getLastPeroid() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message","nice");
        map.put("data",airService.getLastPeroid() );
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "root", value = "根节点", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "peroid", value = "多少期", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "randomStr", value = "字符串号码", dataType = "int",paramType = "query")
    })
    @ApiOperation(value = "插入每期随机数中奖号码")
    @RequestMapping(value = "insterRandomData", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> insterRandomData(String username,String passward,String root,int peroid,String randomStr) {
        Map<String, Object> map = new HashMap<>();
        if(username.equals("cyberpop") == true&& passward.equals("cyberpopBox2022Pool")==true)  {
            map.put("code", "200");
            map.put("message", "nice");
            map.put("data", airService.insterRandomData(root,peroid, randomStr));
        }
        else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "string",paramType = "query"),
    })
    @ApiOperation(value = "得到每期的中奖名单")
    @RequestMapping(value = "getRewardNumberList", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getRewardNumberList(int peroid){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", airService.getRewardNumberList(peroid));
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }


}
