package com.example.air.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 异常处理
 * @date: 2017年6月6日 下午2:12:08
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler{

    /**
     * 业务异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler( BusinessException.class )
    public CommonResp handleBusinessException (BusinessException e ) {
        return CommonResp.create(e.getResultCode());
    }
}    
