package com.induk.ch5_reactive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity4 extends AppCompatActivity implements View.OnClickListener {

    Disposable backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        findViewById(R.id.btn_act4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        runRx();
    }

    private void runRx() {
        ProgressDialog asyncDialog = new ProgressDialog(this);
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        asyncDialog.setMessage("로딩중입니다.");
        asyncDialog.show();

        Observable obs = Observable.fromCallable( // subscribe를 한경우 fromCallable호출
                ()->{// 인자값 없음
                   try {
                       for (int i = 0;i<6;i++){
                           asyncDialog.setProgress(i*20);
                           Thread.sleep(3*1000);// 3초 휴식
                           Log.d("fromCallable","------------------"+i);
                       }
                   }catch (Exception e){}
                   return "ok";
        });

        backgroundTask = obs.subscribeOn(Schedulers.io()) //publisher에게 자료 요청 처리 쓰레드
                .observeOn(AndroidSchedulers.mainThread()) //이벤트처리 쓰레드 (onNext, onError, onComplete)
                .subscribe(
                        new Consumer<String>() {
                            @Override
                            public void accept(String result){
                                Log.d("accept","------------------"+result);
                                asyncDialog.dismiss();
                                backgroundTask.dispose();
                            }
        });
        for (int i = 0;i<18;i++){
            try {
                Thread.sleep(1*1000);// 3초 휴식
                Log.d("******","------------------"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}