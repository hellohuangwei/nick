package com.example.cyerbpopMarket.config;

import lombok.Getter;

/**
 * 自定义业务异常
 * @program: error
 * @description:
 * @author: wanli
 * @create: 2020-05-09 21:49
 **/
@Getter
public class BusinessException extends  Exception{

    private ResultCode resultCode;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public BusinessException(){}


    public BusinessException(ResultCode resultCode){
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public BusinessException(String message){
        super(message);
    }

}

