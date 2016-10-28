package com.luxshare.textokhttp.okGo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/14.
 */
public class LzyResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public boolean IsSuccess;
    public Object ErrMsg;
    public T data;

}
