package com.help.help.http;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * help API http请求相应接口
 */
public class HttpApi {

	/**
	 * 获取网络请求Service
	 * 
	 * @return
	 */
	private static APIService getAPIService() {
		return RetrofitUtils.getAPIService();
	}

	private static <T> void call(Call<T> call, final HttpCallBack myCallback) {
		if (call == null)
			return;
		call.enqueue(new Callback<T>() {
			@Override
			public void onResponse(Call<T> call, Response<T> response) {
				myCallback.onResponse(response.body());
			}

			@Override
			public void onFailure(Call<T> call, Throwable t) {
				myCallback.onFailure(t.getMessage());
			}
		});
	}

	/**
	 * 保存交易密码
	 * 
	 * @param <T>
	 * @param myCallback
	 * @param obj
	 */
//	public static <T> void saveTraderPassword(HttpCallBack myCallback, String... obj) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("traderPassword", obj[0]);
//		Call<ResponseResult> call = getAPIService().saveTraderPassword(map);
//		call(call, myCallback);
//	}

}
