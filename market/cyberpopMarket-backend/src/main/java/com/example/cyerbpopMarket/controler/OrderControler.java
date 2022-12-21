package com.example.cyerbpopMarket.controler;

import com.example.cyerbpopMarket.page.PageList;
import com.example.cyerbpopMarket.pojo.AssetPojo;
import com.example.cyerbpopMarket.pojo.OrderInfoPojo;
import com.example.cyerbpopMarket.service.OrderInfoService;
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
    private OrderInfoService orderInfoService;


    @ApiResponses({@ApiResponse(code = 200,message = "nice",response = OrderInfoPojo.class)})
    @ApiImplicitParams({
             @ApiImplicitParam(name = "pageNum", value = "要访问的那一页", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageRow", value = "一页包含多少元素", dataType = "int", paramType = "query")
    })
    @ApiOperation(value = "显示有效订单信息")
    @RequestMapping(value = "showAllProgressingOrderInfo", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> showAllProgressingOrderInfo(@RequestParam(value = "pageNum")int pageNum, @RequestParam(value = "pageRow") int pageRow) {
        Map<String, Object> map = new HashMap<>();
        PageList pageList = orderInfoService.showAllOrderInfo(pageNum,pageRow);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", pageList);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiResponses({@ApiResponse(code = 200,message = "nice",response = OrderInfoPojo.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "要访问的那一页", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageRow", value = "一页包含多少元素", dataType = "int", paramType = "query")
    })
    @ApiOperation(value = "显示有效订单信息")
    @RequestMapping(value = "showAllProgressingOrderInfo2", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> showAllProgressingOrderInfo2(@RequestParam(value = "pageNum")int pageNum, @RequestParam(value = "pageRow") int pageRow) {
        Map<String, Object> map = new HashMap<>();
        PageList pageList = orderInfoService.showAllOrderInfo2(pageNum,pageRow);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", pageList);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiResponses({@ApiResponse(code = 200,message = "nice",response = OrderInfoPojo.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "要访问的那一页", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageRow", value = "一页包含多少元素", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "accountsAddress", value = "钱包地址", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "显示我的有效订单信息")
    @RequestMapping(value = "showMyProgressingOrderInfo", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> showMyProgressingOrderInfo(@RequestParam(value = "pageNum")int pageNum, @RequestParam(value = "pageRow") int pageRow,String accountsAddress) {
        Map<String, Object> map = new HashMap<>();
        PageList pageList = orderInfoService.showMyProgressingOrderInfo(pageNum,pageRow,accountsAddress);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", pageList);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiResponses({@ApiResponse(code = 200,message = "nice",response = OrderInfoPojo.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "要访问的那一页", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageRow", value = "一页包含多少元素", dataType = "int", paramType = "query")
    })
    @ApiOperation(value = "显示二级市场有效订单", notes = "")
    @RequestMapping(value = "showSecondaryMarketOrderInfo", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> showSecondaryMarketOrderInfo(@RequestParam(value = "pageNum")int pageNum, @RequestParam(value = "pageRow") int pageRow) {
        Map<String, Object> map = new HashMap<>();
        PageList pageList = orderInfoService.showSecondaryMarketOrderInfo(pageNum,pageRow);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", pageList);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiResponses({@ApiResponse(code = 200,message = "nice",response = OrderInfoPojo.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "要访问的那一页", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageRow", value = "一页包含多少元素", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "tokenAddress", value = "宝物地址", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "tokenId", value = "宝物Id", dataType = "string", paramType = "query")
    })
    @ApiOperation(value = "显示宝物的历史成交记录", notes = "")
    @RequestMapping(value = "showHistoryMatchedOrder", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> showHistoryMatchedOrder(@RequestParam(value = "pageNum")int pageNum, @RequestParam(value = "pageRow") int pageRow,String tokenAddress,String tokenId) {
        Map<String, Object> map = new HashMap<>();
        PageList pageList = orderInfoService.showHistoryMatchedOrder(pageNum,pageRow,tokenAddress,tokenId);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", pageList);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiResponses({@ApiResponse(code = 200,message = "nice",response = OrderInfoPojo.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "要访问的那一页", dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "pageRow", value = "一页包含多少元素", dataType = "int", paramType = "query")

    })
    @ApiOperation(value = "显示宝物的历史成交记录", notes = "")
    @RequestMapping(value = "showMatchedOrder", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> showMatchedOrder(@RequestParam(value = "pageNum")int pageNum, @RequestParam(value = "pageRow") int pageRow) {
        Map<String, Object> map = new HashMap<>();
        PageList pageList = orderInfoService.showHistoryMatchedOrder(pageNum,pageRow);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", pageList);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiResponses({@ApiResponse(code = 200,message = "nice",response = OrderInfoPojo.class)})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenaAddress", value = "代币地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tokenbAddress", value = "宝物地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tokenId", value = "宝物Id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "Long", paramType = "query")
    })
    @ApiOperation(value = "显示24小时内宝物的地板价 资产类型erc1155", notes = "")
    @RequestMapping(value = "showFloorPriceForErc1155", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> showFloorPriceForErc1155(String tokenaAddress,String tokenbAddress,String tokenId,Long timestamp) {
        Map<String, Object> map = new HashMap<>();
        AssetPojo assetPojo = orderInfoService.showFloorPriceForErc1155(tokenaAddress,tokenbAddress,tokenId,timestamp);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", assetPojo);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenaAddress", value = "代币地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tokenbAddress", value = "宝物地址", dataType = "String", paramType = "query"),
             @ApiImplicitParam(name = "tokenId", value = "宝物Id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "Long", paramType = "query")
    })
    @ApiOperation(value = "显示24小时内宝物的地板价 资产类型erc721", notes = "")
    @RequestMapping(value = "showFloorPriceForErc721", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> showFloorPriceForErc721(String tokenaAddress,String tokenbAddress,String tokenId,Long timestamp) {
        Map<String, Object> map = new HashMap<>();
        AssetPojo assetPojo = orderInfoService.showFloorPriceForErc721(tokenaAddress,tokenbAddress,tokenId,timestamp);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", assetPojo);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenAddress", value = "宝物地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "Long", paramType = "query")
    })
    @ApiOperation(value = "得到商场目前总共销售了多少代币", notes = "")
    @RequestMapping(value = "getTotalSale", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getTotalSale(String tokenAddress,Long timestamp){
        Map<String, Object> map = new HashMap<>();
        String result = orderInfoService.getTotalSale(tokenAddress,timestamp);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", result);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "Long", paramType = "query")
    @ApiOperation(value = "成交erc721的数量", notes = "")
    @RequestMapping(value = "getTotalSaleForErc721", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getTotalSaleForErc721(Long timestamp){
        Map<String, Object> map = new HashMap<>();
        String result = orderInfoService.getTotalSaleForErc721(timestamp);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", result);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }

    @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "Long", paramType = "query")
    @ApiOperation(value = "成交ercerc1155的数量", notes = "")
    @RequestMapping(value = "getTotalSaleForErc1155", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getTotalSaleForErc1155(Long timestamp){
        Map<String, Object> map = new HashMap<>();
        String result = orderInfoService.getTotalSaleForErc1155(timestamp);
        map.put("code", "200");
        map.put("message","nice");
        map.put("data", result);
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
    }


}
