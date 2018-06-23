package com.wind.fitz.notificationdemo.manager;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringDef;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class NotificationChannelManager {

    private static String TAG = "NotificationChannelManager";
    private static NotificationChannelManager ncm;


    public static NotificationChannelManager getInstance() {
        Log.d(TAG, "getInstance: instance: " + ncm);
        if (ncm == null) {
            ncm = new NotificationChannelManager();
        }
        return ncm;
    }

    //basic notification channel ID
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({CHANNEL.MAX_NOTIFICATION, CHANNEL.HIGH_NOTIFICATION, CHANNEL
            .DEFAULT_NOTIFICATION, CHANNEL.LOW_NOTIFICATION, CHANNEL
            .MIN_NOTIFICATION})
    public @interface CHANNEL {
        String MAX_NOTIFICATION     = "maxnotif";
        String HIGH_NOTIFICATION    = "highnotif";
        String DEFAULT_NOTIFICATION = "defnotif";
        String LOW_NOTIFICATION     = "lownotif";
        String MIN_NOTIFICATION     = "minnotif";
    }

    @CHANNEL
    public static final String[] allChannels = new String[]{CHANNEL.MAX_NOTIFICATION, CHANNEL
            .HIGH_NOTIFICATION, CHANNEL
            .DEFAULT_NOTIFICATION, CHANNEL.LOW_NOTIFICATION, CHANNEL.MIN_NOTIFICATION};

    @NonNull
    @RequiresApi(26)
    public NotificationChannel getChannel(@NonNull Context context, @CHANNEL String channelId) {

        Log.d(TAG, "getChannel..channelId: " + channelId);
        NotificationChannel channel = getNotificationManager(context).getNotificationChannel
                (channelId);
        if (channel == null) {
            channel = createChannel(context, channelId);
        }
        return channel;
    }

    /**
     * Set the channel of notification appropriately. Will create the channel if
     * it does not already exist. Safe to call pre-O (will no-op).
     */
    @TargetApi(26)
    public static void applyChannel(@NonNull NotificationCompat.Builder notification, @NonNull
            Context context, @CHANNEL String channelId) {
        // checkNullity(channelId;
        Log.d(TAG, "applyChannel: : context: " + context + " channelId: " + channelId);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = NotificationChannelManager
                    .getInstance()
                    .getChannel(context, channelId);
            notification.setChannelId(channel.getId());
        }
    }

    /*NotificationCompat.Builder mBuilder = new NotificationCompat.Builder
    (this, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT);*/

    /*
     *
     * @create notification channels
     */
    /*
     * private void setUpNotificationChannel(Context context) {
     *
     * int importance; final NotificationManager mNotificationManager =
     * (NotificationManager)
     * context.getSystemService(Context.NOTIFICATION_SERVICE);
     *
     * importance = NotificationManager.IMPORTANCE_HIGH; channelHigh = new
     * NotificationChannel(HIGH_CHANNEL_ID, "Hello", importance);
     * mNotificationManager.createNotificationChannel(channelHigh); }
     *
     * String getHighNotificationChannel() { return HIGH_CHANNEL_ID; }
     */

    private static NotificationManager getNotificationManager(@NonNull Context context) {
        return context.getSystemService(NotificationManager.class);
    }

    @RequiresApi(26)
    private NotificationChannel createChannel(Context context, @CHANNEL String channelId) {

        Log.d("fitz", channelId);

        Uri          silentRingtone = Uri.EMPTY;
        CharSequence name;
        int          importance;    //通知等级
        boolean      canShowBadge;  //应用桌面角标
        boolean      lights;        //灯
        boolean      vibration;     //震动
        boolean      dnd;           //绕过免打扰
        // int lockScreen;
        Uri sound;                  //铃声

        switch (channelId) {

            case CHANNEL.MAX_NOTIFICATION:
                //It`s Unused.
                name = "MAX Notifications";
                importance = NotificationManager.IMPORTANCE_MAX;
                canShowBadge = false;
                lights = false;
                vibration = false;
                dnd = false;
                // lockScreen = VISIBILITY_PUBLIC;
                break;

            case CHANNEL.HIGH_NOTIFICATION:
                name = "HIGH Notifications";
                importance = NotificationManager.IMPORTANCE_HIGH;
                canShowBadge = true;
                lights = true;
                vibration = true;
                dnd = true;
                // lockScreen = VISIBILITY_PUBLIC;
                break;

            case CHANNEL.DEFAULT_NOTIFICATION:
                name = "DEFAULT Notifications";
                importance = NotificationManager.IMPORTANCE_DEFAULT;
                canShowBadge = true;
                lights = true;
                vibration = false;
                dnd = false;
                // lockScreen = VISIBILITY_PUBLIC;
                break;

            case CHANNEL.LOW_NOTIFICATION:
                name = "LOW Notifications";
                importance = NotificationManager.IMPORTANCE_LOW;
                canShowBadge = false;
                lights = false;
                vibration = false;
                dnd = false;
                // lockScreen = VISIBILITY_PUBLIC;
                break;

            case CHANNEL.MIN_NOTIFICATION:
                name = "MIN Notifications";
                importance = NotificationManager.IMPORTANCE_MIN;
                canShowBadge = false;
                lights = false;
                vibration = false;
                dnd = false;
                // lockScreen = VISIBILITY_PUBLIC;
                break;

            default:
                throw new IllegalArgumentException("Unknown channel: " + channelId);
        }
        NotificationChannel channel = new NotificationChannel(channelId, name,
                importance);
        channel.setShowBadge(canShowBadge);
        channel.enableVibration(vibration);
        channel.enableLights(lights);
        channel.setBypassDnd(dnd);
        channel.setDescription(channelId);
        //channel.setSound(sound, null);
        //channel.setGroup(channelId);
        //channel.setLightColor(1234);
        //channel.setLockscreenVisibility(1);
        //channel.setVibrationPattern(new long[111]);
        //channel.setLockscreenVisibility(lockScreen);

        getNotificationManager(context).createNotificationChannel(channel);
        return channel;
    }


}
