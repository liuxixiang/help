package com.help.help.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.help.help.ui.activity.BaseActivity;
import com.help.help.util.ACache;
import com.help.help.util.HelpUtil;

/**
 * Created by liuxihui on 2015/10/14.
 */
public class BaseFragment extends Fragment implements View.OnClickListener {
    protected BaseActivity mActivity;
    protected ACache aCache;//缓存

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isAdded()) return;
        if (getActivity() instanceof BaseActivity)
            mActivity = (BaseActivity) getActivity();
        aCache = ACache.get(mActivity);
    }

    protected <V> V findView(View view, int id) {
        //noinspection unchecked
        return (V) view.findViewById(id);
    }

    @Override
    public void onClick(View v) {
        if (HelpUtil.isFastDoubleClick()) {
            return;
        }
    }
}
