package fangjs.fangjinsuo.com.help.http;


import fangjs.fangjinsuo.com.help.base.BaseView;
import fangjs.fangjinsuo.com.help.http.exception.ExceptionEngine;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 请求
 * Created by lxh on 2017/4/16.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private String mErrorMsg;

    protected CommonSubscriber(BaseView view){
        this.mView = view;
    }

    protected CommonSubscriber(BaseView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable t) {
        ExceptionEngine.handleException(t);
    }
}
