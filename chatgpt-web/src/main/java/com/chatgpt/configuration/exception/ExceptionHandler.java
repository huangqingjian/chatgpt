package com.chatgpt.configuration.exception;

import com.alibaba.fastjson.JSONObject;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 处理异常返回
     *
     * @param e
     * @param request
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ResponseDTO serviceExceptionHandler(ServiceException e, HttpServletRequest request) {
        if(ServletUtil.isAjax(request)) {
            return ResponseDTO.fail(e.getMessage());
        }
        throw e;
    }

    /**
     * 参数合法性校验异常
     *
     * @param exception
     * @return
     */
    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        StringBuffer buffer = new StringBuffer();
        BindingResult result  = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                log.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                        "},errorMessage{"+fieldError.getDefaultMessage()+"}");
                buffer.append(fieldError.getDefaultMessage()).append(",");
            });
        }
        ResponseDTO response = ResponseDTO.fail(buffer.toString().substring(0, buffer.toString().length() - 1));
        return JSONObject.toJSONString(response);
    }

    /**
     * 处理Authentication异常返回
     *
     * @param e
     * @param request
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public ResponseDTO authenticationExceptionHandler(AuthenticationException e, HttpServletRequest request) {
        if(ServletUtil.isAjax(request)) {
            log.error(e.getMessage(), e);
            return ResponseDTO.fail(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
        throw new ServiceException(e);
    }

    /**
     * 处理异常返回
     *
     * @param e
     * @param request
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseDTO exceptionHandler(Exception e, HttpServletRequest request) {
        if(ServletUtil.isAjax(request)) {
            log.error(e.getMessage(), e);
            return ResponseDTO.fail(e.getMessage());
        }
        throw new ServiceException(e);
    }
}
