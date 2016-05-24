package com.help.help.http;

import com.help.help.entity.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by liuxihui on 2016/3/28.
 */
public interface APIService {
    @POST(HttpURLConstant.LOGIN)
    Call<User> Login(@Query("account") String name1, @Query("password") String name2);

}
