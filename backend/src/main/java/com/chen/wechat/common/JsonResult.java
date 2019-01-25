package com.chen.wechat.common;

import com.chen.wechat.enums.ResultCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * JsonResult
 *
 * @Author LeifChen
 * @Date 2019-01-25
 */
@Getter
@Setter
public class JsonResult {

    /**
     * 请求状态编码
     */
    private Integer code;

    /**
     * 请求返回消息
     */
    private String msg;

    /**
     * 请求返回数据
     */
    private Object data;

    public JsonResult(Integer code) {
        this.code = code;
    }

    public static JsonResult ok() {
        return new JsonResult(ResultCodeEnum.SUCCESS.getCode());
    }

    public static JsonResult ok(Object data) {
        JsonResult result = new JsonResult(ResultCodeEnum.SUCCESS.getCode());
        result.data = data;
        return result;
    }

    public static JsonResult ok(Object data, String msg) {
        JsonResult result = new JsonResult(ResultCodeEnum.SUCCESS.getCode());
        result.msg = msg;
        result.data = data;
        return result;
    }

    public static JsonResult error(String msg) {
        JsonResult result = new JsonResult(ResultCodeEnum.ERROR.getCode());
        result.msg = msg;
        return result;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>(3);
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }
}
