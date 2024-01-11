package com.itmk.exception_advice;

/**
 * @Author java实战基地
 * @Version 3501754007
 */

public enum  BusinessExceptionEnum {
    SERVER_ERROR(500, "服务器异常！"),
    SCHEDULE_REEOR(1001,"排课时间冲突"),
    NO_STOCK(1001,"---->库存不足!"),
    ;

    private Integer code;
    private String message;

    BusinessExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}