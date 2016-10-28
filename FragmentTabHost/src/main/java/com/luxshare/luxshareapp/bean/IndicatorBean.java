package com.luxshare.luxshareapp.bean;

/**
 * Created by Administrator on 2016/9/26.
 */
public class IndicatorBean {

    private int titleId;

    private int iconId;

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public IndicatorBean() {
    }

    public IndicatorBean(int iconId, int titleId) {
        this.iconId = iconId;
        this.titleId = titleId;
    }
}
