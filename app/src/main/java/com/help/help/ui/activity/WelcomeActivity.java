package com.help.help.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.help.help.BuildConfig;


/**
 *  欢迎界面
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initThirdPlug();

        animationToActivity();
        if (!BuildConfig.DEBUG) {
        } else {
            //选择测试环境
//            showUrls();
        }
    }

    @Override
    protected int getLayoutResource() {
        return 0;
    }

    /**
     * 初始化第三方插件
     */
    private void initThirdPlug() {
       /* //注册 友盟 统计
        initUmeng();
        //注册 Talkingdata 统计
        initTalkingdata();*/
    }

    /**
     * 初始化友盟
     */
    /*private void initUmeng() {
        //umeng 设置Channel
        AnalyticsConfig.setChannel(ChannelUtil.getChannel(WelcomeActivity.this, Constant.CHANNEL_ID));
        //发送策略
        MobclickAgent.updateOnlineConfig(this);
        MobclickAgent.openActivityDurationTrack(false);
    }*/

    /**
     * 初始化talkingdata
     */
    /*private void initTalkingdata() {
        TCAgent.LOG_ON = true;
        TCAgent.init(this, "BD858B9B020A070A58179616118806C2", ChannelUtil.getChannel(WelcomeActivity.this, Constant.CHANNEL_ID));
        TCAgent.setReportUncaughtExceptions(true);
    }*/

    /**
     * 延迟1s 跳转
     */
    private void animationToActivity() {
        //获取 esb 公钥 私钥
//        HelpUtil.getClientCryptoKey(WelcomeActivity.this);
        toActivity();
    }

    /**
     * 如果第一次启动跳转到引导界面
     */
    private void toActivity() {
        Intent mIntent = new Intent(this, MainActivity.class);
        startActivity(mIntent);
        this.finish();
    }


    /**
     * 显示url dialog
     */
   /* private void showUrls() {
        final CharSequence[] charSequences = {"10环境", "214环境", "线上环境"};
        AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this);
        builder.setCancelable(false);
        builder.setTitle("服务器环境")
                .setIcon(R.drawable.ic_logo)
                .setItems(charSequences, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                HttpConstant.BASE_URL = HttpConstant.BASE_URL_10;
                                break;
                            case 1:
                                HttpConstant.BASE_URL = HttpConstant.BASE_URL_12;
                                break;
                            case 2:
                                break;
                        }
                        Util.getClientCryptoKey(WelcomeActivity.this);
                        toActivity();
                    }
                }).show();
    }*/

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return true;
    }
}
