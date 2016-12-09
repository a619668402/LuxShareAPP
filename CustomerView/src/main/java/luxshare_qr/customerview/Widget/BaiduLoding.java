package luxshare_qr.customerview.Widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

import luxshare_qr.customerview.R;

/**
 * Created by Luxshare-ICT on 2016/12/7.
 */

public class BaiduLoding extends FrameLayout {

    /**
     * 开始执行的第一个动画索引
     */
    private int mStartIndex = 0;

    /**
     * 交换执行动画的源图片数组
     */
    private int[] src = {R.drawable.red_circle_shape, R.drawable.blue_circle_shape, R.drawable.yellow_circle_shape};

    /**
     * 存放三个ImageView的集合
     */
    private ArrayList<ImageView> mViews = new ArrayList<>();

    /**
     * 执行动画的集合
     */
    private AnimatorSet mAnimatorSet;

    private int maxRadius = 160;

    public BaiduLoding(Context context) {
        this(context, null);
    }

    public BaiduLoding(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.baidu_loding_layout, this, true);
        findViewById();
        startAnima();
    }

    private void startAnima() {
        ObjectAnimator animatorLeft = ObjectAnimator.ofFloat(mViews.get(0), "translationX", 0, -maxRadius, 0);
        animatorLeft.setRepeatCount(-1);
        animatorLeft.setDuration(500);

        ObjectAnimator animatorRight = ObjectAnimator.ofFloat(mViews.get(1), "translationX", 0, maxRadius, 0);
        animatorRight.setRepeatCount(-1);
        animatorRight.setDuration(500);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(animatorRight).with(animatorLeft);
        mAnimatorSet.setInterpolator(new LinearInterpolator());
        mAnimatorSet.start();

        animatorLeft.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                if (mStartIndex == 0) {
                    sweep(0, 2);
                    mStartIndex = 1;
                } else {
                    sweep(1, 2);
                    mStartIndex = 0;
                }
            }
        });
    }

    private void sweep(int i, int i1) {
        mViews.get(i).setImageResource(src[i1]);
        mViews.get(i1).setImageResource(src[i]);
        int temp = src[i1];
        src[i1] = src[i];
        src[i] = temp;
    }

    private void findViewById() {
        ImageView iv_red = (ImageView) findViewById(R.id.red);
        ImageView iv_blue = (ImageView) findViewById(R.id.blue);
        ImageView iv_yellow = ((ImageView) findViewById(R.id.yellow));

        mViews.add(iv_red);
        mViews.add(iv_blue);
        mViews.add(iv_yellow);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAnimatorSet.cancel();
    }
}
