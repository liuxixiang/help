package com.help.help.http;

public interface HttpCallBack<T> {
	 void onResponse(T response);
     void onFailure(String msg);
}
