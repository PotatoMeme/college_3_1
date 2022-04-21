package com.induk.ch4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doInitialTask();
    }

    private void  doInitialTask(){
        Handler handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.d("_______________________________",String.valueOf(msg.what));
                Intent i  = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(7,3*1000);
    }
}