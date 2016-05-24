package com.help.help.enums;

import com.help.help.R;
import com.help.help.ui.fragment.AccountFragment;
import com.help.help.ui.fragment.HomeFragment;
import com.help.help.ui.fragment.ListFragment;

public enum MainTab {

    NEWS(0, R.string.main_tab_name_home, R.drawable.tab_icon_new,
            HomeFragment.class),

    TWEET(1, R.string.main_tab_name_tweet, R.drawable.tab_icon_new,
            ListFragment.class),

    QUICK(2, R.string.main_tab_name_quick, R.drawable.tab_icon_new,
            AccountFragment.class);


    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
