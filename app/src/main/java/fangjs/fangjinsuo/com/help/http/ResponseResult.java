package fangjs.fangjinsuo.com.help.http;

/**
 * Created by fangjinsuo.com on 2017/4/11.
 */

/**
 * 网络返回基类 支持泛型
 * Created by lxh on 2017-04-11.
 */

public class ResponseResult<T> {

    private int status;


    private String desc;
    private T data;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     * 请求是否连通
     * @return
     */
//    public boolean isOk() {
//        return status == 200;
//    }

}