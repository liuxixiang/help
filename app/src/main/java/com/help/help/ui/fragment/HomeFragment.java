package com.help.help.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.help.help.R;
import com.help.help.http.HelpHttpApi;
import com.kymjs.rxvolley.client.HttpCallback;

/**
 * Created by liuxihui on 2016/2/22.
 */
public class HomeFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        HelpHttpApi.login(new HttpCallback() {
//            @Override
//            public void onFailure(int errorNo, String strMsg) {
//                super.onFailure(errorNo, strMsg);
//                Log.e("lxh","---onFailure--");
//            }
//
//            @Override
//            public void onSuccess(String t) {
//                super.onSuccess(t);
//                Log.e("lxh","---onSuccess--" + t);
//            }
//        },"adley2", "12345");

        HelpHttpApi.login1(getActivity(),"adley3", "123456");
    }
}
