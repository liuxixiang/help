package fangjs.fangjinsuo.com.help.http;

import fangjs.fangjinsuo.com.help.http.exception.ExceptionEngine;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


/**
 * 处理Rx线程
 * Created by lxh on 2017/4/12.
 */

public class FlowableTransformerHelper {

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


    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<BaseResponse<T>, T> handleResult() {   //compose判断结果
        return new FlowableTransformer<BaseResponse<T>, T>() {
            @Override
            public Flowable<T> apply(@NonNull Flowable<BaseResponse<T>> httpResponseFlowable) {

                return httpResponseFlowable.flatMap(new Function<BaseResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
                        if(tBaseResponse.getData()!= null) {
                            return createData(tBaseResponse.getData());
                        }else {
                            return Flowable.error(new Exception("服务器返回error"));
                        }
                    }
                });
            }
        };
    }

    /**
     * 统一返回异常处理
     * @param <T>
     * @return
     */
    public static <T> Function<Throwable, Flowable<BaseResponse<T>>> handleException() {
        return new Function<Throwable, Flowable<BaseResponse<T>>>() {
            @Override
            public Flowable<BaseResponse<T>> apply(@NonNull Throwable throwable) throws Exception {
                return Flowable.error(ExceptionEngine.handleException(throwable));
            }
        };
    }

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
