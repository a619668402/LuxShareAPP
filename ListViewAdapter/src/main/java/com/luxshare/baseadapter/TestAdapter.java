package com.luxshare.baseadapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.luxshare.baseadapter.adapter.BaseViewHolder;
import com.luxshare.baseadapter.adapter.CommonAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public class TestAdapter extends CommonAdapter<MyBean> {

    public TestAdapter(Context context, List list, int layoutId) {
        super(context, list, layoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, MyBean item) {
        holder.setText(R.id.textView,item.getString());
        String str = null;
        if (item.isFlag()) {
            str = "点击过";
        } else {
            str = "未点击过";
        }
        ((Button) holder.getView(R.id.button)).setText(str);
        ((Button) holder.getView(R.id.button)).setOnClickListener(new MyClickListener(holder,item));
    }


    class MyClickListener implements View.OnClickListener {

        private BaseViewHolder mHolder;

        private MyBean mBean;

        MyClickListener(BaseViewHolder holder,MyBean bean) {
            this.mHolder = holder;
            this.mBean = bean;
        }

        @Override
        public void onClick(View v) {
            mBean.setFlag(true);
            ((Button) v).setText("点击过");
        }
    }
}
