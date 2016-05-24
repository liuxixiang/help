package com.help.help.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.help.help.R;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by liuxihui on 2016/2/22.
 */
public class ListFragment extends BaseFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Observable<String> mObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello work");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("lxh","onNext---" + s);
            }
        };

        mObservable.subscribe(subscriber);
        testRxJava();
    }

  private void testRxJava() {
      Observable<String> myObservable = Observable.just("你好帅");

      Action1<String> onNextAction = new Action1<String>() {
          @Override
          public void call(String s) {
              Log.e("lxh","call---" + s);
          }
      };

      myObservable.subscribe(onNextAction);

      Observable.just("祥好帅")
              .map(new Func1<String, String>() {
                  @Override
                  public String call(String s) {
                      return s+ "好睡啊";
                  }
              })
              .subscribe(onNextAction);
  }
}
