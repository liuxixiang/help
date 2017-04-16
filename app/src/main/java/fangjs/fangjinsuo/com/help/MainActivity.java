package fangjs.fangjinsuo.com.help;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import fangjs.fangjinsuo.com.help.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_ok)
    TextView btn;
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenter();
        mPresenter.attachView(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getContent();
            }
        });

    }


    @Override
    protected void initInject() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }
}
