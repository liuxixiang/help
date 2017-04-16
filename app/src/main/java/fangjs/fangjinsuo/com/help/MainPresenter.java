package fangjs.fangjinsuo.com.help;

import fangjs.fangjinsuo.com.help.base.BaseView;
import fangjs.fangjinsuo.com.help.base.RxPresenter;
import fangjs.fangjinsuo.com.help.http.BaseResponse;
import fangjs.fangjinsuo.com.help.http.CommonSubscriber;
import fangjs.fangjinsuo.com.help.http.HttpURLConstant;
import fangjs.fangjinsuo.com.help.http.RetrofitUtils;
import io.reactivex.Flowable;

/**
 * Created by fangjinsuo.com on 2017/4/12.
 */

public class MainPresenter extends RxPresenter implements BaseView{
    public MainPresenter() {
    }

    public void getContent() {
        Flowable<BaseResponse<Object>> a = RetrofitUtils.getInstance().getApiService().get(HttpURLConstant.GET_CUSTOMER_LIST);
//        addSubscribe(
//                a.subscribe());
    }

    @Override
    public void showError(String msg) {

    }
}
