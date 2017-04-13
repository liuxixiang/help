package fangjs.fangjinsuo.com.help.http;


import fangjs.fangjinsuo.com.help.base.BaseActivity;
import io.reactivex.functions.Consumer;

/**
 * Created by lxh on 2017/4/12.
 */

public abstract class BaseSubscriber<T> implements Consumer<T> {

    private BaseActivity context;

    public BaseSubscriber(BaseActivity context) {
        this.context = context;
    }

    /*@Override
    public void onStart() {
        super.onStart();

        if (!NetWorkUtils.isMobileNetworkOpen(App.getAppContext())) {

//            Toast.makeText(context, "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
            // 一定好主动调用下面这一句
            onCompleted();
            return;
        }
        // 显示进度条
//        showLoadingProgress();
    }

    @Override
    public void onCompleted() {
        //关闭等待进度条
//        closeLoadingProgress();

    }*/


}