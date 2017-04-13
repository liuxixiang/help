package fangjs.fangjinsuo.com.help.exception;

/**
 * api接口异常
 * Created by lxh on 2017/4/12.
 */

public class ServerException extends RuntimeException {

    public int code;
    public String message;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}