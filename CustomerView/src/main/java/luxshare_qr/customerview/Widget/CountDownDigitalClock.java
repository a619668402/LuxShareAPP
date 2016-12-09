package luxshare_qr.customerview.Widget;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.AttributeSet;
import android.widget.DigitalClock;

import java.util.Calendar;

/**
 * Created by Luxshare-ICT on 2016/12/1.
 * 类似限时抢购的倒计时控件
 */

public class CountDownDigitalClock extends DigitalClock {

    private Calendar mCalendar;

    private final static String m12 = "h:mm:ss";
    private final static String m24 = "k:mm";

    private FormateChangeObserver mFormateChangeObserver;

    private Runnable mTicker;
    private Handler mHandler;

    private long mEndTime;

    private ClockListener mClockListener;

    private boolean mTickerStopped = false;

    private String mFormat;

    public CountDownDigitalClock(Context context) {
        this(context, null);
    }


    public CountDownDigitalClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        initClock(context);
    }

    private void initClock(Context context) {
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
        }

        mFormateChangeObserver = new FormateChangeObserver();
        getContext().getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, mFormateChangeObserver);
        setFormat();
    }

    @Override
    protected void onAttachedToWindow() {
        mTickerStopped = false;
        super.onAttachedToWindow();
        mHandler = new Handler();

        mTicker = new Runnable() {
            @Override
            public void run() {
                if (mTickerStopped) {
                    return;
                }
                long currentTime = System.currentTimeMillis();
                if (currentTime / 1000 == mEndTime / 1000 - 5 * 60) {
                    mClockListener.remainFiveMinutes();
                }
                long distanceTime = mEndTime - currentTime;
                distanceTime /= 1000;
                if (distanceTime == 0) {
                    setText("00:00:00");
                    onDetachedFromWindow();
                    mClockListener.timeEnd();
                } else if (distanceTime < 0) {
                    setText("00:00:00");
                } else {
                    setText(dealTime(distanceTime));
                }
                invalidate();
                long now = SystemClock.uptimeMillis();
                long next = now + (1000 - now % 1000);
                mHandler.postAtTime(mTicker, next);
            }
        };
        mTicker.run();
    }

    /**
     * deal time string
     *
     * @param time
     * @return
     */
    public static String dealTime(long time) {
        StringBuffer returnString = new StringBuffer();
        long day = time / (24 * 60 * 60);
        long hours = (time % (24 * 60 * 60)) / (60 * 60);
        long minutes = ((time % (24 * 60 * 60)) % (60 * 60)) / 60;
        long second = ((time % (24 * 60 * 60)) % (60 * 60)) % 60;
        String dayStr = String.valueOf(day);
        String hoursStr = timeStrFormat(String.valueOf(hours));
        String minutesStr = timeStrFormat(String.valueOf(minutes));
        String secondStr = timeStrFormat(String.valueOf(second));

        returnString.append(hoursStr).append(":")
                .append(minutesStr).append(":")
                .append(secondStr);

        return returnString.toString();
    }

    /**
     * format time
     *
     * @param timeStr
     * @return
     */
    private static String timeStrFormat(String timeStr) {
        switch (timeStr.length()) {
            case 1:
                timeStr = "0" + timeStr;
                break;
        }
        return timeStr;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTickerStopped = true;
        getContext().getContentResolver().unregisterContentObserver(mFormateChangeObserver);
    }

    public void setEndTime(long endTime) {
        mEndTime = endTime;
    }


    private boolean get24HourMode() {
        return android.text.format.DateFormat.is24HourFormat(getContext());
    }

    private void setFormat() {
        if (get24HourMode()) {
            mFormat = m24;
        } else {
            mFormat = m12;
        }
    }

    private class FormateChangeObserver extends ContentObserver {

        /**
         * Creates a content observer.
         */
        public FormateChangeObserver() {
            super(new Handler());
        }

        @Override
        public void onChange(boolean selfChange) {
            setFormat();
        }
    }

    public void setClockListener(ClockListener clockListener) {
        mClockListener = clockListener;
    }

    public interface ClockListener {
        void timeEnd();

        void remainFiveMinutes();
    }
}
