package com.help.help.http;

/**
 * Created by liuxihui on 2016/3/28.
 */

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttpClient自定义工具类
 */
public class OkHttpUtils {
	protected static final String AUTHORIZATION = "token";
	private static OkHttpClient singleton;

	public static OkHttpClient getInstance() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
			@Override
			public void log(String message) {
				Log.e("http", message);
			}
		});
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		Interceptor mTokenInterceptor = new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request originalRequest = chain.request();
				// if (Your.sToken == null ||
				// alreadyHasAuthorizationHeader(originalRequest)) {
				// return chain.proceed(originalRequest);
				// }
				Request authorised = originalRequest.newBuilder().header(AUTHORIZATION, AUTHORIZATION).build();
				return chain.proceed(authorised);
			}
		};
		singleton = new OkHttpClient.Builder().addNetworkInterceptor(mTokenInterceptor).addInterceptor(interceptor)
				.retryOnConnectionFailure(true).connectTimeout(15, TimeUnit.SECONDS)
				// .authenticator(new Authenticator() {
				// @Override
				// public Request authenticate(Route route, Response response)
				// throws IOException {
				// //token 失效
				// // Refresh your access_token using a synchronous api request
				//// newAccessToken = service.refreshToken();
				// Log.e("lxh", "authenticator==="+response.header("token"));
				// // Add new header to rejected request and retry it
				// return response.request().newBuilder()
				// .header(AUTHORIZATION, TOKEN)
				// .build();
				// }
				// })
				.build();
		return singleton;
	}

}