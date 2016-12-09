package com.luxshare.utils;

/**
 * Created by Luxshare-ICT on 2016/12/9.
 */

public class PermissionConstant {

    /**
     * Dangerous Permissions:
     * group:android.permission-group.CONTACTS
     * permission:android.permission.WRITE_CONTACTS
     * permission:android.permission.GET_ACCOUNTS
     * permission:android.permission.READ_CONTACTS
     * <p>
     * group:android.permission-group.PHONE
     * permission:android.permission.READ_CALL_LOG
     * permission:android.permission.READ_PHONE_STATE
     * permission:android.permission.CALL_PHONE
     * permission:android.permission.WRITE_CALL_LOG
     * permission:android.permission.USE_SIP
     * permission:android.permission.PROCESS_OUTGOING_CALLS
     * permission:com.android.voicemail.permission.ADD_VOICEMAIL
     * <p>
     * group:android.permission-group.CALENDAR
     * permission:android.permission.READ_CALENDAR
     * permission:android.permission.WRITE_CALENDAR
     * <p>
     * group:android.permission-group.CAMERA
     * permission:android.permission.CAMERA
     * <p>
     * group:android.permission-group.SENSORS
     * permission:android.permission.BODY_SENSORS
     * <p>
     * group:android.permission-group.LOCATION
     * permission:android.permission.ACCESS_FINE_LOCATION
     * permission:android.permission.ACCESS_COARSE_LOCATION
     * <p>
     * group:android.permission-group.STORAGE
     * permission:android.permission.READ_EXTERNAL_STORAGE
     * permission:android.permission.WRITE_EXTERNAL_STORAGE
     * <p>
     * group:android.permission-group.MICROPHONE
     * permission:android.permission.RECORD_AUDIO
     * <p>
     * group:android.permission-group.SMS
     * permission:android.permission.READ_SMS
     * permission:android.permission.RECEIVE_WAP_PUSH
     * permission:android.permission.RECEIVE_MMS
     * permission:android.permission.RECEIVE_SMS
     * permission:android.permission.SEND_SMS
     * permission:android.permission.READ_CELL_BROADCASTS
     */

    // group:android.permission-group.CONTACTS
    public static final int PERMISSION_WRITE_CONTACTS = 0x001;
    public static final int PERMISSION_GET_ACCOUNTS = 0x002;
    public static final int PERMISSION_READ_CONTACTS = 0x003;


    //  group:android.permission-group.PHONE
    public static final int PERMISSION_READ_CALL_LOG = 0x011;
    public static final int PERMISSION_READ_PHONE_STATE = 0x012;
    public static final int PERMISSION_CALL_PHONE = 0x013;
    public static final int PERMISSION_WRITE_CALL_LOG = 0x014;
    public static final int PERMISSION_USE_SIP = 0x015;
    public static final int PERMISSION_PROCESS_OUTGOING_CALLS = 0x016;
    public static final int PERMISSION_ADD_VOICEMAIL = 0x017;

    // group:android.permission-group.CALENDAR
    public static final int PERMISSION_READ_CALENDAR = 0x021;
    public static final int PERMISSION_WRITE_CALENDAR = 0x022;

    // group:android.permission-group.CAMERA
    public static final int PERMISSION_CAMERA = 0x031;

    // group:android.permission-group.SENSORS
    public static final int PERMISSION_BODY_SENSORS = 0x041;

    // group:android.permission-group.LOCATION
    public static final int PERMISSION_ACCESS_FINE_LOCATION = 0x051;
    public static final int PERMISSION_ACCESS_COARSE_LOCATION = 0x052;

    // group:android.permission-group.STORAGE
    public static final int PERMISSION_READ_EXTERNAL_STORAGE = 0x061;
    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 0x062;

    // group:android.permission-group.MICROPHONE
    public static final int PERMISSION_RECORD_AUDIO = 0x071;

    //group:android.permission-group.SMS
    public static final int PERMISSION_READ_SMS = 0x081;
    public static final int PERMISSION_RECEIVE_WAP_PUSH = 0x082;
    public static final int PERMISSION_RECEIVE_MMS = 0x083;
    public static final int PERMISSION_RECEIVE_SMS = 0x084;
    public static final int PERMISSION_SEND_SMS = 0x085;
    public static final int PERMISSION_READ_CELL_BROADCASTS = 0x086;

    // all
    public static final int PERMISSION_ALL = 0x091;
}
