package com.example.gm.mg_notifition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gm.mg_notifition.mg_notification.MG_Notification;
import com.example.gm.mg_notifition.mg_notification.MG_NotificationType;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MG_Notification mg_notification = new MG_Notification.Builder(this, MG_NotificationType.NORMAL_NOTIFICATION)
                .title("标题")
                .message("内容")
                .intentAction("com.example.otherPage")  //隐式跳转action
                .create();
        mg_notification.show();



        final MG_Notification mg_notice_progress = new MG_Notification.Builder(this, MG_NotificationType.PROGRESS_NOTIFICATION)
                .title("标题_进度条")
                .message("内容_进度条")
                .progress_max(100) //最大值
                .progress_rate(1)  //当前值,初始为默认为0
                .intentAction("com.example.otherPage")
                .create();
        mg_notice_progress.show();




        //--------进度条通知的滚动线程-----------
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progress = 0;

            @Override
            public void run() {
                Log.i("progress", progress + "");
                while (progress <= 100) {
                    progress++;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //更新进度条
                    mg_notice_progress.setProgress_rate(progress);
                    //再次通知
//                    notificationManager.notify(2, builderProgress.build());
                }
                //计时器退出
                this.cancel();
                //进度条退出
                mg_notice_progress.cancel();
                return;//结束方法
            }
        }, 0);


    }
}
