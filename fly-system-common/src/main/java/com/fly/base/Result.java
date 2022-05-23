package com.fly.base;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Result
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Data
public class Result<T> {
    protected Integer code = 200;
    protected String msg = "success";
    protected T data;
    private Map<String, String> enumVal = new HashMap();

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> error(String msg) {
        return new Result(-1, msg);
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result(-1, msg);
        if (null != data) {
            result.setData(data);
        }

        return result;
    }

    public static <T> Result<T> error(Integer code, String msg, T data) {
        Result<T> result = new Result(code, msg);
        if (null != data) {
            result.setData(data);
        }

        return result;
    }

    public static <T> Result<T> success() {
        return success(null, null);
    }

    public static <T> Result<T> success(T data) {
        return success((String) null, data);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result();
        if (StrUtil.isNotBlank(msg)) {
            result.setMsg(msg);
            result.setCode(200);
        }

        if (null != data) {
            result.setData(data);
        }

        return result;
    }
}
