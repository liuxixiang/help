package fangjs.fangjinsuo.com.help.http;

/**
 * Created by fangjinsuo.com on 2017/4/11.
 */

import java.io.Serializable;

import fangjs.fangjinsuo.com.help.bean.CustomerResponseBean;

/**
 * 网络返回基类 支持泛型
 * Created by lxh on 2017-04-11.
 */

public class ResponseResult1 implements Serializable {

    private int status;


    private String desc;
    private CustomerResponseBean data;


    public CustomerResponseBean getData() {
        return data;
    }

    public void setData(CustomerResponseBean data) {
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