package com.example.gm.mg_notifition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.gm.mg_notifition.mg_notification.MG_Notification;
import com.example.gm.mg_notifition.mg_notification.MG_NotificationType;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private final int progressMax = 300; //进度条通知的最大进度值
    private EditText app_testTransfer_et;

    public static final String BUNDLE_STR_KEY = "str_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app_testTransfer_et = findViewById(R.id.app_testTransfer_et);


    }

    public void noticeNormal(View v){
        Bundle bundle = initBundle();

        MG_Notification mg_notification = new MG_Notification.Builder(this, MG_NotificationType.NORMAL_NOTIFICATION)
                .title("标题 title")
                .message("内容  content")
                .intentAction("com.example.otherPage")  //隐式跳转action
                .intentBundle(bundle) //跳转传值
                .create();
        mg_notification.show();

        initEditTextValue();
    }

    public void noticeProgress(View v){
        Bundle bundle = initBundle();

        MG_Notification mg_notice_progress = new MG_Notification.Builder(this, MG_NotificationType.PROGRESS_NOTIFICATION)
                .title("标题_进度条  title_progress")
                .message("内容_进度条  content_progress")
                .progress_max(progressMax) //最大值
                .progress_rate(10)  //当前起始值,不调用此方法的话初始值默认为0
                .intentAction("com.example.otherPage")
                .intentBundle(bundle)
                .create();
        mg_notice_progress.show();

        initEditTextValue();

        startProgressUpdateThread(mg_notice_progress);
    }

    /**
     * 对进度类型通知进行状态更新
     * */
    private void startProgressUpdateThread(final MG_Notification mg_notice_progress){
        //--------进度条通知的滚动线程-----------
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int progress = 0;

            @Override
            public void run() {
                Log.i("progress", progress + "");
                while (progress <= progressMax) {
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
        }, 500);
    }

    private Bundle initBundle(){
        String value = app_testTransfer_et.getText().toString();
        if (!TextUtils.isEmpty(value)){
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_STR_KEY, value);
            return bundle;
        }
        return null;
    }

    private void initEditTextValue(){
        if (null!=app_testTransfer_et)
        app_testTransfer_et.setText(null);
    }

}
