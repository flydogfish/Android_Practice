package com.example.chapter6;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    // 在baseActivity中动态注册广播，设定强制下线
    private ForceOfflineReceiver receiver;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActvityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestppractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActvityCollector.removeActivity(this);
    }

      class ForceOfflineReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. please try to login agagin");
            builder.setCancelable(false);
            builder.setPositiveButton("OK",new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActvityCollector.finishAll();// 销毁所有活动
                    Intent intent = new Intent(context,LoginActivty.class);
                    context.startActivity(intent);// 重启Login
                }
            });
            builder.show();
        }
    }
}
