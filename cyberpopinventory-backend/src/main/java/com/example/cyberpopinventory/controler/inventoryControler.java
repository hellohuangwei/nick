package com.example.cyberpopinventory.controler;

import com.example.cyberpopinventory.pojo.BasicDispositionPojo;
import com.example.cyberpopinventory.pojo.DispositionPojo;
import com.example.cyberpopinventory.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Api(value = "/group", description = "cbox")
@CrossOrigin
@RestController
public class inventoryControler {

    @Autowired
    private InventoryService inventoryService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "peroid", value = "多少期", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "startTIme", value = "开始时间", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "多少期", dataType = "int",paramType = "query")
    })
    @ApiOperation(value = "用于管理员插入库存期数数据")
    @RequestMapping(value = "insertInventoryPeroid", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> insertInventoryPeroid(@RequestBody Map<String,String> mapData) {
        Map<String, Object> map = new HashMap<>();
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopInventory2022administrator")==true) {
            map.put("code", "200");
            map.put("message","nice");
            map.put("data", inventoryService.insertInventoryPeroid(Integer.valueOf(mapData.get("peroid")),mapData.get("startTIme"),mapData.get("endTime")));
        }
        else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "第一次初始的库存数量", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "price", value = "单价", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "id", value = "合约id", dataType = "string",paramType = "query")
    })
    @ApiOperation(value = "用于管理员初始化每期库存数据")
    @RequestMapping(value = "initInsertDispositionData", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> initInsertDispositionData(@RequestBody Map<String,String> mapData) {
        Map<String, Object> map = new HashMap<>();
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopInventory2022administrator")==true) {
            map.put("code", "200");
            map.put("message","nice");
            map.put("data", inventoryService.initInsertDispositionData(mapData.get("contractAddress"),Integer.valueOf(mapData.get("num")),mapData.get("price"),Integer.valueOf(mapData.get("peroid")),mapData.get("id")));
        }
        else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "每次增减数量", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", dataType = "string",paramType = "query"),
    })
    @ApiOperation(value = "更新库存数量")
    @RequestMapping(value = "updateNUm", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> updateNUmByOperator(@RequestBody Map<String,String> mapData){
        Map<String, Object> map = new HashMap<>();

        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopInventory2022")==true && Integer.valueOf(mapData.get("num")) <0 ) {
            map.put("code", "200");
            map.put("message","nice");
            map.put("data", inventoryService.updateNUm(Integer.valueOf(mapData.get("num")),Integer.valueOf(mapData.get("peroid")) ,mapData.get("contractAddress"),mapData.get("id")));
        }
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopInventory2022administrator")==true) {
            map.put("code", "200");
            map.put("message","nice");
            map.put("data", inventoryService.updateNUm(Integer.valueOf(mapData.get("num")),Integer.valueOf(mapData.get("peroid")) ,mapData.get("contractAddress"),mapData.get("id")));
        }
        else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "passward", value = "密码", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "每次增减数量", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", dataType = "string",paramType = "query"),
    })
    @ApiOperation(value = "用于管理员更新库存数量")
    @RequestMapping(value = "resetNUmByadministrator", method = RequestMethod.POST)
    public ResponseEntity<Map<String,Object>> resetNUmByadministrator(@RequestBody Map<String,String> mapData){
        Map<String, Object> map = new HashMap<>();
        if(mapData.get("username").equals("cyberpop") == true&& mapData.get("passward").equals("cyberpopInventory2022administrator")==true) {
            map.put("code", "200");
            map.put("message","nice");
            map.put("data", inventoryService.resetNUm(Integer.valueOf(mapData.get("num")),Integer.valueOf(mapData.get("peroid")) ,mapData.get("contractAddress"),mapData.get("id")));
        }
        else {
            map.put("code", "400");
            map.put("message", "权限认证失败");
        }
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "int",paramType = "query"),
    })
    @ApiOperation(value = "得到所有的配置信息")
    @RequestMapping(value = "getAllDispositionData", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getAllDispositionData(int peroid){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message","nice");
        map.put("data",  inventoryService.getDispositionData(peroid));
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "peroid", value = "期数", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "id", value = "合约id", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "contractAddress", value = "合约地址", dataType = "string",paramType = "query")
    })
    @ApiOperation(value = "得到指定合约的配置信息")
    @RequestMapping(value = "getDispositionData", method = RequestMethod.GET)
    public  ResponseEntity<Map<String,Object>> getDispositionData(int peroid, String contractAddress,String id){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", inventoryService.getDispositionData(peroid,contractAddress,id));
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }


    @ApiOperation(value = "得到最后的期数")
    @RequestMapping(value = "getLastPeroid", method = RequestMethod.GET)
    public  ResponseEntity<Map<String,Object>> getLastPeroid(){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", inventoryService.getLastPeroid());
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

}
