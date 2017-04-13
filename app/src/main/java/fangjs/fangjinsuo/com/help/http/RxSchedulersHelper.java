package fangjs.fangjinsuo.com.help.http;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


/**
 * 处理Rx线程
 * Created by lxh on 2017/4/12.
 */

public class RxSchedulersHelper {

    /**
     * 接收一个自定义ObservableTransformer
     * @param transformer
     * @param <T>
     * @return
     */
    public static final <T> FlowableTransformer<T, T> schedulersTransformer(FlowableTransformer transformer){
        return (FlowableTransformer<T, T>)transformer;
    }
    /**
     * 普通请求ObservableTransformer
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> schedulersTransformer() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 请求下载的ObservableTransformer
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> schedulersTransformerDown() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(@NonNull Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(Schedulers.io());
            }
        };
    }
}
