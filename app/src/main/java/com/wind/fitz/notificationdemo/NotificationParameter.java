package com.wind.fitz.notificationdemo;

import android.support.annotation.NonNull;

public class NotificationParameter {

    private static NotificationParameter np;

    public static NotificationParameter getInstance() {
        if (np == null) {
            np = new NotificationParameter();
        }
        return np;
    }

    @NonNull String channelLevel;
    @NonNull int smallIcon = -1;
    @NonNull CharSequence contentTitle;
    @NonNull CharSequence contentText;
    @NonNull int notifyID = -1;
    String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public boolean checknull() {
        if (getChannelLevel() == null || getSmallIcon() == -1 || getContentTitle() == null || getContentText() == null || getNotifyID() == -1) {
            return true;
        } else {
            return false;
        }
    }

    public String getChannelLevel() {
        return channelLevel;
    }

    public void setChannelLevel(String channelLevel) {
        this.channelLevel = channelLevel;
    }


    public int getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
    }

    public CharSequence getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(CharSequence contentTitle) {
        this.contentTitle = contentTitle;
    }

    public CharSequence getContentText() {
        return contentText;
    }

    public void setContentText(CharSequence contentText) {
        this.contentText = contentText;
    }

    public int getNotifyID() {
        return notifyID;
    }

    public void setNotifyID(int notifyID) {
        this.notifyID = notifyID;
    }


}
