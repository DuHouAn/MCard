package com.southeast.passbook.advice;

import com.southeast.passbook.vo.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <h1>全局异常处理</h1>
 * Created by 18351 on 2019/5/12.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ErrorInfo<String> errorHandler(HttpServletRequest request,Exception e){
        // Controller 中抛出异常，ControllerAdvice 就会捕获，定位到 ExceptionHandler 中处理

        ErrorInfo<String> info = new ErrorInfo<>();

        info.setCode(ErrorInfo.ERROR);
        info.setMessage(e.getMessage());
        info.setData("Do Not Have Return Data");
        info.setUrl(request.getRequestURL().toString());

        return info;
    }
}
