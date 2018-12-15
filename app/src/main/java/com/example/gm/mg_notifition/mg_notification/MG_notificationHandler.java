package com.example.gm.mg_notifition.mg_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;

import com.example.gm.mg_notifition.R;


/**
 * Created by guomeng on 2018/12/13.
 */

class MG_notificationHandler{
    private NotificationCompat.Builder notification;
    private NotificationManager notificationManager;
    private Context mContext;

    private String title = "标题miss啦~";
    private String content = "内容miss啦~";
    private String intentAction = null;
    private Bundle intentBundle = null;

    private int progress_max = 100;
    private int progress_rate = 0;

    private int id = this.hashCode();


    private MG_NotificationType type = MG_NotificationType.NORMAL_NOTIFICATION;


    /**
     *
     *
     */
    protected void createNotification(@NonNull Context context){
        this.mContext = context;
        switch (type){
            case NORMAL_NOTIFICATION:
                createNormalNotification();
                break;
            case PROGRESS_NOTIFICATION:
                createNotificationProgress();
                break;
        }
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected void setContent(String content) {
        this.content = content;
    }

    protected void setType(MG_NotificationType type) {
        this.type = type;
    }

    protected void setIntentAction(String intentAction) {
        this.intentAction = intentAction;
    }

    protected void setIntentBundle(Bundle intentBundle){
        this.intentBundle = intentBundle;
    }

    protected void setProgress_max(int progress_max){
        this.progress_max = progress_max;
    }

    public void setProgress_rate(int progress_rate){
        this.progress_rate = progress_rate;
        if (progress_rate > 0 && null!=notification){
            notification.setProgress(progress_max, progress_rate, false);
            this.notifyNotification();
        }
    }

    private PendingIntent createJumpIntent(){
        if (TextUtils.isEmpty(intentAction)){
            return null;
        }

        Intent intent = new Intent(intentAction);
        //设置 Intent 的动作
        intent.setAction(intentAction);
        if (null != intentBundle){
            intent.putExtras(intentBundle);
        }

        return PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void createNormalNotification() {
        notification = new NotificationCompat.Builder(mContext); //使用 NotificationCompat.Builder 以兼容更多手机
                /**设置通知左边的大图标**/
        notification.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher));
                /**设置通知右边的小图标**/
        notification.setSmallIcon(R.mipmap.ic_launcher);
                /**通知首次出现在通知栏，带上升动画效果的**/
        notification.setTicker("通知来了");
                /**设置通知的标题**/
        notification.setContentTitle(title);
                /**设置通知的内容**/
        notification.setContentText(content);
                /**通知产生的时间，会在通知信息里显示**/
        notification.setWhen(System.currentTimeMillis());
                /**设置该通知优先级**/
        notification.setPriority(Notification.PRIORITY_DEFAULT);
                /**设置这个标志当用户单击面板就可以让通知将自动取消**/
        notification.setAutoCancel(true);
                /**设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)**/
        notification.setOngoing(false);
                /**向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：**/
        notification.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);
        notification.setContentIntent(createJumpIntent());

    }

    protected void createNotificationProgress() {
        //进度条通知
        notification = new NotificationCompat.Builder(mContext);
        notification.setContentTitle(title);
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("进度条通知");
        notification.setProgress(progress_max, 0, false);
        notification.setContentIntent(createJumpIntent());

        notification.setProgress(progress_max, progress_rate, false);
    }

    protected void notifyNotification(){
        if (null == notificationManager){
            notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
        }
        /**发起通知**/
        notificationManager.notify(id, notification.build());
    }

    protected void cancel(){
        if (null == notificationManager){
            notificationManager = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
        }
        /**发起通知**/
        notificationManager.cancel(id);
    }
}
