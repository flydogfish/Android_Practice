package com.example.chapter6;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class BootCompleteReceiver extends BroadcastReceiver {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"廖桂樱启动",Toast.LENGTH_LONG).show();
        Log.d("BootCompleteReceiver","廖桂樱启动！！！");
    }
}