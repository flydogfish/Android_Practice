package com.example.chapter6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TimeworkChangeReceiver timeworkChangeReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置过滤器用来监听每分钟系统时间的变化
        // 定义一个过滤器和网络广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");

        timeworkChangeReceiver = new TimeworkChangeReceiver();
        registerReceiver(timeworkChangeReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 当activity的销毁周期会注销广播注册
        unregisterReceiver(timeworkChangeReceiver);
    }

    static class TimeworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "time changes", Toast.LENGTH_SHORT).show();
        }
    }

}