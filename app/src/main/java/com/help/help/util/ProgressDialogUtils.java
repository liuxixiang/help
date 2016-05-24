package com.help.help.util;

import android.content.Context;
import android.content.DialogInterface;

import com.help.help.R;
import com.help.help.widget.dialog.LoadingDialog;


public class ProgressDialogUtils {
    private static LoadingDialog mProgressDialog = null;
    private static int loadCount = 0;
    private Context mContext;
    private static ProgressDialogUtils instance;

    public static ProgressDialogUtils getInstance() {
        if (instance == null) {
            instance = new ProgressDialogUtils();
        }
        return instance;
    }

    public ProgressDialogUtils() {
    }

    public void showProgressDialog(Context context) {
        showProgressDialog(context, context.getString(R.string.please_wait_str));
    }

    public void showProgressDialog(Context context, String msg) {
        if (mContext != null) {
            if (!mContext.equals(context)) {
                loadCount = 0;
            }
            mContext = context;
        } else {
            mContext = context;
        }
        if (loadCount < 0) {
            loadCount = 0;
        }
        loadCount++;
        if (loadCount == 1) {
            try {

                if (mProgressDialog == null || !mProgressDialog.isShowing()) {
                    mProgressDialog = new LoadingDialog(context);
                    mProgressDialog.show();
                    mProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            loadCount = 0;
                        }
                    });

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dismissProgressDialog(Context context) {
        loadCount--;
        if (loadCount <= 0) {
            try {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                loadCount = 0;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
