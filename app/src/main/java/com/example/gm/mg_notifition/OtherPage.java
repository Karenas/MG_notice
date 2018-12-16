package com.example.gm.mg_notifition;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

/**
 * Created by guomeng on 12/15.
 */

public class OtherPage extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        String showStr = "this is otherPage";

        if (null!=bundle){
            String transferStr = bundle.getString(MainActivity.BUNDLE_STR_KEY);
            if (!TextUtils.isEmpty(transferStr)){
                showStr = showStr+"   \r\n\r\noh~ the value is："+transferStr;
            }
        }


        TextView textView = new TextView(this);
        textView.setText(showStr);
        setContentView(textView);
    }
}
