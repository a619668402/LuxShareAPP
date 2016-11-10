package com.luxshare.Widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/4.
 */
public class CustomRelativeLayout extends RelativeLayout {


    public static interface RetryListener {
        void retry();
    }

    private ProgressBar mProgressBar = null;

    private Button mBtn_refresh;

    private TextView mTextView;

    private boolean mIsNormal = true;

    private RetryListener mRetryListener;

    private final static int tvTipId = 0x1001;


    public CustomRelativeLayout(Context context) {
        this(context, null);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置加载页面
     */
    public void setInProgress() {
        if (mProgressBar == null) {
            int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics());
            mProgressBar = new ProgressBar(getContext());
            RelativeLayout.LayoutParams lp = new LayoutParams(size, size);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            mProgressBar.setLayoutParams(lp);
            addView(mProgressBar);
        }

        if (getChildCount() > 0) {
            int childCount = getChildCount();
            if (childCount > 0) {
                for (int i = (childCount - 1); i >= 0; i--) {
                    getChildAt(i).setVisibility(View.GONE);
                }
            }
        }

        if (mBtn_refresh != null) {
            mBtn_refresh.setVisibility(View.GONE);
        }
        if (mTextView != null) {
            mTextView.setVisibility(View.GONE);
        }
        mProgressBar.setVisibility(View.VISIBLE);
    }


    /**
     * 显示加载失败页面，隐藏所有正常的view元素
     */
    public void setChildrenGone() {
        if (mBtn_refresh == null) {
            mBtn_refresh = new Button(getContext());
            mTextView = new TextView(getContext());
            mTextView.setText("加载失败，请重试");
            mTextView.setGravity(Gravity.CENTER);
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            mBtn_refresh.setText("刷新");

            mBtn_refresh.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRetryListener != null) {
                        mRetryListener.retry();
                    } else {
                        Toast.makeText(getContext(), "暂无数据", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            int btnWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
            RelativeLayout.LayoutParams lp = new LayoutParams(btnWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.addRule(CENTER_IN_PARENT);
            lp.setMargins(0, 10, 0, 0);
            mBtn_refresh.setLayoutParams(lp);
            mBtn_refresh.setId(tvTipId);
            mBtn_refresh.setVisibility(View.GONE);
            addView(mBtn_refresh);

            RelativeLayout.LayoutParams lp_tv = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lp_tv.addRule(RelativeLayout.ABOVE, tvTipId);
            mTextView.setLayoutParams(lp_tv);
            addView(mTextView);

            if (getChildCount() > 0) {
                int childCount = getChildCount();
                if (childCount > 0) {
                    for (int i = (childCount - 1); i >= 0; i--) {
                        getChildAt(i).setVisibility(View.GONE);
                    }
                }
            }

            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.GONE);
            }
            mIsNormal = false;
            mBtn_refresh.setVisibility(View.VISIBLE);
            mTextView.setVisibility(View.VISIBLE);
        }
    }


    public void setChildrenVisible() {
        if (getChildCount() > 0) {
            int childCount = getChildCount();
            if (childCount > 0) {
                for (int i = (childCount - 1); i >= 0; i--) {
                    getChildAt(i).setVisibility(View.VISIBLE);
                }
            }
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.GONE);
            }
            if (mBtn_refresh != null) {
                mBtn_refresh.setVisibility(View.GONE);
            }
            if (mTextView != null) {
                mTextView.setVisibility(View.GONE);
            }
            mIsNormal = true;
        }
    }


    public boolean isNormalView() {
        return mIsNormal;
    }

    public void setRetryListener(RetryListener retryListener) {
        mRetryListener = retryListener;
    }
}


