package com.help.help.widget.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.help.help.R;


public class LoadingDialog extends ProgressDialog {
    private Context mContext;
    private View mDialogView;
    private TextView mTxtMsg;
    private Dialog mDialog;

    public LoadingDialog(Context context) {
        super(context);
        this.mContext = context;
        mDialogView = LayoutInflater.from(context)
                .inflate(R.layout.dialog_loading, null);
        mTxtMsg = (TextView) mDialogView.findViewById(R.id.txtMsg);
        initDialog();
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    private void initDialog() {
        mDialog = new Dialog(mContext, R.style.net_loading_dialog);
        mDialog.setContentView(mDialogView);
        mDialog.setCanceledOnTouchOutside(false);
    }

    public void setMessage(CharSequence msg) {
        mTxtMsg.setText(msg);
    }

    public void setMessage(int msg) {
        mTxtMsg.setText(msg);
    }

    public void show() {
        if (mDialog != null)
            mDialog.show();
    }

    public void dismiss() {
        if (mDialog != null)
            mDialog.dismiss();
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }

}
