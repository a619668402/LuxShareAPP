package com.luxshare.utils.utils;

import android.os.Handler;

/**
 * Created by Administrator on 2016/10/21.
 * 循环定时器
 */
public class LoopTimer {

    private int intervalMillis; // 间隔时间
    private boolean running; // 运行状态
    private Handler handler; // 消息处理器
    private ExecuteRunnable mRunnable;

    /**
     * 执行Runnable
     */
    private class ExecuteRunnable implements Runnable {

        private Runnable mRunnable;

        public ExecuteRunnable(Runnable runnable) {
            this.mRunnable = runnable;
        }

        @Override
        public void run() {
            if (running && mRunnable != null) {
                mRunnable.run();
            }
        }

        public void setRunnable(Runnable runnable) {
            this.mRunnable = runnable;
        }
    }


    /**
     * 创建一个循环定时器
     *
     * @param handler
     * @param runnable
     * @param intervalMillis
     */
    public LoopTimer(Handler handler, Runnable runnable, int intervalMillis) {
        this.handler = handler;
        this.intervalMillis = intervalMillis;
        this.mRunnable = new ExecuteRunnable(runnable);
    }

    /**
     * 创建循环定时器
     * @param runnable
     * @param intervalMillis
     */
    public LoopTimer(Runnable runnable, int intervalMillis) {
        this(new Handler(), runnable, intervalMillis);
    }


    /**
     * 立即启动
     */
    public void start() {
        running = true;
        handler.removeCallbacks(mRunnable);
        handler.post(mRunnable);
    }


    /**
     * 延时启动
     */
    public void delayStart() {
        running = true;
        handler.removeCallbacks(mRunnable);
        handler.postDelayed(mRunnable,intervalMillis);
    }


    /**
     * 立即停止
     */
    public void stop() {
        running = false;
        handler.removeCallbacks(mRunnable);
    }


    /**
     * 获取间隔毫秒
     * @return
     */
    public int getIntervalMillis() {
        return intervalMillis;
    }


    /**
     * 设置间隔毫秒
     * @param intervalMillis
     */
    public void setIntervalMillis(int intervalMillis) {
        this.intervalMillis = intervalMillis;
    }


    /**
     * 是否正在运行
     * @return
     */
    public boolean isRunning() {
        return running;
    }


    /**
     * 设置消息处理器
     * @param handler
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }


    /**
     * 设置执行内容
     * @param runnable
     */
    public void setRunnable(Runnable runnable) {
        mRunnable.setRunnable(runnable);
    }
}
