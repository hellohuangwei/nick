package com.example.demo.controler;

import com.example.demo.utils.Proof;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class CetProofControler {

    @ApiOperation(value = "用于获取cet价格的证明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account", value = "钱包账户地址", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "amount", value = "买多少个", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "chainId", value = "链id", dataType = "String", paramType = "query"),

    })
    @PostMapping(value = "getCetPriceProof")
    public Map<String, Object> getCetPriceProof(String account,String amount,String chainId){
        Map<String, Object> map = new HashMap<>();
            Proof gameProof = new Proof();
            map.put("code", "200");
            map.put("data",gameProof.provideCetProof(account,amount,chainId));
            map.put("message", "得到证明");
            return map;
        }

}
