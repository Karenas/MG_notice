package com.example.gm.mg_notifition.mg_notification;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * 使用Builder模式
 */
public class MG_Notification extends MG_notificationHandler{
    //必要参数
    private final Context mContext;
    private final MG_NotificationType type;

    //可选参数
    private final String title;
    private final String content;
    private final String intentAction;
    private final Bundle intentBundle;

    private final int max_progress;
    private final int rate_progress;


    private MG_Notification(Builder builder) {
        this.mContext = builder.mContext;
        this.type = builder.type;
        this.title = builder.title;
        this.content = builder.content;
        this.intentAction = builder.intentAction;
        this.intentBundle = builder.intentBundle;

        this.max_progress = builder.max_progress;
        this.rate_progress = builder.rate_progress;


        super.setType(type);
        super.setTitle(title);
        super.setContent(content);
        super.setIntentAction(intentAction);
        super.setIntentBundle(intentBundle);
        super.setProgress_max(max_progress);
        super.setProgress_rate(rate_progress);
    }

    public void show(){
        super.notifyNotification();
    }

    public void cancel(){
        super.cancel();
    }

    public static class Builder{
        //必要参数
        private final MG_NotificationType type;
        private final Context mContext;

        //可选参数
        private String title;
        private String content;
        private String intentAction;
        private Bundle intentBundle;

        private int max_progress;
        private int rate_progress;

        public Builder(@NonNull Context mContext, MG_NotificationType type) {
            this.mContext = mContext;
            this.type = type;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder message(String message) {
            this.content = message;
            return this;
        }

        public Builder intentAction(String intentAction){
            this.intentAction = intentAction;
            return this;
        }

        public Builder intentBundle(Bundle intentBundle){
            this.intentBundle = intentBundle;
            return this;
        }

        public Builder progress_max(int max_progress){
            this.max_progress = max_progress;
            return this;
        }

        public Builder progress_rate(int rate_progress){
            this.rate_progress = rate_progress;
            return this;
        }

        public MG_Notification create() {
            MG_Notification mg_notification = new MG_Notification(this);
            mg_notification.createNotification(mContext);
            return mg_notification;
        }

//        public MG_Notification show() {
//            MG_Notification mg_notification = create();
//            mg_notification.show();
//            return mg_notification;
//        }

    }


}
