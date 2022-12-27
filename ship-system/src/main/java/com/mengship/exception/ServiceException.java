package com.mengship.exception;

import lombok.Getter;

/**
 * @Description  自定义异常
 * @Author meng
 * @Data 2022/12/2 11:27
 */
@Getter
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
