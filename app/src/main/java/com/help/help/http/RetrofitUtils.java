package com.help.help.http;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuxihui on 2016/3/28.
 */


public class RetrofitUtils {
    private static Retrofit retrofit;

    public static <T> T create(Context context, Class<T> clazz) {
        if (retrofit == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(HttpURLConstant.BASE_URL)
                            .client(OkHttpUtils.getInstance(context))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit.create(clazz);
    }
}
