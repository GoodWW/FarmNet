package com.cdzp.farmnet.bean;

/**
 * 作者：张人文
 * 时间：2019/11/4 10:47
 * 邮箱：479696877@QQ.COM
 * 描述： bean基类
 */
public class BaseEntity<T> {


    private boolean success;

    private int code;

    private T data;

    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "success=" + success +
                ", code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
