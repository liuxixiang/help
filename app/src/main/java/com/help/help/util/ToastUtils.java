package com.help.help.util;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtils {

	private static ToastUtils mToastUtils = null;
	private static Toast mToast = null;

	public ToastUtils(Context context) {
		mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
	}

	public static ToastUtils getStance(Context context) {
		if (mToastUtils == null) {
			mToastUtils = new ToastUtils(context);
		}
		return mToastUtils;
	}
	
	public void showShortToast(final String msg) {
		new Handler().post(new Runnable() {
			@Override
			public void run() {
				mToast.setText(msg);
				mToast.show();
			}
		});
	}


	public void showCentreShortToast(final String msg) {
		new Handler().post(new Runnable() {
			@Override
			public void run() {
				mToast.setText(msg);
				mToast.setGravity(Gravity.CENTER, 0, 0);
				mToast.show();
			}
		});
	}
}
