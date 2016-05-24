package com.help.help.http;


import android.content.Context;
import android.support.v7.util.SortedList;
import android.util.Log;

import com.help.help.entity.User;
import com.kymjs.rxvolley.client.HttpCallback;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * help API
 * http请求相应接口
 */
public class HelpHttpApi {
    public static void login(HttpCallback handler, String... obj) {
        HashMap<String,Object> params = new HashMap();
        params.put("account", obj[0]);
        params.put("password", obj[1]);
        AsyncHttpHelp.post(HttpURLConstant.LOGIN, params, handler);
    }

    public static void login1(Context Context, String... obj) {
        APIService apiService = RetrofitUtils.create(Context, APIService.class);
        Call<User> call = apiService.Login(obj[0], obj[1]);
        if(call == null) return;
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("lxh","onResponse===="+response.message() + "===response=="  +response.raw());
                Log.e("lxh","onResponse===="+response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("lxh","onResponse====onFailure"+call.toString());
            }
        });
    }


}
