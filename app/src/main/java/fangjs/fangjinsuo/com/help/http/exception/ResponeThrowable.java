package fangjs.fangjinsuo.com.help.http.exception;

/**
 * Created by lxh on 2017/4/12.
 */

public class ResponeThrowable extends Exception {

    private int code;
    private String message;

    public ResponeThrowable(java.lang.Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
