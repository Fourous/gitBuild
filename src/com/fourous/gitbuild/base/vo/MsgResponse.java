package com.fourous.gitbuild.base.vo;

import com.fourous.gitbuild.base.controller.BaseController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fourous
 * @date: 2019/10/31
 * @description: 状态返回码
 */
public class MsgResponse implements Serializable {
    private int code;
    private String msg;
    private Map pair = null;

    public MsgResponse() {
        this.pair = new HashMap();
    }

    public MsgResponse(int code, String msg) {
        this.pair = new HashMap();
        this.setCode(code);
        this.setMsg(msg);
    }

    public static MsgResponse error(String message) {
        return result(BaseController.CODE_ERROR, message);
    }

    public static MsgResponse success() {
        return result(BaseController.CODE_OK, "");
    }

    public static MsgResponse success(String message) {
        return result(BaseController.CODE_OK, message);
    }

    public static MsgResponse result(int code, String message) {
        MsgResponse response = new MsgResponse(code, message);
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

    public Map getPair() {
        return pair;
    }

    public void setPair(Map pair) {
        this.pair = pair;
    }

    public MsgResponse setPair(String key, Object value) {
        this.pair.put(key, value);
        return this;
    }
}
