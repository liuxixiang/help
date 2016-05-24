package com.help.help.http;

/**
 * Created by liuxihui on 2016/3/28.
 */

import android.content.Context;
import android.util.Log;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttpClient自定义工具类
 */
public class OkHttpUtils {
    private static OkHttpClient singleton;

    public static OkHttpClient getInstance(Context context) {
        if (singleton == null) {
            synchronized (OkHttpUtils.class) {
                if (singleton == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.e("http", message);
                        }
                    });
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    singleton = new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
//                .addNetworkInterceptor(mTokenInterceptor)
                            .build();
                }
            }
        }
        return singleton;
    }
}