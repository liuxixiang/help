package com.help.help.ui.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.help.help.AppManager;
import com.help.help.R;
import com.help.help.util.ACache;
import com.help.help.util.HelpUtil;
import com.help.help.util.ProgressDialogUtils;
import com.help.help.util.ToastUtils;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private TextView actionBarTitle;//标题
    protected ACache aCache;//缓存
    private View actionBarMiddle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将activity加入到AppManager堆栈中
        AppManager.getAppManager().addActivity(this);
        if(getLayoutResource() != 0) {
            setContentView(getLayoutResource());
        }
        aCache = ACache.get(this);
    }

    protected abstract int getLayoutResource();

    // 关闭该Activity
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.toolbar);
        actionBarTitle = (TextView) findViewById(R.id.toolbar_title);
        actionBarMiddle = findViewById(R.id.tool_bar_middle);
        getToolbar();
        setActionBarLeftIcon(R.drawable.ic_action_bar_left);
        //actionBar 和布局文件结合
        LayoutInflater inflater = LayoutInflater.from(this);
        View contentView = inflater.inflate(layoutResID, null);

        LinearLayout layout = (LinearLayout) findViewById(R.id.action_bar_parent);
        layout.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


    public void setToolBarColor(int color){
        getToolbar().setBackgroundResource(color);
    }

    public void setToolBarTextTitleColor(int color){
        actionBarTitle.setTextColor(getResources().getColor(color));
    }

    /**
     * 获取toolbar
     * @return
     */
    protected Toolbar getToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                setActionBarLeftIsShow(true);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }
        }
        return toolbar;
    }


    protected void hideLeft() {
        getToolbar().setNavigationIcon(null);
    }

    /**
     * 监听左键点击事件
     * @param listener
     */
    protected void setLeftOnClick(View.OnClickListener listener) {
        getToolbar().setNavigationOnClickListener(listener);
    }

    /**
     * 隐藏actionBar
     */
    protected void hideActionbar() {
        getSupportActionBar().hide();
    }

    protected void setActionBarLeftIsShow(Boolean isShow) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
    }

    /**
     * 监听标题点击事件
     * @param listener
     */
    protected void setActionBarMiddleOnClick(View.OnClickListener listener) {
        actionBarMiddle.setOnClickListener(listener);
    }

    /**
     *  设置actionBar 左边的图标
     * @param iconRes
     */
    protected void setActionBarLeftIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
    }


    /**
     * 设置标题
     * @param title
     */
    protected void setActionBarTitle(String title) {
        actionBarTitle.setText(title);
    }
    /**
     * 获得标题
     */
    protected TextView getActionBarTitle() {
        return actionBarTitle;
    }

    /**
     * 弹出Toast信息
     * @param msg
     */
    public void showShortToast(String msg) {
        ToastUtils.getStance(this).showShortToast(msg);
    }


    /**
     * 中间弹出Toast信息
     * @param msg
     */
    public void showCentreShortToast(String msg) {
        ToastUtils.getStance(this).showCentreShortToast(msg);
    }

    public void showProgressDialog() {
        ProgressDialogUtils.getInstance().showProgressDialog(this);
    }

    public void showProgressDialog(String msg) {
        ProgressDialogUtils.getInstance().showProgressDialog(this, msg);
    }

    public void dismissProgressDialog() {
        ProgressDialogUtils.getInstance().dismissProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        if (HelpUtil.isFastDoubleClick()) {
            return;
        }
    }
}
