package com.luxshare.textokhttp.okGo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/14.
 */
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public boolean IsSuccess;
    public Object ErrMsg;

    public LzyResponse toLzyResponse() {
        LzyResponse lzyResponse = new LzyResponse();
        lzyResponse.IsSuccess = IsSuccess;
        lzyResponse.ErrMsg = ErrMsg;
        return lzyResponse;
    }
}
