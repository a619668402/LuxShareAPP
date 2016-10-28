package com.luxshare.baseadapter;

/**
 * Created by Administrator on 2016/9/29.
 */
public class MyBean {

    private boolean flag;

    private String mString;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getString() {
        return mString;
    }

    public void setString(String string) {
        mString = string;
    }

    public MyBean(boolean flag, String string) {
        this.flag = flag;
        mString = string;
    }
}
