package fangjs.fangjinsuo.com.help;

import android.util.Log;

import java.util.List;

import fangjs.fangjinsuo.com.help.base.BaseView;
import fangjs.fangjinsuo.com.help.base.RxPresenter;
import fangjs.fangjinsuo.com.help.bean.CustomerResponseBean;
import fangjs.fangjinsuo.com.help.http.CommonSubscriber;
import fangjs.fangjinsuo.com.help.http.FlowableTransformerHelper;
import fangjs.fangjinsuo.com.help.http.HttpURLConstant;
import fangjs.fangjinsuo.com.help.http.ResponseResult;
import fangjs.fangjinsuo.com.help.http.RetrofitUtils;

/**
 * Created by fangjinsuo.com on 2017/4/12.
 */

public class MainPresenter extends RxPresenter implements BaseView{
    public MainPresenter() {
    }

    public void getContent() {

        addSubscribe(RetrofitUtils.getInstance().getApiService().<List<CustomerResponseBean>> get(HttpURLConstant.GET_CUSTOMER_LIST)
                .compose(FlowableTransformerHelper.<ResponseResult<List<CustomerResponseBean>>>schedulersTransformer())
                .compose(FlowableTransformerHelper.<List<CustomerResponseBean>>handleResult())
                .onErrorResumeNext(FlowableTransformerHelper.<List<CustomerResponseBean>>handleException())
                .subscribeWith(new CommonSubscriber<List<CustomerResponseBean>>(mView) {
                    @Override
                    public void onNext(List<CustomerResponseBean> customerResponseBeen) {
                        Log.e("lxh", "size===" + customerResponseBeen.size());
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
