package com.luxshare.utils.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by Administrator on 2016/10/6.
 * 网络相关的工具类
 */
public class NetWorkUtils {

    /**
     * 网络类型 无连接
     */
    public static final int NETWORK_TYPE_NO_CONNECTION = -1231545315;

    public static final String NETWORK_TYPE_WIFI = "wifi";
    public static final String NETWORK_TYPE_3G = "3g";
    public static final String NETWORK_TYPE_2G = "2g";
    public static final String NETWORK_TYPE_WAP = "wap";
    public static final String NETWORK_TYPE_UNKNOWN = "unknown";
    public static final String NETWORK_TYPE_DISCONNECT = "disconnect";


    /**
     * 获取网络状态
     *
     * @param context
     * @return 网络状态
     */
    public static int getNetworkType(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager == null ? null : manager.getActiveNetworkInfo();
        return networkInfo == null ? -1 : networkInfo.getType();
    }


    /**
     * Get network type name
     * @param context
     * @return NetworkTypeName
     */
    public static String getNetworkTypeName(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        String type = NETWORK_TYPE_DISCONNECT;
        if (manager == null || (networkInfo = manager.getActiveNetworkInfo()) == null) {

            return type;
        }

        if (networkInfo.isConnected()) {

            String typeName = networkInfo.getTypeName();
            if ("WIFI".equalsIgnoreCase(typeName)) {
                return NETWORK_TYPE_WIFI;
            } else if ("MoBILE".equalsIgnoreCase(typeName)) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                type = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(context) ? NETWORK_TYPE_3G : NETWORK_TYPE_2G) :NETWORK_TYPE_WAP;
            } else {
                type = NETWORK_TYPE_UNKNOWN;
            }
        }
        return type;
    }


    /**
     * Whether is fast mobile network
     * @param context
     * @return FastMobileNetwork
     */
    public static boolean isFastMobileNetwork(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {

            return false;
        }

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }


    /**
     * 获取当前网络状态
     * @param context
     * @return 当前网络的状态。具体类型可参照NetworkInfo.State.CONNECTED、NetworkInfo.State.CONNECTED.DISCONNECTED等字段。当前没有网络连接时返回null
     */
    public static NetworkInfo.State getCurrentNetworkState(Context context) {

        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null ? networkInfo.getState() : null;
    }

    /**
     * 获取当前网络类型
     * @param context
     * @return 当前网络的类型。具体类型可参照ConnectivityManager中的TYPE_BLUETOOTH、TYPE_MOBILE、TYPE_WIFI等字段。当前没有网络连接时返回NetworkUtils.NETWORK_TYPE_NO_CONNECTION
     */
    public static int getCurrentNetworkType(Context context) {

        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null ? networkInfo.getType() : NETWORK_TYPE_NO_CONNECTION;
    }


    /**
     * 判断当前网络是否已经连接
     * @param context
     * @return 当前网络是否已经连接。false：尚未连接
     */
    public static boolean isConnectedByState(Context context) {

        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTED;
    }


    /**
     * 判断当前网络是否正在连接
     * @param context
     * @return 当前网络是否正在连接
     */
    public static boolean isConnectingByState(Context context) {

        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTING;
    }


    /**
     * 判断当前网络是否已经断开
     * @param context
     * @return 判断网络是否已经断开
     */
    public static boolean isDisconnectedByState(Context context) {

        return getCurrentNetworkState(context) == NetworkInfo.State.DISCONNECTED;
    }


    /**
     * 判断当前网络的类型是否是移动网络
     * @param context
     * @return 当前网络的类型是否是移动网络。false：当前没有网络连接或者网络类型不是移动网络
     */
    public static boolean isMobileByType(Context context) {

        return getCurrentNetworkType(context) == ConnectivityManager.TYPE_MOBILE;
    }


    /**
     * 判断当前网络的类型是否是Wifi
     * @param context
     * @return 当前网络的类型是否是Wifi。false：当前没有网络连接或者网络类型不是wifi
     */
    public static boolean isWifiByType(Context context) {

        return getCurrentNetworkType(context) == ConnectivityManager.TYPE_WIFI;
    }
}
