package com.iccd.storage.core.response;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {


    SUCCESS(0, "SUCCESS"),

    ERROR(1, "ERROR"),

    TOKEN_EXPIRE(2, "TOKEN_EXPIRE"),

    ERROR_PARAM(3, "ERROR_PARAM"),

    ACCESS_DENIED(4, "ACCESS_DENIED"),

    /**
     * 分享的文件丢失
     */
    SHARE_FILE_MISS(5, "分享的文件丢失"),
    /**
     * 分享已经被取消
     */
    SHARE_CANCELLED(6, "分享已经被取消"),
    /**
     * 分享已过期
     */
    SHARE_EXPIRE(7, "分享已过期"),

    NEED_LOGIN(10, "NEED_LOGIN");

    private Integer code;

    private String desc;
}
