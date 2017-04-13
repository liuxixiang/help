package fangjs.fangjinsuo.com.help.http;

/**
 * Created by fangjinsuo.com on 2017/4/11.
 */

/**
 * 网络返回基类 支持泛型
 * Created by lxh on 2017-04-11.
 */

public class BaseResponse<T> {

    private int code;
    private String msg;
    private T data;

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

    public boolean isOk() {
        return code == 0;
    }

}