package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应DTO
 */
@ApiModel("响应DTO")
public class ResponseDTO<T> {
    private static final int DEFAULT_ERROR_CODE = -1;

    @ApiModelProperty("错误码")
    private int code = 0;
    @ApiModelProperty("错误信息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;

    /**
     * 成功
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> ResponseDTO success(T t) {
        ResponseDTO response = new ResponseDTO();
        response.setData(t);
        return response;
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static ResponseDTO fail(int errorCode, String errorMessage) {
        ResponseDTO response = new ResponseDTO();
        response.setCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    /**
     * 失败
     *
     * @param errorMessage
     * @return
     */
    public static ResponseDTO fail(String errorMessage) {
        ResponseDTO response = new ResponseDTO();
        response.setCode(DEFAULT_ERROR_CODE);
        response.setMessage(errorMessage);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
