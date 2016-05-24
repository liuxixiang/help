package com.help.help;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.help.help.util.StringUtils;
import com.kymjs.okhttp.OkHttpStack;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.http.RequestQueue;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.util.Properties;
import java.util.UUID;


/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 *
 */
public class HelpApp extends Application {
    private static HelpApp appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        // 注册App异常崩溃处理器
//        CustomActivityOnCrash.install(this);
        File cacheFolder = getCacheDir();
        RxVolley.setRequestQueue(RequestQueue.newRequestQueue(cacheFolder, new
                OkHttpStack(new OkHttpClient())));
        appContext = this;
    }

    /**
     * 获取App唯一标识
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if (StringUtils.isEmpty(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 获取App安装包信息
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    public static HelpApp getInstance() {
        return appContext;
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }
}
