package fangjs.fangjinsuo.com.help.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by fangjinsuo.com on 2017/4/10.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView{
    protected Activity mContext;
    private Unbinder mUnBinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        initInject();
       /* if (mPresenter != null)
            mPresenter.attachView(this);*/
//        App.getInstance().addActivity(this);
//        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*if (mPresenter != null)
            mPresenter.detachView();*/
        mUnBinder.unbind();
//        App.getInstance().removeActivity(this);
    }

    @Override
    public void showError(String msg) {

    }

    protected abstract void initInject();
    protected abstract int getLayout();
}
