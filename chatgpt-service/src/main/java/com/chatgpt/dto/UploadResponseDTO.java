package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 上传响应DTO
 */
@ApiModel("上传响应DTO")
public class UploadResponseDTO<T> {
    private static final int DEFAULT_ERROR_CODE = -1;

    @ApiModelProperty("错误码")
    private int code = 0;
    @ApiModelProperty("错误信息")
    private String msg;
    @ApiModelProperty("返回数据")
    private T data;

    /**
     * 成功
     *
     * @param t
     * @return
     */
    public static <T> UploadResponseDTO success(T t) {
        UploadResponseDTO response = new UploadResponseDTO();
        response.setData(t);
        return response;
    }

    /**
     * 失败
     *
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static UploadResponseDTO fail(int errorCode, String errorMsg) {
        UploadResponseDTO response = new UploadResponseDTO();
        response.setCode(errorCode);
        response.setMsg(errorMsg);
        return response;
    }

    /**
     * 失败
     *
     * @param errorMsg
     * @return
     */
    public static UploadResponseDTO fail(String errorMsg) {
        UploadResponseDTO response = new UploadResponseDTO();
        response.setCode(DEFAULT_ERROR_CODE);
        response.setMsg(errorMsg);
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 上传结果DTO
     */
    @ApiModel("上传结果DTO")
    public static class UploadResultDTO extends BaseDTO {
        @ApiModelProperty("图片名称")
        private String title;
        @ApiModelProperty("图片路径")
        private String src;

        /**
         *
         * @param title
         * @return
         */
        public UploadResultDTO title(String title) {
            this.setTitle(title);
            return this;
        }

        /**
         *
         * @param src
         * @return
         */
        public UploadResultDTO src(String src) {
            this.setSrc(src);
            return this;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }
}
