package com.help.help.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.help.help.widget.fragmentTabHost.MyFragmentTabHost;
import com.help.help.R;
import com.help.help.enums.MainTab;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Bind(android.R.id.tabhost)
    public MyFragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initActionBar();
        initTabs();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private void initActionBar() {
        hideActionbar();
    }

    private void initTabs() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.main_tabcontent);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView icon = (ImageView) indicator.findViewById(R.id.tab_icon);
            Drawable drawable = this.getResources().getDrawable(
                    mainTab.getResIcon());
//            drawable.setBounds(0, 0, 10,10);
//            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null,
//                    null);
            icon.setImageDrawable(drawable);
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            mTabHost.addTab(tab, mainTab.getClz(), null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
