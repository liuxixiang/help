package fangjs.fangjinsuo.com.help;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.reactivestreams.Publisher;

import java.util.List;

import fangjs.fangjinsuo.com.help.base.BaseView;
import fangjs.fangjinsuo.com.help.base.RxPresenter;
import fangjs.fangjinsuo.com.help.bean.CustomerResponseBean;
import fangjs.fangjinsuo.com.help.http.CommonSubscriber;
import fangjs.fangjinsuo.com.help.http.FlowableTransformerHelper;
import fangjs.fangjinsuo.com.help.http.HttpURLConstant;
import fangjs.fangjinsuo.com.help.http.ResponseResult;
import fangjs.fangjinsuo.com.help.http.ResponseResult1;
import fangjs.fangjinsuo.com.help.http.RetrofitUtils;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by fangjinsuo.com on 2017/4/12.
 */

public class MainPresenter extends RxPresenter implements BaseView{
    public MainPresenter() {
    }

    public void getContent() {

//        addSubscribe(RetrofitUtils.getInstance().getApiService().getCustomerList()
//                .compose(FlowableTransformerHelper.<ResponseResult<CustomerResponseBean>>schedulersTransformer())
//                .compose(FlowableTransformerHelper.<CustomerResponseBean>handleResult())
//                .onErrorResumeNext(FlowableTransformerHelper.<CustomerResponseBean>handleException())
//                .subscribeWith(new CommonSubscriber<CustomerResponseBean>(mView) {
//                    @Override
//                    public void onNext(CustomerResponseBean customerResponseBeen) {
//                        Log.e("lxh", "size===" + customerResponseBeen.getCity());
//                    }
//                })
//        );

        addSubscribe(RetrofitUtils.getInstance().getApiService().getCustomerList1()
                .compose(FlowableTransformerHelper.<ResponseBody>rxSchedulerHelper())
                .compose(new FlowableTransformer<ResponseBody, CustomerResponseBean>() {
                    @Override
                    public Flowable<CustomerResponseBean> apply(@NonNull Flowable<ResponseBody> upstream) {

                        return upstream.map(new Function<ResponseBody, CustomerResponseBean>() {
                            @Override
                            public CustomerResponseBean apply(@NonNull ResponseBody responseBody) throws Exception {
                                Log.e("lxh","responseBody---" + responseBody.string());
                                Gson gson=new Gson();
                                 try {
                                     ResponseResult1 a = gson.fromJson(responseBody.string(), ResponseResult1.class);

                                 }catch (Exception e){
                                     e.printStackTrace();
                                 }
                                //CustomerResponseBean b =  a.getData();
                                return null;
                            }
                        });
                    }
                })
                .onErrorResumeNext(FlowableTransformerHelper.<CustomerResponseBean>handleException())
                .subscribeWith(new CommonSubscriber<CustomerResponseBean>(mView) {
                    @Override
                    public void onNext(CustomerResponseBean customerResponseBeen) {
                        Log.e("lxh", "size===" + customerResponseBeen.getCity());
                    }
                })
        );

//        Call<ResponseResult<List<CustomerResponseBean>>> a = RetrofitUtils.getInstance().getApiService().getCustomerList();
//        a.enqueue(new Callback<ResponseResult<List<CustomerResponseBean>>>() {
//            @Override
//            public void onResponse(Call<ResponseResult<List<CustomerResponseBean>>> call, Response<ResponseResult<List<CustomerResponseBean>>> response) {
//                Log.e("lxh","onResponse");
//            }
//
//            @Override
//            public void onFailure(Call<ResponseResult<List<CustomerResponseBean>>> call, Throwable t) {
//                Log.e("lxh","onFailure----" + t);
//            }
//        });
    }

    @Override
    public void showError(String msg) {

    }
}
