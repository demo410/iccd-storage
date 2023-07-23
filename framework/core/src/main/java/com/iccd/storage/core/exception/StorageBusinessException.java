package com.iccd.storage.core.exception;

import com.iccd.storage.core.response.ResponseCode;
import lombok.Data;
import lombok.Getter;

/**
 * 全局业务异常类
 */
@Getter
@Data
public class StorageBusinessException extends RuntimeException {

    private Integer code;

    private String msg;


    public StorageBusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public StorageBusinessException(ResponseCode code) {
        this.code = code.getCode();
        this.msg = code.getDesc();
    }

    public StorageBusinessException(String msg) {
        this.code = ResponseCode.ERROR.getCode();
        this.msg = msg;
    }

    public StorageBusinessException() {
        this.code = ResponseCode.ERROR.getCode();
        this.msg =  ResponseCode.ERROR.getDesc();
    }
}
