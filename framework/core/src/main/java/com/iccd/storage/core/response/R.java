package com.iccd.storage.core.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class R<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    private R(Integer code) {
        this.code = code;
    }

    public R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return Objects.equals(this.code, ResponseCode.SUCCESS.getCode());
    }

    public static <T> R<T> success() {
        return new R<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> R<T> success(String msg) {
        return new R<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> data(T data) {
        return new R<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static <T> R<T> fail(){
        return new R<T>(ResponseCode.ERROR.getCode());
    }

    public static <T> R<T> fail(String msg) {
        return new R<T>(ResponseCode.ERROR.getCode(), msg);
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return new R<T>(code, msg);
    }

    public static <T> R<T> fail(ResponseCode code) {
        return new R<T>(code.getCode(), code.getDesc());
    }

}
